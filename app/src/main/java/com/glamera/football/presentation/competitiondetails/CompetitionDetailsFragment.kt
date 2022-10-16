package com.glamera.football.presentation.competitiondetails

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.glamera.football.databinding.FragmentCompetitionsBinding
import com.glamera.football.databinding.FragmentDetailsBinding
import com.glamera.football.domain.entity.CompetitionItem
import com.glamera.football.presentation.base.BaseFragment
import com.glamera.football.presentation.competitions.CompetitionUiState
import com.glamera.football.presentation.competitions.CompetitionsViewModel
import com.glamera.football.presentation.competitions.adapter.CompetitionsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class CompetitionDetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    private lateinit var viewModel: CompetitionDetailsViewModel

    override fun constructViewBinding(): ViewBinding = FragmentDetailsBinding.inflate(layoutInflater)

    override fun init(viewBinding: ViewBinding) {
        viewModel = ViewModelProvider(this).get(CompetitionDetailsViewModel::class.java)
        viewModel.getCompetitionsDetails("PL")

        fetchCompetitions()
    }


    private fun fetchCompetitions() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is CompetitionDetailsViewModel.FootballDetailUiState  .Loaded -> onLoaded(state.itemState)
                        is CompetitionDetailsViewModel.FootballDetailUiState.Error -> showError(state.message)
                        is CompetitionDetailsViewModel.FootballDetailUiState.Loading -> showLoading()
                    }
                }
            }
        }
    }

    private suspend fun onLoaded(competitionDetailsUiState: CompetitionDetailsUiState) {

        competitionDetailsUiState.run {
            getViewBinding().tvName.text=competitionDetailsUiState.name
            getViewBinding().tvArea.text= competitionDetailsUiState.area?.name
            getViewBinding().tvType.text=competitionDetailsUiState.type
            getViewBinding().ivImage
            Glide.with(requireContext())
                .load(competitionDetailsUiState.emblem)
                .into( getViewBinding().ivImage);

        }
    }



    private fun showLoading() {
        Timber.d("showLoading")
    }

    private fun showError(@StringRes stringRes: Int) {

        Toast.makeText(requireContext(), stringRes, Toast.LENGTH_SHORT).show()
    }

}
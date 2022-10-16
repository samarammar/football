package com.glamera.football.presentation.competitions

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.glamera.football.R
import com.glamera.football.databinding.FragmentCompetitionsBinding
import com.glamera.football.domain.entity.CompetitionItem
import com.glamera.football.presentation.base.BaseFragment
import com.glamera.football.presentation.competitions.adapter.CompetitionsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import timber.log.Timber


@AndroidEntryPoint
class CompetitionFragment : BaseFragment<FragmentCompetitionsBinding>() {

    private lateinit var viewModel: CompetitionsViewModel
    private lateinit var adaptercompet: CompetitionsAdapter
    private fun initUi() {
        getViewBinding().rvCompetition.run {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = adaptercompet
        }
    }
    override fun constructViewBinding(): ViewBinding = FragmentCompetitionsBinding.inflate(layoutInflater)

    private fun onListItemClick(code:String) {
        view?.let {
            Navigation.findNavController(it).navigate(
                R.id.action_mainFragment_to_detailsFragment)
        };

    }
    override fun init(viewBinding: ViewBinding) {
        viewModel = ViewModelProvider(this).get(CompetitionsViewModel::class.java)
        viewModel.getCompetitionsList()
        adaptercompet = CompetitionsAdapter{ code -> onListItemClick(code) }

        initUi()
        fetchCompetitions()
    }



    private fun fetchCompetitions() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is CompetitionsViewModel.FootballUiState.Loaded -> onLoaded(state.itemState)
                        is CompetitionsViewModel.FootballUiState.Error -> showError(state.message)
                        is CompetitionsViewModel.FootballUiState.Loading -> showLoading()
                    }
                }
            }
        }
    }

    private suspend fun onLoaded(competitionUiState: CompetitionUiState) {

        competitionUiState.run {
            val flowOfLists: Flow<List<CompetitionItem>> = competitions
            val flatList: List<CompetitionItem> = flowOfLists.flattenToList()
            adaptercompet.update(flatList)

        }
    }

    suspend fun <T> Flow<List<T>>.flattenToList() =
        flatMapConcat { it.asFlow() }.toList()

    private fun showLoading() {
        Timber.d("showLoading")
    }

    private fun showError(@StringRes stringRes: Int) {

        Toast.makeText(requireContext(), stringRes, Toast.LENGTH_SHORT).show()
    }


}
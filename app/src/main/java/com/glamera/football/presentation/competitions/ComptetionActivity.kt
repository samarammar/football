package com.glamera.football.presentation.competitions

import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.glamera.football.databinding.ActivityCompetitionsBinding
import com.glamera.football.domain.entity.CompetitionItem
import com.glamera.football.presentation.base.BaseActivity
import com.glamera.football.presentation.competitions.adapter.CompetitionsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class ComptetionActivity :  BaseActivity<ActivityCompetitionsBinding>() {

    override val bindLayout: (LayoutInflater) -> ActivityCompetitionsBinding
        get() = ActivityCompetitionsBinding::inflate

    private lateinit var viewModel: CompetitionsViewModel
    private lateinit var adaptercompet:CompetitionsAdapter
    private fun initUi() {
        binding.rvCompetition.run {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = adaptercompet
        }
    }
    override fun prepareView() {
        viewModel = ViewModelProvider(this).get(CompetitionsViewModel::class.java)
        viewModel.getCompetitionsList()
        adaptercompet = CompetitionsAdapter(this)

        initUi()
        fetchCompetitions()
    }



    private fun fetchCompetitions() {
        Log.i("comppp", "flatList.size.toString()")

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is CompetitionsViewModel.FootballUiState.Loaded -> onLoaded(state.itemState)
//                        is CompetitionsViewModel.FootballUiState.Loaded -> {
//                            val flowOfLists: Flow<List<CompetitionItem>> = state.itemState.competitions
//                            val flatList: List<CompetitionItem> = flowOfLists.flattenToList()
//                            Log.i("comppp", flatList.size.toString())
//                        }
                        is CompetitionsViewModel.FootballUiState.Error -> showError(state.message)
                        is CompetitionsViewModel.FootballUiState.Loading -> showLoading()
                    }
                }
            }
        }
    }

    private suspend fun onLoaded(competitionUiState: CompetitionUiState) {
        Timber.d("showerror")

        competitionUiState.run {
            val flowOfLists: Flow<List<CompetitionItem>> = competitions
            val flatList: List<CompetitionItem> = flowOfLists.flattenToList()
            Log.i("comppp", competitions.toString())
            adaptercompet.update(flatList)

//        competitions.forEach { Log.i("list",it.toString()) }
//            Log.i("comppp", competitions.toString())
//            competitions.collect{}
        }
    }

     suspend fun <T> Flow<List<T>>.flattenToList() =
        flatMapConcat { it.asFlow() }.toList()
    
    private fun showLoading() {
        Timber.d("showLoading")
    }

    private fun showError(@StringRes stringRes: Int) {

        Toast.makeText(this, stringRes, Toast.LENGTH_SHORT).show()
    }
}
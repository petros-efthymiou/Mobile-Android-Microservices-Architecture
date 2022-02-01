/*
 *
 * Copyright (C) 2022 Petros Efthymiou Open Source Project
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.petros.efthymiou.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petros.efthymiou.domain.usecases.GetArticles
import com.petros.efthymiou.domain.usecases.LikeArticle
import com.petros.efthymiou.presentation.HomeIntent
import com.petros.efthymiou.presentation.HomeState
import com.petros.efthymiou.presentation.HomeStateMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val stateMapper: HomeStateMapper,
    private val getArticles: GetArticles,
    private val likeArticle: LikeArticle
) : ViewModel() {

    private val initialState = HomeState.Loading

    private val _uiState: MutableStateFlow<HomeState> = MutableStateFlow(initialState)
    val uiState: StateFlow<HomeState> = _uiState.asStateFlow()

    fun handleIntent(intent: HomeIntent) {
        viewModelScope.launch(Dispatchers.IO) {
            when (intent) {
                is HomeIntent.ViewAllArticles -> viewArticles()
                //In case you care about the outcome of this operation,
                //add a return type and handle success or error
                is HomeIntent.LikeArticle -> likeArticle(intent.id)
            }
        }
    }

    private suspend fun viewArticles() {
        getArticles().map(stateMapper).collect {
            _uiState.value = it
        }
    }
}






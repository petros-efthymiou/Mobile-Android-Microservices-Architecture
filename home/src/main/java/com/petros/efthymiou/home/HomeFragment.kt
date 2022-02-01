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

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import com.petros.efthymiou.presentation.ArticlePresentation
import com.petros.efthymiou.presentation.HomeIntent
import com.petros.efthymiou.presentation.HomeState


class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val root = view.findViewById<ComposeView>(R.id.landing_root)
        viewModel.handleIntent(HomeIntent.ViewAllArticles)

        lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is HomeState.Success -> root.setContent {
                            Log.d("MA TAG", state.articles.toString())
                            ArticlesList(articles = state.articles)
                        }
                        is HomeState.Loading -> root.setContent {
                            LoadingScreen()
                        }
                        is HomeState.Empty -> root.setContent {
                            EmptyScreen()
                        }
                        is HomeState.Error -> root.setContent {
                            ErrorScreen()
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun LoadingScreen() {
        Column {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(20.dp)
            )
            Text(
                "Loading",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }

    @Composable
    fun ErrorScreen() {
        Column {
            Icon(
                Icons.Rounded.Warning,
                contentDescription = "Localized description",
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(20.dp)
            )
            Text(
                "Something went wrong. Please try again later",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }

    @Composable
    fun EmptyScreen() {
        Column {
            Icon(
                Icons.Rounded.List,
                contentDescription = "Localized description",
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(20.dp)
            )
            Text(
                "We couldn't find any new articles",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }


    @Composable
    fun ArticlesList(articles: List<ArticlePresentation>) {
        LazyColumn {
            items(
                items = articles,
                itemContent = {
                    ArticleListItem(it)
                }
            )
        }
    }

    @OptIn(ExperimentalCoilApi::class)
    @Composable
    fun ArticleListItem(article: ArticlePresentation) {
        Card(
            shape = RoundedCornerShape(8.dp),
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 4.dp,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column {
                Image(
                    painter = rememberImagePainter(article.imageUrl, builder = {
                        size(OriginalSize)
                    }),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = article.title,
                    fontSize = 19.sp,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
                Text(
                    text = article.desc,
                    fontSize = 17.sp,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                )
                Text(
                    text = article.authorName,
                    fontSize = 15.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("like_article")
                        .clickable {
                            viewModel.handleIntent(HomeIntent.LikeArticle(article.id))
                        }
                ) {
                    Text(
                        text = article.likes.toString(),
                        fontSize = 20.sp,
                        fontWeight = Bold
                    )
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        contentDescription = "Localized description",
                        tint = Color.Blue,
                        modifier = Modifier
                            .size(48.dp)
                            .alpha(article.likeActionAlpha)
                            .padding(horizontal = 4.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}
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

package com.petros.efthymiou.presentation

data class ArticlePresentation(
    val id: String,
    val title: String,
    val desc: String,
    val date: String,
    val readTime: Int,
    val category: String,
    val imageUrl: String,
    val likes: Int,
    val authorName: String,
    val canLike: Boolean,
    val likeActionAlpha: Float
)


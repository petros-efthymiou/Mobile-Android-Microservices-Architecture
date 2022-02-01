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

package com.petros.efthymiou.mobilemicroservicesarchitecture.home

import androidx.compose.ui.test.*
import com.petros.efthymiou.mobilemicroservicesarchitecture.BaseUITest
import org.junit.Test

class HomeFeature : BaseUITest() {

    @Test
    fun displaysListOfArticles() {
        composeTestRule.onNodeWithText("Lorem Ipsum").assertIsDisplayed()

        composeTestRule.onNodeWithText("Consectetur adipiscing elit").performScrollTo()

        composeTestRule.onRoot().onChild().onChildren().assertCountEquals(4)
    }

    @Test
    fun likeArticle() {
        composeTestRule.onAllNodesWithTag("like_article").onFirst().assertHasClickAction()
    }
}
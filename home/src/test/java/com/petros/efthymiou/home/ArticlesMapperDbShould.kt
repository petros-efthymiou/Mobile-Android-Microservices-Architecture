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

import com.petros.efthymiou.home.local.database.ArticleDb
import com.petros.efthymiou.home.remote.ArticleRaw
import com.petros.efthymiou.home.remote.ArticlesMapperDb
import com.petros.efthymiou.utils.BaseUnitTest
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ArticlesMapperDbShould : BaseUnitTest() {

    private val mapper = ArticlesMapperDb()

    @Test
    fun mapRawToDb() {
        val actual = mapper(input)

        assertEquals(expected, actual)
    }

    private val input = listOf(
        ArticleRaw(
            "id1",
            "title1",
            "desc1",
            "2000/10/10",
            4000,
            "sports",
            "imageUrl1",
            200,
            "authorId1",
            listOf("junk1", "junk2")
        )
    )

    private val expected = listOf(
        ArticleDb(
            "id1",
            "title1",
            "desc1",
            "2000/10/10",
            4000,
            "sports",
            "imageUrl1",
            200,
            "authorId1",
        )
    )
}
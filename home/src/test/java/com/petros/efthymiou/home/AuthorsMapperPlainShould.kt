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

import com.petros.efthymiou.home.local.files.AuthorsMapperPlain
import com.petros.efthymiou.utils.BaseUnitTest
import com.petros.efthymiou.utils.authorsLocal
import com.petros.efthymiou.utils.authorsPlain
import junit.framework.TestCase.assertEquals
import org.junit.Test

class AuthorsMapperPlainShould : BaseUnitTest() {

    private val sut = AuthorsMapperPlain()

    @Test
    fun mapLocalToPlain() {
        val actual = sut(input)

        assertEquals(expected, actual)
    }

    private val input = authorsLocal

    private val expected = authorsPlain
}
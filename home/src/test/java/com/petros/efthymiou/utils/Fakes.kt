package com.petros.efthymiou.utils

import com.petros.efthymiou.domain.entities.plain.ArticlePlain
import com.petros.efthymiou.domain.entities.plain.AuthorPlain
import com.petros.efthymiou.home.local.database.ArticleDb
import com.petros.efthymiou.home.local.files.AuthorLocal
import com.petros.efthymiou.home.remote.ArticleRaw

val articleRaw1 = ArticleRaw(
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

val articleRaw2 = ArticleRaw(
    "id2",
    "title2",
    "desc2",
    "2000/10/11",
    5000,
    "music",
    "imageUrl2",
    300,
    "authorId2",
    listOf("junk3", "junk4")
)

val articlesRaw = listOf(articleRaw1, articleRaw2)

val articleDb1 = ArticleDb(
    "id1",
    "title1",
    "desc1",
    "2000/10/10",
    4000,
    "sports",
    "imageUrl1",
    200,
    "authorId1"
)

val articleDb2 = ArticleDb(
    "id2",
    "title2",
    "desc2",
    "2000/10/11",
    5000,
    "music",
    "imageUrl2",
    300,
    "authorId2",
)

val articlesDb = listOf(articleDb1, articleDb2)

val articlePlain1 = ArticlePlain(
    "id1",
    "title1",
    "desc1",
    "2000/10/10",
    4000,
    "sports",
    "imageUrl1",
    200,
    "authorId1"
)

val articlePlain2 = ArticlePlain(
    "id2",
    "title2",
    "desc2",
    "2000/10/11",
    5000,
    "music",
    "imageUrl2",
    300,
    "authorId2",
)

val articlesPlain = listOf(articlePlain1, articlePlain2)

val authorLocal1 = AuthorLocal(
    "authorId1",
    "Petros",
    "Efthymiou",
    5,
    "junk"
)

val authorLocal2 = AuthorLocal(
    "authorId2",
    "Nikos",
    "Voulgaris",
    5,
    "junk"
)

val authorsLocal = listOf(authorLocal1, authorLocal2)

val authorPlain1 = AuthorPlain(
    "authorId1",
    "Petros Efthymiou",
    5
)

val authorPlain2 = AuthorPlain(
    "authorId2",
    "Nikos Voulgaris",
    5
)

val authorsPlain = listOf(authorPlain1, authorPlain2)
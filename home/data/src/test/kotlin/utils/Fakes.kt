package utils

import com.petros.efthymiou.domain.entities.plain.ArticlePlain
import com.petros.efthymiou.domain.entities.plain.AuthorPlain

val likedIds = listOf(
    "id",
    "id1"
)

val authorsPlain = listOf(
    AuthorPlain(
        "authorId1",
        "Petros Efthymiou",
        5
    ),
    AuthorPlain(
        "authorId2",
        "Nikos Voulgaris",
        5
    )
)

val articlePlain1 = ArticlePlain(
    "id1",
    "title",
    "desc",
    "date",
    1200,
    "sports",
    "imageUrl",
    455,
    "authorId1"
)

val articlePlain2 = ArticlePlain(
    "id2",
    "title2",
    "desc2",
    "date2",
    1300,
    "music",
    "imageUrl2",
    555,
    "authorId2"
)

val articlesPlain = listOf(articlePlain1, articlePlain2)
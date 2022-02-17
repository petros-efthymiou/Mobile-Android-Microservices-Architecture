package utils

import com.petros.efthymiou.domain.entities.Article
import com.petros.efthymiou.domain.entities.plain.ArticleCategory
import com.petros.efthymiou.domain.entities.plain.ArticlePlain
import com.petros.efthymiou.domain.entities.plain.AuthorPlain

val article1 = Article(
    "id1",
    "title",
    "desc",
    "date",
    1200 / 200,
    ArticleCategory.SPORTS,
    "imageUrl",
    455,
    "Petros Efthymiou",
    false
)

val article2 = Article(
    "id2",
    "title2",
    "desc2",
    "date2",
    1300 / 200,
    ArticleCategory.MUSIC,
    "imageUrl2",
    555,
    "Nikos Voulgaris",
    true
)

val articles = listOf(article1, article2)

val articlesPlain = listOf(
    ArticlePlain(
        "id1",
        "title",
        "desc",
        "date",
        1200,
        "sports",
        "imageUrl",
        455,
        "authorId1"
    ),
    ArticlePlain(
        "id2",
        "title2",
        "desc2",
        "date2",
        1300,
        "music",
        "imageUrl2",
        555,
        "authorId2"
    ),
)

val authorsPlain = listOf(
    AuthorPlain(
        "authorId1",
        "Petros Efthymiou",
        100
    ),
    AuthorPlain(
        "authorId2",
        "Nikos Voulgaris",
        99
    )
)

val likedArticleIds = listOf(
    "id1"
)
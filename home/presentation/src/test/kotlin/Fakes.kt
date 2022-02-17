import com.petros.efthymiou.domain.entities.Article
import com.petros.efthymiou.domain.entities.plain.ArticleCategory
import com.petros.efthymiou.presentation.ArticlePresentation

val article1 = Article(
    "id1",
    "title",
    "desc",
    "2000-05-20",
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
    "2001-06-30",
    1300 / 200,
    ArticleCategory.MUSIC,
    "imageUrl2",
    555,
    "Nikos Voulgaris",
    true
)

val articles = listOf(article1, article2)

val articlePresentation1 = ArticlePresentation(
    "id1",
    "title",
    "desc",
    "20-05-2000",
    1200 / 200,
    "Sports",
    "imageUrl",
    455,
    "Petros Efthymiou",
    false,
    0.2F
)

val articlePresentation2 = ArticlePresentation(
    "id2",
    "title2",
    "desc2",
    "30-06-2001",
    1300 / 200,
    "Music",
    "imageUrl2",
    555,
    "Nikos Voulgaris",
    true,
    1F
)

val articlesPresentation = listOf(articlePresentation1, articlePresentation2)
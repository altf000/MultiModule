package ru.altf000.multimodule.movie_detail_impl.start

import com.github.terrakok.cicerone.androidx.Creator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.altf000.multimodule.common.di.ScopeScreen
import ru.altf000.multimodule.common.navigation.ScreenKeys
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.movie_detail_api.MovieDetailScreenCreator
import ru.altf000.multimodule.movie_detail_impl.presentation.view.MovieDetailFragment
import javax.inject.Inject

@ScopeScreen
internal class MovieDetailScreenCreatorImpl @Inject constructor() : MovieDetailScreenCreator {

    override fun createScreen(content: Content) = FragmentScreen(
        key = ScreenKeys.MOVIE_DETAIL,
        clearContainer = true,
        fragmentCreator = Creator {
            MovieDetailFragment().apply {
                this.content = content
            }
        }
    )
}
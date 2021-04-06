package ru.altf000.multimodule.movie_detail_impl.start

import androidx.fragment.app.Fragment
import ru.altf000.multimodule.common.navigation.ScreenKeys
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.common.di.ScopeFeature
import ru.altf000.multimodule.common.di.ScopeScreen
import ru.altf000.multimodule.movie_detail_api.MovieDetailScreenCreator
import ru.altf000.multimodule.movie_detail_impl.presentation.view.MovieDetailFragment
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject

@ScopeScreen
internal class MovieDetailScreenCreatorImpl @Inject constructor() : MovieDetailScreenCreator {

    override fun createScreen(content: Content): Screen {
        return object : SupportAppScreen() {

            override fun getFragment(): Fragment? {
                return MovieDetailFragment().apply {
                    this.content = content
                }
            }

            override fun getScreenKey(): String {
                return ScreenKeys.MOVIE_DETAIL
            }
        }
    }
}
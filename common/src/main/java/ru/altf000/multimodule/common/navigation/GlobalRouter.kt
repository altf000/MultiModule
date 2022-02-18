package ru.altf000.multimodule.common.navigation

import com.github.terrakok.cicerone.Forward
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import ru.altf000.multimodule.collection_list_api.CollectionListApi
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.movie_detail_api.MovieDetailApi
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class GlobalRouter @Inject constructor(
    private val collectionListScreenCreator: Provider<CollectionListApi>,
    private val movieDetailScreenCreator: Provider<MovieDetailApi>
) : Router() {

    fun openCollection(collectionId: Int) {
        showScreen(collectionListScreenCreator.get().getScreenCreator().createScreen(collectionId))
    }

    fun openMovieDetail(content: Content) {
        showScreen(movieDetailScreenCreator.get().getScreenCreator().createScreen(content))
    }

    private fun showScreen(screen: Screen) {
        executeCommands(Forward(screen))
    }

}
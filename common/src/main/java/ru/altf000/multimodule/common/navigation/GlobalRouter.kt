package ru.altf000.multimodule.common.navigation

import ru.altf000.multimodule.collection_list_api.CollectionListApi
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.movie_detail_api.MovieDetailApi
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.commands.Forward
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class GlobalRouter @Inject constructor(
    private val collectionListScreen: Provider<CollectionListApi>,
    private val movieDetailScreenCreator: Provider<MovieDetailApi>
) : Router() {

    private fun addScreen(screen: Screen) {
        executeCommands(Forward(screen))
    }

    fun openCollection(collectionId: Int) {
        addScreen(collectionListScreen.get().getScreenCreator().createScreen(collectionId))
    }

    fun openMovieDetail(content: Content) {
        addScreen(movieDetailScreenCreator.get().getScreenCreator().createScreen(content))
    }
}
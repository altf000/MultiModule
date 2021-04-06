package ru.altf000.multimodule.common.navigation

import ru.altf000.multimodule.collection_list_api.CollectionListApi
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.movie_detail_api.MovieDetailApi
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Screen
import javax.inject.Inject

import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class CustomRouter @Inject constructor(
    private val collectionListScreen: Provider<CollectionListApi>,
    private val movieDetailScreenCreator: Provider<MovieDetailApi>
) : Router() {

    override fun navigateTo(screen: Screen) {
        super.navigateTo(screen)
        ScreensChecker.add(screen)
    }

    override fun exit() {
        ScreensChecker.removeLast()
        super.exit()
    }

    fun openCollection(collectionId: Int) {
        navigateTo(collectionListScreen.get().getScreenCreator().createScreen(collectionId))
    }

    fun openMovieDetail(content: Content) {
        navigateTo(movieDetailScreenCreator.get().getScreenCreator().createScreen(content))
    }
}
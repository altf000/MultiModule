package ru.altf000.multimodule.navigator

import androidx.navigation.navOptions
import kotlinx.coroutines.flow.MutableSharedFlow
import ru.altf000.multimodule.R
import ru.altf000.multimodule.collection_list.NavCollectionListDirections
import ru.altf000.multimodule.common.navigation.NavigateAction
import ru.altf000.multimodule.common.navigation.Navigator
import ru.altf000.multimodule.common.navigation.OpenScreenAction
import ru.altf000.multimodule.common_entities.domain.Content
import ru.altf000.multimodule.movie_detail.NavMovieDetailDirections

internal class NavigatorImpl : Navigator {

    override val navigateActions = MutableSharedFlow<NavigateAction>(extraBufferCapacity = 1)

    override fun collection(collectionId: Int) {
        navigate(
            OpenScreenAction(
                NavCollectionListDirections.actionGlobalCollectionListFragment(collectionId),
                navOptions {
                    popUpTo(R.id.nav_app) {
                        inclusive = true
                    }
                }
            )
        )
    }

    override fun movieDetail(item: Content) {
        navigate(OpenScreenAction(NavMovieDetailDirections.actionGlobalMovieDetailFragment(item)))
    }

    override fun navigate(action: NavigateAction) {
        navigateActions.tryEmit(action)
    }
}
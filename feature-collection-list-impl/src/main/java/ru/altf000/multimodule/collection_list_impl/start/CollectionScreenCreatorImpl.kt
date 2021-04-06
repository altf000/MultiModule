package ru.altf000.multimodule.collection_list_impl.start

import androidx.fragment.app.Fragment
import ru.altf000.multimodule.collection_list_api.CollectionScreenCreator
import ru.altf000.multimodule.collection_list_impl.presentation.view.CollectionListFragment
import ru.altf000.multimodule.common.navigation.ScreenKeys
import ru.altf000.multimodule.common.di.ScopeFeature
import ru.altf000.multimodule.common.di.ScopeScreen
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject

@ScopeScreen
internal class CollectionScreenCreatorImpl @Inject constructor() : CollectionScreenCreator {

    override fun createScreen(collectionId: Int): Screen {
        return object : SupportAppScreen() {

            override fun getFragment(): Fragment? {
                return CollectionListFragment().apply {
                    this.collectionId = collectionId
                }
            }

            override fun getScreenKey(): String {
                return ScreenKeys.COLLECTION_LIST
            }
        }
    }
}
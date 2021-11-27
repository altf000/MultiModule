package ru.altf000.multimodule.collection_list_impl.start

import com.github.terrakok.cicerone.androidx.Creator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.altf000.multimodule.collection_list_api.CollectionScreenCreator
import ru.altf000.multimodule.collection_list_impl.presentation.view.CollectionListFragment
import ru.altf000.multimodule.common.di.ScopeScreen
import ru.altf000.multimodule.common.navigation.ScreenKeys
import javax.inject.Inject

@ScopeScreen
internal class CollectionScreenCreatorImpl @Inject constructor() : CollectionScreenCreator {

    override fun createScreen(collectionId: Int) = FragmentScreen(
        key = ScreenKeys.COLLECTION_LIST,
        clearContainer = true,
        fragmentCreator = Creator {
            CollectionListFragment().apply {
                this.collectionId = collectionId
            }
        }
    )
}
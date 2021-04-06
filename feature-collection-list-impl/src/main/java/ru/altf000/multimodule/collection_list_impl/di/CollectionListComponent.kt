package ru.altf000.multimodule.collection_list_impl.di

import dagger.Component
import ru.altf000.multimodule.collection_list_api.CollectionListApi
import ru.altf000.multimodule.collection_list_impl.presentation.view.CollectionListFragment
import ru.altf000.multimodule.common.di.ScopeFeature
import ru.altf000.multimodule.common.di.ScopeScreen

@Component(
    modules = [CollectionListModule::class],
    dependencies = [CollectionListDependencies::class]
)
@ScopeScreen
internal abstract class CollectionListComponent : CollectionListApi {

    internal abstract fun inject(collectionListFragment: CollectionListFragment)
}


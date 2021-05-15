package ru.altf000.multimodule.collection_list_impl.di

import ru.altf000.multimodule.collection_list_api.CollectionListApi
import ru.altf000.multimodule.module_injector.ComponentHolder

object CollectionListComponentHolder :
    ComponentHolder<CollectionListApi, CollectionListDependencies> {

    private var component: CollectionListComponent? = null

    override fun init(dependencies: CollectionListDependencies) {
        if (component == null) {
            synchronized(CollectionListComponentHolder::class.java) {
                if (component == null) {
                    component = DaggerCollectionListComponent
                        .builder()
                        .collectionListDependencies(dependencies)
                        .build()
                }
            }
        }
    }

    override fun get(): CollectionListApi = getComponent()

    internal fun getComponent(): CollectionListComponent {
        return component!!
    }

    override fun reset() {
        component = null
    }
}
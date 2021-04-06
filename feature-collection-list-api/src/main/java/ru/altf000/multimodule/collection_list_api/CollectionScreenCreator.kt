package ru.altf000.multimodule.collection_list_api

import ru.terrakok.cicerone.Screen

interface CollectionScreenCreator {

    fun createScreen(collectionId: Int): Screen
}
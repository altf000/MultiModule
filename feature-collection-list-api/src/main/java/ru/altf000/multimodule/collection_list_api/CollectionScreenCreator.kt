package ru.altf000.multimodule.collection_list_api

import com.github.terrakok.cicerone.Screen

interface CollectionScreenCreator {
    fun createScreen(collectionId: Int): Screen
}
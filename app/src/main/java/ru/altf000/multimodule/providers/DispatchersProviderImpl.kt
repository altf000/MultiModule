package ru.altf000.multimodule.providers

import kotlinx.coroutines.Dispatchers
import ru.altf000.multimodule.common.providers.DispatchersProvider

internal class DispatchersProviderImpl : DispatchersProvider {
    override val default = Dispatchers.Default
    override val main = Dispatchers.Main
    override val io = Dispatchers.IO
    override val unconfined = Dispatchers.Unconfined
}

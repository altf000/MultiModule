package ru.altf000.multimodule.domain

import ru.altf000.multimodule.common.usecase.BaseSuspendUseCase

class BootloaderUseCase(
    private val bootloaderRepository: BootloaderRepository
) : BaseSuspendUseCase<Boolean, Unit>() {

    override suspend fun invoke(params: Unit) = bootloaderRepository.load()
}
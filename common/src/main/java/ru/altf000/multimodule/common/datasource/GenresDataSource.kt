package ru.altf000.multimodule.common.datasource

import ru.altf000.multimodule.common.datasource.base.DataSource
import ru.altf000.multimodule.common.datasource.base.ReadOnlyDataSource
import ru.altf000.multimodule.common.datasource.base.UpdatableDataSource
import ru.altf000.multimodule.common_entities.domain.Genre

interface GenresReadOnlyDataSource : ReadOnlyDataSource<List<Genre>>
interface GenresUpdatableDataSource : UpdatableDataSource<List<Genre>>

class GenresDataSource : DataSource<List<Genre>>(emptyList()), GenresReadOnlyDataSource,
    GenresUpdatableDataSource

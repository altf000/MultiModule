package ru.altf000.multimodule.common.datasource

import ru.altf000.multimodule.common.datasource.base.DataSource
import ru.altf000.multimodule.common.datasource.base.ReadOnlyDataSource
import ru.altf000.multimodule.common.datasource.base.UpdatableDataSource
import ru.altf000.multimodule.common_entities.domain.Country

interface CountriesReadOnlyDataSource : ReadOnlyDataSource<List<Country>>
interface CountriesUpdatableDataSource : UpdatableDataSource<List<Country>>

class CountriesDataSource : DataSource<List<Country>>(emptyList()), CountriesReadOnlyDataSource,
    CountriesUpdatableDataSource

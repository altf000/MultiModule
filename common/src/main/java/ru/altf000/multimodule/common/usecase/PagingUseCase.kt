package ru.altf000.multimodule.common.usecase

abstract class PagingUseCase<T, P : PagingUseCase.Params, I> : BaseUseCase<T, P>() {

    @Volatile
    protected var lastLoadedPage = 0

    @Volatile
    protected var currentList = mutableListOf<I>()

    @Volatile
    protected var isLoading = false

    @Volatile
    protected var canLoadElse = true

    companion object {
        const val DEFAULT_PAGE_SIZE = 20
    }

    fun saveList(items: List<I>, currentPage: Int) {
        currentList.addAll(items)
        canLoadElse = items.size == DEFAULT_PAGE_SIZE
        lastLoadedPage = currentPage
    }

    fun reset() {
        currentList.clear()
        lastLoadedPage = 0
        isLoading = false
        canLoadElse = true
    }

    fun canLoad(params: P): Boolean {
        return !isLoading && (!params.isLoadMore || canLoadElse)
    }

    fun startLoading() {
        isLoading = true
    }

    fun stopLoading() {
        isLoading = false
    }

    open class Params(var isLoadMore: Boolean)
}
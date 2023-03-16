package ru.altf000.multimodule.common_ui.adapterdelegates

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.Flow
import ru.altf000.multimodule.common_utils.extentions.collectOnCreated

fun createAdapter(
    lifecycleOwner: LifecycleOwner,
    recyclerView: RecyclerView,
    layoutManager: LinearLayoutManager = LinearLayoutManager(recyclerView.context),
    selector: DSelector,
    block: AdapterDelegateBuilder.() -> Unit = {},
): ConcatAdapter {
    recyclerView.apply {
        this.layoutManager = layoutManager
    }
    return AdapterDelegateBuilder(lifecycleOwner, selector).apply(block).build()
}

fun delegateSelector(
    block: AdapterDelegatesSelectorImpl.() -> Unit,
) = AdapterDelegatesSelectorImpl().apply(block)

class AdapterDelegateBuilder(
    private val lifecycleOwner: LifecycleOwner,
    private val selector: DSelector,
) {

    private val concatAdapter = ConcatAdapter(DEFAULT_CONFIG)

    fun addAdapters(vararg items: Flow<List<DItem>>) {
        items.forEach { flow ->
            val compositeAdapter = CompositeAdapter(selector)
            flow.collectOnCreated(lifecycleOwner) { items ->
                compositeAdapter.submitList(items)
            }
            concatAdapter.addAdapter(compositeAdapter)
        }
    }

    fun addPagingAdapter(
        items: Flow<PagingData<out DItem>>,
        onItemsLoadedAction: ((List<DItem>) -> Unit)? = null,
    ): CompositePagingAdapter {

        if (concatAdapter.adapters.find { it is CompositePagingAdapter } != null) {
            error("ConcatAdapter could not contains more than one CompositePagingAdapter")
        }

        val pagingAdapter = CompositePagingAdapter(lifecycleOwner.lifecycleScope, selector)

        items.collectOnCreated(lifecycleOwner) { pagingData ->
            @Suppress("UNCHECKED_CAST")
            pagingAdapter.submitData(pagingData as PagingData<DItem>)
        }

        pagingAdapter.onPagesUpdatedFlow.collectOnCreated(lifecycleOwner) {
            onItemsLoadedAction?.let { action ->
                action(pagingAdapter.snapshot().items)
            }
        }

        with(concatAdapter) {
            addAdapter(pagingAdapter)
        }

        return pagingAdapter
    }

    fun CompositePagingAdapter.withNetworkStateAdapter(): CompositePagingAdapter {
        val networkStateAdapter = NetworkStateAdapter(this)
        addLoadStateListener { states ->
            networkStateAdapter.loadState = states.append
        }
        concatAdapter.addAdapter(networkStateAdapter)
        return this
    }

    fun build() = concatAdapter

    companion object {
        private val DEFAULT_CONFIG =
            ConcatAdapter.Config.Builder().setIsolateViewTypes(false).build()
    }
}

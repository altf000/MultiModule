package ru.altf000.multimodule.feature_content_detail.presentation.view

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.altf000.multimodule.common.providers.ContentMetaInfoProvider
import ru.altf000.multimodule.common_entities.domain.FullContent
import ru.altf000.multimodule.common_ui.adapterdelegates.createAdapter
import ru.altf000.multimodule.common_ui.adapterdelegates.delegateSelector
import ru.altf000.multimodule.common_ui.fragment.BaseFragment
import ru.altf000.multimodule.common_ui.utils.load
import ru.altf000.multimodule.common_ui.utils.viewBinding
import ru.altf000.multimodule.common_utils.extentions.collectOnStarted
import ru.altf000.multimodule.feature_content_detail.R
import ru.altf000.multimodule.feature_content_detail.databinding.FragmentContentDetailBinding
import ru.altf000.multimodule.feature_content_detail.presentation.adapter.RecommendationItemAdapterSelector
import ru.altf000.multimodule.feature_content_detail.presentation.viewmodel.ContentDetailViewModel

internal class ContentDetailFragment : BaseFragment(R.layout.fragment_content_detail) {

    private val args by navArgs<ContentDetailFragmentArgs>()
    private val viewModel: ContentDetailViewModel by viewModel { parametersOf(args.content) }
    private val metaProvider: ContentMetaInfoProvider by inject()

    override val binding by viewBinding(FragmentContentDetailBinding::bind)

    override fun onBind(savedInstanceState: Bundle?) = binding.apply {
        recommendations.adapter = createAdapter(
            lifecycleOwner = viewLifecycleOwner,
            recyclerView = recommendations,
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false),
            selector = delegateSelector {
                addDelegateSelector(RecommendationItemAdapterSelector { content ->
                    viewModel.onContentClicked(content)
                })
            }
        ) {
            addAdapters(viewModel.recommendationsItems)
        }
        viewModel.contentInfo.collectOnStarted(viewLifecycleOwner) { content ->
            setContentInfo(content)
        }
        viewModel.recommendationsItems.collectOnStarted(viewLifecycleOwner) { recommendations ->
            binding.recommendationsContainer.isVisible = recommendations.isNotEmpty()
        }
    }

    private fun setContentInfo(content: FullContent) {
        binding.apply {
            background.load(content.thumbUrl)
            title.text = content.title
            meta.text = metaProvider.getMeta(content)
            desctiption.text = content.synopsis
        }
    }
}
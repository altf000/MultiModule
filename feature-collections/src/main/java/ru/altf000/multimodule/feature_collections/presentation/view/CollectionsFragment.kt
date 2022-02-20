package ru.altf000.multimodule.feature_collections.presentation.view

import android.os.Bundle
import ru.altf000.multimodule.common_ui.fragment.BaseFragment
import ru.altf000.multimodule.common_ui.utils.viewBinding
import ru.altf000.multimodule.feature_collections.R
import ru.altf000.multimodule.feature_collections.databinding.FragmentCollectionsBinding

class CollectionsFragment : BaseFragment(R.layout.fragment_collections) {

    override val binding by viewBinding(FragmentCollectionsBinding::bind)

    override fun onBind(savedInstanceState: Bundle?) = binding.apply {

    }
}
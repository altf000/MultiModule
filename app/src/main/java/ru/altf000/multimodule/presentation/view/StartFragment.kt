package ru.altf000.multimodule.presentation.view

import android.os.Bundle
import androidx.core.view.isVisible
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.altf000.multimodule.R
import ru.altf000.multimodule.common_ui.fragment.BaseFragment
import ru.altf000.multimodule.common_ui.utils.viewBinding
import ru.altf000.multimodule.common_utils.extentions.collectOnCreated
import ru.altf000.multimodule.databinding.FragmentStartBinding
import ru.altf000.multimodule.presentation.viewmodel.StartViewModel

class StartFragment : BaseFragment(R.layout.fragment_start) {

    override val binding by viewBinding(FragmentStartBinding::bind)

    private val viewModel: StartViewModel by viewModel()

    override fun onBind(savedInstanceState: Bundle?) = binding.apply {
        viewModel.isLoaded.collectOnCreated(viewLifecycleOwner) { isLoaded ->
            loader.isVisible = !isLoaded
        }
    }
}

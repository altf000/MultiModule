package ru.altf000.multimodule.presentation.view

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ru.altf000.multimodule.R
import ru.altf000.multimodule.common_ui.fragment.BaseFragment
import ru.altf000.multimodule.common_ui.utils.viewBinding
import ru.altf000.multimodule.databinding.FragmentMainBinding

class MainFragment : BaseFragment(R.layout.fragment_main) {

    override val binding by viewBinding(FragmentMainBinding::bind)

    private lateinit var nestedNavController: NavController

    override fun onBind(savedInstanceState: Bundle?) = binding.apply {
        val nestedNavHostFragment =
            childFragmentManager.findFragmentById(R.id.hostMain) as NavHostFragment
        nestedNavController = nestedNavHostFragment.navController
        bottomNavigationView.setupWithNavController(nestedNavController)
    }

    override fun onBackPressed(): Boolean {
        if (binding.bottomNavigationView.selectedItemId != R.id.nav_collection_list) {
            binding.bottomNavigationView.selectedItemId = R.id.nav_collection_list
            return true
        }
        return false
    }
}
package ru.altf000.multimodule.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import org.koin.android.ext.android.inject
import ru.altf000.multimodule.R
import ru.altf000.multimodule.common.navigation.Navigator
import ru.altf000.multimodule.common_utils.extentions.collectOnCreated
import ru.altf000.multimodule.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {

    private val navController: NavController by lazy { findNavController() }
    private val navigator: Navigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigator.navigateActions.collectOnCreated(this) { action ->
            action?.navigate(navController)
        }
    }

    private fun findNavController() = (supportFragmentManager.findFragmentById(R.id.fragmentNavHost) as NavHostFragment).navController
}
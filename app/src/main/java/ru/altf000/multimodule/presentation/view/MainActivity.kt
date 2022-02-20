package ru.altf000.multimodule.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import org.koin.android.ext.android.inject
import ru.altf000.multimodule.R
import ru.altf000.multimodule.common.navigation.Navigator
import ru.altf000.multimodule.common_utils.extentions.collectOnCreated
import ru.altf000.multimodule.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val navigator: Navigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigator.navigateActions.collectOnCreated(this) { action ->
            action?.navigate(findNavController(this, R.id.hostGlobal))
        }
    }
}
package ru.altf000.multimodule.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.github.terrakok.cicerone.NavigatorHolder
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import ru.altf000.multimodule.R
import ru.altf000.multimodule.common.navigation.GlobalNavigator
import ru.altf000.multimodule.common.navigation.GlobalRouter
import ru.altf000.multimodule.constants.Constants
import ru.altf000.multimodule.databinding.ActivityMainBinding
import ru.altf000.multimodule.di.app.DaggerMainActivityComponent
import ru.altf000.multimodule.di.app.MainActivityComponent
import ru.altf000.multimodule.di.modules.ActivityModule
import ru.altf000.multimodule.di.modules.FragmentManagerModule
import ru.altf000.multimodule.presentation.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: GlobalRouter

    @Inject
    lateinit var navigator: GlobalNavigator

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by viewModels { factory }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        val component = DaggerMainActivityComponent
            .builder()
            .activityModule(ActivityModule(this))
            .fragmentManagerModule(FragmentManagerModule(supportFragmentManager, R.id.container))
            .build()

        MainActivityComponent.init(component)
        MainActivityComponent.get().inject(this)

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigatorHolder.setNavigator(navigator)

        lifecycleScope.launch {
            viewModel.initFlow
                .onStart { binding.progressBar.isVisible = true }
                .flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
                .collect {
                    if (it) {
                        binding.progressBar.isVisible = false
                        if (savedInstanceState == null) {
                            router.openCollection(Constants.Content.BASE_COLLECTION_ID)
                        }
                    }
                }
        }
    }

    override fun onDestroy() {
        MainActivityComponent.release()
        super.onDestroy()
    }

    override fun onBackPressed() {
        router.exit()
    }
}
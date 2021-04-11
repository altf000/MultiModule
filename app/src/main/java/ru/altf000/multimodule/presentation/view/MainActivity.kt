package ru.altf000.multimodule.presentation.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.altf000.multimodule.R
import ru.altf000.multimodule.common.navigation.CustomNavigator
import ru.altf000.multimodule.common.navigation.CustomRouter
import ru.altf000.multimodule.common.viewmodel.injectViewModel
import ru.altf000.multimodule.databinding.ActivityMainBinding
import ru.altf000.multimodule.di.app.DaggerMainActivityComponent
import ru.altf000.multimodule.di.app.MainActivityComponent
import ru.altf000.multimodule.di.modules.ActivityModule
import ru.altf000.multimodule.di.modules.FragmentManagerModule
import ru.altf000.multimodule.presentation.viewmodel.MainViewModel
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    @Inject
    lateinit var router: CustomRouter
    @Inject
    lateinit var navigator: CustomNavigator
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel
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

        viewModel = injectViewModel<MainViewModel>(viewModelFactory)
            .apply {
                init.observe(this@MainActivity, Observer {
                    binding.progressBar.visibility = View.GONE
                    if (savedInstanceState == null) {
                        router.openCollection(4661)
                    }
                })
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
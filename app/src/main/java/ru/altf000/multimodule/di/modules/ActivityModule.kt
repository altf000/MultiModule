package ru.altf000.multimodule.di.modules

import android.content.Context
import android.content.res.Resources
import androidx.fragment.app.FragmentActivity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class ActivityModule(private val activity: FragmentActivity) {

    @Provides
    @Singleton
    fun provideActivity(): FragmentActivity {
        return activity
    }

    @Provides
    @Singleton
    fun provideResources(): Resources {
        return activity.resources
    }

    @Provides
    @Singleton
    fun provideContext(): Context {
        return activity
    }
}

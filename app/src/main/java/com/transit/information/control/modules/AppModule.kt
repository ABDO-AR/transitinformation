package com.transit.information.control.modules

import com.transit.information.control.managers.APIManager
import com.transit.information.control.repositories.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class AppModule {

    @Provides // Providing(MainRepository):
    fun provideMainRepository(): MainRepository = MainRepository()
}
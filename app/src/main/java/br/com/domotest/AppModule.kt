package br.com.domotest

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import br.com.domotest.database.AppDatabase
import br.com.domotest.datasource.TodoRemoteDatasource
import br.com.domotest.datasource.TodoRemoteDatasourceImpl
import br.com.domotest.datasource.LoginLocalDatasource
import br.com.domotest.datasource.LoginLocalDatasourceImpl
import br.com.domotest.datasource.TodoLocalDatasource
import br.com.domotest.datasource.TodoLocalDatasourceImpl
import br.com.domotest.domain.DeleteAllTodosUseCase
import br.com.domotest.domain.DeleteAllTodosUseCaseImpl
import br.com.domotest.domain.GenerateUUIDUseCase
import br.com.domotest.domain.GenerateUUIDUseCaseImpl
import br.com.domotest.domain.GetTodoListUseCase
import br.com.domotest.domain.GetTodoListUseCaseImpl
import br.com.domotest.domain.GetTodoUseCase
import br.com.domotest.domain.GetTodoUseCaseImpl
import br.com.domotest.domain.GetUserIdUseCase
import br.com.domotest.domain.GetUserIdUseCaseImpl
import br.com.domotest.domain.SaveTodoUseCase
import br.com.domotest.domain.SaveTodoUseCaseImpl
import br.com.domotest.domain.SaveUserIdUseCase
import br.com.domotest.domain.SaveUserIdUseCaseImpl
import br.com.domotest.network.RetrofitClient
import br.com.domotest.network.RetrofitApi
import br.com.domotest.repository.TodoRepository
import br.com.domotest.repository.TodoRepositoryImpl
import br.com.domotest.repository.LoginRepository
import br.com.domotest.repository.LoginRepositoryImpl
import br.com.domotest.ui.HomeViewModel
import br.com.domotest.ui.LoginViewModel
import br.com.domotest.ui.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "br.com.domotest")

val appModule = module {
    viewModel { MainViewModel(get(), get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { HomeViewModel(get(), get()) }

    single<GenerateUUIDUseCase> {
        GenerateUUIDUseCaseImpl(
            applicationContext = androidContext()
        )
    }
    single<SaveUserIdUseCase> {
        SaveUserIdUseCaseImpl(
            loginRepository = get()
        )
    }
    single<GetUserIdUseCase> {
        GetUserIdUseCaseImpl(
            loginRepository = get()
        )
    }
    single<GetTodoListUseCase> {
        GetTodoListUseCaseImpl(
            todoRepository = get()
        )
    }
    single<SaveTodoUseCase> {
        SaveTodoUseCaseImpl(
            todoRepository = get()
        )
    }
    single<DeleteAllTodosUseCase> {
        DeleteAllTodosUseCaseImpl(
            todoRepository = get()
        )
    }
    single<GetTodoUseCase> {
        GetTodoUseCaseImpl(
            todoRepository = get()
        )
    }
    single<LoginRepository> {
        LoginRepositoryImpl(
            loginLocalDataSource = get()
        )
    }
    single<TodoRepository> {
        TodoRepositoryImpl(
            todoRemoteDataSource = get(),
            todoLocalDataSource = get()
        )
    }
    single<LoginLocalDatasource> {
        LoginLocalDatasourceImpl(
            dataStore = androidContext().dataStore
        )
    }
    single<TodoRemoteDatasource> {
        TodoRemoteDatasourceImpl(
            api = get()
        )
    }
    single<TodoLocalDatasource> {
        TodoLocalDatasourceImpl(
            todoDao = get()
        )
    }
    single { RetrofitClient().createService(RetrofitApi::class.java) }
}

val databaseModule = module {
    single { AppDatabase.createDatabase(androidApplication()) }
    single { get<AppDatabase>().messageDao() }
}
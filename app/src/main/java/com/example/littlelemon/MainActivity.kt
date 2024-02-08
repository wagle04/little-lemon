package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.littlelemon.navigation.AppNavigator
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.utils.API_URL
import com.example.littlelemon.utils.SP_APPNAME
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    private suspend fun fetchMenu()
            : List<MenuItemNetwork> {
        val response = httpClient.get(API_URL)
            .body<MenuNetwork>()
        println(response)
        return response.menuItems
    }

    private val database by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, SP_APPNAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    private fun saveToDatabase(menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
        database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xffffffff)
                ) {
                    AppNavigator(database)
                }
            }
        }

        try {
            lifecycleScope.launch(Dispatchers.IO) {
                if (database.menuItemDao().isEmpty()) {
                    val data = fetchMenu()
                    saveToDatabase(data)
                }
            }
        } catch (err: Error) {
            println("error while fetching data from backend")
            println(err)
        }


    }
}


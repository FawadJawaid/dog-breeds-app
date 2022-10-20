package com.fawadjmalik.dogbreeds.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.fawadjmalik.dogbreeds.ui.base.BaseActivity
import com.fawadjmalik.dogbreeds.ui.navigation.DogBreedsNavigation
import com.fawadjmalik.dogbreeds.ui.theme.DogBreedsTheme
import com.fawadjmalik.dogbreeds.utils.Constants.DARK_THEME
import dagger.hilt.android.AndroidEntryPoint

/**
 * Author: Muhammad Fawad Jawaid Malik
 * This is the MainActivity of the application. We are using the SingleActivity for this app while using Composables for different views and screens.
 */
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val currentTheme = isSystemInDarkTheme()
            val toggleTheme: () -> Unit = {
                if (currentTheme) setDayTheme() else setDarkTheme()
            }
            DogBreedsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    DogBreedsNavigation(toggleTheme)
                }
            }
        }
    }

    private fun setDayTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun setDarkTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }
}

@ExperimentalAnimationApi
@Preview(DARK_THEME, widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    DogBreedsTheme(darkTheme = true) {
        DogBreedsNavigation(toggleTheme = { })
    }
}
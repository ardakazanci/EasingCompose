package com.ardakazanci.easing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ardakazanci.easing.demo.EasingDemoScreen
import com.ardakazanci.easing.ui.theme.EasingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EasingTheme {
                EasingDemoScreen()
            }
        }
    }
}

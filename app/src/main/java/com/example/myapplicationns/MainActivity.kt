package com.example.myapplicationns
 
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.myapplicationns.ui.theme.MyApplicationNSTheme
 
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Use full screen (including behind status bar)
 
        setContent {
            // Apply the Material 3 theme to everything
            MyApplicationNSTheme {
                // Launch the navigation system — this controls all screens
                // Note: Ensure AppNavigation() is defined in your project
                AppNavigation()
            }
        }
    }
}

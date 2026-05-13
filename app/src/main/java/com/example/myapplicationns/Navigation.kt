package com.example.myapplicationns
 
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
 
// --- Screen route names (like URLs for each screen) ---
object Screen {
    const val HOME = "home"
    const val ASSET_LIST = "asset_list"
    const val ADD_ASSET = "add_asset"
    const val CAMERA = "camera"
}
 
// ----------------------------------------------------------
// AppNavigation — the main navigation container
// This composable sets up all the screens and wires them together
// ----------------------------------------------------------
@Composable
fun AppNavigation() {
    // NavController tracks which screen you're on
    val navController: NavHostController = rememberNavController()
 
    // One shared ViewModel for all screens (so data is shared)
    val viewModel: AssetViewModel = viewModel()
 
    // NavHost = the container that shows the correct screen
    NavHost(
        navController = navController,
        startDestination = Screen.HOME  // First screen shown
    ) {
 
        // --- Home / Dashboard Screen ---
        composable(Screen.HOME) {
            HomeScreen(
                viewModel = viewModel,
                onNavigateToList = { navController.navigate(Screen.ASSET_LIST) },
                onNavigateToAdd = { navController.navigate(Screen.ADD_ASSET) }
            )
        }
 
        // --- Asset List Screen ---
        composable(Screen.ASSET_LIST) {
            AssetListScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }, // Go back
                onNavigateToAdd = { navController.navigate(Screen.ADD_ASSET) }
            )
        }
 
        // --- Add Asset Screen ---
        composable(Screen.ADD_ASSET) {
            AddAssetScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        // --- Camera Screen ---
        composable(Screen.CAMERA) {
            CameraScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}

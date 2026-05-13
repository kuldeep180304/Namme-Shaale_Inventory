package com.example.myapplicationns
 
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplicationns.ui.theme.MyApplicationNSTheme
 
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssetListScreen(
    viewModel: AssetViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToAdd: () -> Unit
) {
    val assets by viewModel.assets.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
 
    AssetListScreenContent(
        assets = assets,
        isLoading = isLoading,
        errorMessage = errorMessage,
        onClearError = { viewModel.clearError() },
        onUpdateCondition = { id, condition -> viewModel.updateCondition(id, condition) },
        onDeleteAsset = { id -> viewModel.deleteAsset(id) },
        onNavigateBack = onNavigateBack,
        onNavigateToAdd = onNavigateToAdd
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssetListScreenContent(
    assets: List<Asset>,
    isLoading: Boolean,
    errorMessage: String?,
    onClearError: () -> Unit,
    onUpdateCondition: (String, String) -> Unit,
    onDeleteAsset: (String) -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateToAdd: () -> Unit
) {
    // Show error as a Snackbar
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            snackbarHostState.showSnackbar(it)
            onClearError()
        }
    }
 
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("All Assets (${assets.size})") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToAdd) {
                Icon(Icons.Default.Add, contentDescription = "Add Asset")
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
 
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (assets.isEmpty()) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Default.Inventory2,
                        contentDescription = null,
                        modifier = Modifier.size(64.dp),
                        tint = MaterialTheme.colorScheme.outline
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("No assets yet!", style = MaterialTheme.typography.titleMedium)
                    Text("Tap + to add your first asset.", style = MaterialTheme.typography.bodySmall)
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(assets, key = { it.id }) { asset ->
                        AssetCard(
                            asset = asset,
                            onConditionChange = { newCondition ->
                                onUpdateCondition(asset.id, newCondition)
                            },
                            onDelete = { onDeleteAsset(asset.id) }
                        )
                    }
                }
            }
        }
    }
}
 
@Composable
fun AssetCard(
    asset: Asset,
    onConditionChange: (String) -> Unit,
    onDelete: () -> Unit
) {
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showConditionMenu by remember { mutableStateOf(false) }
 
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
 
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = asset.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = { showDeleteDialog = true }) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
 
            if (asset.serialNumber.isNotEmpty()) {
                Text(
                    text = "S/N: ${asset.serialNumber}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline
                )
            }
 
            if (asset.category.isNotEmpty()) {
                Text(
                    text = asset.category,
                    style = MaterialTheme.typography.bodySmall
                )
            }
 
            Spacer(modifier = Modifier.height(8.dp))
 
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Condition: ", style = MaterialTheme.typography.bodyMedium)
                Box {
                    val badgeColor = when (asset.condition) {
                        ConditionOptions.WORKING -> Color(0xFF28A745)
                        ConditionOptions.NEEDS_REPAIR -> Color(0xFFFFC107)
                        ConditionOptions.BROKEN -> Color(0xFFDC3545)
                        else -> MaterialTheme.colorScheme.outline
                    }
                    AssistChip(
                        onClick = { showConditionMenu = true },
                        label = { Text(asset.condition) },
                        colors = AssistChipDefaults.assistChipColors(
                            labelColor = badgeColor
                        )
                    )
                    DropdownMenu(
                        expanded = showConditionMenu,
                        onDismissRequest = { showConditionMenu = false }
                    ) {
                        ConditionOptions.all.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                onClick = {
                                    onConditionChange(option)
                                    showConditionMenu = false
                                }
                            )
                        }
                    }
                }
            }
 
            if (asset.notes.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Note: ${asset.notes}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }
    }
 
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Delete Asset?") },
            text = { Text("Are you sure you want to delete '${asset.name}'?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDelete()
                        showDeleteDialog = false
                    }
                ) { Text("Delete", color = MaterialTheme.colorScheme.error) }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) { Text("Cancel") }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AssetListScreenPreview() {
    MyApplicationNSTheme {
        AssetListScreenContent(
            assets = listOf(
                Asset(id = "1", name = "Microscope", condition = ConditionOptions.WORKING, category = "Lab"),
                Asset(id = "2", name = "Football", condition = ConditionOptions.BROKEN, category = "Sports")
            ),
            isLoading = false,
            errorMessage = null,
            onClearError = {},
            onUpdateCondition = { _, _ -> },
            onDeleteAsset = {},
            onNavigateBack = {},
            onNavigateToAdd = {}
        )
    }
}

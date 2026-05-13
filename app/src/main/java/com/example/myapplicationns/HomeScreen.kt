package com.example.myapplicationns
 
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplicationns.ui.theme.MyApplicationNSTheme
 
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: AssetViewModel,
    onNavigateToList: () -> Unit,
    onNavigateToAdd: () -> Unit
) {
    val assets by viewModel.assets.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
 
    HomeScreenContent(
        total = assets.size,
        working = viewModel.countByCondition(ConditionOptions.WORKING),
        needsRepair = viewModel.countByCondition(ConditionOptions.NEEDS_REPAIR),
        broken = viewModel.countByCondition(ConditionOptions.BROKEN),
        isLoading = isLoading,
        onRefresh = { viewModel.loadAssets() },
        onNavigateToList = onNavigateToList,
        onNavigateToAdd = onNavigateToAdd
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    total: Int,
    working: Int,
    needsRepair: Int,
    broken: Int,
    isLoading: Boolean,
    onRefresh: () -> Unit,
    onNavigateToList: () -> Unit,
    onNavigateToAdd: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("Namma-Shaale", fontWeight = FontWeight.Bold)
                        Text("Inventory", style = MaterialTheme.typography.bodySmall)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                actions = {
                    IconButton(onClick = onRefresh) {
                        Icon(Icons.Default.Refresh, contentDescription = "Refresh")
                    }
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onNavigateToAdd,
                icon = { Icon(Icons.Default.Add, contentDescription = "Add") },
                text = { Text("Add Asset") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (isLoading) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
 
            Text(
                text = "Asset Summary",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
 
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StatCard(
                    modifier = Modifier.weight(1f),
                    title = "Total",
                    value = total.toString(),
                    icon = Icons.Default.Inventory,
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
                StatCard(
                    modifier = Modifier.weight(1f),
                    title = "Working",
                    value = working.toString(),
                    icon = Icons.Default.CheckCircle,
                    containerColor = Color(0xFFD4EDDA)
                )
            }
 
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StatCard(
                    modifier = Modifier.weight(1f),
                    title = "Needs Repair",
                    value = needsRepair.toString(),
                    icon = Icons.Default.Build,
                    containerColor = Color(0xFFFFF3CD)
                )
                StatCard(
                    modifier = Modifier.weight(1f),
                    title = "Broken",
                    value = broken.toString(),
                    icon = Icons.Default.Cancel,
                    containerColor = Color(0xFFF8D7DA)
                )
            }
 
            Spacer(modifier = Modifier.height(8.dp))
 
            Text(
                text = "Quick Actions",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
 
            Button(
                onClick = onNavigateToList,
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp)
            ) {
                Icon(Icons.Default.List, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("View All Assets ($total)")
            }
 
            OutlinedButton(
                onClick = onNavigateToAdd,
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp)
            ) {
                Icon(Icons.Default.AddBox, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Register New Asset")
            }
        }
    }
}
 
@Composable
fun StatCard(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    icon: ImageVector,
    containerColor: Color
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(icon, contentDescription = title, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

// --- PREVIEW SECTION ---
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MyApplicationNSTheme {
        HomeScreenContent(
            total = 10,
            working = 7,
            needsRepair = 2,
            broken = 1,
            isLoading = false,
            onRefresh = {},
            onNavigateToList = {},
            onNavigateToAdd = {}
        )
    }
}

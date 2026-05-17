package com.demonlab.lune.ui

import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.demonlab.lune.R
import com.demonlab.lune.ui.theme.*

import com.demonlab.lune.core.rememberPerformanceMode
import com.demonlab.lune.core.PerformanceMode

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val performanceMode = rememberPerformanceMode()
    val isLowEnd = performanceMode == PerformanceMode.POWER_SAVER
    
    var currentTab by remember { mutableStateOf(0) }
    var isPlayerExpanded by remember { mutableStateOf(false) }
    
    // Manage root container to handle bottom navigation and miniplayer visibility
    Box(modifier = modifier
        .fillMaxSize()
        .background(DazaiBlack)
        .statusBarsPadding()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Content Area
            Box(modifier = Modifier.weight(1f)) {
                when (currentTab) {
                    0 -> LibraryScreen()
                    1 -> SettingsScreen()
                }
            }
            
            // Player and Navigation Section - Protected from Navigation Bar Overlap
            Surface(
                color = DazaiBlack.copy(alpha = 0.95f),
                modifier = Modifier.navigationBarsPadding()
            ) {
                Column {
                    MiniPlayer(onClick = { isPlayerExpanded = true })
                    
                    NavigationBar(
                        containerColor = Color.Transparent,
                        contentColor = Color.White,
                        tonalElevation = 0.dp
                    ) {
                        NavigationBarItem(
                            selected = currentTab == 0,
                            onClick = { currentTab = 0 },
                            icon = { Icon(Icons.Default.List, contentDescription = null) },
                            label = { Text(stringResource(R.string.library)) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = DazaiBlue,
                                selectedTextColor = DazaiBlue,
                                indicatorColor = DazaiBlue.copy(alpha = 0.1f),
                                unselectedIconColor = DazaiGrayMuted,
                                unselectedTextColor = DazaiGrayMuted
                            )
                        )
                        NavigationBarItem(
                            selected = currentTab == 1,
                            onClick = { currentTab = 1 },
                            icon = { Icon(Icons.Default.Settings, contentDescription = null) },
                            label = { Text(stringResource(R.string.settings)) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = DazaiBlue,
                                selectedTextColor = DazaiBlue,
                                indicatorColor = DazaiBlue.copy(alpha = 0.1f),
                                unselectedIconColor = DazaiGrayMuted,
                                unselectedTextColor = DazaiGrayMuted
                            )
                        )
                    }
                }
            }
        }

        // Full Player Overlay
        AnimatedVisibility(
            visible = isPlayerExpanded,
            enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
        ) {
            PlayerScreen(onMinimize = { isPlayerExpanded = false })
        }
    }
}

@Composable
fun MiniPlayer(onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .height(72.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(20.dp),
        color = DazaiSurface,
        tonalElevation = 8.dp,
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.05f))
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(48.dp),
                shape = RoundedCornerShape(12.dp),
                color = DazaiBlue.copy(alpha = 0.1f)
            ) {
                Icon(
                    Icons.Default.PlayArrow, 
                    contentDescription = null, 
                    modifier = Modifier.padding(12.dp),
                    tint = DazaiBlue
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "مقطوعة ضوء القمر",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Text(
                    text = "بيتهوفن",
                    style = MaterialTheme.typography.labelMedium,
                    color = DazaiGrayMuted
                )
            }
            
            IconButton(onClick = {}) {
                Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color.White)
            }
        }
    }
}

@Composable
fun LibraryScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Text(
            text = stringResource(R.string.library),
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            fontWeight = FontWeight.Black
        )
        Spacer(modifier = Modifier.height(24.dp))
        
        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(List(5) { it }) { i ->
                SongItemPlaceholder()
            }
        }
    }
}

@Composable
fun SongItemPlaceholder() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(56.dp),
            shape = RoundedCornerShape(12.dp),
            color = DazaiSurface
        ) {}
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Box(Modifier.width(140.dp).height(12.dp).alpha(0.3f).background(Color.White, RoundedCornerShape(4.dp)))
            Spacer(modifier = Modifier.height(8.dp))
            Box(Modifier.width(80.dp).height(8.dp).alpha(0.1f).background(Color.White, RoundedCornerShape(4.dp)))
        }
    }
}

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(R.string.settings),
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            fontWeight = FontWeight.Black
        )
        Spacer(modifier = Modifier.height(32.dp))
        
        SettingHeader(stringResource(R.string.about_developer))
        
        AboutDeveloperCard()
        
        Spacer(modifier = Modifier.height(16.dp))
        
        SettingItem(stringResource(R.string.developer_info), stringResource(R.string.developer_name))
        SettingItem("إصدار التطبيق", "1.0.0 (Dazai Soul)")
    }
}

@Composable
fun AboutDeveloperCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        shape = RoundedCornerShape(24.dp),
        color = DazaiSurface,
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.05f))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(64.dp),
                shape = CircleShape,
                color = DazaiBlue.copy(alpha = 0.1f),
                border = BorderStroke(2.dp, DazaiBlue)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        "D", 
                        style = MaterialTheme.typography.headlineMedium, 
                        color = DazaiBlue,
                        fontWeight = FontWeight.Black
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column {
                Text(
                    text = "Mohammad Haider",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "@haiderbyte | Dazai Engine",
                    style = MaterialTheme.typography.labelMedium,
                    color = DazaiBlueLight
                )
            }
        }
    }
}

@Composable
fun SettingHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.labelLarge,
        color = DazaiBlue,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun SettingItem(title: String, desc: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp)) {
        Text(title, style = MaterialTheme.typography.titleMedium, color = Color.White)
        Text(desc, style = MaterialTheme.typography.bodySmall, color = DazaiGrayMuted)
        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider(color = Color.White.copy(alpha = 0.05f))
    }
}

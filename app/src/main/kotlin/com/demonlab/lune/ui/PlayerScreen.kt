package com.demonlab.lune.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.demonlab.lune.R
import com.demonlab.lune.core.PerformanceMode
import com.demonlab.lune.core.rememberPerformanceMode
import com.demonlab.lune.ui.theme.*

@Composable
fun PlayerScreen(onMinimize: () -> Unit) {
    val performanceMode = rememberPerformanceMode()
    val isLowEnd = performanceMode == PerformanceMode.POWER_SAVER

    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseScale by if (!isLowEnd) {
        infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = 1.05f,
            animationSpec = infiniteRepeatable(
                animation = tween(2000, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "pulseScale"
        )
    } else {
        remember { mutableStateOf(1f) }
    }

    val backgroundAlpha by if (!isLowEnd) {
        infiniteTransition.animateFloat(
            initialValue = 0.4f,
            targetValue = 0.6f,
            animationSpec = infiniteRepeatable(
                animation = tween(4000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "bgAlpha"
        )
    } else {
        remember { mutableStateOf(0.4f) }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // --- Dazai Soul Cinematic Background ---
        Box(modifier = Modifier.fillMaxSize()) {
            // Base Black
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {}
            
            if (!isLowEnd) {
                // Layered Radial Glows - Only for mid/high devices
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(backgroundAlpha)
                        .drawWithCache {
                            val brush = Brush.radialGradient(
                                colors = listOf(DazaiBlueDeep.copy(alpha = 0.3f), Color.Transparent),
                                center = Offset(size.width * 0.5f, size.height * 0.3f),
                                radius = size.width * 1.5f
                            )
                            onDrawBehind {
                                drawRect(brush)
                            }
                        }
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(backgroundAlpha * 0.5f)
                        .drawWithCache {
                            val brush = Brush.radialGradient(
                                colors = listOf(DazaiBlueLight.copy(alpha = 0.2f), Color.Transparent),
                                center = Offset(size.width * 0.1f, size.height * 0.8f),
                                radius = size.width * 1.2f
                            )
                            onDrawBehind {
                                drawRect(brush)
                            }
                        }
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // ... (Rest of the UI remains the same but uses conditional styles)
            // Elegant Top Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    onClick = onMinimize,
                    shape = CircleShape,
                    color = Color.White.copy(alpha = 0.08f),
                    modifier = Modifier.size(44.dp)
                ) {
                    Icon(
                        Icons.Default.KeyboardArrowDown, 
                        contentDescription = null, 
                        modifier = Modifier.padding(10.dp),
                        tint = DazaiGrayLight
                    )
                }
                
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = stringResource(R.string.now_playing),
                        style = MaterialTheme.typography.labelMedium,
                        color = DazaiGrayMuted,
                        letterSpacing = 2.sp
                    )
                }

                Surface(
                    shape = CircleShape,
                    color = Color.White.copy(alpha = 0.08f),
                    modifier = Modifier.size(44.dp)
                ) {
                    Icon(
                        Icons.Default.MoreVert, 
                        contentDescription = null, 
                        modifier = Modifier.padding(10.dp),
                        tint = DazaiGrayLight
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1.2f))

            // Artwork with neon glow (scaled only on mid/high end)
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.graphicsLayer {
                    scaleX = pulseScale
                    scaleY = pulseScale
                }
            ) {
                if (!isLowEnd) {
                    // Glow behind artwork
                    Box(
                        modifier = Modifier
                            .size(280.dp)
                            .blur(40.dp)
                            .background(DazaiBlue.copy(alpha = 0.2f), CircleShape)
                    )
                }
                
                Surface(
                    modifier = Modifier
                        .size(300.dp)
                        .clip(RoundedCornerShape(40.dp)),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    border = BorderStroke(1.dp, Color.White.copy(alpha = 0.1f))
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        // Placeholder for artwork
                        Icon(
                            Icons.Default.PlayArrow, 
                            contentDescription = null, 
                            modifier = Modifier.size(100.dp), 
                            tint = Color.White.copy(alpha = 0.1f)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Poetic Song Info
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "مقطوعة ضوء القمر",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "لودفيج فان بيتهوفن",
                    style = MaterialTheme.typography.titleLarge,
                    color = DazaiBlueLight,
                    fontWeight = FontWeight.Light,
                    letterSpacing = 1.sp
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Cinematic Seekbar
            Column(modifier = Modifier.fillMaxWidth()) {
                Slider(
                    value = 0.4f,
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    colors = SliderDefaults.colors(
                        thumbColor = Color.White,
                        activeTrackColor = Color.White,
                        inactiveTrackColor = Color.White.copy(alpha = 0.1f)
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("1:04", style = MaterialTheme.typography.labelSmall, color = DazaiGrayMuted)
                    Text("3:45", style = MaterialTheme.typography.labelSmall, color = DazaiGrayMuted)
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            // Glassmorphism Controls
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {}) {
                    Icon(Icons.Default.Share, contentDescription = null, tint = DazaiGrayMuted)
                }
                
                IconButton(onClick = {}, modifier = Modifier.size(56.dp)) {
                    Icon(Icons.Default.KeyboardArrowLeft, contentDescription = null, modifier = Modifier.size(36.dp), tint = Color.White)
                }

                // Central Pulse Button
                Surface(
                    onClick = {},
                    modifier = Modifier
                        .size(88.dp)
                        .graphicsLayer {
                            shadowElevation = 20f
                        },
                    shape = CircleShape,
                    color = Color.White,
                    tonalElevation = 12.dp
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            Icons.Default.PlayArrow, 
                            contentDescription = null, 
                            modifier = Modifier.size(48.dp), 
                            tint = Color.Black
                        )
                    }
                }

                IconButton(onClick = {}, modifier = Modifier.size(56.dp)) {
                    Icon(Icons.Default.KeyboardArrowRight, contentDescription = null, modifier = Modifier.size(36.dp), tint = Color.White)
                }

                IconButton(onClick = {}) {
                    Icon(Icons.Default.FavoriteBorder, contentDescription = null, tint = DazaiGrayMuted)
                }
            }

            Spacer(modifier = Modifier.weight(0.5f))
            
            // Soft Lyrics Float
            Surface(
                onClick = {},
                shape = RoundedCornerShape(24.dp),
                color = Color.White.copy(alpha = 0.05f),
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.lyrics),
                    modifier = Modifier.padding(horizontal = 32.dp, vertical = 12.dp),
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun innerPadding() = WindowInsets.safeDrawing.asPaddingValues()

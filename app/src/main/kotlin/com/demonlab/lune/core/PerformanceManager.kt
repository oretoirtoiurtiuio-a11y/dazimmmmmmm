package com.demonlab.lune.core

import android.app.ActivityManager
import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext

/**
 * Dazai Logic: Adaptive Performance System
 * Detects device capabilities to adjust visual fidelity.
 */
enum class PerformanceMode {
    CINEMATIC, // Full blurs, neon glows, 120FPS animations
    BALANCED,  // Soft blurs, standard animations
    POWER_SAVER // No blurs, static backgrounds, reduced recomposition
}

@Composable
fun rememberPerformanceMode(): PerformanceMode {
    val context = LocalContext.current
    return remember(context) {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memoryInfo = ActivityManager.MemoryInfo()
        activityManager.getMemoryInfo(memoryInfo)
        
        val totalRamGb = memoryInfo.totalMem / (1024 * 1024 * 1024)
        
        when {
            totalRamGb >= 8 -> PerformanceMode.CINEMATIC
            totalRamGb >= 4 -> PerformanceMode.BALANCED
            else -> PerformanceMode.POWER_SAVER
        }
    }
}

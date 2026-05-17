package com.demonlab.lune.playback

import androidx.media3.common.Player
import kotlinx.coroutines.*

class SleepTimer(private val player: Player) {
    
    private var timerJob: Job? = null
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    fun startTimer(durationMinutes: Int) {
        timerJob?.cancel()
        timerJob = scope.launch {
            delay(durationMinutes * 60 * 1000L)
            
            // Fade out
            for (i in 10 downTo 0) {
                // In real app, adjust player volume
                delay(100)
            }
            
            player.pause()
        }
    }

    fun stopTimer() {
        timerJob?.cancel()
    }
}

package com.demonlab.lune.playback

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class VisualizerPipeline {
    
    // Expose FFT data for rendering
    fun getFftData(): Flow<FloatArray> = flow {
        val dummyData = FloatArray(128)
        while (true) {
            // Generate dummy animated data for the demo
            for (i in dummyData.indices) {
                dummyData[i] = (0..100).random().toFloat() / 100f
            }
            emit(dummyData)
            delay(50) // 20 FPS
        }
    }
}

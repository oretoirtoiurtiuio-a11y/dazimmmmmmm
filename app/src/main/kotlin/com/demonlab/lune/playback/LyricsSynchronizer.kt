package com.demonlab.lune.playback

data class LyricLine(val timeMs: Long, val text: String)

class LyricsSynchronizer {
    
    fun findActiveLine(lines: List<LyricLine>, currentTimeMs: Long): Int {
        var activeIndex = -1
        for (i in lines.indices) {
            if (lines[i].timeMs <= currentTimeMs) {
                activeIndex = i
            } else {
                break
            }
        }
        return activeIndex
    }
}

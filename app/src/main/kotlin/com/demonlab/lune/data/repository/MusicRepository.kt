package com.demonlab.lune.data.repository

import com.demonlab.lune.data.MusicDatabase
import com.demonlab.lune.data.entity.SongOverride
import kotlinx.coroutines.flow.Flow

class MusicRepository(private val database: MusicDatabase) {
    
    fun getFavorites(): Flow<List<SongOverride>> = database.songOverrideDao().getFavorites()
    
    suspend fun toggleFavorite(songId: Long) {
        val song = database.songOverrideDao().getById(songId)
        song?.let {
            database.songOverrideDao().upsert(it.copy(isFavorite = !it.isFavorite))
        }
    }
}

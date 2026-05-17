package com.demonlab.lune.data.dao

import androidx.room.*
import com.demonlab.lune.data.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SongOverrideDao {
    @Query("SELECT * FROM song_overrides WHERE id = :id")
    suspend fun getById(id: Long): SongOverride?

    @Upsert
    suspend fun upsert(override: SongOverride)

    @Query("SELECT * FROM song_overrides WHERE isFavorite = 1")
    fun getFavorites(): Flow<List<SongOverride>>
}

@Dao
interface PlaylistDao {
    @Query("SELECT * FROM playlists")
    fun getAllPlaylists(): Flow<List<Playlist>>

    @Insert
    suspend fun createPlaylist(playlist: Playlist): Long

    @Delete
    suspend fun deletePlaylist(playlist: Playlist)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSongToPlaylist(playlistSong: PlaylistSong)

    @Query("""
        SELECT * FROM song_overrides 
        INNER JOIN playlist_songs ON song_overrides.id = playlist_songs.songId 
        WHERE playlist_songs.playlistId = :playlistId
    """)
    fun getSongsInPlaylist(playlistId: Long): Flow<List<SongOverride>>
}

@Dao
interface PlaybackStatsDao {
    @Query("SELECT * FROM playback_stats WHERE songId = :songId")
    suspend fun getStats(songId: Long): PlaybackStats?

    @Upsert
    suspend fun upsert(stats: PlaybackStats)

    @Query("SELECT * FROM playback_stats ORDER BY playCount DESC LIMIT 20")
    fun getMostPlayed(): Flow<List<PlaybackStats>>
}

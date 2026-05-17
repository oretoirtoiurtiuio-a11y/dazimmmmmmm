package com.demonlab.lune.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "song_overrides")
data class SongOverride(
    @PrimaryKey val id: Long,
    val title: String?,
    val artist: String?,
    val album: String?,
    val lyrics: String?,
    val isFavorite: Boolean = false,
    val path: String
)

@Entity(tableName = "playlists")
data class Playlist(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "playlist_songs", primaryKeys = ["playlistId", "songId"])
data class PlaylistSong(
    val playlistId: Long,
    val songId: Long
)

@Entity(tableName = "playback_stats")
data class PlaybackStats(
    @PrimaryKey val songId: Long,
    val playCount: Int = 0,
    val lastPlayed: Long = 0
)

package com.demonlab.lune.data

import android.content.Context
import androidx.room.*
import com.demonlab.lune.data.dao.*
import com.demonlab.lune.data.entity.*

@Database(
    entities = [SongOverride::class, Playlist::class, PlaylistSong::class, PlaybackStats::class],
    version = 6,
    exportSchema = false
)
abstract class MusicDatabase : RoomDatabase() {
    abstract fun songOverrideDao(): SongOverrideDao
    abstract fun playlistDao(): PlaylistDao
    abstract fun playbackStatsDao(): PlaybackStatsDao

    companion object {
        @Volatile
        private var INSTANCE: MusicDatabase? = null

        fun getDatabase(context: Context): MusicDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MusicDatabase::class.java,
                    "music_database"
                )
                .fallbackToDestructiveMigration() // For simplicity in this demo, usually use AutoMigration
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

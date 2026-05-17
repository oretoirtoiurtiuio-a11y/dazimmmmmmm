# Room
-keepclassmembers class * extends androidx.room.RoomDatabase {
    <init>(...);
}

# Media3
-keep class androidx.media3.** { *; }

# Motion (Compose animations)
-keep class androidx.compose.animation.** { *; }

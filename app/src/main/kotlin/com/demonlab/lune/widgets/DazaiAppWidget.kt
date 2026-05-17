package com.demonlab.lune.widgets

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.*
import androidx.glance.text.Text
import androidx.glance.unit.ColorProvider
import com.demonlab.lune.ui.theme.DarkBackground

class DazaiAppWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = DazaiAppWidget()
}

class DazaiAppWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            GlanceContent()
        }
    }

    @androidx.compose.runtime.Composable
    private fun GlanceContent() {
        Box(
            modifier = GlanceModifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Column {
                Text("Dazai Player", style = androidx.glance.text.TextStyle(color = ColorProvider(DarkBackground)))
                Text("Now Playing: None", style = androidx.glance.text.TextStyle(color = ColorProvider(DarkBackground)))
            }
        }
    }
}

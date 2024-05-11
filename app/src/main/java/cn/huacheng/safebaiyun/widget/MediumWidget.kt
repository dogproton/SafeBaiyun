package cn.huacheng.safebaiyun.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.ImageProvider
import androidx.glance.action.actionStartActivity
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.components.CircleIconButton
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import cn.huacheng.safebaiyun.ShortcutActivity
import cn.huacheng.safebaiyun.R

/**
 *
 *@description:
 *@author: guangzhou
 *@create: 2024-05-06
 */

class MediumReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = MediumWidget
}

object MediumWidget : GlanceAppWidget() {


    override val sizeMode: SizeMode = SizeMode.Single

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            GlanceTheme {
                WidgetContent()
            }
        }
    }

    @Composable
    private fun WidgetContent() {
        Row(
            modifier = GlanceModifier
                .background(GlanceTheme.colors.surface)
                .fillMaxWidth()
                .cornerRadius(50.dp)
                .padding(start = 24.dp, top = 12.dp, bottom = 12.dp, end = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "白云通",
                    style = TextStyle(fontWeight = FontWeight.Medium, fontSize = 16.sp),
                )
                Text(text = "点击解锁", style = TextStyle(fontSize = 14.sp))
            }
            Spacer(modifier = GlanceModifier.defaultWeight())
            CircleIconButton(
                imageProvider = ImageProvider(R.drawable.unlock),
                contentDescription = "",
                backgroundColor = GlanceTheme.colors.primary,
                contentColor = GlanceTheme.colors.onPrimary,
                onClick = actionStartActivity<ShortcutActivity>()
            )

        }
    }

}


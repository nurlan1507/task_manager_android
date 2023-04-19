package com.nurlan1507.task_manager_mobile.feature_users.presentation.components

import android.graphics.drawable.Drawable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nurlan1507.task_manager_mobile.ui.theme.Typography
import com.nurlan1507.task_manager_mobile.utils.WindowSize

@Composable
fun SignInButton(text :String,
                 modifier: Modifier = Modifier,
                 onClick: () -> Unit,
                 imageVector: Int
    ) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = ButtonDefaults.buttonElevation(2.dp),
        shape = RectangleShape,
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)){
            Icon(
                painter = painterResource(id = imageVector),
                contentDescription = "Google",
                modifier = Modifier
                    .size(24.dp)
                    .padding(start = 4.dp),
                tint = Color.Unspecified
            )
            Text(modifier = Modifier.fillMaxWidth(), text = text, textAlign = TextAlign.Center, style = Typography.bodyMedium, color = Color.Black)
        }
    }
}
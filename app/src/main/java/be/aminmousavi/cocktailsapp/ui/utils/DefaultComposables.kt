package be.aminmousavi.cocktailsapp.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.aminmousavi.cocktailsapp.ui.theme.CocktailsAppTheme

@Composable
fun HeadingText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        ),
        modifier = modifier.padding(0.dp, 5.dp)
    )
}

@Composable
fun SubHeadingText(text: String, color: Color = Color.Black, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = color
        ),
        modifier = modifier
    )
}

@Composable
fun TitleText(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(Color(0xFFD78F8F), RoundedCornerShape(2.dp))
            .fillMaxWidth()
    ) {
        SubHeadingText(text = text, color = Color.White, modifier = Modifier.padding(2.dp))
    }
}
@Composable
fun Paragraph(text: String, modifier: Modifier = Modifier) {
    Text(text=text, modifier = Modifier.padding(0.dp, 4.dp, 0.dp, 12.dp))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CocktailsAppTheme {
        Column(){
            HeadingText("Heading Text")
            SubHeadingText(text = "Sub Heading Text")
            TitleText(text = "Title")
            Paragraph(text = "Paragraph")
        }
    }
}
package be.aminmousavi.cocktailsapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.aminmousavi.cocktailsapp.R
import be.aminmousavi.cocktailsapp.data.DataSource
import be.aminmousavi.cocktailsapp.ui.theme.CocktailsAppTheme
import be.aminmousavi.cocktailsapp.ui.utils.Logo

@Composable
fun HomeScreen(
    clickableCardOptions: List<DataSource.ClickableCardOption>,
    onClickableCardClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Logo()
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(clickableCardOptions.size) { index ->
                val option = clickableCardOptions[index]
                ClickableCard(
                    labelResId = option.labelResId,
                    backgroundColorResId = option.backgroundColorResId,
                    fontColorResId = option.fontColorResId,
                    onClick = { onClickableCardClicked(option.labelResId) }
                )
            }
        }
    }
}

@Composable
fun ClickableCard(
    modifier: Modifier = Modifier,
    labelResId: Int,
    backgroundColorResId: Int,
    fontColorResId: Int,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(backgroundColorResId))
                .padding(0.dp, 8.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(id = labelResId),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = colorResource(fontColorResId),
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    CocktailsAppTheme {
        HomeScreen(
            clickableCardOptions = DataSource.clickableCardOptions,
            onClickableCardClicked = {}
        )
    }
}


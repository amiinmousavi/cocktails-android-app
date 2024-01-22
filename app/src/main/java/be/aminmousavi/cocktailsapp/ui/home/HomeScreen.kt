package be.aminmousavi.cocktailsapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.aminmousavi.cocktailsapp.ui.theme.CocktailsAppTheme
import be.aminmousavi.cocktailsapp.ui.utils.Logo

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Logo()
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    CocktailsAppTheme {
        HomeScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}


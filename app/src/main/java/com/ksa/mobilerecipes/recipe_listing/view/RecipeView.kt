package com.ksa.mobilerecipes.recipe_listing.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.ksa.mobilerecipes.recipe_listing.model.Category
import com.ksa.mobilerecipes.recipe_listing.viewModel.MainViewModel

@Composable
fun RecipeScreen(modifier: Modifier = Modifier) {
    val recipeViewModel: MainViewModel = viewModel()

    val viewState by recipeViewModel.categoriesState

    Column {
        NavigationBar(modifier = Modifier.height(50.dp)) {
            Text("Categories", modifier = Modifier.padding(10.dp))
        }
        Box(modifier = Modifier.fillMaxSize()) {
            when {
                viewState.loading -> {
                    CircularProgressIndicator(
                        modifier.align(Alignment.Center)
                    )
                }

                viewState.error != null -> {
                    Text(viewState.error ?: "An error occurred")
                }

                else -> {
                    CategoryScreen(categories = viewState.list)
                }


            }
        }
    }

}

@Composable
fun CategoryScreen(categories: List<Category>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize(),
    ) {
        items(categories) { item ->
            CategoryItem(item = item)

        }
    }
}

@Composable
fun CategoryItem(item: Category) {
    Column(
        Modifier
            .padding(8.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = item.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f),
        )
        Text(
            text = item.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),

            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
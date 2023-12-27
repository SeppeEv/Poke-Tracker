package com.example.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application.ui.theme.ApplicationTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            PokeTrackerApp(windowSizeClass)
        }
    }
}

@Composable
fun PokeTrackerApp(windowSize: WindowSizeClass) {
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            PokeTrackerAppPortrait()
        }
        WindowWidthSizeClass.Expanded -> {
            PokeTrackerAppLandscape()
        }
    }
}

@Composable
fun GreetingText(message: String, from: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier,
    ) {
        Text(
            text = message,
            fontSize = 100.sp,
            lineHeight = 116.sp,
            textAlign = TextAlign.Center,
        )
        Text(
            text = from,
            fontSize = 36.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.End),
        )
    }
}

@Composable
fun GreetingImage(message: String, from: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.lugia_background)
    Box {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .fillMaxSize(),
            alpha = 0.5f,
        )
        GreetingText(
            message = message,
            from = from,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
        )
    }
}

@Composable
fun Searchbar(
    modifier: Modifier = Modifier,
) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
        ),
        placeholder = {
            Text(stringResource(R.string.placeholder_search))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp),
    )
}

@Composable
fun TypeElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier,
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape),
            )
            Text(
                text = stringResource(text),
                modifier = Modifier
                    .paddingFromBaseline(top = 24.dp, bottom = 8.dp),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Preview
@Composable
fun TypeElementPreview() {
    ApplicationTheme {
        TypeElement(
            drawable = R.drawable.grass_sprite,
            text = R.string.grass_type,
            Modifier.padding(8.dp),
        )
    }
}

@Composable
fun GenerationCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(255.dp),
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp),
            )
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(16.dp),
            )
        }
    }
}

@Preview
@Composable
fun GenerationCardPreview() {
    ApplicationTheme {
        GenerationCard(
            drawable = R.drawable.pokemon_red__1_,
            text = R.string.generation_1,
            Modifier.padding(8.dp),
        )
    }
}

@Composable
fun TypeElementRow(
    modifier: Modifier = Modifier,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier,
    ) {
        val typesData = listOf(
            "grass",
            "fire",
            "water",
            "bug",
            "normal",
            "poison",
            "electric",
            "ground",
        )
        items(typesData) { item ->
            TypeElement(
                drawable = R.drawable.grass_sprite,
                text = R.string.grass_type,
            )
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(Modifier.height(16.dp))
        Searchbar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.types) {
            TypeElemntRow()
        }
        HomeSection(title = R.string.generations) {
            GenerationCardGrid()
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, heightDp = 180)
@Composable
fun HomeScreenContentPreview() {
    ApplicationTheme {
        HomeScreen()
    }
}

@Composable
fun GenerationCardGrid(
    modifier: Modifier = Modifier,
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .height(240.dp),
    ) {
        val generationsData = listOf(
            "generation_1",
            "generation_2",
            "generation_3",
            "generation_4",
            "generation_5",
            "generation_6",
            "generation_7",
            "generation_8",
        )
        items(generationsData) { item ->
            GenerationCard(
                drawable = R.drawable.pokemon_red__1_,
                text = R.string.generation_1,
                Modifier.height(80.dp),
            )
        }
    }
}

@Preview
@Composable
fun GenerationCardGridPreview() {
    ApplicationTheme {
        GenerationCardGrid()
    }
}

@Preview
@Composable
fun TypeElemntRow() {
    ApplicationTheme {
        TypeElementRow()
    }
}

@Composable
fun BottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(
        modifier = modifier,
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null,
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_home),
                )
            },
            selected = true,
            onClick = {},
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_profile),
                )
            },
            selected = false,
            onClick = {},
        )
    }
}

@Preview
@Composable
fun BottomNavigationPreview() {
    ApplicationTheme {
        BottomNavigation()
    }
}

@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        Column(modifier) {
            Text(
                text = stringResource(title),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                    .padding(horizontal = 16.dp),
            )
            content()
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeSectionPreview() {
    ApplicationTheme {
        HomeSection(R.string.types) {
            TypeElementRow()
        }
    }
}

@Composable
fun PokeTrackerAppPortrait() {
    ApplicationTheme {
        Scaffold(
            bottomBar = {
                BottomNavigation()
            },
        ) { padding ->
            HomeScreen(Modifier.padding(padding))
        }
    }
}

@Preview
@Composable
fun PokeTrackerAppPortraitPreview() {
    PokeTrackerAppPortrait()
}

@Composable
fun PokeTrackerNavigationRail(modifier: Modifier = Modifier) {
    NavigationRail(
        modifier = modifier.padding(start = 8.dp, end = 8.dp),
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_home))
                },
                selected = true,
                onClick = {},
            )
            Spacer(modifier = Modifier.height(8.dp))
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_profile))
                },
                selected = false,
                onClick = {},
            )
        }
    }
}

@Composable
fun PokeTrackerAppLandscape() {
    ApplicationTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Row {
                PokeTrackerNavigationRail()
                HomeScreen()
            }
        }
    }
}

@Preview
@Composable
fun PokeTrackerAppLandscapePreview() {
    PokeTrackerAppLandscape()
}

@Preview
@Composable
fun PokeTrackerNavigationRailPreview() {
    PokeTrackerNavigationRail()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ApplicationTheme {
        // GreetingImage(message = "Hello user", from = "From Seppe")
        Searchbar()
    }
}

package com.example.littlelemon

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.ui.theme.LittleLemonColor
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import com.example.littlelemon.navigation.Profile
import com.example.littlelemon.ui.theme.Markazi


@Composable
fun HomeScreen(navController: NavController, database: AppDatabase) {
    val databaseMenuItems by database.menuItemDao().getAll().observeAsState(initial = emptyList())

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
        ) {


            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo",
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .fillMaxWidth()
                    .align(Alignment.Center)
            )

            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile",
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
                    .clickable { navController.navigate(Profile.route) }
                    .fillMaxWidth()
                    .align(Alignment.BottomEnd)
            )

        }
        HeroSection(databaseMenuItems)
    }
}

@Composable
fun HeroSection(menuItemsLocal: List<MenuItemRoom>) {
    var menuItems = menuItemsLocal
    var selectedCategory by remember { mutableStateOf("") }
    var searchPhrase by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    if (searchPhrase.isNotEmpty()) {
        menuItems =
            menuItems.filter { it.title.contains(searchPhrase, ignoreCase = true) }
    }

    if (selectedCategory.isNotEmpty()) {
        menuItems = menuItems.filter { it.category.contains(selectedCategory) }
    }

    Column {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .background(LittleLemonColor.green)
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
        ) {
            Text(
                "Little Lemon",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = LittleLemonColor.yellow,
                fontFamily = Markazi,
            )
            Row {
                Column(
                    modifier = Modifier
                        .padding(bottom = 15.dp, end = 5.dp)
                        .fillMaxWidth(0.6f),
                ) {
                    Text(
                        "Chicago",
                        fontSize = 24.sp,
                        color = LittleLemonColor.lightGrey,
                        fontFamily = Markazi,
                    )
                    Text(
                        "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                        style = TextStyle(color = LittleLemonColor.green),
                        color = LittleLemonColor.lightGrey
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.heroimage),
                    contentDescription = "Hero Image",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .height(100.dp)
                )
            }
            Box(modifier = Modifier.clip(RoundedCornerShape(8.dp))) {
                TextField(
                    label = { Text(text = "Enter search phrase", color = Color.Black) },
                    value = searchPhrase,
                    onValueChange = { searchPhrase = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(LittleLemonColor.lightGrey),
                    leadingIcon = {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.Black
                        )
                    },
                )
            }
            Box(modifier = Modifier.height(20.dp))
        }

        Column(modifier = Modifier.padding(horizontal = 10.dp)) {
            Text(
                text = "ORDER FOR DELIVERY!",
                modifier = Modifier.padding(top = 25.dp),
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp)
                    .horizontalScroll(scrollState)
            ) {

                MenuButton(onClick = { selectedCategory = "starters" }, label = "Starters")
                MenuButton(onClick = { selectedCategory = "mains" }, label = "Mains")
                MenuButton(onClick = { selectedCategory = "desserts" }, label = "Desserts")
                MenuButton(onClick = { selectedCategory = "drinks" }, label = "Drinks")

            }

            Box(modifier = Modifier.height(10.dp))
            Divider(color = LittleLemonColor.lightGrey, thickness = 1.dp)
            Box(modifier = Modifier.height(10.dp))


            MenuItems(menuItems)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun MenuItems(items: List<MenuItemRoom>) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(
            items = items,

            itemContent = { menuItem ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(modifier = Modifier.padding(end = 5.dp)) {
                        Text(
                            text = menuItem.title, style = TextStyle(
                                color = LittleLemonColor.charcoal,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = menuItem.description, style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                color = LittleLemonColor.green
                            ),
                            modifier = Modifier
                                .fillMaxWidth(0.75f)
                                .padding(top = 5.dp)
                                .padding(bottom = 5.dp)
                        )
                        Text(
                            text = "$%.2f".format(menuItem.price),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                color = LittleLemonColor.green
                            )
                        )

                    }

                    GlideImage(
                        model = menuItem.image,
                        contentDescription = "Menu Item Image",
                        modifier = Modifier.clip(RoundedCornerShape(5.dp))
                    )
                }
                Divider(color = LittleLemonColor.lightGrey, thickness = 1.dp)

            }
        )
    }
}

package com.example.thecatapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thecatapi.ui.theme.TheCatApiTheme
import androidx.compose.foundation.lazy.items
import coil.compose.rememberAsyncImagePainter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: MainViewModel by viewModels()

        setContent {

            val num = remember { mutableStateOf("") }

            TheCatApiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Column {
                        Row {
                            TextField(value = num.value, onValueChange = { num.value = it })
                            Button(onClick = {
                                viewModel.load(num.value)
                            }) {
                                Text(text = "Загрузить")
                            }
                        }
                        if (viewModel.isLoad.value) {
                            CircularProgressIndicator()
                        }
                        if (viewModel.error.value.isNotEmpty()) {
                            Text(viewModel.error.value)
                        }
                        if (viewModel.catsData.value.isNotEmpty()) {
                            LazyColumn(modifier = Modifier.fillMaxSize()) {
                                items(viewModel.catsData.value) {
                                    Card(
                                        modifier = Modifier
                                            .padding(horizontal = 10.dp, vertical = 2.5.dp)
                                            .fillMaxWidth(),
                                        backgroundColor = Color.White
                                    ) {
                                        Image(
                                            painter = rememberAsyncImagePainter(it.url),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .width(it.width.dp)
                                                .height(it.height.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TheCatApiTheme {
        //Greeting("Android")
    }
}
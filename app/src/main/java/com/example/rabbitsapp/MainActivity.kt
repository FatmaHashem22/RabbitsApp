package com.example.rabbitsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.bumptech.glide.integration.compose.GlideImage
import com.example.rabbitsapp.ui.theme.RabbitsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RabbitsAppTheme {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    val viewModel: MainViewModel = hiltViewModel()
                    val rabbit = viewModel.state.value.rabbit
                    val isLoading = viewModel.state.value.isLoading

                    rabbit?.let {
                        Image(
                            painter = rememberImagePainter(data = rabbit.imageUrl)
                            , contentDescription = "Rabbit Image"
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = rabbit.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = rabbit.description)

                        Spacer(modifier = Modifier.height(8.dp))

                    }

                    Button(
                        onClick = viewModel::getRandomRabbit,
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(text = "Next Rabbit!")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    if (isLoading) {
                        CircularProgressIndicator()
                    }


                }
            }
        }
    }
}

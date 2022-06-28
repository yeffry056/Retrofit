package com.jjvcorporation.retrofit.ui.coin

import android.text.style.BackgroundColorSpan
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjvcorporation.retrofit.data.remote.dto.Coin
import coil.compose.AsyncImage
import coil.request.ImageRequest


@Composable
fun CoinListScreen(
    viewModel: CoinViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Lista de Coins")})
        }
    ) {
        Column(modifier = Modifier.padding(it).padding(8.dp).fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(state.coins){ coin ->
                    CoinItem(coin = coin, {})
                }
            }

            if (state.isLoading)
                CircularProgressIndicator()

        }
    }




}

@Composable
fun CoinItem(
    coin: Coin,
    onClick : (Coin) -> Unit
) {

    Card(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 3.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = 8.dp

    ) {
        Row(modifier = Modifier

            .clickable { onClick(coin) }
            .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row() {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(coin.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = coin.descripcion,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(35.dp)
                )
                Spacer(modifier = Modifier.width(15.dp))
                Text(text = coin.descripcion.orEmpty())


            }

           // Text(text = coin.descripcion.orEmpty())
            Text(
                text = "$ ${coin.valor} ",
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.End

            )



            /* Text(
                 text = if(coin.is_active) "Activa" else "Inactiva",
                 color = if(coin.is_active) Color.Green else Color.Red ,
                 fontStyle = FontStyle.Italic,
                 style = MaterialTheme.typography.body2,
                 modifier = Modifier.align(Alignment.CenterVertically)
             )*/

        }

    }

}


@Composable
fun CoinScreen(viewModel: CoinViewModel = hiltViewModel()) {
    //val coin = viewModel.coin.value

    Column(modifier = Modifier.fillMaxSize()) {
        /* Text(text = coin.name)
         Text(text = coin.symbol)*/
    }

}
package com.jjvcorporation.retrofit.ui.coin

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.jjvcorporation.retrofit.data.CoinApi
import com.jjvcorporation.retrofit.data.remote.dto.Coin
import com.jjvcorporation.retrofit.data.repository.CoinsRepository
import java.lang.reflect.Type


val focusRequesterDescripcion = FocusRequester()
val focusRequesterImageUrl = FocusRequester()
val focusRequesterPrecio = FocusRequester()

@Composable
fun RegistroCoins(
    goConsulta : () -> Unit,
    viewModel: CoinViewModel = hiltViewModel()
) {

    val mContext = LocalContext.current


    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Registro Coins") })
        }

    ) {
        Column(modifier = Modifier
            .padding(it)
            .padding(8.dp)) {
            OutlinedTextField(
                label = {
                    Text(text = "Descripcion")
                },
                modifier = Modifier.fillMaxWidth().focusRequester(focusRequesterDescripcion),
                leadingIcon = { Icon(imageVector = Icons.Default.Description, contentDescription = null) },
                value = viewModel.Descripcion,
                onValueChange = {viewModel.Descripcion = it}
            )
            OutlinedTextField(
                label = {
                    Text(text = "ImageUrl")
                },
                modifier = Modifier.fillMaxWidth().focusRequester(focusRequesterImageUrl),
                leadingIcon = { Icon(imageVector = Icons.Default.Http, contentDescription = null) },
                value = viewModel.ImageUrl,
                onValueChange = {viewModel.ImageUrl = it}
            )
            OutlinedTextField(
                label = {
                    Text(text = "Precio")
                },
                modifier = Modifier.fillMaxWidth().focusRequester(focusRequesterPrecio),
                leadingIcon = { Icon(imageVector = Icons.Default.MonetizationOn, contentDescription = null) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = viewModel.Precio,
                onValueChange = {viewModel.Precio = it}
            )

            OutlinedButton(
                onClick = {

                    if(!validar(viewModel, mContext)){
                        return@OutlinedButton
                    }

                    val coins = Coin(
                    descripcion = viewModel.Descripcion,
                    valor = viewModel.Precio.toDouble(),
                    imageUrl = viewModel.ImageUrl)

                    viewModel.pushPost(coins)

                    viewModel.cargar()

                    goConsulta()


                },
                modifier = Modifier.fillMaxWidth()

            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = null)
                Text(text = "Guardar")
            }
        }
    }

}




private fun validar(viewModel: CoinViewModel, context: Context) : Boolean{

    var valid: Boolean = true
    if(viewModel.Descripcion.isNullOrEmpty()){
        focusRequesterDescripcion.requestFocus()
        Toast.makeText(context, "Descripcion vacia", Toast.LENGTH_SHORT).show()
        valid = false
        return valid
    }
    if(viewModel.ImageUrl.isNullOrEmpty()){
        focusRequesterImageUrl.requestFocus()
        Toast.makeText(context, "ImageUrl vacio", Toast.LENGTH_SHORT).show()
        valid = false
        return valid
    }
    if(viewModel.Precio.isNullOrEmpty()){
        focusRequesterPrecio.requestFocus()
        Toast.makeText(context, "Precio vacio", Toast.LENGTH_SHORT).show()
        valid = false
        return valid
    }

    return valid
}

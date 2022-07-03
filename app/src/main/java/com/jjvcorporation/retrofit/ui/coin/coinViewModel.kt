package com.jjvcorporation.retrofit.ui.coin

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjvcorporation.retrofit.CoinListState
import com.jjvcorporation.retrofit.data.remote.dto.Coin

import com.jjvcorporation.retrofit.data.repository.CoinsRepository
import com.jjvcorporation.retrofit.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val coinsRepository: CoinsRepository
) : ViewModel() {

    var Descripcion by mutableStateOf("")
    var Precio by mutableStateOf("")
    var ImageUrl by mutableStateOf("")

    var myResponse : MutableLiveData<Response<Coin>> = MutableLiveData()

    fun pushPost(coin: Coin){
        viewModelScope.launch {
            val response = coinsRepository.postCoin(coin)
            myResponse.value = response
        }
    }




     var _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    fun cargar(){
        coinsRepository.getCoins().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = CoinListState(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CoinListState(error = result.message ?: "Error desconocido")
                }
            }
        }.launchIn(viewModelScope)
       /* coinsRepository.getCoins().onEach {res->
            _state.value = CoinListState(coins =  res.data ?: emptyList())

        }.launchIn(viewModelScope)*/

        // var state:State<CoinListState> = _state


    }

   /*fun guardar(){
       viewModelScope.launch {
           coinsRepository.postCoin(
               Coin(
                   descripcion = Descripcion,
                   imageUrl = ImageUrl,
                   valor = Precio.toDouble()
               )
           )
       }
   }*/

    init {
        cargar()

    }

}
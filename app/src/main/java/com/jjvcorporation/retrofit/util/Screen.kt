package com.jjvcorporation.retrofit.util

sealed class Screen(val route: String) {
    object RegistroCoin : Screen("RegistroCoin")
    object ConsultaCoin : Screen("ConsultaCoin")
    }
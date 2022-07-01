package com.example.aplicacionteniente.Navegation

sealed class RouteScreens(val route: String){

    object Presentacion : RouteScreens("Presentacion")
    object Login: RouteScreens("Login")
    object Iniciada: RouteScreens("Iniciada")
    object UnasPreguntasMas : RouteScreens("UnasPreguntasMas")
}

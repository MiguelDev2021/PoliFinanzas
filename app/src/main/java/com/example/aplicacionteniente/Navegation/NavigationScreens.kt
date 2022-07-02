package com.example.aplicacionteniente.Navegation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.aplicacionteniente.PresentacionScreen
import com.example.aplicacionteniente.screens.LoginScreen
import com.example.aplicacionteniente.screens.Iniciada
import com.example.aplicacionteniente.screens.UnasPreguntasMas

@Composable
fun AppNavegation(){

    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = RouteScreens.Iniciada.route){


        composable(route = RouteScreens.Iniciada.route){
            Iniciada(navController)
        }


        composable(route = RouteScreens.Login.route){
            LoginScreen(navController)
        }
        composable(route = RouteScreens.UnasPreguntasMas.route + "/{correo}/{contraseña}"){
            UnasPreguntasMas(navController)
        }

        composable(route = RouteScreens.Presentacion.route + "/{correo}/{codigo}/{contraseña}",
            arguments = listOf(
                navArgument(name = "correo"){
                    type = NavType.StringType
                },
                 navArgument(name = "codigo"){
                     type  = NavType.StringType
                 },

                navArgument(name = "contraseña"){
                    type = NavType.StringType

                }


            )

        ){
            PresentacionScreen(navController,
                it.arguments?.getString("correo"),
                it.arguments?.getString("codigo"),
                it.arguments?.getString("contraseña")
                )
        }

    }
}
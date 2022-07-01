package com.example.aplicacionteniente.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aplicacionteniente.Navegation.RouteScreens
import com.example.aplicacionteniente.backendscreens.*

//variable para guardar la contraseña







@Composable
fun LoginScreen(navController: NavController){
    PartsView(navController)

}

@Composable
fun Texto(texto: String){
    Text(text = "$texto")

}


@Composable
fun LetreroUsuario(){
    Texto(texto = "USUARIO")
}

@Composable
fun LetreroContraseña() {
    Texto(texto = "CONTRASEÑA")

}

@Composable
fun LetreroConfirmarUsuario(){
    Texto(texto = "CONFIRMAR USUARIO")
}

@Composable
fun LetreroConfirmarContraseña() {
    Texto(texto = "CONFIRMAR CONTRASEÑA")
}

@Composable
fun LetreroError(error: String) {
    Text(text = "$error",
        modifier = Modifier.padding(bottom = 10.dp),
        Color(0xffff0000),
    )
}






@Composable
fun PartsView(navController: NavController) {

    //variables para tomar los datos del Textfield
    var usuario by remember { mutableStateOf("") }
    var VerificacionCorreo by remember { mutableStateOf("") }
    var Contraseña by remember { mutableStateOf("") }
    var ConfirmarContraseña by remember { mutableStateOf("") }
    var textoError by remember { mutableStateOf("") }

    // variables para verificar
    var datos: Boolean // la variable mas importante por gestiona si todos lo datos son correctos.
    var usuarioConfirmado: Boolean
    var VerificacionCorreoConfirmado: Boolean
    var ContraseñaConfirmado: Boolean
    var ConfirmarContraseñaConfirmado: Boolean
    var ContraseñasIguales: Boolean
    var UsuarioIguales: Boolean

    var usuarioparametro : String

    Column(
        modifier = Modifier
            .fillMaxSize(),

        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Column(
            modifier = Modifier.padding(
                vertical =
                100.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //para que coloque el correo.
            LetreroUsuario()
            OutlinedTextField(

                modifier = Modifier.padding(bottom = 30.dp),
                value = usuario,
                onValueChange = { usuario = it },
                label = { Text("Correo Institucional") }

            )
            //verificacion del correo.
            LetreroConfirmarUsuario()
            OutlinedTextField(

                modifier = Modifier.padding(bottom = 30.dp),
                value = VerificacionCorreo,
                onValueChange = { VerificacionCorreo = it },
                label = { Text("Correo Institucional") }
            )

            //campo para la contraseña.
            LetreroContraseña()
            OutlinedTextField(
                modifier = Modifier.padding(bottom = 30.dp),
                value = Contraseña,
                onValueChange = { Contraseña = it },
                label = { Text("Password") }
            )

            // confirma contraseña
            LetreroConfirmarContraseña()
            OutlinedTextField(
                modifier = Modifier.padding(bottom = 20.dp),
                value = ConfirmarContraseña,
                onValueChange = { ConfirmarContraseña = it },
                label = { Text("Password") }
            )

            LetreroError(error = textoError)

            Button(onClick = {
                datos = false
                //esto es la forma de cambiar de vista.

                //Verificar si los campos no estan vacios
                usuarioConfirmado = validarcampo(usuario)
                VerificacionCorreoConfirmado = validarcampo(VerificacionCorreo)
                ContraseñaConfirmado = validarcampo(Contraseña)
                ConfirmarContraseñaConfirmado = validarcampo(ConfirmarContraseña)

                if (datos != true) {
                    var datosCompletos: Boolean
                    var Contraseñasiguales: Boolean
                    var correo: Boolean
                    var contraseña: Boolean
                    // este if valida que ningun dato este vacio
                    if (usuarioConfirmado && VerificacionCorreoConfirmado && ContraseñaConfirmado && ConfirmarContraseñaConfirmado) {
                        datosCompletos = true

                    } else {
                        datosCompletos = false
                        textoError = "*NO PUEDEN DEJAR CAMPOS VACIOS"

                    }

                    // este if valida que la contraseña tenga minimo 4 caracteres

                    if (validacionContraseña(Contraseña)) {
                        contraseña = true
                        println("contraseña Ok")
                    } else {
                        contraseña = false
                        textoError = "*Contraseña Muy Corta"
                    }

                    //este if valida que las contraseñas que sean iguales
                    if (validacioniguales(Contraseña, ConfirmarContraseña)) {
                        println("contraseñas iguales")
                        Contraseñasiguales = true

                    } else {
                        textoError = "*Las Contraseñas No Iguales"
                        Contraseñasiguales = false

                    }

                    //este if valida si es un correo

                    if (validacionCorreo(usuario)) {
                        println("el correo tiene el @")
                        correo = true
                    } else {
                        correo = false
                        textoError = "*El Correo Le Falta el @"
                    }

                    // este if valida si es un correo institucional
                    if (validacionCorreoInstitucional(usuario)) {
                        correo = true
                        println("el correo es institucional")
                    } else {
                        textoError = "*El Correo No Es Institucional"
                        correo = false

                    }


                    // este if valida la confirmacion del correo electronico
                    if (validacioniguales(usuario, VerificacionCorreo)) {
                        correo = true
                        println("los correos son iguales")
                    } else {
                        correo = false
                        textoError = "*Los correos No Son Iguales"
                    }

                    //este if valida que todos los datos esten okey y da paso a la siguiente pantalla
                    if (correo == true && Contraseñasiguales == true && datosCompletos == true && contraseña == true) {
                        var codigo: String
                        //generar Codigo
                        codigo = Codigo()
                        //enviar Correo para verificar si el numero es correcto
                        enviarCorreosgmail(codigo, usuario)
                        navController.navigate(route = RouteScreens.Presentacion.route + "/$usuario/$codigo")

                    }


                }


                //verificamos si el usuario no esta guardado e la base de datos.

            }) {
                Text("REGISTRARSE")
            }


        }
    }

}

package com.example.aplicacionteniente

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.aplicacionteniente.Navegation.RouteScreens
import com.example.aplicacionteniente.backendscreens.validacioniguales


@Composable
fun PresentacionScreen(navController: NavController, correo: String?, codigo : String?){
    Scaffold() {
        Presentacionelements(correo , codigo, navController)
    }


}

@Composable
 fun LetreroEnvioCodigo(correo: String){


    val annotatedText = buildAnnotatedString {
         append("Hemos enviado un codigo de validacion al siguiente correo: $correo ")
        pushStringAnnotation(tag = "",
            annotation = "")
        withStyle(style = SpanStyle(color = Color (148, 195, 37),
            fontWeight = FontWeight.Bold)
        ) {


            append("¿Corregir Correo?")
        }

        pop()
    }



        ClickableText(
            text = annotatedText,
            style = TextStyle(textAlign = TextAlign.Center),
            onClick ={
            //enviarlos a la pantlla anterior


        }, )


 }






@Composable
fun Presentacionelements(correo: String?, codigo: String?, navController: NavController){
    var correoRecibido : String = ""
    var CodigoRecibido :  String = ""
    correo?.let {
        correoRecibido = correo
    }
    codigo?.let {
        CodigoRecibido  = codigo
    }

    println("el correo es $correo  y el codigo es $codigo" )
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "POLIFINANZAS",
                                    color = Color(148,195,37),

            )},
            backgroundColor = Color(0,23,75),

            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }

            }
            )
        }
    ) {
        var Codigo by remember { mutableStateOf("") }
        var Advertencia by remember { mutableStateOf("*No olvides revisar la bandeja de correos no deseados ") }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(top = 40.dp, end = 30.dp, start = 30.dp),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {




                LetreroEnvioCodigo(correoRecibido)

                OutlinedTextField(
                    modifier = Modifier.padding(top = 30.dp, bottom = 10.dp),
                    value = Codigo,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = { Codigo = it },
                    label = { Text("Codigo") },

                )
                Text(text = Advertencia ,
                    color = Color.Red
                )

            Button(
                modifier = Modifier.padding(top =20.dp).width(120.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(148,195,37)),
                onClick = {

                    if(validacioniguales(Codigo, CodigoRecibido )){
                        //guardar el correo y contraseña
                        navController.navigate(route = RouteScreens.UnasPreguntasMas.route )

                    }else{
                        Advertencia = "EL CODIGO DIGITADO NO ES CORRECTO"
                    }


                },) {


                Text(text = "VALIDAR", color = Color.White)

            }
            






            }
        }

    }






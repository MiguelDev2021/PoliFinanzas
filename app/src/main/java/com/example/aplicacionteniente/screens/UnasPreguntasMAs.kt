package com.example.aplicacionteniente.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.aplicacionteniente.data.UserViewModel
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun LetreroRango(){

    Text(text = "Seleciona tu cargo Actual", modifier = Modifier.padding(top = 13.dp))
}

@Composable
fun LetreroOrdenPublico(){

    Text(text = "Recibes prima de orden publico",
        modifier = Modifier.padding(top = 13.dp)
        )
}








@Composable
fun UnasPreguntasMas(navController: NavController, viewModel: UserViewModel = hiltViewModel()) {


    //variables para el menu despegable de rango Actual y Prima de orden Publico

    var mExpanded by remember { mutableStateOf(false) }
    var mExpanded2 by remember { mutableStateOf(false) }
    // Create a list of cities
    val mCities = listOf("GENERAL", "MAYOR GENERAL", "BRIGADIER GENERAL", "CORONEL", "TENIENTE CORONEL", "MAYOR", "CAPITAN", "TENIENTE",
                         "SUBTENIENTE", "COMISARIO","SUBCOMISARIO", "INTENDENTE JEFE", "INTENDENTE", "SUBINTENDENTE", "PATRULLERO", "SARGENTO MAYOR",
                        "AGENTES", "ALFEREZ", "CADETES", "ALUMNOS", "AUXILIARES DE POLICIA"
                        )
    val validacion = listOf("SI", "NO")
    // Create a string value to store the selected city
    var mSelectedText by remember { mutableStateOf("") }
    var mSelectedText2 by remember { mutableStateOf("") }

    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}
    var mTextFieldSize2 by remember { mutableStateOf(Size.Zero)}

   //ejemplo para hacer una validacion de lo que se manda por la ruta.
    //correo?.let {
     //   correoRecibido = correo
   // }
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "POLIFINANZAS",
                    color = Color(148, 195, 37),

                    )
            },
                backgroundColor = Color(0, 23, 75),
            )
        }

    ) {
        var Nombre by remember { mutableStateOf("") }
        var advertencia by remember { mutableStateOf("") }





        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(top = 40.dp, end = 30.dp, start = 30.dp),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Escribe Tu nombre",

            )


            OutlinedTextField(
                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                value = Nombre,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = { Nombre = it },
                label = { Text("Nombre") },

                )
            LetreroRango()


            // --------------------MENU DESPEGABLE RANGO --------------------//
            val icon = if (mExpanded)
                Icons.Filled.KeyboardArrowUp
            else
                Icons.Filled.KeyboardArrowDown

            Column(Modifier.padding(start = 25.dp, end = 25.dp, bottom = 10.dp, top = 8.dp)) {

                // Create an Outlined Text Field
                // with icon and not expanded
                OutlinedTextField(
                    value = mSelectedText,
                    onValueChange = { mSelectedText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            // This value is used to assign to
                            // the DropDown the same width
                            mTextFieldSize = coordinates.size.toSize()
                        },
                    label = {Text("Rangos")},
                    trailingIcon = {
                        Icon(icon,"contentDescription",
                            Modifier.clickable { mExpanded = !mExpanded })
                    }
                )

                // Create a drop-down menu with list of cities,
                // when clicked, set the Text Field text as the city selected
                DropdownMenu(
                    expanded = mExpanded,
                    onDismissRequest = { mExpanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
                ) {
                    mCities.forEach { label ->
                        DropdownMenuItem(onClick = {
                            mSelectedText = label
                            mExpanded = false
                        }) {
                            Text(text = label)
                        }
                    }
                }
            }

            // -------------------- LETRERO ORDEN PUBLICO
            LetreroOrdenPublico()

            // ----------------- MENU DESPEGABLE ORDEN PUBLICO ---------------//
            val icon1 = if (mExpanded2)
                Icons.Filled.KeyboardArrowUp
            else
                Icons.Filled.KeyboardArrowDown

            Column(Modifier.padding(start = 25.dp, end = 25.dp, bottom = 10.dp, top = 8.dp)) {

                // Create an Outlined Text Field
                // with icon and not expanded
                OutlinedTextField(
                    value = mSelectedText2,
                    onValueChange = { mSelectedText2 = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            // This value is used to assign to
                            // the DropDown the same width
                            mTextFieldSize2 = coordinates.size.toSize()
                        },
                    label = {Text("Selecionar")},
                    trailingIcon = {
                        Icon(icon,"contentDescription",
                            Modifier.clickable { mExpanded2 = !mExpanded2 })
                    }
                )

                // Create a drop-down menu with list of cities,
                // when clicked, set the Text Field text as the city selected
                DropdownMenu(
                    expanded = mExpanded2,
                    onDismissRequest = { mExpanded2 = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
                ) {
                    validacion.forEach { label ->
                        DropdownMenuItem(onClick = {
                            mSelectedText2 = label
                            mExpanded2 = false
                        }) {
                            Text(text = label)
                        }
                    }
                }
            }


            Text(text = advertencia)

            // ------------------ BOTON GUARDAR---------------------------------------//
            Button(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .width(120.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(148, 195, 37)),
                onClick = {

                          println("La variable rango $mSelectedText  la variable rango $mSelectedText2")


                },
            ) {


                Text(text = "ENVIAR", color = Color.White)

            }
        }
    }
}

package com.example.aplicacionteniente.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.Rgb
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.aplicacionteniente.Navegation.RouteScreens
import com.example.aplicacionteniente.R


@Composable
fun Iniciada(navController: NavController){
    Scaffold() {
        ComponentesIniciada(navController)
    }
}


@Composable
fun LetreroPolifinanzas(){
    Text(text = "PoliFinanzas" ,
        fontSize = 60.sp,
        color = Color(148,195,37),
        modifier = Modifier.padding(bottom = 5.dp, top = 35.dp)
        )
}

@Composable
fun LetreroEslogan(){
    Text(text = "Una aplicacion para cuidar tu bolsillo, señor" ,
        fontSize = 17.sp,
        color = Color(255,255,255),
        textAlign  = TextAlign.Center
    )
}
@Composable
fun Policia(){
    Text(text = "POLICIA." ,
        fontSize = 17.sp,
        color = Color(255,255,255),
        textAlign  = TextAlign.Center
    )
}

@Composable
fun ImagenMonedas(){

    Image(
        painter = painterResource(id = R.drawable.monedas),
        contentDescription = "Image",
        modifier = Modifier
            .padding(top = 100.dp)
            .width(200.dp)
            .height(150.dp)

    )
}
@Composable
fun BotonContinuar(navController: NavController) {
    //CutCornerShape(percent: Int)- it will consider as percentage
    //CutCornerShape(size: Dp)- you can pass Dp also.
    //Here we use Int, so it will take percentage.
    Button(onClick = {
        navController.navigate(route = RouteScreens.Login.route)
    },
            modifier = Modifier.padding(top =  90.dp ),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(148,195,37)),
            shape = CutCornerShape(10)
        ,elevation =  ButtonDefaults.elevation(
            defaultElevation = 10.dp,
            pressedElevation = 15.dp,
            disabledElevation = 0.dp


        )
    ) {
        Image(
            painterResource(id = R.drawable.logocontinuar),
            contentDescription ="next button icon",
            modifier = Modifier.size(30.dp)

        )
        Text(text = "CONTINUAR",
            modifier = Modifier
                .padding(start = 8.dp, top = 10.dp)
                .width(110.dp)
                .height(30.dp),
                    textAlign  = TextAlign.Center
        )
    }
}

@Composable
fun ImagenEsunhonorserpolicia(){

    Image(
        painter = painterResource(id = R.drawable.esunhonorserpolicia),
        contentDescription = "Es un honor ser policia",

        modifier = Modifier.padding(top = 70.dp).width(190.dp).height(100.dp)


    )
}



@Composable
fun ComponentesIniciada(navController: NavController){


    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0, 23, 75)),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        ImagenMonedas()

        LetreroPolifinanzas()// polifinanzas
        LetreroEslogan()
        Policia()
        BotonContinuar(navController)
        ImagenEsunhonorserpolicia()

    }
}
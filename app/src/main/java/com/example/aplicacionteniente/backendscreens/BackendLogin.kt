package com.example.aplicacionteniente.backendscreens

import android.R.attr.password
import android.os.StrictMode
import java.lang.reflect.Array.get
import java.nio.file.Paths.get
import java.util.*
import javax.mail.*
import javax.mail.internet.AddressException
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


fun validarcampo(campo : String): Boolean {
         var validacion : Boolean
        if(campo.isNullOrEmpty()){
            validacion = false
        }else{
            validacion = true
        }
        return validacion
    }

fun  validacioniguales(campo1 :String ,  campo2:String):Boolean{
    var validacion : Boolean

    if (campo1.equals(campo2)){
        validacion =true

    }else{
        validacion = false

    }
    return validacion
}
fun validacionCorreoInstitucional(correo:String):Boolean{
    var correoVerificar = correo.lowercase().trim()
    val delim = "@"
    var arr = correoVerificar.split(delim).toTypedArray()
    var correoconfirmado : Boolean = false
    var DominioAceptado: String = "correo.policia.gov.co"


    if (arr.size > 2 ){
        correoconfirmado = false

    }else{
        try {
            if(arr.get(1) == DominioAceptado){
                correoconfirmado = true
            }
        }catch (e : ArrayIndexOutOfBoundsException){
            println("error en los datos del correo")
            correoconfirmado = false
        }

    }


return correoconfirmado
}




fun validacionCorreo(correo:String):Boolean{
    var correoVerificar = correo.lowercase().trim()
    val delim = "@"
    var arr = correoVerificar.split(delim).toTypedArray()
    var correoescrito : Boolean = false


    println("esto es lo que hay en el arr" + arr.contentToString())
    if (arr.size == 1 ){
        correoescrito = false

    }else{
        correoescrito = true
    }


    return correoescrito
}

fun validacionContraseña(contraseña:String):Boolean{
    var contraseñaconfirmada : Boolean

    if (contraseña.length < 4){
        contraseñaconfirmada = false
    }else{
        contraseñaconfirmada = true
    }




    return contraseñaconfirmada
}

fun Codigo() : String{

    val numbers = mutableListOf("1", "2", "3", "4", "5", "6", "7", "8", "9")
    var number:String = ""
    var digitos = 0

    do{
        number +=  numbers.random()
        digitos++

    }while(digitos < 5)
    println( "el numero es: " + number + "y los digitos son : " + digitos )

    return number

}

// esta funcion envia correos
/*
fun CorreoVerficacion(numero : String){
    var policy  = StrictMode.ThreadPolicy.Builder().permitAll().build()
    StrictMode.setThreadPolicy(policy)

    var CorreoRemitente:String = "polifinanzas@outlook.com"
    var CorreoDestinatario : String  = "pedraza.2001@hotmail.com"
    var Contraseña: String = "3138766575c"
    var textoCorreo :String = "hola"

    val props =  Properties()
    props.put("mail.smtp.auth", "true")
    props.put("mail.smtp.starttls.enable", "true")
    props.put("mail.smtp.host","smtp-mail.outlook.com")
    props.put("mail.smtp.port","587")
    val auth: Authenticator = object : Authenticator() {
        override fun getPasswordAuthentication(): PasswordAuthentication? {
            return PasswordAuthentication(CorreoRemitente, Contraseña)
        }
    }

    var session = Session.getInstance(props, auth)

    try {
        var message = MimeMessage(session)
        var internetAddress = InternetAddress(CorreoRemitente)
        message.setFrom(internetAddress)
        message.setRecipients(Message.RecipientType.TO, CorreoDestinatario)
        message.setText(textoCorreo)
        Transport.send(message)

    }catch (e: MessagingException  ){
        println(e)
    }



}
*/

fun enviarCorreosgmail(codigo : String, Correo : String){

    var policy  = StrictMode.ThreadPolicy.Builder().permitAll().build()
    StrictMode.setThreadPolicy(policy)

     var from : String = "miguel.2022.gilibert@gmail.com"
     var pass : String = "ftuvtwjnphrcnfxl"
     var RECEPIENT : String =  Correo
     var to  = arrayOf( RECEPIENT)
     var subject = "CODIGO VERIFICACION"
     var body = "Hola gracias por usar Polifinanzas, Tu Codigo de Verificacion es: $codigo"
    val props: Properties = System.getProperties()
    val host = "smtp.gmail.com"
    props["mail.smtp.starttls.enable"] = "true"
    props["mail.smtp.host"] = host
    props["mail.smtp.user"] = from
    props["mail.smtp.password"] = pass
    props["mail.smtp.port"] = "587"
    props["mail.smtp.auth"] = "true"

    val session: Session = Session.getDefaultInstance(props)
    val message = MimeMessage(session)
    try {
        message.setFrom(InternetAddress(from))


        var toAddress = arrayOfNulls<InternetAddress>(to.size)


        for (i in toAddress.indices) {
            toAddress[i] = InternetAddress(to[i])
        }
        for (i in toAddress.indices) {
            message.addRecipient(Message.RecipientType.TO, toAddress[i]);

        }


        message.subject = subject
        message.setText(body)
        val transport: Transport = session.getTransport("smtp")
        transport.connect(host, from, pass)
        transport.sendMessage(message, message.allRecipients)
        transport.close()
    } catch (ae: AddressException) {
        ae.printStackTrace()
    } catch (me: MessagingException) {
        me.printStackTrace()
    }
}






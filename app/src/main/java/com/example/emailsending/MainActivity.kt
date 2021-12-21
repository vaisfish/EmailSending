package com.example.emailsending

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.EditText
import java.util.*
import javax.mail.Authenticator
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import android.os.AsyncTask
import android.os.StrictMode
import android.util.Log
import javax.mail.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        var but = findViewById<Button>(R.id.but)
        var email = findViewById<EditText>(R.id.email)

       /* var sm = SendMail("emanufish@gmail.com", "20elul163",
            "yehudashor789@gmail.com", "hhh", "jjjjjjjjj")

        but.setOnClickListener(View.OnClickListener {  })*/
        var s : StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(s)



        var properties = Properties()
        properties.put("mail.smtp.auth", "true")
                    properties.put("mail.smtp.starttls.enable", "true")
                    properties.put("mail.smtp.host", "smtp.gmail.com")
                    properties.put("mail.smtp.port", "587")
                    val session = Session.getDefaultInstance(properties,
                        object : Authenticator() {
                            override fun getPasswordAuthentication(): PasswordAuthentication {
                                return PasswordAuthentication("emanufish@gmail.com", "20elul163")
                }
            })

        session.debug = true

        try {
            val mm = MimeMessage(session)
            var i = InternetAddress.parse("emanufish@gmail.com")
            mm.addFrom(i)
            mm.addRecipients(javax.mail.Message.RecipientType.TO, InternetAddress("yehudashor789@gmail.com").toString())
            mm.subject = "hhh"
            mm.setText("jjjjjjjjj")
            Transport.send(mm)

        } catch (e: MessagingException) {
            Log.d("SendMail", "doInBackground: " + e.message)
        }
    }
}


/*



class SendMail(
    private val senderEmail: String,
    private val senderPass: String,
    private val email: String,
    private val subject: String,
    private val message: String
) :
    AsyncTask<Void?, Void?, Void?>() {
    override fun doInBackground(vararg params: Void?): Void? {
        val properties = Properties()
        properties["mail.smtp.host"] = "smtp.gmail.com"
        properties["mail.smtp.socketFactory.port"] = "465"
        properties["mail.smtp.socketFactory.class"] = "javax.net.ssl.SSLSocketFactory"
        properties["mail.smtp.auth"] = "true"
        properties["mail.smtp.port"] = "465"
        val session = Session.getDefaultInstance(properties,
            object : Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(senderEmail, senderPass)
                }
            })
        try {
            val mm = MimeMessage(session)
            mm.setFrom(InternetAddress(senderEmail))
            mm.addRecipients(javax.mail.Message.RecipientType.TO, InternetAddress(email).toString())
            mm.subject = subject
            mm.setText(message)
            Transport.send(mm)
        } catch (e: MessagingException) {
            Log.d("SendMail", "doInBackground: " + e.message)
        }
        return null
    }
}


*/
/* but.setOnClickListener(View.OnClickListener {
     var userName = "emanufish@gmail.com"
     var password = "20elul163"
     var massage = "laila tov"
     var prop = Properties()
     prop.put("mail.smtp.auth", "true")
     prop.put("mail.smtp.starttls.enable", "true")
     prop.put("mail.smtp.host", "smtp.gmail.com")
     prop.put("mail.smtp.port", "587")


    // var authenticator = javax.mail.PasswordAuthentication
     val session : Session = Session.getInstance(prop,  object : Authenticator() {
          override fun getPasswordAuthentication(): PasswordAuthentication {
                      return PasswordAuthentication(userName, password)
     }

     try
     {
         val mm = MimeMessage()
         mm.setFrom(InternetAddress(userName))
     }
 });

 })
}*/

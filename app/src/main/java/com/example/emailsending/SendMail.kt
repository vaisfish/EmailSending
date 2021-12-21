package com.example.emailsending

import android.os.AsyncTask
import android.util.Log
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

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
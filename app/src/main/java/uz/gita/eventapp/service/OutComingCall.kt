package uz.gita.eventapp.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.speech.tts.TextToSpeech
import android.widget.Toast
import uz.gita.eventapp.app.App
import uz.gita.eventapp.data.prefs.MyPrefs
import java.util.*

class OutComingCall:BroadcastReceiver() {
    private lateinit var textToSpeech: TextToSpeech
    private val prefs = MyPrefs.getPrefs()
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent?.action.equals("android.provider.Telephony.SMS_RECEIVED")){
            if(prefs.allAnnouncements && prefs.smsStatus) {


                textToSpeech = TextToSpeech(App.instance) {

                    if (it == TextToSpeech.SUCCESS) {
                        val lang = textToSpeech.setLanguage(Locale.ENGLISH)
                        if (lang == TextToSpeech.LANG_MISSING_DATA || lang == TextToSpeech.LANG_NOT_SUPPORTED) {
                            Toast.makeText(
                                App.instance,
                                "Language not Supported",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                        textToSpeech.speak("SMS RECEIVED", TextToSpeech.QUEUE_FLUSH, null)
                    }
                }
            }
        }


    }
}
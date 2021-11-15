package uz.gita.eventapp.service

import android.database.ContentObserver
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.widget.Toast
import uz.gita.eventapp.app.App
import uz.gita.eventapp.data.prefs.MyPrefs
import java.util.*


class MyObserver(handler: Handler?) : ContentObserver(handler) {
    private lateinit var textToSpeech: TextToSpeech
    private val prefs = MyPrefs.getPrefs()
    override fun onChange(selfChange: Boolean) {
        super.onChange(selfChange)
        if (prefs.allAnnouncements && prefs.outgoingSMS && prefs.smsStatus) {


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
                    textToSpeech.speak("SMS OUTGOING", TextToSpeech.QUEUE_FLUSH, null)
                }
            }
        }


    }
}
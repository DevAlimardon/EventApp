package uz.gita.eventapp.service

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import android.speech.tts.TextToSpeech
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.widget.Toast
import uz.gita.eventapp.app.App
import uz.gita.eventapp.data.prefs.MyPrefs
import java.util.*

class Receiver : BroadcastReceiver() {
    private val prefs = MyPrefs.getPrefs()
    private lateinit var textToSpeech: TextToSpeech

    override fun onReceive(context: Context?, intent: Intent?) {
        var text =""
        textToSpeech = TextToSpeech(App.instance) {

            if (it == TextToSpeech.SUCCESS) {
                val lang = textToSpeech.setLanguage(Locale.ENGLISH)
                if (lang == TextToSpeech.LANG_MISSING_DATA || lang == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(App.instance, "Language not Supported", Toast.LENGTH_SHORT)
                        .show()
                }


                when (intent?.action) {
                    Intent.ACTION_BATTERY_LOW -> {

                        if (prefs.allAnnouncements && prefs.batteryLow && prefs.batterStatus) {

                            textToSpeech.speak("Battery low", TextToSpeech.QUEUE_FLUSH, null)

                        }
                    }
                    Intent.ACTION_POWER_DISCONNECTED -> {

                        if (prefs.allAnnouncements && prefs.powerDisConnect && prefs.batterStatus) {

                            textToSpeech.speak(
                                "Power disconnected",
                                TextToSpeech.QUEUE_FLUSH,
                                null
                            )
                        }
                    }
                    Intent.ACTION_POWER_CONNECTED -> {
                        if (prefs.allAnnouncements && prefs.powerConnect && prefs.batterStatus) {

                            textToSpeech.speak("Power connected", TextToSpeech.QUEUE_ADD, null)
                        }
                    }
                    Intent.ACTION_SCREEN_OFF -> {

                        if (prefs.allAnnouncements && prefs.screenOff && prefs.screenStatus) {

                            textToSpeech.speak("Screen off", TextToSpeech.QUEUE_FLUSH, null)
                        }
                    }
                    Intent.ACTION_SCREEN_ON -> {
                        if (prefs.allAnnouncements && prefs.screenOn && prefs.screenStatus) {

                            textToSpeech.speak("Screen on", TextToSpeech.QUEUE_FLUSH, null)
                        }
                    }
                    Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                        if(prefs.allAnnouncements && prefs.airMode ){
                            textToSpeech.speak("Airplane mode changed", TextToSpeech.QUEUE_FLUSH, null)
                        }
                    }


                }
            }
        }
    }

}
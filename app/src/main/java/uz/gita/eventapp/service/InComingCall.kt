package uz.gita.eventapp.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.speech.tts.TextToSpeech
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.widget.Toast
import uz.gita.eventapp.app.App
import uz.gita.eventapp.data.prefs.MyPrefs
import java.util.*


class InComingCall : BroadcastReceiver() {
    private lateinit var textToSpeech: TextToSpeech
    var telManager: TelephonyManager? = null
    private val prefs = MyPrefs.getPrefs()
    override fun onReceive(context: Context?, p1: Intent?) {
        telManager =
            context!!.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        telManager?.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE)

    }
    private val phoneListener:PhoneStateListener = object :PhoneStateListener(){
        override fun onCallStateChanged(state: Int, phoneNumber: String?) {
            super.onCallStateChanged(state, phoneNumber)
            try{
                when(state){
                    TelephonyManager.CALL_STATE_RINGING -> {
                       if(phoneNumber != null){
                            if(prefs.allAnnouncements && prefs.callStatus && prefs.incomingCall) {


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
                                        var startedCall = false // New added boolean



                                        textToSpeech.speak(
                                            "Incoming Call",
                                            TextToSpeech.QUEUE_FLUSH,
                                            null
                                        )
                                    }
                                }
                            }
                       }
                    }
                    TelephonyManager.CALL_STATE_OFFHOOK -> {

                        if(phoneNumber != null){
                            if(prefs.allAnnouncements && prefs.callStatus && prefs.outgoingCall) {


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
                                        var startedCall = false // New added boolean



                                        textToSpeech.speak(
                                            "Outgoing Call",
                                            TextToSpeech.QUEUE_FLUSH,
                                            null
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }



}
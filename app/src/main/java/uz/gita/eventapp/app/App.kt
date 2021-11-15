package uz.gita.eventapp.app

import android.app.Application
import android.speech.tts.TextToSpeech
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import uz.gita.eventapp.BuildConfig
import uz.gita.eventapp.data.prefs.MyPrefs

@HiltAndroidApp
class App:Application() {
    companion object{
       lateinit var instance:App
        private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        MyPrefs.init(this)
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}
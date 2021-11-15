package uz.gita.eventapp.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.os.IBinder
import android.speech.tts.TextToSpeech
import androidx.core.app.NotificationCompat
import uz.gita.eventapp.R
import uz.gita.eventapp.app.App
import uz.gita.eventapp.data.prefs.MyPrefs
import java.util.*
import android.content.ContentResolver
import android.net.Uri
import android.os.Handler


class MyService : Service() {
    override fun onBind(p0: Intent?): IBinder? = null
    private val CHANNEL_ID = "MY_MUSIC"
     private val receiver by lazy { Receiver() }
     private val incomingReceiver by lazy { InComingCall() }
     private val outgoingReceiver by lazy { OutComingCall() }

    private val notificationManager by lazy {
        this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }
    private val notification by lazy {
        NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_bell)
            .setSilent(true)
            .setContentTitle("Voice announcer")
            .setContentText("We are here to announce what event is happening")
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .build()
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Voice Announcer",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.setSound(null, null)
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(999, notification)
       val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED)
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        intentFilter.addAction(Intent.ACTION_BATTERY_LOW)
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF)
        intentFilter.addAction(Intent.ACTION_SCREEN_ON)
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)


        this.registerReceiver(receiver,intentFilter)
        this.registerReceiver(incomingReceiver,IntentFilter())
       this.registerReceiver(outgoingReceiver, IntentFilter())

        val myObserver = MyObserver(Handler())
        val contentResolver = this.applicationContext.contentResolver
        contentResolver.registerContentObserver(Uri.parse("content://sms/sent"), true, myObserver)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()
        createChannel()
    }

    override fun onDestroy() {
        super.onDestroy()
       stopForeground(true)
        unregisterReceiver(receiver)
        unregisterReceiver(incomingReceiver)
      unregisterReceiver(outgoingReceiver)

    }
}


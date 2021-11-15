package uz.gita.eventapp.data.prefs

import android.content.Context
import uz.gita.eventapp.app.App

class MyPrefs private constructor(context: Context) {

    companion object {
        private lateinit var instance: MyPrefs

        fun init(context: Context) {
            instance = MyPrefs(context)
        }

        fun getPrefs(): MyPrefs = instance
    }

    private val prefs = App.instance.getSharedPreferences("prefs", Context.MODE_PRIVATE)


    var isFirst:Boolean
        get() = prefs.getBoolean("isFirst", true)
        set(value) = prefs.edit().putBoolean("isFirst", value).apply()
    var batteryLow: Boolean
        get() = prefs.getBoolean("batteryLow", true)
        set(value) = prefs.edit().putBoolean("batteryLow", value).apply()
    var powerConnect: Boolean
        get() = prefs.getBoolean("powerConnect", true)
        set(value) = prefs.edit().putBoolean("powerConnect", value).apply()
    var powerDisConnect: Boolean
        get() = prefs.getBoolean("powerDisConnect", true)
        set(value) = prefs.edit().putBoolean("powerDisConnect", value).apply()
    var batterStatus: Boolean
        get() = prefs.getBoolean("batterStatus", true)
        set(value) = prefs.edit().putBoolean("batterStatus", value).apply()
    var screenStatus: Boolean
        get() = prefs.getBoolean("screenStatus", true)
        set(value) = prefs.edit().putBoolean("screenStatus", value).apply()
    var screenOff: Boolean
        get() = prefs.getBoolean("screenOff", true)
        set(value) = prefs.edit().putBoolean("screenOff", value).apply()
    var screenOn: Boolean
        get() = prefs.getBoolean("screenOn", true)
        set(value) = prefs.edit().putBoolean("screenOn", value).apply()
    var airMode: Boolean
        get() = prefs.getBoolean("airMode", true)
        set(value) = prefs.edit().putBoolean("airMode", value).apply()
    var allAnnouncements: Boolean
        get() = prefs.getBoolean("allAnnouncements", true)
        set(value) = prefs.edit().putBoolean("allAnnouncements", value).apply()
    var callStatus: Boolean
        get() = prefs.getBoolean("callStatus", true)
        set(value) = prefs.edit().putBoolean("callStatus", value).apply()
    var incomingCall:Boolean
        get() = prefs.getBoolean("incomingCall", true)
        set(value) = prefs.edit().putBoolean("incomingCall", value).apply()
    var outgoingCall:Boolean
        get() = prefs.getBoolean("outgoingCall", true)
        set(value) = prefs.edit().putBoolean("outgoingCall", value).apply()
    var callName: Boolean
        get() = prefs.getBoolean("callName", true)
        set(value) = prefs.edit().putBoolean("callName", value).apply()
    var callNumber: Boolean
        get() = prefs.getBoolean("callNumber", true)
        set(value) = prefs.edit().putBoolean("callNumber", value).apply()
    var callNameNumber: Boolean
        get() = prefs.getBoolean("callNameNumber", true)
        set(value) = prefs.edit().putBoolean("callNameNumber", value).apply()
    var smsStatus: Boolean
        get() = prefs.getBoolean("smsStatus", true)
        set(value) = prefs.edit().putBoolean("smsStatus", value).apply()
    var incomingSMS:Boolean
        get() = prefs.getBoolean("incomingSMS", true)
        set(value) = prefs.edit().putBoolean("incomingSMS", value).apply()
    var outgoingSMS:Boolean
        get() = prefs.getBoolean("outgoingSMS", true)
        set(value) = prefs.edit().putBoolean("outgoingSMS", value).apply()
    var smsName: Boolean
        get() = prefs.getBoolean("smsName", true)
        set(value) = prefs.edit().putBoolean("smsName", value).apply()
    var smsNumber: Boolean
        get() = prefs.getBoolean("smsNumber", true)
        set(value) = prefs.edit().putBoolean("smsNumber", value).apply()
    var smsNameNumber: Boolean
        get() = prefs.getBoolean("smsNameNumber", true)
        set(value) = prefs.edit().putBoolean("smsNameNumber", value).apply()

}
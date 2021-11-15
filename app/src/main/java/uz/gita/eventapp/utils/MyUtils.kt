package uz.gita.eventapp.utils

import android.view.View
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import uz.gita.eventapp.app.App

fun <T : ViewBinding> T.scope(block: T.()->(Unit)){
    block(this)
}

fun View.gone(){
    this.visibility = View.INVISIBLE
}
fun View.visible(){
    this.visibility = View.VISIBLE
}

fun toast(str:String){
    Toast.makeText(App.instance, str, Toast.LENGTH_SHORT).show()
}
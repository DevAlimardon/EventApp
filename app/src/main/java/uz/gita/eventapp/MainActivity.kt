package uz.gita.eventapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.eventapp.data.prefs.MyPrefs

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){
    private val prefs = MyPrefs.getPrefs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val graph =navHostFragment.navController.navInflater.inflate(R.navigation.app_graph)

        if(prefs.isFirst){
            graph.startDestination = R.id.introScreen
        }else{
            graph.startDestination = R.id.mainScreen
        }
        navHostFragment.navController.graph = graph
    }
}

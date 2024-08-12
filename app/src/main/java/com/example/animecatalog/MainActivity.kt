package com.example.animecatalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.animecatalog.app.DiProvider
import com.example.animecatalog.databinding.ActivityMainBinding
import com.example.animecatalog.navigation.Router
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        DiProvider.appComponent.inject(this)

        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = binding.navHostFragment.getFragment<NavHostFragment>()
        router.init(navController.navController)
    }


}
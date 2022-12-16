package uz.muhamadaziz.roomcodialapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import uz.muhamadaziz.roomcodialapp.databinding.ActivityMainBinding
import uz.muhamadaziz.roomcodialapp.databinding.ActivitySplashBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var handler: Handler
    private var backtoExit = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    }
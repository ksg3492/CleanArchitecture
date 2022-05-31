package com.sunggil.cleanarchitecture.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.sunggil.cleanarchitecture.BuildConfig
import com.sunggil.cleanarchitecture.databinding.ActivityMainBinding
import com.sunggil.cleanarchitecture.domain.ServiceValue
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val mainViewModel : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        ServiceValue.isDebug = BuildConfig.DEBUG

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        this.mainViewModel.loadSplash()
        this.mainViewModel.splashFinished.observe(this, Observer {
            it?.let {
                val message = if (it) "Succeed" else "Failed"
                Snackbar.make(binding.root, "Load $message!", Snackbar.LENGTH_SHORT).show()
            }
        })

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}
package com.sunggil.cleanarchitecture.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        this.mainViewModel.getVersion()
        this.mainViewModel.getCategory()

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}
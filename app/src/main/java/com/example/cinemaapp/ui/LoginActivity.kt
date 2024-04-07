package com.example.cinemaapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.cinemaapp.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener(this)
        binding.crearCuenta.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v!!.id){
            binding.login.id->{}
            binding.crearCuenta.id->{
                val intent = Intent(applicationContext, `Sing upActivity`::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
package com.example.cinemaapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.cinemaapp.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener(this)
        binding.crearCuenta.setOnClickListener(this)

        // Recuperar datos de SharedPreferences
        val sharedPref = getSharedPreferences("CinemaAppPrefs", MODE_PRIVATE)
        val nombreUsuario = sharedPref.getString("nombreUsuario", "")
        val contrasenaUsuario = sharedPref.getString("contrasenaUsuario", "")

        // Autocompletar los campos si existen datos
        if (!nombreUsuario.isNullOrEmpty()) {
            binding.mail.setText(nombreUsuario)
        }
        if (!contrasenaUsuario.isNullOrEmpty()) {
            binding.passwd.setText(contrasenaUsuario)
        }
    }
    
    override fun onClick(v: View?) {
        when(v!!.id){
            binding.login.id -> {
                val email = binding.mail.text.toString().trim()
                val password = binding.passwd.text.toString().trim()
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    // Aquí deberías agregar una validación más robusta, como comprobar con una base de datos.
                    val intent = Intent(this, MoviesActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Mostrar un mensaje de error al usuario, por ejemplo, usando un Toast
                    Toast.makeText(this, "Por favor, rellena todos los campos.", Toast.LENGTH_SHORT).show()
                }
            }
            binding.crearCuenta.id -> {
                // La lógica existente para ir a la pantalla de registro está bien
                val intent = Intent(this, SingupActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
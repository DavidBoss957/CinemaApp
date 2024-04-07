package com.example.cinemaapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cinemaapp.databinding.ActivitySingupBinding

class SingupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonRegistrar.setOnClickListener {
            registrarUsuario()
        }
    }

    private fun registrarUsuario() {
        val nombre = binding.editText.text.toString().trim()
        val contraseña = binding.contrasena.text.toString().trim()
        val confirmarContraseña = binding.confirmarContraseA.text.toString().trim()
        val correo = binding.correo.text.toString().trim()

        if (nombre.isEmpty()) {
            Toast.makeText(this, "Falta introducir el nombre", Toast.LENGTH_SHORT).show()
            return
        }

        if (contraseña.isEmpty()) {
            Toast.makeText(this, "Falta introducir la contraseña", Toast.LENGTH_SHORT).show()
            return
        }

        if (confirmarContraseña.isEmpty()) {
            Toast.makeText(this, "Falta confirmar la contraseña", Toast.LENGTH_SHORT).show()
            return
        }

        if (correo.isEmpty()) {
            Toast.makeText(this, "Falta introducir el correo", Toast.LENGTH_SHORT).show()
            return
        }

        if (contraseña != confirmarContraseña) {
            Toast.makeText(this, "La contraseña introducida no es correcta", Toast.LENGTH_SHORT).show()
            return
        }
    }
}
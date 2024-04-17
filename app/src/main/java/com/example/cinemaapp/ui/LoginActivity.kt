package com.example.cinemaapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.cinemaapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth // Referencia a FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar Firebase Auth
        auth = FirebaseAuth.getInstance()

        binding.login.setOnClickListener(this)
        binding.crearCuenta.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            binding.login.id -> {
                val email = binding.mail.text.toString().trim()
                val password = binding.passwd.text.toString().trim()

                if (email.isNotEmpty() && password.isNotEmpty()) {
                    // Intenta iniciar sesión con Firebase Authentication
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Inicio de sesión exitoso, actualiza la UI con la información del usuario
                                val intent = Intent(this, MoviesActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                // Si el inicio de sesión falla, muestra un mensaje al usuario
                                Toast.makeText(this, "Las credenciales son incorrectas.", Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
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

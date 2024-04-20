package com.example.cinemaapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cinemaapp.databinding.ActivitySingupBinding
import com.google.firebase.auth.FirebaseAuth

class SingupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingupBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Configurar listener para el botón de registro
        binding.botonRegistrar.setOnClickListener {
            val email = binding.correo.text.toString().trim()
            val password = binding.contrasena.text.toString().trim()
            val confirmPassword = binding.confirmarContraseA.text.toString().trim()

            when {
                email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() -> {
                    Toast.makeText(this, "Todos los campos son obligatorios.", Toast.LENGTH_SHORT).show()
                }
                password != confirmPassword -> {
                    Toast.makeText(this, "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    // Proceso de registro usando Firebase
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Registro exitoso, navegar al siguiente pantalla
                                val intent = Intent(this, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                // Si el registro falla, muestra un mensaje al usuario.
                                Toast.makeText(this, "Error de registro: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            goToMainActivity()
        }
    }

    private fun goToMainActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)

    }
}

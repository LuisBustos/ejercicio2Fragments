package com.example.dmc

import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.dmc.models.Usuario
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login_fragment.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    val users = arrayListOf<Usuario>(Usuario("Luis","123"),Usuario("Ignacio","456"),Usuario("Victor","789"))
    lateinit var fragmento1 : UsuarioCorrecto
    lateinit var fragmento2 : UsuarioIncorrecto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_fragment)
        fragmento1 = UsuarioCorrecto()
        fragmento2 = UsuarioIncorrecto()

        next_button.setOnClickListener {
            if (!isPasswordValid(password_edit_text.text!!)) {
                password_text_input.error = "Password demasiado corto."
            } else {
                password_text_input.error = null;
                val userLogin = Usuario(username.editText?.text.toString(),password_text_input.editText?.text.toString())

                if(users.contains(userLogin)){
                    Toast.makeText(baseContext,"Usuario correcto",Toast.LENGTH_LONG).show()
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.contenedor, fragmento1)
                        commit()
                    }
                } else {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.contenedor, fragmento2)
                        commit()
                    }
                }

            }
        }

        password_edit_text.addTextChangedListener {
            if (isPasswordValid(password_edit_text.text!!))
                password_text_input.error = null
        }
    }

    private fun isPasswordValid(text: Editable?): Boolean{
        return text != null && text.length >= 3;
    }
}
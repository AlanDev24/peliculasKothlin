package gonzalez.alan.peliculas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RegistroActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var et_correo:EditText
    lateinit var et_contraseña:EditText
    lateinit var et_contraseña2:EditText
    lateinit var btn_registrar:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        et_correo = findViewById(R.id.et_correoRegistro)
        et_contraseña = findViewById(R.id.et_contraseña)
        et_contraseña2 = findViewById(R.id.et_contraseña2)
        btn_registrar = findViewById(R.id.btn_registro)

        //inicializar auth firebase
        auth = Firebase.auth
        btn_registrar.setOnClickListener{
            var correo:String = et_correo.text.toString()
            var contraseña1:String = et_contraseña.text.toString()
            var contraseñq2:String = et_contraseña2.text.toString()

            if(!correo.isNullOrEmpty() && !contraseña1.isNullOrEmpty() && !contraseñq2.isNullOrEmpty()){

                if(contraseña1 == contraseñq2){
                    auth.createUserWithEmailAndPassword(correo, contraseña1)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("exito", "createUserWithEmail:success")
                                Toast.makeText(
                                    baseContext,
                                    "Se ha registado correctamente",
                                    Toast.LENGTH_SHORT,
                                ).show()
                                finish()
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("error", "createUserWithEmail:failure", task.exception)
                                Toast.makeText(
                                    baseContext,
                                    "No se pudo registrar al usuario",
                                    Toast.LENGTH_SHORT,
                                ).show()
                            }
                        }
                } else {
                    Toast.makeText(this, "Las contraseñ no son iguales", Toast.LENGTH_SHORT).show()

                }
            }
            else{
                Toast.makeText(this, "Ingresar datos de correo y contraseña", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
package io.github.charlesanjos.userssharedpref;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    EditText usuario;
    EditText senha;
    Button login;
    Button limpar;
    String nomeusuario;
    String senhausuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent helloActivityIntent = new Intent(this, HelloActivity.class);

        usuario = findViewById(R.id.usuario);
        senha = findViewById(R.id.senha);
        login = findViewById(R.id.login);
        limpar = findViewById(R.id.limpar);

        preferences = getSharedPreferences("historicoDeUsuarios",Context.MODE_PRIVATE);
        nomeusuario = preferences.getString("usuario",null);
        senhausuario = preferences.getString("senha",null);

        usuario.setText(nomeusuario);
        senha.setText(senhausuario);


        if(nomeusuario != null && senhausuario != null){
            helloActivityIntent.putExtra("usuario", nomeusuario);
            helloActivityIntent.putExtra("senha", senhausuario);
            startActivity(helloActivityIntent);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!usuario.getText().toString().equals("") && !senha.getText().toString().equals("")){
                    preferences = getSharedPreferences("historicoDeUsuarios",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("usuario",usuario.getText().toString());
                    editor.putString("senha",senha.getText().toString());
                    editor.apply();
                    helloActivityIntent.putExtra("usuario",
                            usuario.getText().toString());
                    helloActivityIntent.putExtra("senha",
                            senha.getText().toString());
                    startActivity(helloActivityIntent);
                }else{
                    Toast.makeText(MainActivity.this,"Digite seus dados!",
                            Toast.LENGTH_SHORT).show();
                    if(usuario.getText().toString().equals("")){
                        usuario.setError("Digite seu usuario");
                    }
                    if(senha.getText().toString().equals("")){
                        senha.setError("Digite sua senha");
                    }
                }
            }
        });


        limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences = getSharedPreferences("historicoDeUsuarios",Context.MODE_PRIVATE);
                preferences.edit().clear().apply();
                usuario.setText("");
                senha.setText("");
            }
        });
    }
}
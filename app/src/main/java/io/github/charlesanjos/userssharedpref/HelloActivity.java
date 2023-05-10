package io.github.charlesanjos.userssharedpref;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class HelloActivity extends AppCompatActivity {

    TextView dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        String usuario = getIntent().getStringExtra("usuario");
        String senha = getIntent().getStringExtra("senha");

        dados = findViewById(R.id.dados);
        dados.append("Olá, " + usuario + "\n");
        dados.append("Sua senha é " + senha + "\n");
    }
}
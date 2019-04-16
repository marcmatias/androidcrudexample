package com.example.estudandolistview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class CadastrarUsuarioActivity extends AppCompatActivity {

    private EditText edtNome, edtIdade, edtCpf;
    private Button btnCadastrar;
    public static final int RESULT_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        edtNome = findViewById(R.id.edtNome);
        edtIdade = findViewById(R.id.edtIdade);
        edtCpf = findViewById(R.id.edtCpf);
        getSupportActionBar().setTitle("Usuarios");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menuItemCadastrar:
                String nome = edtNome.getText().toString();
                int idade = Integer.parseInt(edtIdade.getText().toString());
                String cpf = edtCpf.getText().toString();

                Usuario p = new Usuario(nome, idade, cpf);

                Intent i = new Intent();
                i.putExtra("pessoa", p);

                setResult(RESULT_CODE, i);

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.android_menu_operacoes_create, menu);
        return super.onCreateOptionsMenu(menu);
    }

}

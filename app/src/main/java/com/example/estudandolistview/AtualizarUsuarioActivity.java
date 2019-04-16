package com.example.estudandolistview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AtualizarUsuarioActivity extends AppCompatActivity {

    private EditText edtNome, edtIdade, edtCpf;
    private Button btnEditar, btnDeletar;
    public static final int RESULT_CODE = 2;

    Intent intent = getIntent();

    public String getposition(){
        String position = intent.getStringExtra("position");
        return position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_usuario);

        edtNome = findViewById(R.id.edtNome);
        edtIdade = findViewById(R.id.edtIdade);
        edtCpf = findViewById(R.id.edtCpf);

        Intent intent = getIntent();

        if(!intent.getStringExtra("nome").isEmpty())
        {
            edtNome.setText(intent.getStringExtra("nome"));
        }
        if(!intent.getStringExtra("idade").isEmpty())
        {
            edtIdade.setText(intent.getStringExtra("idade"));
        }
        if(!intent.getStringExtra("cpf").isEmpty())
        {
            edtCpf.setText(intent.getStringExtra("cpf"));
        }
        getSupportActionBar().setTitle("Usuarios");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent i = new Intent();
        Intent intent = getIntent();
        switch (id){
            case R.id.menuItemDeletar:
                i.putExtra("position", intent.getStringExtra("position"));
                setResult(RESULT_CODE, i);
                finish();
                break;
            case R.id.menuItemEditar:
                String nome = edtNome.getText().toString();
                int idade = Integer.parseInt(edtIdade.getText().toString());
                String cpf = edtCpf.getText().toString();
                Usuario p = new Usuario(nome, idade, cpf);
                i.putExtra("pessoa", p);
                i.putExtra("position", intent.getStringExtra("position"));
                setResult(RESULT_CODE, i);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.android_menu_operacoes_update, menu);
        return super.onCreateOptionsMenu(menu);
    }

}

package com.example.estudandolistview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

public class AtualizarPessoaActivity extends AppCompatActivity {

    private EditText edtNome, edtIdade, edtCpf;
    private Button btnEditar, btnDeletar;
    public static final int RESULT_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_pessoa);

        edtNome = findViewById(R.id.edtNome);
        edtIdade = findViewById(R.id.edtIdade);
        edtCpf = findViewById(R.id.edtCpf);
        btnEditar = findViewById(R.id.btnEditar);
        btnDeletar = findViewById(R.id.btnDeletar);

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

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edtNome.getText().toString();
                int idade = Integer.parseInt(edtIdade.getText().toString());
                String cpf = edtCpf.getText().toString();

                Pessoa p = new Pessoa(nome, idade, cpf);

                Intent i = new Intent();
                i.putExtra("pessoa", p);

                Intent intent = getIntent();
                i.putExtra("position", intent.getStringExtra("position"));

                setResult(RESULT_CODE, i);

                finish();

            }
        });
        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                Intent intent = getIntent();
                i.putExtra("position", intent.getStringExtra("position"));

                setResult(RESULT_CODE, i);

                finish();

            }
        });

    }

}

package com.example.estudandolistview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastrarPessoaActivity extends AppCompatActivity {

    private EditText edtNome, edtIdade, edtCpf;
    private Button btnCadastrar;
    public static final int RESULT_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_pessoa);

        edtNome = findViewById(R.id.edtNome);
        edtIdade = findViewById(R.id.edtIdade);
        edtCpf = findViewById(R.id.edtCpf);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edtNome.getText().toString();
                int idade = Integer.parseInt(edtIdade.getText().toString());
                String cpf = edtCpf.getText().toString();

                Pessoa p = new Pessoa(nome, idade, cpf);

                Intent i = new Intent();
                i.putExtra("pessoa", p);

                setResult(RESULT_CODE, i);

                finish();

            }
        });

    }

}

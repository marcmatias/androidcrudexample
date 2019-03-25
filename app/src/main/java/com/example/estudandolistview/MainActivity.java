package com.example.estudandolistview;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listviewPessoas;
    private FloatingActionButton fabCadastrar;
    private List<Pessoa> pessoas;
    private ArrayAdapter<Pessoa> arrayAdapter;

    public static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pessoas = new ArrayList<>();
        pessoas.add(new Pessoa("A", 30, "02145887"));
        pessoas.add(new Pessoa("B", 40, "989898989"));
        pessoas.add(new Pessoa("C", 60, "878787878"));

        listviewPessoas = findViewById(R.id.listviewPessoas);
        fabCadastrar = findViewById(R.id.fabCadastrar);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pessoas);

        listviewPessoas.setAdapter(arrayAdapter);

        fabCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastrarPessoaActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        listviewPessoas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pessoa p = pessoas.get(position);
                Intent intent = new Intent(MainActivity.this, AtualizarPessoaActivity.class);
                intent.putExtra("nome", p.getNome());
                intent.putExtra("idade", Integer.toString(p.getIdade()));
                intent.putExtra("cpf", p.getCpf());
                intent.putExtra("position", Integer.toString(position));
                startActivityForResult(intent, REQUEST_CODE);
            }

//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Pessoa p = pessoas.get(position);
//                Toast.makeText(MainActivity.this, p.toString() + "\nFoi removido", Toast.LENGTH_LONG).show();
//                pessoas.remove(p);
//                arrayAdapter.notifyDataSetChanged();
//            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE){
            if(resultCode == CadastrarPessoaActivity.RESULT_CODE){
                Pessoa p = (Pessoa) data.getSerializableExtra("pessoa");
                pessoas.add(p);
                arrayAdapter = null;
                arrayAdapter =  new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pessoas);
                listviewPessoas.setAdapter(arrayAdapter);
            }
            if(resultCode == AtualizarPessoaActivity.RESULT_CODE){
                try {
                    Pessoa p = (Pessoa) data.getSerializableExtra("pessoa");
                    String position = (String) data.getSerializableExtra("position");
                    pessoas.set(Integer.parseInt(position), p);
                    Toast.makeText(MainActivity.this, p.toString(), Toast.LENGTH_LONG).show();
                }catch (RuntimeException e){
                    String position = (String) data.getSerializableExtra("position");
                    pessoas.remove(Integer.parseInt(position));
                    arrayAdapter = null;
                    arrayAdapter =  new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pessoas);
                    listviewPessoas.setAdapter(arrayAdapter);
                }
            }
        }
    }
}

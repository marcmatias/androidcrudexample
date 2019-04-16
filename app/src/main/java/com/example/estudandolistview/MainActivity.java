package com.example.estudandolistview;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listviewUsuarios;
    private List<Usuario> usuarios;
    private ArrayAdapter<Usuario> arrayAdapter;

    public static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuarios = new ArrayList<>();
        usuarios.add(new Usuario("Marcel", 30, "02145887"));
        usuarios.add(new Usuario("Rodrigo", 40, "989898989"));
        usuarios.add(new Usuario("Evangil", 60, "878787878"));

        listviewUsuarios = findViewById(R.id.listviewPessoas);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, usuarios);

        listviewUsuarios.setAdapter(arrayAdapter);

        listviewUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Usuario p = usuarios.get(position);
                Intent intent = new Intent(MainActivity.this, AtualizarUsuarioActivity.class);
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

        getSupportActionBar().setTitle("Usuarios");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menuItemCadastrar:
                Intent i =  new Intent(MainActivity.this, CadastrarUsuarioActivity.class);
                startActivityForResult(i, REQUEST_CODE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.android_menu_operacoes, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE){
            if(resultCode == CadastrarUsuarioActivity.RESULT_CODE){
                Usuario p = (Usuario) data.getSerializableExtra("pessoa");
                usuarios.add(p);
                arrayAdapter = null;
                arrayAdapter =  new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, usuarios);
                listviewUsuarios.setAdapter(arrayAdapter);
            }
            if(resultCode == AtualizarUsuarioActivity.RESULT_CODE){
                try {
                    Usuario p = (Usuario) data.getSerializableExtra("pessoa");
                    String position = (String) data.getSerializableExtra("position");
                    usuarios.set(Integer.parseInt(position), p);
                    Toast.makeText(MainActivity.this, p.toString(), Toast.LENGTH_LONG).show();
                }catch (RuntimeException e){
                    String position = (String) data.getSerializableExtra("position");
                    usuarios.remove(Integer.parseInt(position));
                    arrayAdapter = null;
                    arrayAdapter =  new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, usuarios);
                    listviewUsuarios.setAdapter(arrayAdapter);
                }
            }
        }
    }
}

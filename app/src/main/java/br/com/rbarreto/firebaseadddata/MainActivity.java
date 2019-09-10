package br.com.rbarreto.firebaseadddata;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.rbarreto.firebaseadddata.adapter.MeuAdapater;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private List<String> lista = new ArrayList<String>();
    private ArrayAdapter<String> arrayAdapter;
    private ListView listView;
    private EditText editTextTarefa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        editTextTarefa = findViewById(R.id.editTextTarefa);
        conectarBanco();
        eventoBanco();

    }

    public void eventoBanco(){
        databaseReference.child("Tarefa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                lista.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String dado = snapshot.child("info").getValue(String.class);
                    if (dado.equals("dados")){

                    }
                    else {
                        lista.add(dado);
                    }
                }

                arrayAdapter = new MeuAdapater(MainActivity.this, (ArrayList<String>) lista);
                listView.setAdapter(arrayAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.read){
            Intent intent = new Intent(this, LeituraActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    public void conectarBanco(){
        FirebaseApp.initializeApp(MainActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void salvarTarefa(View view){
        String info = editTextTarefa.getText().toString();
        databaseReference.child("Tarefa").child(UUID.randomUUID().toString()).child("info").setValue(info);
    }
}

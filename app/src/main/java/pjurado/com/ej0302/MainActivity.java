package pjurado.com.ej0302;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Contacto> directorio = new ArrayList<>();
    private RecyclerView myRecyclerView;
    private AdapterDirectorio myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        creaDatos();

        myRecyclerView = findViewById(R.id.recyclerView);
        myRecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(layoutManager);

        myAdapter = new AdapterDirectorio(directorio);

        myRecyclerView.setAdapter(myAdapter);

    }

    private void creaDatos(){
        directorio.add(new Contacto("Pedro Jurado", "987123456", "pjurado@gmail.com", R.drawable.foto));
        directorio.add(new Contacto("Pepe Pérez", "987121256", "ppe@gmail.com", R.drawable.foto));
        directorio.add(new Contacto("Antonio Gómex", "934643456", "aoox@gmail.com", R.drawable.foto));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Contacto persona = data.getParcelableExtra("persona");
            Integer i = data.getExtras().getInt("posicion");
            directorio.get(i).setNombre(persona.getNombre());
            directorio.get(i).setTelefono(persona.getTelefono());
            directorio.get(i).setEmail(persona.getEmail());
            myAdapter.notifyDataSetChanged();
        }
    }
}
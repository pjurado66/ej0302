package pjurado.com.ej0302;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class IntentActivity extends AppCompatActivity {
    private Contacto persona;
    private Button btnGuardar;
    private Button btnCancelar;
    private ImageView ivFoto;
    private EditText etNombre;
    private EditText etTelefono;
    private EditText etEmail;
    private Integer pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        btnGuardar = findViewById(R.id.buttonGuardar);
        btnCancelar = findViewById(R.id.buttonCancelar);
        etNombre = findViewById(R.id.editTextModificaNombre);
        etEmail = findViewById(R.id.editTextTextModificarEmail);
        etTelefono = findViewById(R.id.editTextModificaTelefono);
        ivFoto = findViewById(R.id.imageViewModificaFoto);

        persona = getIntent().getParcelableExtra("persona");
        pos = getIntent().getExtras().getInt("posicion");


        etNombre.setText(persona.getNombre());
        etEmail.setText(persona.getEmail());
        etTelefono.setText(persona.getTelefono());
        ivFoto.setImageResource(persona.getFoto());

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                persona.setNombre(etNombre.getText().toString());
                persona.setEmail(etEmail.getText().toString());
                persona.setTelefono(etTelefono.getText().toString());
                i.putExtra("persona", persona);
                i.putExtra("posicion", pos);

                setResult(RESULT_OK, i);
                finish();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

    }


}
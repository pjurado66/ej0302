package pjurado.com.ej0302;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class AdapterDirectorio extends RecyclerView.Adapter<AdapterDirectorio.MyViewHolder>  {

    private ArrayList<Contacto> directorio;
    public AdapterDirectorio(ArrayList directorio){
        this.directorio = directorio;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent,  false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final MyViewHolder h = holder;
        holder.etNombre.setText(directorio.get(position).getNombre());

        holder.etTelefono.setText(directorio.get(position).getTelefono());
        holder.etEmail.setText(directorio.get(position).getEmail());
        holder.ivFoto.setImageResource(directorio.get(position).getFoto());

        holder.ivMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llama(v, directorio.get(position).getEmail());
            }
        });

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), IntentActivity.class);
                i.putExtra("persona", directorio.get(position));
                i.putExtra("posicion", position);
                ((Activity) v.getContext()).startActivityForResult(i, 1);
            }
        });

        holder.ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhoneNumber(v, directorio.get(position).getTelefono());
            }
        });
    }

    @Override
    public int getItemCount() {
        return directorio.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public TextView etNombre;
        public TextView etTelefono;
        public TextView etEmail;
        public ImageView ivFoto;
        public ImageView ivCall;
        public ImageView ivMail;
        public ImageView ivEdit;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            etNombre = mView.findViewById(R.id.textViewNombre);
            etTelefono = mView.findViewById(R.id.textViewTelefono);
            etEmail = mView.findViewById(R.id.textViewEmail);
            ivFoto = mView.findViewById(R.id.imageView);
            ivMail = mView.findViewById(R.id.imageViewMail);
            ivCall = mView.findViewById(R.id.imageViewCall);
            ivEdit = mView.findViewById(R.id.imageViewEdit);
        }
    }
    public void llama(View v, String email){
        String[] destinatario= {""};
        destinatario[0] = email;
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_EMAIL, destinatario);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "textMessage");
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Asunto de email de prueba");

        sendIntent.setType("message/rfc822");

        if (sendIntent.resolveActivity(v.getContext().getPackageManager()) != null) {
            v.getContext().startActivity(sendIntent);
        }
    }

    public void dialPhoneNumber(View v, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(v.getContext().getPackageManager()) != null) {
            v.getContext().startActivity(intent);
        }
    }
}

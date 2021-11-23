package pjurado.com.ej0302;

import android.os.Parcel;
import android.os.Parcelable;

public class Contacto implements Parcelable {
    private String Nombre;
    private String Telefono;
    private String email;
    private int foto;

    protected Contacto(Parcel in) {
        Nombre = in.readString();
        Telefono = in.readString();
        email = in.readString();
        foto = in.readInt();
    }

    public static final Creator<Contacto> CREATOR = new Creator<Contacto>() {
        @Override
        public Contacto createFromParcel(Parcel in) {
            return new Contacto(in);
        }

        @Override
        public Contacto[] newArray(int size) {
            return new Contacto[size];
        }
    };

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public Contacto(String nombre, String telefono, String email, int foto) {
        Nombre = nombre;
        Telefono = telefono;
        this.email = email;
        this.foto = foto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(Nombre);
        dest.writeString(Telefono);
        dest.writeString(email);
        dest.writeInt(foto);

    }
}

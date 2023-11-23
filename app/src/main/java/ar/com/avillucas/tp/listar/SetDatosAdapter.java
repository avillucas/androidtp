package ar.com.avillucas.tp.listar;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.List;
import java.util.Locale;

import ar.com.avillucas.tp.R;
import ar.com.avillucas.tp.entidades.SetDatos;

public class SetDatosAdapter extends RecyclerView.Adapter<SetDatosViewHolder> {


        private final RecyclerViewInterface recyclerViewInterface;
        private final List<SetDatos> datos;

    public SetDatosAdapter(List<SetDatos> datos, RecyclerViewInterface recyclerViewInterface) {
            this.datos = datos;
            this.recyclerViewInterface = recyclerViewInterface;
        }

        @NonNull
        @Override
        public SetDatosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.dato, parent, false);
            return new SetDatosViewHolder(vista);
        }


        @SuppressLint({"DefaultLocale", "SetTextI18n"})
        @Override
        public void onBindViewHolder(@NonNull SetDatosViewHolder holder, int position) {
            SetDatos dato = this.datos.get(position);
            TextView fecha = holder.itemView.findViewById(R.id.lblDatoFecha);
            TextView valor = holder.itemView.findViewById(R.id.lblDatoValor);
            DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());
            fecha.setText( dateFormat.format(dato.getFecha()));
            valor.setText(String.format("%.4f ", dato.getValor())+"%");
        }

        @Override
        public int getItemCount() {
            return this.datos.size();
        }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<SetDatos> nuevosDatos) {
        this.datos.clear();
        this.datos.addAll(nuevosDatos);
        this.notifyDataSetChanged();
    }

}

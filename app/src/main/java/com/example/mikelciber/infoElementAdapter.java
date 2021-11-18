package com.example.mikelciber;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class infoElementAdapter extends RecyclerView.Adapter<infoElementAdapter.productosHolder>{
    List<infoElements> productos;

    public infoElementAdapter(List<infoElements> productos) {
        this.productos = productos;
    }

    @NonNull
    @Override
    public productosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.reciclamientoreciclado,parent, false);
        productosHolder holder = new productosHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull productosHolder holder, int position) {
        infoElements infoelements = productos.get(position);
        holder.holderNombre.setText(infoelements.getFBNombre());
        holder.holdercb.setText(infoelements.getFBCodigoBarras());
        holder.holderPrecioUnitario.setText(infoelements.getFBPrecioUnitario());
        holder.holderUniddes.setText(infoelements.getFBUnidades());
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public static class productosHolder extends RecyclerView.ViewHolder{
        TextView holderNombre, holdercb, holderPrecioUnitario, holderUniddes, holderNumeral;

        public productosHolder(@NonNull View itemView) {
            super(itemView);
            holderNombre = (TextView)itemView.findViewById(R.id.nombreproducto);
            holdercb = (TextView) itemView.findViewById(R.id.codigodebarras);
            holderPrecioUnitario=(TextView) itemView.findViewById(R.id.preciounitario);
            holderUniddes=(TextView) itemView.findViewById(R.id.unidades);
        }
    }
}

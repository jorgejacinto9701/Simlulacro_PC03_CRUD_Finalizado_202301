package com.example.semana10_simulacro_pc03.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.semana10_simulacro_pc03.R;
import com.example.semana10_simulacro_pc03.entity.Cliente;

import java.util.List;

public class ClienteCrudAdapter extends ArrayAdapter<Cliente> {

    private Context context;
    private List<Cliente> lista;

    public ClienteCrudAdapter(@NonNull Context context, int resource, @NonNull List<Cliente> lista) {
        super(context, resource, lista);
        this.context = context;
        this.lista = lista;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.activity_cliente_crud_item, parent, false);

        TextView txtId =row.findViewById(R.id.txtCrudClienteItemId);
        TextView txtNombre = row.findViewById(R.id.txtCrudClienteItemNombre);
        TextView txtDNI = row.findViewById(R.id.txtCrudClienteItemDNI);
        TextView txtEstado = row.findViewById(R.id.txtCrudClienteItemEstado);
        TextView txtCategoria = row.findViewById(R.id.txtCrudClienteItemCategoria);

        Cliente obj = lista.get(position);
        txtId.setText(String.valueOf(obj.getIdCliente()));
        txtNombre.setText(obj.getNombre());
        txtDNI.setText(obj.getDni());
        txtEstado.setText(String.valueOf(obj.getEstado()));
        txtCategoria.setText(obj.getCategoria().getDescripcion());

        return row;
    }
}

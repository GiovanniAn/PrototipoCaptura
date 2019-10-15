package balam.app.protocaptura.Adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import balam.app.protocaptura.R;
import balam.app.protocaptura.activity.DiaryActivity;


public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {


    static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView departamento, porcentaje, operador;
        Button concluir, empezarToma;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            departamento = itemView.findViewById(R.id.texDepartamento);
            porcentaje = itemView.findViewById(R.id.txtPorcentaje);
            operador = itemView.findViewById(R.id.tv_operador);
            concluir = itemView.findViewById(R.id.btn_concluir_toma);
            empezarToma = itemView.findViewById(R.id.btn_iniciar_toma);

        }
    }

    List<DiaryActivity.Inventario> inventarios;

    public RVAdapter(List<DiaryActivity.Inventario> inventarios) {
        this.inventarios = inventarios;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PersonViewHolder pvh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_calendario, parent, false);
        pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final PersonViewHolder holder, int position) {
        holder.departamento.setText(inventarios.get(position).departamento);
        holder.porcentaje.setText(inventarios.get(position).porcentaje);
        holder.operador.setText(inventarios.get(position).operador);
        holder.concluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.porcentaje.setText("%100");
                holder.porcentaje.setTextColor(Color.GREEN);
            }
        });

        holder.empezarToma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return inventarios.size();
    }


}

package com.android.listadetarefas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.listadetarefas.R
import com.android.listadetarefas.adapter.TarefaAdapter.MyViewHolder
import com.android.listadetarefas.model.Tarefa


class TarefaAdapter(val tarefas : List<Tarefa>) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.lista_tarefa_adapter, parent, false);

        return  MyViewHolder(view);
    }

    override fun getItemCount(): Int {
       return tarefas.size;
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val tarefa : Tarefa = tarefas[position];
        holder.descricao.text = tarefa.descricao;

    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val descricao : TextView = itemView.findViewById(R.id.tvDescricao);
    }

}
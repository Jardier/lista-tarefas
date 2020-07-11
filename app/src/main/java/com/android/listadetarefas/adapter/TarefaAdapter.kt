package com.android.listadetarefas.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.listadetarefas.adapter.TarefaAdapter.MyViewHolder
import com.android.listadetarefas.model.Tarefa


class TarefaAdapter(val tarefas : List<Tarefa>) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
       return tarefas.size;
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}
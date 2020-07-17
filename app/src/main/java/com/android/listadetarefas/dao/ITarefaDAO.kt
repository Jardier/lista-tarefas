package com.android.listadetarefas.dao

import com.android.listadetarefas.model.Tarefa

interface ITarefaDAO {

    public fun salvar(tarefa: Tarefa) : Boolean;
    public fun atualizar(tarefa: Tarefa) : Boolean;
    public fun deletar(tarefa: Tarefa) : Boolean;
    public fun listar() : List<Tarefa>;

}
package com.android.listadetarefas.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.android.listadetarefas.helper.DBHelper
import com.android.listadetarefas.model.Tarefa

class TarefaDAO(context: Context)  : ITarefaDAO {

    lateinit var escreve : SQLiteDatabase;
    lateinit var le : SQLiteDatabase;

    init {
        val dbHelper = DBHelper(context);
        escreve = dbHelper.writableDatabase;
        le = dbHelper.readableDatabase;
    }

    override fun salvar(tarefa: Tarefa): Boolean {
        val cv = ContentValues();
        cv.put("descicao", tarefa.descricao);

        try {
            escreve.insert(DBHelper.TABELA_TAREFAS, null, cv);
            Log.i("INFO", "Tarefa:  ${tarefa.descricao} salva com sucesso");
        } catch (e : Exception) {
            Log.i("INFO", "Ocorreu um erro ao salvar a tarefa: ${tarefa.descricao}")
        }
        escreve.insert(DBHelper.TABELA_TAREFAS, null, cv);

        return true;
    }

    override fun atualizar(tarefa: Tarefa): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deletar(tarefa: Tarefa): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun listar(): List<Tarefa> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
package com.android.listadetarefas.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
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
        cv.put("descricao", tarefa.descricao);

        try {
            escreve.insert(DBHelper.TABELA_TAREFAS, null, cv);
            Log.i("INFO", "Tarefa:  ${tarefa.descricao} salva com sucesso.");

        } catch (e : Exception) {
            Log.i("INFO", "Ocorreu um erro ao salvar a tarefa: ${tarefa.descricao}");
            return false;
        }
        return true;
    }

    override fun atualizar(tarefa: Tarefa): Boolean {
        val cv = ContentValues();
        cv.put("descricao", tarefa.descricao);
        var args = arrayListOf<String>(tarefa.id.toString());

        try {
            escreve.update(DBHelper.TABELA_TAREFAS, cv, "id=?", args.toTypedArray() )
            Log.i("INFO", "Tarefa: ${tarefa.descricao} atulizada com sucesso.")

        } catch (e : java.lang.Exception) {
            Log.i("INFO", "Ocorreu um erro ao atualizar a tarefa: ${tarefa.descricao}.")
            return false;
        }
        return true;
    }

    override fun deletar(tarefa: Tarefa): Boolean {
       var args = arrayListOf<String>(tarefa.id.toString());

        try {
            escreve.delete(DBHelper.TABELA_TAREFAS,"id=?", args.toTypedArray());
            Log.i("INFO", "Tarefa: ${tarefa.descricao} excluida com sucesso.")
        } catch (e: Exception) {
            Log.i("INFO", "Ocorreu um erro ao atualizar a tarefa: ${tarefa.descricao}.");
            return false;
        }
        return true;
    }

    override fun listar(): ArrayList<Tarefa> {
        val tarefas : ArrayList<Tarefa> = ArrayList();

        val sql : String = "SELECT * FROM "
            .plus(DBHelper.TABELA_TAREFAS)
            .plus(";");

        //Recuperando as tarefas
        val cursor : Cursor = le.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndex("id"));
            val descricao = cursor.getString(cursor.getColumnIndex("descricao"))
            val tarefa = Tarefa(id,descricao);

            tarefas.add(tarefa);
        }

        return tarefas;
    }
}
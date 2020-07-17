package com.android.listadetarefas.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.lang.Exception


class DBHelper : SQLiteOpenHelper{

    constructor(context: Context) : super(context, NOME_DB, null, VERSION) {
    }
    companion object {
        const val VERSION : Int = 1;
        const val NOME_DB: String = "DB_TAREFAS";
        const val TABELA_TAREFAS = "tarefas";
    }

    override fun onCreate(db: SQLiteDatabase?) {
       var sql : String = "CREATE TABLE IF NOT EXISTS ${TABELA_TAREFAS} "
                   .plus( "( id INTEGER PRIMARY KEY AUTOINCREMENT,")
                   .plus(" descricao TEXT NOT NULL );");
        try {
           db!!.execSQL(sql);
            Log.i("INFO DB", "Tabela ${TABELA_TAREFAS} criada com sucesso.")
        } catch (e : Exception) {
            Log.i("INFO DB", "Ocorreu um erro ao criar a tabela ${TABELA_TAREFAS} - ${e.message}");
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
package com.android.listadetarefas.model


import java.io.Serializable
import kotlin.properties.Delegates

class Tarefa : Serializable {
    lateinit var descricao : String;
    var id by Delegates.notNull<Long>();

    constructor(descricao : String) {
        this.descricao = descricao;
    }
    constructor(id : Long, descricao: String) : this(descricao){
        this.id = id;
    }


}
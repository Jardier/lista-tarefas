package com.android.listadetarefas.activity.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.android.listadetarefas.R
import com.android.listadetarefas.dao.TarefaDAO
import com.android.listadetarefas.model.Tarefa
import com.google.android.material.textfield.TextInputEditText

class AdicionarTarefaActivity : AppCompatActivity() {

    lateinit var tarefaDAO: TarefaDAO;
    lateinit var tieDescTarefa : TextInputEditText;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_tarefa)

        //inicializando variáveis
        this.tieDescTarefa = findViewById(R.id.tieDescTarefa);
        this.tarefaDAO = TarefaDAO(this);
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_adicionar_tarefa, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.itemSalvar -> {
                val tarefa = Tarefa(this.tieDescTarefa.text.toString());

                if(tarefa != null && !tarefa.descricao.equals("")){
                    tarefaDAO.salvar(tarefa);
                    finish();
                } else {
                    Toast.makeText(this, "Favor preenche o nome da tarefa",Toast.LENGTH_LONG).show();
                }

            }
        }

        return super.onOptionsItemSelected(item)
    }
}

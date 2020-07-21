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

    lateinit var tarefaDAO: TarefaDAO
    lateinit var txtDescricaoTarefa : TextInputEditText
    lateinit var tarefaAtual : Tarefa;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_tarefa)

       //inicializando variáveis
        this.txtDescricaoTarefa = findViewById(R.id.tieDescTarefa)
        this.tarefaDAO = TarefaDAO(this);

        //Caso seja edição, recebemos os dados, do contrário uma Tarefa vazia
        tarefaAtual = intent.getSerializableExtra("TAREFA") as Tarefa;

        if(!tarefaAtual.descricao.trim().equals("")){
            txtDescricaoTarefa.setText(tarefaAtual.descricao);
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_adicionar_tarefa, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.itemSalvar -> {
                val nomeTarefa : String = txtDescricaoTarefa.text.toString();

                if(nomeTarefa.trim().equals("")) {
                    Toast.makeText(applicationContext, "Favor preencher a tarefa", Toast.LENGTH_LONG).show();
                    return false;
                }
                if(this.tarefaAtual.id == null) {//salvar
                   if(tarefaDAO.salvar(Tarefa(null, nomeTarefa))){
                       finish();
                       Toast.makeText(applicationContext, "Tarefa salva com sucesso.", Toast.LENGTH_LONG).show();
                   } else {
                       Toast.makeText(applicationContext, "Ocorreu um erro ao salvar a tarefa.", Toast.LENGTH_LONG).show();
                   }
                }else {//edição
                   if(tarefaDAO.atualizar(Tarefa(tarefaAtual.id, nomeTarefa))) {
                       finish();
                       Toast.makeText(applicationContext, "Tarefa atualizada com sucesso.", Toast.LENGTH_LONG).show();
                   } else {
                       Toast.makeText(applicationContext, "Ocorreu um erro ao atualizar a tarefa.", Toast.LENGTH_LONG).show();
                   }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

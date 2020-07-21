package com.android.listadetarefas.activity.activity



import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast

import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.listadetarefas.R
import com.android.listadetarefas.adapter.TarefaAdapter
import com.android.listadetarefas.dao.TarefaDAO

import com.android.listadetarefas.listener.RecyclerItemClickListener
import com.android.listadetarefas.model.Tarefa

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView;
    lateinit var tarefaDAO : TarefaDAO;
    lateinit var tarefaSelecionada : Tarefa;
    lateinit var tarefas : ArrayList<Tarefa>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //inicializando variáveis
        recyclerView = findViewById(R.id.recyclerView);
        tarefaDAO = TarefaDAO(this);
        tarefas = ArrayList<Tarefa>();

        tarefaSelecionada = Tarefa();

        //Evento de click
        fab.setOnClickListener { view ->
            val intent = Intent(applicationContext, AdicionarTarefaActivity::class.java);
            intent.putExtra("TAREFA", tarefaSelecionada);
            startActivity(intent);
        }

        //Evento de Click no RecycleView
        recyclerView.addOnItemTouchListener(RecyclerItemClickListener(applicationContext, recyclerView, object : RecyclerItemClickListener.OnItemClickListener{

            //O clinck usado para alterar
            override fun onItemClick(view: View, position: Int) {
                val tarefa: Tarefa = tarefas.get(position);
                val intent = Intent(this@MainActivity, AdicionarTarefaActivity::class.java);
                intent.putExtra("TAREFA", tarefa);
                startActivity(intent);

            }

            override fun onLongItemClick(view: View, position: Int) {
                val tarefa: Tarefa = tarefas.get(position);
                val builder = AlertDialog.Builder(this@MainActivity);
                builder.setTitle("Confirmação de exclusão");
                builder.setMessage("Deseja excluír a tarefa: ${tarefa.descricao}?");

                builder.setPositiveButton("SIM", DialogInterface.OnClickListener{dialog, which ->
                    if(tarefaDAO.deletar(tarefa)) {
                        carregarListaDeTarefas();
                        dialog.dismiss();
                        Toast.makeText(applicationContext, "Tarefa excluída com sucesso.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(applicationContext, "Ocorreu um erro ao excluír a tarefa.", Toast.LENGTH_LONG).show();
                    }
                })

                builder.setNegativeButton("NÃO", DialogInterface.OnClickListener{dialog, which ->
                    dialog.dismiss();
                });

                val dialog: AlertDialog = builder.create();
                dialog.show();
            }

        }));
    }

    override fun onStart() {
        carregarListaDeTarefas();
        super.onStart();
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun carregarListaDeTarefas() {
        tarefas = tarefaDAO.listar();

        //Criando um Adapter
        val adapter = TarefaAdapter(tarefas);

        //Configurando o RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.adapter = adapter;

    }

}

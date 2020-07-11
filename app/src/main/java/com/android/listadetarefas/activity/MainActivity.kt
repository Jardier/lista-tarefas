package com.android.listadetarefas.activity.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.listadetarefas.R
import com.android.listadetarefas.adapter.TarefaAdapter
import com.android.listadetarefas.model.Tarefa

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView;
    lateinit var tarefas : List<Tarefa>;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //inicializando variáveis
        recyclerView = findViewById(R.id.recyclerView);

        fab.setOnClickListener { view ->
            val intent = Intent(applicationContext, AdicionarTarefaActivity::class.java);
            startActivity(intent);

        }

        //Criando um Adapter
        val adapter = TarefaAdapter(tarefas);

        //Configurando o RecyclerWier
        recyclerView.layoutManager = LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.adapter = adapter;


    }


    override fun onStart() {
        super.onStart()
        tarefas = criarTarefas();
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

    private fun criarTarefas() : List<Tarefa> {
        val tarefas = arrayListOf<Tarefa>(
            Tarefa("Estudar Java"),
            Tarefa("Estudar Kotlin")
        )

        return tarefas
    }

}

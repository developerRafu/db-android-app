package com.example.processos;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.processos.database.Database;
import com.example.processos.models.Processo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends Activity {

    private Database database;
    private List<Processo> processosFound;
    private ListView lista;
    private TextView txtCod;
    private TextView txtAssunto;
    private TextView txtReu;
    private TextView txtValor;
    private TextView txtAutor;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.instantiateVars();
        this.loadList();
        this.AddActionsToButtons();
    }

    public void instantiateVars() {
        this.database = new Database(this);
        this.lista = findViewById(R.id.lista);
        this.txtCod = this.findViewById(R.id.txtCod);
        this.txtAssunto = this.findViewById(R.id.txtAssunto);
        this.txtReu = this.findViewById(R.id.txtReu);
        this.txtValor = this.findViewById(R.id.txtValor);
        this.txtAutor = this.findViewById(R.id.txtAutor);
        this.btnSave = this.findViewById(R.id.btnSave);
    }

    private void AddActionsToButtons() {
        this.btnSave.setOnClickListener(view -> save());
    }

    private void loadList() {
        this.processosFound = this.database.findAll();
        this.lista.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, processosFound));
    }

    private void clearField() {
        this.txtCod.setText(null);
        this.txtValor.setText(null);
        this.txtAssunto.setText(null);
        this.txtAutor.setText(null);
        this.txtReu.setText(null);
    }
    int errors = 0;
    private void save() {
        errors = 0;
        try {
            String codigo = !this.txtCod.getText().toString().equals("") ? this.txtCod.getText().toString() : this.addErrors();
            String reu = !this.txtReu.getText().toString().equals("") ? this.txtReu.getText().toString() : this.addErrors();
            String autor = !this.txtAutor.getText().toString().equals("") ? this.txtAutor.getText().toString() : this.addErrors();
            String assunto = !this.txtAssunto.getText().toString().equals("") ? this.txtAssunto.getText().toString() : this.addErrors();
            Double valorPedido = Double.parseDouble(!this.txtValor.getText().toString().equals("") ? this.txtValor.getText().toString() : this.addErrors());
            if(this.errors>0){
                Toast.makeText(this, "Insira os dados corretos", Toast.LENGTH_SHORT).show();
                throw new RuntimeException("Erro ao pegar valores");
            }
            Processo processo = new Processo(null, codigo, reu, autor, valorPedido, assunto);
            this.database.save(processo);
            this.clearField();
            this.loadList();
            Toast.makeText(this, "Processo salvo", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(this, "Erro ao salvar processo", Toast.LENGTH_SHORT).show();
        }
    }
    private String addErrors(){
        this.errors++;
        return "";
    }
}

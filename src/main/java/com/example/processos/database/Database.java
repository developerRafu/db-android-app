package com.example.processos.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.processos.models.Processo;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Database extends SQLiteOpenHelper {
    private static final int VERSAO_BANCO_DADOS = 1;
    private static final String NOME_BANCO_DADOS = "processos";

    public Database(Context context) {
        super(context, NOME_BANCO_DADOS, null, VERSAO_BANCO_DADOS);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE IF NOT EXISTS TAB_PROCESSO (" +
                "    id          INTEGER       PRIMARY KEY AUTOINCREMENT" +
                "                              NOT NULL" +
                "                              UNIQUE," +
                "    numero      VARCHAR (255) UNIQUE" +
                "                              NOT NULL," +
                "    reu         VARCHAR (255) NOT NULL," +
                "    autor       VARCHAR (255) NOT NULL," +
                "    valorPedido DECIMAL       NOT NULL," +
                "    assunto     VARCHAR (255) NOT NULL" +
                ");";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<Processo> findAll() {
        List<Processo> processos = new ArrayList<>();
        SQLiteDatabase scriptRunner = getReadableDatabase();
        String query = "SELECT * FROM TAB_PROCESSO";
        Cursor cursorResult = scriptRunner.rawQuery(query, null);
        while (cursorResult.moveToNext()) {
            Processo processo = new Processo(
                    Integer.parseInt(cursorResult.getString(0)),
                    cursorResult.getString(1),
                    cursorResult.getString(2),
                    cursorResult.getString(3),
                    Double.parseDouble(cursorResult.getString(4)),
                    cursorResult.getString(5)
            );
            processos.add(processo);
        }
        return processos;
    }

    public void save(Processo processo) {
        SQLiteDatabase scriptRunner = getWritableDatabase();
        scriptRunner.execSQL("INSERT INTO TAB_PROCESSO (" +
                "assunto," +
                "valorPedido," +
                "autor," +
                "reu," +
                "numero" +
                ")" +
                "VALUES (" +
                "'" + processo.getAssunto() + "'," +
                "" + processo.getValorPedido() + "," +
                "'" + processo.getReu() + "'," +
                "'" + processo.getAutor() + "'," +
                "'" + processo.getNumero() + "'" +
                ");");
    }

    public Processo findById(Integer id) {
        SQLiteDatabase scriptRunner = getReadableDatabase();
        String query = "SELECT * FROM TAB_PROCESSO WHERE id = " + id;
        Cursor cursorResult = scriptRunner.rawQuery(query, null);
        while (cursorResult.moveToNext()) {
            Processo processo = new Processo(
                    Integer.parseInt(cursorResult.getString(0)),
                    cursorResult.getString(1),
                    cursorResult.getString(2),
                    cursorResult.getString(3),
                    Double.parseDouble(cursorResult.getString(4)),
                    cursorResult.getString(5)
            );
            return processo;
        }
        return null;
    }

}

package com.example.database;



import database.DatabaseContract.Names;
import database.DatabaseContract.Names.NamesColumns;
import database.DatabaseOpenHelper;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;


public class DataActivity extends Activity {

    static final String TAG = DataActivity.class.getSimpleName();
    private Long mRowId;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        //получаем из инта нужный нам айдишник и открываем нужное поле
        long id = getIntent().getLongExtra("_id",-6);
        DatabaseOpenHelper dbhelper = new DatabaseOpenHelper(getBaseContext());
        SQLiteDatabase sqliteDB = dbhelper.getReadableDatabase();
        Cursor c = sqliteDB.query(Names.TABLE_NAME, null, BaseColumns._ID + "=" + id, null, null, null,
                null);
        TextView lv = (TextView) findViewById(R.id.response);
        TextView tw = (TextView) findViewById(R.id.request);
        //выводим все в текствьюхи
        if (c.moveToFirst()) {
            tw.setText(c.getString(c.getColumnIndex(NamesColumns.NAME)));
            lv.setText(c.getString(c.getColumnIndex(NamesColumns.AGE)));
        }
        dbhelper.close();
        sqliteDB.close();
        Log.v(TAG, "ID=" + id);
    }
}
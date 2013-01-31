package com.example.database;

import database.ManController;
import database.DatabaseContract.Names;
import database.DatabaseContract.Names.NamesColumns;
import database.DatabaseOpenHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListActivity extends Activity {
	
	final Context context = this;
	int rowId = 0;
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);
		DatabaseOpenHelper dbhelper = new DatabaseOpenHelper(getBaseContext());
		SQLiteDatabase sqliteDB = dbhelper.getReadableDatabase();
		final String[] from = { NamesColumns.FNAME, BaseColumns._ID };
		final Cursor c = sqliteDB.query(Names.TABLE_NAME, null, null, null, null, null,
				Names.DEFAULT_SORT);
		final int i = c.getCount();
		final int[] to = new int[] { R.id.text1 };
		final SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.list,
				c, from, to);
		final ListView lv = (ListView) findViewById(R.id.listView1);		
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {

				Intent intent = new Intent(ListActivity.this, DataActivity.class);				
				intent.putExtra("_id", id);
				startActivity(intent);
				finish();
			}
		});
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int pos, long id) {

				final CharSequence[] items = { "Удалить", "Переименовать" };
				AlertDialog.Builder builder3 = new AlertDialog.Builder(ListActivity.this);
				builder3.setTitle("Введите новое имя").setItems(items,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int item) {

								switch (item) {
									case 0: {
										DatabaseOpenHelper dbhelper = new DatabaseOpenHelper(getBaseContext());
										SQLiteDatabase sqliteDB = dbhelper.getReadableDatabase();
										ManController.delete(getBaseContext(), adapter.getItemId(pos));		
										final Cursor c = sqliteDB.query(Names.TABLE_NAME, null, null, null, null, null,
												Names.DEFAULT_SORT);
										adapter.changeCursor(c);
										dbhelper.close();
										sqliteDB.close();
									}
										break;
									case 1: {
										// подключаем наш кастомный диалог лайаут
										LayoutInflater li = LayoutInflater.from(context);
										View promptsView = li.inflate(R.layout.promt, null);
										AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
												context);
										// делаем его диалогом
										alertDialogBuilder.setView(promptsView);
										final EditText userInput = (EditText) promptsView
												.findViewById(R.id.editTextDialogUserInput);
										// вешаем на него событие
										alertDialogBuilder.setCancelable(false).setPositiveButton("OK",
												new DialogInterface.OnClickListener() {

													@Override
													public void onClick(DialogInterface dialog, int id) {

														DatabaseOpenHelper dbhelper = new DatabaseOpenHelper(getBaseContext());
														SQLiteDatabase sqliteDB = dbhelper.getReadableDatabase();
														ManController.update(getBaseContext(), userInput
																.getText().toString(), adapter.getItemId(pos));		
														final Cursor c = sqliteDB.query(Names.TABLE_NAME, null, null, null, null, null,
																Names.DEFAULT_SORT);
														adapter.changeCursor(c);
														dbhelper.close();
														sqliteDB.close();
													}
												}).setNegativeButton("Cancel",
												new DialogInterface.OnClickListener() {

													@Override
													public void onClick(DialogInterface dialog, int id) {

														dialog.cancel();
													}
												});
										// создаем диалог
										AlertDialog alertDialog = alertDialogBuilder.create();
										// показываем его
										alertDialog.show();
									}
										break;
								}
							}
						});
				builder3.show();
				return true;
			}
		});
		dbhelper.close();
		sqliteDB.close();
	}
}

package com.example.database;

import database.ManController;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity  extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_to_db);
        
        final EditText name = (EditText) findViewById(R.id.name);
        final EditText fname = (EditText) findViewById(R.id.fname);
        final EditText age = (EditText) findViewById(R.id.age);
        Button btn = (Button) findViewById(R.id.button1);
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				ManController.write(getBaseContext(), '"'+name.getText().toString()+'"', '"'+fname.getText().toString()+'"', Integer.parseInt(age.getText().toString()));
			}
		});
    }
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {

		menu.add(Menu.NONE, 1, 0, "Список записей");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
			case 1: {
				Intent intent = new Intent(this, ListActivity.class);
				startActivity(intent);
			}
				break;
		}
		return true;
	}
}

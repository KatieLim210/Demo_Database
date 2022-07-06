package sg.edu.rp.c346.id21023701.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
Button btnInsert, btnGetTasks;
EditText etDesc, etDate;
TextView tvResults;
ListView listView;
private static String order;
boolean altOrder = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);

        tvResults = findViewById(R.id.tvResults);
        listView = findViewById(R.id.listview);
        etDesc = findViewById(R.id.editTextDesc);
        etDate = findViewById(R.id.editTextDate);


        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (altOrder) {
                    order = "ASC";
                    altOrder = false;

                } else {
                    order = "DESC";
                    altOrder = true;
                }
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertTask(etDesc.getText().toString(), etDate.getText().toString());

            }
        });
        btnGetTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getTaskContent();
                ArrayList<Task> dataList = db.getTasks();
                db.close();
                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);
                ArrayAdapter adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, dataList);
                listView.setAdapter(adapter);



            }

        });





            }
}
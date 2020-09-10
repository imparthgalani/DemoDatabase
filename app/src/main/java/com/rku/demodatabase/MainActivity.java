package com.rku.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtName, edtSurname, edtMarks;
    DatabaseHelper databaseHelper;
    TextView txtData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        edtName = findViewById(R.id.edtName);
        edtSurname = findViewById(R.id.edtSurname);
        edtMarks = findViewById(R.id.edtMarks);
        txtData = findViewById(R.id.txtData);
    }

    public void saveRecord(View view) {
        String valName,valSurname,valMarks;
        valName =edtName.getText().toString();
        valSurname =edtSurname.getText().toString();
        valMarks =edtMarks.getText().toString();

        //Database operation
        if (databaseHelper.insertData(valName,valSurname,valMarks)){
            Toast.makeText(this,"Data insert successfuly", Toast.LENGTH_SHORT).show();
            edtName.setText("");
            edtSurname.setText("");
            edtMarks.setText("");
        }
    }


    public void displayData(View view) {
        Cursor cursor = databaseHelper.selectData();
        String data ="";
        if (cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                String name = cursor.getString(1);
                String surname = cursor.getString(2);
                int marks = cursor.getInt(3);
                data += name + " " + surname +" " + " ("+marks+")\n";

            }while (cursor.moveToNext());

            txtData.setText(data);

             /*Intent intent = new Intent(MainActivity.this,Display.class);
             intent.putExtra("username",data);
             startActivity(intent);*/

        }else{
                 Toast.makeText(this, "NO RECORD FOUND", Toast.LENGTH_SHORT).show();
             }
        }


}
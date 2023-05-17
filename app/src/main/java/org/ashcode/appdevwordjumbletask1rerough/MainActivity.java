package org.ashcode.appdevwordjumbletask1rerough;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static String word1Input_ma;
    private static String clue1Input_ma;
    int gridSize = 4;
    public static String getword1Input_ma(){
        return word1Input_ma;
    }

    public static String getClue1Input_ma(){
        return clue1Input_ma;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextWord1Input_ma = findViewById(R.id.editTextWord1Input_ma);
        EditText editTextClue1Input_ma = findViewById(R.id.editTextClue1Input_ma);

        Button startbutton_ma = findViewById(R.id.startbutton_ma);


        startbutton_ma.setOnClickListener(view -> {
            word1Input_ma = editTextWord1Input_ma.getText().toString().toUpperCase();
            clue1Input_ma = editTextClue1Input_ma.getText().toString().toUpperCase();
            if (word1Input_ma.length()>gridSize*gridSize){
                Toast.makeText(getApplicationContext(),"Input Word Length Below "+Integer.toString(gridSize*gridSize),Toast. LENGTH_SHORT).show();
            }
            else if (word1Input_ma.length()<1){
                Toast.makeText(getApplicationContext(),"Input Word",Toast. LENGTH_SHORT).show();
            }
            else if (clue1Input_ma.length()<1){
                Toast.makeText(getApplicationContext(),"Input Clue",Toast. LENGTH_SHORT).show();
            }
            else {
                Intent goto_ma2Intent_from_ma = new Intent(getApplicationContext(), MainActivity2.class);
                //goto_ma2Intent_from_ma.putExtra("word1Input", editTextWord1Input_ma);
                startActivity(goto_ma2Intent_from_ma); }
        });
    }
}
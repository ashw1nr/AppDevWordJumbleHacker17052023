package org.ashcode.appdevwordjumbletask1rerough;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity3 extends AppCompatActivity {

    private static String wordsAllInput_ma3;
    private static String cluesAllInput_ma3;
    private static String clue1Input_ma3;
    private static ArrayList<ArrayList<String>> wordsAllInput_ma3_larr;
    private static ArrayList<String> cluesAllInput_ma3_larr;
    private static int gridSizeA, gridSizeB;

    public static String getClue1Input_ma3() {
        return clue1Input_ma3;
    }

    public static String getWordsAllInput_ma3(){
        return wordsAllInput_ma3;
    }

    public static String getcluesAllInput_ma3(){
        return cluesAllInput_ma3;
    }
    public static ArrayList<ArrayList<String>> getWordsAllInput_ma3_larr(){ return wordsAllInput_ma3_larr;}

    public static ArrayList<String> getCluesAllInput_ma3_larr() { return cluesAllInput_ma3_larr;}
    //Error Producing below
/*
    public static void setCluesAllInput_ma3_larr(ArrayList<String> cluesAllInput_ma3_larr) {
        MainActivity3.cluesAllInput_ma3_larr = cluesAllInput_ma3_larr;
    }*/

    public static int getGridSizeA_ma3() {
        return gridSizeA;
    }

    public static int getGridSizeB_ma3() {
        return gridSizeB;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        EditText editTextWord1Input_ma3 = findViewById(R.id.editTextWord1Input_ma3);
        EditText editTextClue1Input_ma3 = findViewById(R.id.editTextClue1Input_ma3);
        EditText editTextGridsizeA1Input_ma3 = findViewById(R.id.editTextGridsizeA1Input_ma3);
        EditText editTextGridsizeB1Input_ma3 = findViewById(R.id.editTextGridsizeB1Input_ma3);

        Button startbutton_ma3 = findViewById(R.id.startbutton_ma3);

        startbutton_ma3.setOnClickListener(view -> {
            wordsAllInput_ma3 = editTextWord1Input_ma3.getText().toString().toUpperCase();
            cluesAllInput_ma3 = editTextClue1Input_ma3.getText().toString().toUpperCase();
            gridSizeA = Integer.parseInt(editTextGridsizeA1Input_ma3.getText().toString());
            gridSizeB = Integer.parseInt(editTextGridsizeB1Input_ma3.getText().toString());
            wordsAllInput_ma3 = wordsAllInput_ma3.trim();
            cluesAllInput_ma3 = cluesAllInput_ma3.trim();
            ArrayList<String> tempsplit_larr_wordsAllInput_ma3=new ArrayList<String>(Arrays.asList(wordsAllInput_ma3.split("\n")));
            cluesAllInput_ma3_larr=new ArrayList<String>(Arrays.asList(cluesAllInput_ma3.split("\n")));

            wordsAllInput_ma3_larr = new ArrayList<ArrayList<String>>();

            if (cluesAllInput_ma3_larr.size() == tempsplit_larr_wordsAllInput_ma3.size())
            {
                boolean flag = true;
                for (String tempS:tempsplit_larr_wordsAllInput_ma3) {
                    if (flag)
                    {
                        if (tempS.length()>gridSizeA*gridSizeB){
                            Toast.makeText(getApplicationContext(),"Input Word Length Below "+Integer.toString(gridSizeA*gridSizeB)+" for Word "+Integer.toString(tempsplit_larr_wordsAllInput_ma3.indexOf(tempS)+1),Toast. LENGTH_SHORT).show();
                            flag = false;
                        }
                        else if (tempS.length()<1){
                            Toast.makeText(getApplicationContext(),"Input Word length upto"+Integer.toString(gridSizeA*gridSizeB)+" for Word "+Integer.toString(tempsplit_larr_wordsAllInput_ma3.indexOf(tempS)+1),Toast. LENGTH_SHORT).show();
                            flag = false;
                        }
                        ArrayList<String> tempWordCurrInput_ma_larr = new ArrayList<String>(
                                Arrays.asList(tempS.split("")));
                        wordsAllInput_ma3_larr.add(tempWordCurrInput_ma_larr);
                    }
                    else
                    {
                        break;
                    }
                }
                for (String tempSC:cluesAllInput_ma3_larr)
                {
                    if (flag)
                    {
                        if(tempSC.length()<1){
                            Toast.makeText(getApplicationContext(),"Input Clue "+Integer.toString(cluesAllInput_ma3_larr.indexOf(tempSC)+1),Toast. LENGTH_SHORT).show();
                            flag = false;
                        }
                    }
                    else
                    {
                        break;
                    }
                }
                clue1Input_ma3=cluesAllInput_ma3_larr.get(0);
                if (flag) {
                    Intent goto_ma4Intent_from_ma3 = new Intent(getApplicationContext(), MainActivity4.class);
//                goto_ma4Intent_from_ma3.putExtra("word1Input", word1Input_ma3);
//                goto_ma4Intent_from_ma3.putExtra("clue1Input", clue1Input_ma3);
//                goto_ma4Intent_from_ma3.putExtra("gridSizeA", gridSizeA);
//                goto_ma4Intent_from_ma3.putExtra("girdSizeB", gridSizeB);
                    startActivity(goto_ma4Intent_from_ma3); }
            }
            else{
                Toast.makeText(getApplicationContext(),"Input equal no. of Words and Clues",Toast. LENGTH_SHORT).show();

            }

        });
    }}
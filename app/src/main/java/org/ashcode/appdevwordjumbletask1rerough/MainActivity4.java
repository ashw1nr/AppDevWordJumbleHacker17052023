package org.ashcode.appdevwordjumbletask1rerough;

        import androidx.appcompat.app.AlertDialog;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.fragment.app.DialogFragment;

        import android.annotation.SuppressLint;
        import android.app.Dialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.view.View;
        import android.view.Window;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.GridView;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.lang.reflect.Array;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.Collections;
        import java.util.Random;

public class MainActivity4 extends AppCompatActivity {

    GridView gridViewBtns_ma4;
    int gridSizeA = MainActivity3.getGridSizeA_ma3();
    int gridSizeB = MainActivity3.getGridSizeB_ma3();
    //private String wordsAllInput_ma3 = MainActivity3.getWordsAllInput_ma3();
    //private String clue1Input_ma3 = MainActivity3.getClue1Input_ma3();
    private ArrayList<String> currentTextGuessView_la_ma4;
    private ArrayList<ArrayList<String>> wordsAllInput_ma3_larr = MainActivity3.getWordsAllInput_ma3_larr();
    private ArrayList<String> cluesAllInput_ma3_larr = MainActivity3.getCluesAllInput_ma3_larr();
    private ArrayList<String> assignedWordCurrInput_ma3_larr;
    private ArrayList<BtnModelInGrid> btnModelArrayList;
    private BtnGVAdapter_ma2 adapter;
    private TextView letterSelecTextView_ma2;
    private ImageView infoClueIcon_IV_ma2;
    private Button hrtBtn1;
    private Button hrtBtn2;
    private Button hrtBtn3;
    private ArrayList<String> gridChar1Input_larr_ma2;

    int lives;
    private int currScore;
    private int wordsAvailable;

    private ArrayList<Integer> ReferringIndicesLA = new ArrayList<Integer>();
    private int assignedIndex;
    private int currReferredIndexofLA;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        initialiseRandomReferringIndicesStart();

    }
    private void initialiseRandomReferringIndicesStart(){
        ReferringIndicesLA.clear();
        wordsAvailable = cluesAllInput_ma3_larr.size();
        int i;
        for (i=0;i<wordsAvailable;i++){
            ReferringIndicesLA.add(i);
        }
        Collections.shuffle(ReferringIndicesLA);
        currReferredIndexofLA = -1;
        currScore = 0;
        assigningIndexFn();
    }
    private void assigningIndexFn(){
        if (currReferredIndexofLA>-1){
            currScore += 100*lives*assignedWordCurrInput_ma3_larr.size()*gridSizeA*gridSizeB;
        }
        if (currReferredIndexofLA<wordsAvailable-1)
        {
            currReferredIndexofLA++;
            assignedIndex = ReferringIndicesLA.get(currReferredIndexofLA);
            onCreateMethodTemp();
        }
        else {
            showGameOverDialog();
        }
    }
    private void onCreateMethodTemp()
    {


        Button resetButton_ma2 = findViewById(R.id.resetButton_ma2);
        Button checkButton_ma2 = findViewById(R.id.checkButton_ma2);
        letterSelecTextView_ma2 = findViewById(R.id.letterSelecTextView_ma2);
        infoClueIcon_IV_ma2 = findViewById(R.id.infoClueIcon_IV_ma2);

        hrtBtn1 = findViewById(R.id.hrtBtn1);
        hrtBtn2 = findViewById(R.id.hrtBtn2);
        hrtBtn3 = findViewById(R.id.hrtBtn3);

        updateHrtBtnLives(true);

        //String[] word1Input_maSplit = word1Input_ma3.split("");
        // Now convert string into ArrayList
        //assignedWordCurrInput_ma3_larr = new ArrayList<String>(
        //        Arrays.asList(word1Input_maSplit));
        assignedWordCurrInput_ma3_larr = new ArrayList<String>(wordsAllInput_ma3_larr.get(assignedIndex));

        //gridChar1Input_larr_ma2 = new ArrayList<String>(
        //        Arrays.asList(word1Input_maSplit));

        gridChar1Input_larr_ma2 = new ArrayList<String>(wordsAllInput_ma3_larr.get(assignedIndex));
        Random rand = new Random();
        int i;
        for (i=0;i<(gridSizeA*gridSizeB-assignedWordCurrInput_ma3_larr.size());i++){
            int randomNum = rand.nextInt(26)+65;
            gridChar1Input_larr_ma2.add(Character.toString((char)randomNum));
        }

        Collections.shuffle(gridChar1Input_larr_ma2);

        gridViewBtns_ma4 = (GridView) findViewById(R.id.gridViewBtns_ma4);
        gridViewBtns_ma4.setNumColumns(gridSizeA);

        btnModelArrayList = new ArrayList<BtnModelInGrid>();
        currentTextGuessView_la_ma4 = new ArrayList<String>();
        update_letterSelecTextView_ma2();

        for (String s: gridChar1Input_larr_ma2 ) {
            btnModelArrayList.add(new BtnModelInGrid(false, s));
        }

        adapter = new BtnGVAdapter_ma2(getApplicationContext(), btnModelArrayList);
        gridViewBtns_ma4.setAdapter(adapter);

        gridViewBtns_ma4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onTVClickInGrid(position);
            }
        });

        resetButton_ma2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGridViewBtnsTV();
                Toast.makeText(getApplicationContext(),"Reset Grid", Toast.LENGTH_SHORT).show();
            }
        });

        checkButton_ma2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentTextGuessView_la_ma4.size() != assignedWordCurrInput_ma3_larr.size() ){
                    Toast.makeText(getApplicationContext(),"Enter "+String.valueOf(assignedWordCurrInput_ma3_larr.size())+" Characters", Toast.LENGTH_SHORT).show();
                }
                else if (currentTextGuessView_la_ma4.equals(assignedWordCurrInput_ma3_larr) == true){
                    if (currReferredIndexofLA<wordsAvailable-1) {
                        Toast.makeText(getApplicationContext(),"Correct Guess!!! Guess the next word!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Correct Guess!", Toast.LENGTH_SHORT).show();
                    }
                    assigningIndexFn();
                }
                else {
                    lives-=1;
                    if (lives==0){
                        if (currReferredIndexofLA<wordsAvailable-1) {
                            Toast.makeText(getApplicationContext(), "You ran out of lives. Guess the next word!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "You ran out of lives", Toast.LENGTH_SHORT).show();
                        }
                        assigningIndexFn();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Wrong Guess", Toast.LENGTH_SHORT).show();
                        updateHrtBtnLives(false);
                        resetGridViewBtnsTV();
                    }
                }

            }
        });
        infoClueIcon_IV_ma2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showClueDialog();
                /*DialogFragment clueInfo_diagfrag = new ClueInfoDialogFragment();
                clueInfo_diagfrag.show(getSupportFragmentManager(), "ClueInfo");*/
            }
        });
    }

    private void onTVClickInGrid(int position){
        BtnModelInGrid btnModel = btnModelArrayList.get(position);
        if (btnModel.isBtnClickedState()){
            Toast.makeText(getApplicationContext(),"Can't Click Again", Toast.LENGTH_SHORT).show();
        } else if (currentTextGuessView_la_ma4.size()<assignedWordCurrInput_ma3_larr.size()) {
            String s = btnModel.getBtnTextLetter();
            //btnModel.setBtnTextLetter("Clicked"+s);
            btnModel.setBtnBgColor(R.color.teal_200);
            btnModel.setBtnTextColor(R.color.white);
            btnModel.setBtnClickedState(true);
            gridViewBtns_ma4.setAdapter(adapter);
            currentTextGuessView_la_ma4.add(s);
            update_letterSelecTextView_ma2();
        }
        else Toast.makeText(getApplicationContext(),"Done clicking required no. of letters", Toast.LENGTH_SHORT).show();
    }
    private void update_letterSelecTextView_ma2(){
        String showing = "";
        int i =0;
        for (String s:currentTextGuessView_la_ma4){
            showing=showing+s+" ";
            i++;
        }
        while(i<assignedWordCurrInput_ma3_larr.size()){
            showing=showing+"_ ";
            i++;
        }
        letterSelecTextView_ma2.setText(showing);
    }

    private void updateHrtBtnLives(boolean reset){
        if (!reset){
            if (lives==2){
                hrtBtn3.setBackground(hrtBtn3.getContext().getResources().getDrawable(R.drawable.heart_grey_ma2,getTheme()));
            }
            else if (lives==1){
                hrtBtn2.setBackground(hrtBtn2.getContext().getResources().getDrawable(R.drawable.heart_grey_ma2,getTheme()));
            }
            else if (lives==0){
                hrtBtn1.setBackground(hrtBtn1.getContext().getResources().getDrawable(R.drawable.heart_grey_ma2,getTheme()));
            }}
        else{
            lives = 3;
            hrtBtn3.setBackground(hrtBtn3.getContext().getResources().getDrawable(R.drawable.heart_yellow_ma2,getTheme()));
            hrtBtn2.setBackground(hrtBtn2.getContext().getResources().getDrawable(R.drawable.heart_yellow_ma2,getTheme()));
            hrtBtn1.setBackground(hrtBtn1.getContext().getResources().getDrawable(R.drawable.heart_yellow_ma2,getTheme()));
        }
    }

    private void resetGridViewBtnsTV(){
        Collections.shuffle(gridChar1Input_larr_ma2);
        btnModelArrayList.clear();
        for (String s: gridChar1Input_larr_ma2 ) {
            btnModelArrayList.add(new BtnModelInGrid(false, s));
        }
        gridViewBtns_ma4.setAdapter(adapter);
        currentTextGuessView_la_ma4.clear();
        update_letterSelecTextView_ma2();
    }
    private void showClueDialog(){
        final Dialog clueDialog = new Dialog(MainActivity4.this);
        clueDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        clueDialog.setCancelable(true);
        clueDialog.setContentView(R.layout.cluedialoglayout_ma2);
        TextView cluePasteTV  = clueDialog.findViewById(R.id.cluePasteTextViewOnDialog);
        Button okayBtn = clueDialog.findViewById(R.id.okaybtnbg_cluedialog_ma2);
        cluePasteTV.setText(cluesAllInput_ma3_larr.get(assignedIndex));
        okayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clueDialog.dismiss();
            }
        });
        clueDialog.show();
    }

    private void showGameOverDialog(){
        final Dialog gOverDialog = new Dialog(MainActivity4.this);
        gOverDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        gOverDialog.setCancelable(true);
        gOverDialog.setContentView(R.layout.checkwondialoglayout_ma2);

        SharedPreferences sharedPreferences = getSharedPreferences("highscorefile", MODE_PRIVATE);
        int highscore = sharedPreferences.getInt("highscorekey",0);

        if (highscore<currScore) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("highscorekey", currScore);
            editor.commit();
        }

        TextView highScoreTV  = gOverDialog.findViewById(R.id.highScoreTextViewOnDialog);
        Button homeBtn = gOverDialog.findViewById(R.id.homeBtnOverDialog_ma2);
        Button playAgnBtn = gOverDialog.findViewById(R.id.playAgainBtnOverDialog_ma2);
        highScoreTV.setText("Your Score : "+String.valueOf(currScore));
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gOverDialog.dismiss();
                Intent goto_hs1Intent_from_ma4Dialog = new Intent(getApplicationContext(), MainActivityHS1.class);
                //goto_maIntent_from_ma2Dialog.putExtra("word1Input", editTextWord1Input_ma);
                startActivity(goto_hs1Intent_from_ma4Dialog);
            }
        });
        playAgnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gOverDialog.dismiss();
                //updateHrtBtnLives(true);
                initialiseRandomReferringIndicesStart();
            }
        });
        gOverDialog.show();
    }
}
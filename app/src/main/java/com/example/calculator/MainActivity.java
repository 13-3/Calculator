package com.example.calculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText display;

   //changed

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(getString(R.string.display).equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });
    }

    private void updateText(String strToAdd){
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String letStr = oldStr.substring(0,cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if (getString(R.string.display).equals(display.getText().toString())) {

            display.setText(strToAdd);
            display.setSelection(cursorPos+1);
        }
        else{
            display.setText(String.format("%s%s%s",letStr,strToAdd,rightStr));
            display.setSelection(cursorPos+1);
        }


    }


    public void zerobtn(View view){

        updateText("0");
    }

    public void onebtn(View view){
        updateText("1");

    }

    public void twobtn(View view){
        updateText("2");
    }

    public void threebtn(View view){
        updateText("3");
    }

    public void fourbtn(View view){
        updateText("4");
    }

    public void fivebtn(View view){
        updateText("5");
    }

    public void sixbtn(View view){
        updateText("6");
    }

    public void sevenbtn(View view){
        updateText("7");
    }

    public void eightbtn(View view){
        updateText("8");
    }

    public void ninebtn(View view){
        updateText("9");
    }

    public void subtractbtn(View view){
        updateText("-");
    }

    public void addbtn(View view){
        updateText("+");
    }

    public void multbtn(View view){
        updateText("×");
    }

    public void dividebtn(View view){
        updateText("÷");
    }

    public void pointbtn(View view){
        updateText(".");
    }

    public void percentagebtn(View view){
        updateText("%");
    }

    public void backspacebtn(View view){

        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if(cursorPos !=0 && textLen !=0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos-1,cursorPos,"");
            display.setText(selection);
            display.setSelection(cursorPos-1);

        }

    }


    public void expbtn(View view){
        updateText("^");
    }

    public void clearbtn(View view){
        display.setText("");

    }

    public void equalbtn(View view){

        String userExp = display.getText().toString();

       userExp = userExp.replaceAll("×","*");
       userExp = userExp.replaceAll("%","/100");
       userExp = userExp.replaceAll("÷","/");


       Expression exp = new Expression(userExp);

       String result = String.valueOf(exp.calculate());

       display.setText(result);
       display.setSelection(result.length());

    }

    public void paranthesesbtn(View view){
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closePar = 0;
        int textLen = display.getText().length();

        for(int i=0;i<cursorPos;i++){
            if(display.getText().toString().substring(i,i+1).equals("(")){
                openPar +=1;
            }

           if(display.getText().toString().substring(i,i+1).equals(")")){
               closePar +=1;
            }
        }

        if(openPar == closePar || display.getText().toString().substring(textLen-1,textLen).equals("(")){

            updateText("(");
            display.setSelection(cursorPos+1);
        }

       else if(closePar < openPar &&! display.getText().toString().substring(textLen-1,textLen).equals("(")){

            updateText(")");
            display.setSelection(cursorPos+1);
        }

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        MainActivity.super.onBackPressed();
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }

}
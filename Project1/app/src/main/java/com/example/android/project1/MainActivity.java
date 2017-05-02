package com.example.android.project1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
        public void submitOrder(View view) {
                EditText emailText = (EditText)findViewById(R.id.email_edittext_view);
                String email = emailText.getText().toString();
                CheckBox isUCI = (CheckBox) findViewById(R.id.question_one);
                boolean yesUCI = isUCI.isChecked();
                CheckBox eatLecture = (CheckBox) findViewById(R.id.question_two);
                boolean yesEat = eatLecture.isChecked();
                CheckBox skipNap = (CheckBox) findViewById(R.id.question_three);
                boolean yesNap = skipNap.isChecked();
                CheckBox infinityFountain = (CheckBox) findViewById(R.id.question_four);
                boolean yesFountain = infinityFountain.isChecked();
                CheckBox netflix = (CheckBox) findViewById(R.id.question_five);
                boolean yesNetflix = netflix.isChecked();
                CheckBox ringRoad = (CheckBox) findViewById(R.id.question_six);
                boolean yesRoad= ringRoad.isChecked();
                CheckBox sleepLecture = (CheckBox) findViewById(R.id.question_seven);
                boolean yesSleep = sleepLecture.isChecked();
                CheckBox boba = (CheckBox) findViewById(R.id.question_eight);
                boolean yesBoba = boba.isChecked();
                CheckBox shocktober = (CheckBox) findViewById(R.id.question_nine);
                boolean yesShocktober = shocktober.isChecked();
                CheckBox soulstice = (CheckBox) findViewById(R.id.question_ten);
                boolean yesSoulstice = soulstice.isChecked();
                CheckBox aldrichPark = (CheckBox) findViewById(R.id.question_eleven);
                boolean yesPark = aldrichPark.isChecked();
                CheckBox person = (CheckBox) findViewById(R.id.question_twelve);
                boolean yesPerson = person.isChecked();
                CheckBox library = (CheckBox) findViewById(R.id.question_thirteen);
                boolean yesLibrary = library.isChecked();
                CheckBox utc = (CheckBox) findViewById(R.id.question_fourteen);
                boolean yesUTC = utc.isChecked();



                EditText nameEditText = (EditText) findViewById(R.id.name_edittext_view);
                String nameStr = nameEditText.getText().toString();
                String totalScore =  (nameStr);
                EditText emailEditText = (EditText) findViewById(R.id.email_edittext_view);
                String emailStir = emailEditText.getText().toString();
                int finalScore = calculateScore(yesUCI, yesEat, yesNap, yesFountain, yesNetflix, yesRoad, yesSleep, yesBoba, yesShocktober, yesSoulstice, yesPark, yesPerson, yesLibrary, yesUTC);
                String subjectStr = "You are " + finalScore + "% UCI!";
                String emailStr[] = {"hungb1@uci.edu", email};
                composeEmail(emailStr, subjectStr, totalScore);
            }



    private int calculateScore(boolean yesUCI, boolean yesEat, boolean yesNap, boolean yesFountain, boolean yesNetflix, boolean yesRoad, boolean yesSleep, boolean yesBoba, boolean yesShocktober, boolean yesSoulstice, boolean yesPark, boolean yesPerson, boolean yesLibrary, boolean yesUTC){
            int score = 0;
            if(yesUCI) {
               score += 10;}
            if(yesEat) {
                score += 13;}
            if(yesNap) {
                score += 5;}
            if(yesFountain) {
                score += 9;}
            if(yesNetflix) {
                score += 6;}
            if(yesRoad) {
                score += 1;}
            if(yesSleep) {
                score += 4;}
            if(yesBoba) {
                score += 10;}
            if(yesShocktober) {
                score += 11;}
            if(yesSoulstice) {
                score += 12;}
            if(yesPark) {
                score += 6;}
            if(yesPerson) {
                score += 4;}
            if(yesLibrary){
                score += 2;}
            if(yesUTC) {
                score += 6;}

            if(score < 0)
                score = 0;
            return score;

            }

        public void composeEmail(String[] addresses, String subject, String body) {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, addresses);
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, body);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
            }


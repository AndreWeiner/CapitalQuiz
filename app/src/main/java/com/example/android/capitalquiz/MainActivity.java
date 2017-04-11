package com.example.android.capitalquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Random;

import static android.R.attr.angle;
import static android.R.attr.onClick;

public class MainActivity extends AppCompatActivity {

    Random rand = new Random();
    private String[] countries = new String[10];
    private String[] capitals = new String[10];
    private int currentIndex = 0;
    private int currentCheckBox = 0;
    private int totalAnswers = 0;
    private int correct = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCountriesCapitals();
        askQuestion();


    }

    /*
      This method provides some test data
     */
    private void setCountriesCapitals() {
        countries[0] = "Germany";
        capitals[0] = "Berlin";
        countries[1] = "France";
        capitals[1] = "Paris";
        countries[2] = "Spain";
        capitals[2] = "Madrid";
        countries[3] = "Italy";
        capitals[3] = "Rome";
        countries[4] = "Austria";
        capitals[4] = "Vienna";
        countries[5] = "Switzerland";
        capitals[5] = "Zurich";
        countries[6] = "Denmark";
        capitals[6] = "Copenhagen";
        countries[7] = "Norway";
        capitals[7] = "Oslo";
        countries[8] = "Sweden";
        capitals[8] = "Stockholm";
        countries[9] = "England";
        capitals[9] = "London";
    }

    /*
      TODO: There is still a bug that sometimes leads to incorrect evaluation of the answer
     */
    public void askQuestion() {
        currentIndex = rand.nextInt(10);
        setCountry();
        setCapitals();

    }

    public void setCountry() {
        TextView country = (TextView) findViewById(R.id.country);
        country.setText("Country: " + countries[currentIndex]);
    }

    public void setCapitals() {
        RadioButton answer1 = (RadioButton) findViewById(R.id.answer1);
        RadioButton answer2 = (RadioButton) findViewById(R.id.answer2);
        RadioButton answer3 = (RadioButton) findViewById(R.id.answer3);
        currentCheckBox = rand.nextInt(3);

        if (currentCheckBox == 0) {
            answer1.setText(capitals[currentIndex]);
        } else {
            answer1.setText(capitals[rand.nextInt(10)]);
        }

        if (currentCheckBox == 1) {
            answer2.setText(capitals[currentIndex]);
        } else {
            answer2.setText(capitals[rand.nextInt(10)]);
        }

        if (currentCheckBox == 2) {
            answer3.setText(capitals[currentIndex]);
        } else {
            answer3.setText(capitals[rand.nextInt(10)]);
        }
    }

    public void answer1Clicked(View view) {
        if (currentCheckBox == 0) {
            correct += 1;
        }
        totalAnswers += 1;
        updateDisplay();
    }

    public void answer2Clicked(View view) {
        if (currentCheckBox == 1) {
            correct += 1;
        }
        totalAnswers += 1;
        updateDisplay();
    }

    public void answer3Clicked(View view) {
        if (currentCheckBox == 2) {
            correct += 1;
        }
        totalAnswers += 1;
        updateDisplay();
    }

    public void updateDisplay() {
        TextView score = (TextView) findViewById(R.id.correct_field);
        score.setText("" + correct);
        TextView total = (TextView) findViewById(R.id.total_field);
        total.setText("" + totalAnswers);
        ImageView thumb = (ImageView) findViewById(R.id.thumb);
        float angle = (1 - (float) correct / totalAnswers) * 180;
        thumb.setRotation(angle);
        unsetCheckBoxes();
        askQuestion();
    }

    private void unsetCheckBoxes() {
        RadioButton answer1 = (RadioButton) findViewById(R.id.answer1);
        RadioButton answer2 = (RadioButton) findViewById(R.id.answer2);
        RadioButton answer3 = (RadioButton) findViewById(R.id.answer3);
        answer1.setChecked(false);
        answer2.setChecked(false);
        answer3.setChecked(false);

    }

    public void resetAll(View view) {
        unsetCheckBoxes();
        totalAnswers = 0;
        correct = 0;
        TextView score = (TextView) findViewById(R.id.correct_field);
        score.setText("" + correct);
        TextView total = (TextView) findViewById(R.id.total_field);
        total.setText("" + totalAnswers);
        ImageView thumb = (ImageView) findViewById(R.id.thumb);
        thumb.setRotation(0);
        askQuestion();
    }
}

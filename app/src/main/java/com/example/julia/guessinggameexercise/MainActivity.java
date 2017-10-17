package com.example.julia.guessinggameexercise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int number_to_guess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number_to_guess = (int) (Math.random() * 11);

        //        Add layout elements
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                TextView text = (TextView) findViewById(R.id.textView2);
                EditText text_input_field = (EditText) findViewById(R.id.editText);
                String text_input = text_input_field.getText().toString();

                int user_input = isInt(text_input);
                int difference = user_input - number_to_guess;

                if (user_input == -1) {
                    text.setText("How hard is it to enter an integer?");
                    text_input_field.setText("");
                } else if ( user_input < 0 || user_input > 10) {
                    text.setText("I said between 0 and 10...");
                    text_input_field.setText("");
                } else if (difference > 2) {
                    text.setText("Too high!");
                    text_input_field.setText("");
                } else if (difference < -2) {
                    text.setText("Too low!");
                    text_input_field.setText("");
                } else if (difference < 2 & difference != 0) {
                    text.setText("Very close, slightly too high...");
                    text_input_field.setText("");
                } else if (difference > -2 & difference != 0) {
                    text.setText("Nearly, slightly too low...");
                    text_input_field.setText("");
                } else {

                }

            }
        });
    }

    public int isInt(String integerCheck) {

        try {
            int result = Integer.parseInt(integerCheck);
            return result;

        } catch (NumberFormatException e) {
            return -1;
        }

    }
}

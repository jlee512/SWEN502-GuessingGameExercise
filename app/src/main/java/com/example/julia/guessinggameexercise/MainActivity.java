package com.example.julia.guessinggameexercise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int number_to_guess;
    int num_guesses = 0;
    ArrayList<String> game_completion_comments = new ArrayList<>();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game_completion_comments.add("Got it one, can't do much better than that");
        game_completion_comments.add("Very good! You got it in ");
        game_completion_comments.add("Passable, you go it in ");
        game_completion_comments.add("Took you long enough, guessed it in ");

        number_to_guess = (int) (Math.random() * 11);
        Log.d("ANSWER", "" + number_to_guess);

        //        Add layout elements
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                num_guesses++;

                TextView text = (TextView) findViewById(R.id.textView2);
                EditText text_input_field = (EditText) findViewById(R.id.editText);
                String text_input = text_input_field.getText().toString();

                int user_input = isInt(text_input);
                Log.d("USER GUESS", "" + user_input);

                int difference = user_input - number_to_guess;
                Log.d("DIFFERENCE", "" + difference);

                if (user_input == -1) {
                    text.setText("How hard is it to enter an integer?");
                    text_input_field.setText("");
                } else if ( user_input < 0 || user_input > 10) {
                    text.setText("I said between 0 and 10...");
                    text_input_field.setText("");
                } else if (difference > 0) {
                    if (difference > 2) {
                        text.setText("Too high!");
                        text_input_field.setText("");
                    } else {
                        text.setText("Very close, slightly too high...");
                        text_input_field.setText("");
                    }
                } else if (difference < 0) {
                    if (difference < -2) {
                        text.setText("Too low!");
                        text_input_field.setText("");
                    } else {
                        text.setText("Nearly, slightly too low...");
                        text_input_field.setText("");
                    }
                } else {
                    setContentView(R.layout.correct_guess);
                    TextView finish_game = (TextView) findViewById(R.id.gameCompletionText);
                    if (num_guesses == 1) {
                        finish_game.setText(game_completion_comments.get(0));
                    } else if (num_guesses < 3) {
                        finish_game.setText(game_completion_comments.get(1) + num_guesses);
                    } else if (num_guesses < 4) {
                        finish_game.setText(game_completion_comments.get(2) + num_guesses);
                    } else {
                        finish_game.setText(game_completion_comments.get(3) + num_guesses);
                    }

                    Button restart = (Button) findViewById(R.id.button2);
                    restart.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                        }
                    });
                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("END APP", "Closed");
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

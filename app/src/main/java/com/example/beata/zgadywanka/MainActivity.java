package com.example.beata.zgadywanka;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Beata Humeniuk
 * @version 1.0 23/11/2018
 */

public class MainActivity extends AppCompatActivity {

    private String content = null;
    private String hint;
    private int points;
    private int score;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null) {
            String categoryName = (String) bundle.get("category");
            if (categoryName != null) {
                List<Word> wordList = selectCategory(categoryName);
                Word word = getWord(wordList);
                content = word.getContent();
                hint = word.getHint();
                points = word.getPoints();
                String password = convertWordToContent(word);
                TextView text = findViewById(R.id.text);
                text.setText(password);
                TextView category = findViewById(R.id.category);
                category.setText(categoryName);
                TextView wordPoints = findViewById(R.id.points);
                String pointsText = String.valueOf(points);
                wordPoints.setText(pointsText);
                TextView scoreView = findViewById(R.id.score);
                int scoreValue = bundle.getInt("score");
                scoreView.setText(String.valueOf(scoreValue));
            }
        }
    }

    /**
     * obsługa kazdego z przycisków zawierających litery
     */
    public void getChar(View view) {
        Button button = (Button)view;
        String character = button.getText().toString();
        TextView textView = findViewById(R.id.text);
        String text = checkChar(character.charAt(0));
        textView.setText(text);
        if(!content.contains(character)){
            points--;
            TextView wordPoints = findViewById(R.id.points);
            String pointsText = String.valueOf(points);
            wordPoints.setText(pointsText);
        }

            if (isGuessed(text) || points == 0) {
            if(points == 0) {
            }
                if(isGuessed(text)) {
                    TextView scoreView = findViewById(R.id.score);
                    int scoreValue = Integer.parseInt(scoreView.getText().toString());
                    this.score = scoreValue + points;
                    scoreView.setText(String.valueOf(score));
                }
                nextWord(view);
            }
    }

    /**
     *
     *
     * @param character litera z buttonu
     * @return nowy stan wyrazu z zamieninym znakiem "-" na podana literę o ile znajduje sie ona w odgadywanym haśle
     */

    private String checkChar(char character) {
        TextView textView = findViewById(R.id.text);
        String text = textView.getText().toString();
        StringBuilder stringBuilder = new StringBuilder(text);
        for (int i = 0; i < text.length(); i++) {
            if (content.charAt(i) == character) {
                stringBuilder.setCharAt(i, character);
            }
        }
        return String.valueOf(stringBuilder);
    }

    /**
     *
     * @param answer wyraz zgadywany w obecnym stanie
     * @return informacja true/flase czy wyraz został odgadnięty
     */

    private boolean isGuessed(String answer) {
        return !answer.contains("-");
    }

    /**
     *
     * @param word wyraz do przekonwertowania
     * @return wyraz w formie "---.."
     */

    public String convertWordToContent(Word word) {
        String content = word.getContent();
        char[] newContent = new char[content.length()];
        for (int i = 0; i < newContent.length; i++) {
            if (!(content.charAt(i) == ' ')) {
                newContent[i] = '-';
            } else {
                newContent[i] = content.charAt(i);
            }
        }
        return String.valueOf(newContent);
    }

    /**
     *
     * @param words lista wyrazów
     * @return jeden losowy wyraz z listy
     */

    public Word getWord(List<Word> words) {
        Random random = new Random();
        int index = random.nextInt(words.size());
        return words.get(index);
    }

    /**
     * Matoda wyszukuje w pliku words.json liste wyrazów.
     * Kada lista w pliku words.json nosi nazwę odpowiadającą danej kategorii.
     *
     * @param newCategoryName - nazwa wybranej kategorii
     * @return lista wyrazów do nowej gry
     */
    public List<Word> selectCategory(String newCategoryName) {
        List<Word> words = new ArrayList<>();
        JSONObject input = null;
        String text = null;
        String hint = null;
        try {
            input = new JSONObject(getJson());
            JSONArray category = input.getJSONArray(newCategoryName);
            if(category!= null) {
                for(int i = 0; i < category.length(); i++) {
                    JSONObject jsonObject = category.getJSONObject(i);
                    text = jsonObject.getString("content");
                    hint = jsonObject.getString("hint");
                    Word word = new Word(text, text.length(), hint);
                    words.add(word);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return words;
    }

    /**
     * konwertowanie pliku .json do ciągu znaków String
     */

    private String getJson(){
        String json = null;
        try {
            InputStream is = getAssets().open("words.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    /**
     *
     * zmiana kategorii
     * wynik się zeruje
     */

    public void changeCategory(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("score", score);
        Intent intent = new Intent(MainActivity.this, ChangeCategoryActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     *
     * wyswietlenie następnego słowa
     * wynik się zeruje
     */

    public void nextWord(View view) {
        TextView category = findViewById(R.id.category);
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("category", category.getText().toString());
        bundle.putInt("score", score);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     *
     * wyswietlenie podpowiedzi
     * liczba punktow jest dzielona na 2
     */
    public void getHint(View view) {
        TextView hintView = findViewById(R.id.hint);
        hintView.setText(hint);
        points = points/2;
        TextView wordPoints = findViewById(R.id.points);
        String pointsText = String.valueOf(points);
        wordPoints.setText(pointsText);
    }
}

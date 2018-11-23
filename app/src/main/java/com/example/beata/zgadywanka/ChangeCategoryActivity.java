package com.example.beata.zgadywanka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * @author Beata Humeniuk
 * @version 1.0 23/11/2018
 */

public class ChangeCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_category);
    }

    /**
     * obsługa przycisków kategorii
     */

    public void chooseCategory(View view) {
        Button button = (Button)view;
        String newCategory = button.getText().toString();
        System.out.print(newCategory);
        Intent intent = new Intent(ChangeCategoryActivity.this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("category", newCategory);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}

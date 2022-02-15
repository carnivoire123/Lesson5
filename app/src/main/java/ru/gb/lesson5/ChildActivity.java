package ru.gb.lesson5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ChildActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText userLastName;
    private Button returnLastName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        userLastName = findViewById(R.id.user_last_name);
        returnLastName = findViewById(R.id.return_last_name);

        Intent myIntent = getIntent();
        if(myIntent != null && myIntent.hasExtra(MainActivity.FIRSTNAME))
        {
            String name = myIntent.getStringExtra(MainActivity.FIRSTNAME);
            userLastName.setText(name);
        }



        returnLastName.setOnClickListener(this);
    }

    public static final String LASTNAME = "LASTNAME";

    @Override
    public void onClick(View view) {
        String  last = userLastName.getText().toString();

        Intent result = new Intent();

        result.putExtra(LASTNAME, last);

        setResult(RESULT_OK, result);

        finish();

    }
}

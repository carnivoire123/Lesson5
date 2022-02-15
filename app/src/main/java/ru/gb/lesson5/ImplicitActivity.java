package ru.gb.lesson5;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ImplicitActivity  extends AppCompatActivity implements View.OnClickListener {

    private EditText implicitIntent ;
    private Button startImplicitIntent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit);
        implicitIntent = findViewById(R.id.implicit_intent);
        startImplicitIntent = findViewById(R.id.start_implicit_intent);

        startImplicitIntent.setOnClickListener(this);

        findViewById(R.id.show_chooser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                // MIME  text/html   image/jpeg
                intent.setType("*/*");
                intent.putExtra(Intent.EXTRA_TEXT, "Max");

                startActivity(
                        Intent.createChooser(intent, "Show all")
                );

            }
        });

    }

    @Override
    public void onClick(View view) {

        String uri = implicitIntent.getText().toString();

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));

        // URI universal resource identifier - универсальный идентификатор ресурса
        // http://www.google.com
        // ftp://ftp.uni.net

        if(intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intent);
        }


    }
}

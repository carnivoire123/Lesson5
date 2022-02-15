package ru.gb.lesson5;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userName;
    private Button getLastName;

    private ActivityResultLauncher<Intent> childActivityLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.user_name);
        getLastName = findViewById(R.id.get_last_name);

//        getLastName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });


        childActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent data = result.getData();
                        if(result.getResultCode() == RESULT_OK && data != null)
                        {
                            String last = data.getStringExtra(ChildActivity.LASTNAME);
                            Toast.makeText(MainActivity.this, "Last name is: " + last, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        getLastName.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_LAST_NAME)
        {
            if(resultCode == RESULT_OK && data != null)
            {
                String last = data.getStringExtra(ChildActivity.LASTNAME);

                Toast.makeText(this, "Last name is: " + last, Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



    public static final String FIRSTNAME = "FIRSTNAME";
    public static final int REQUEST_LAST_NAME = 44;

    @Override
    public void onClick(View view) {

        String name = userName.getText().toString();

        // Explicit - явный интент
        Intent childActivityIntent = new Intent(this, ChildActivity.class);

        childActivityIntent.putExtra(FIRSTNAME, name);
        // childActivityIntent.putExtra(FIRSTNAMEE, name);

        //startActivity(childActivityIntent);


        // Если хотим вернуть результат из запускаемой активити
        // startActivityForResult(childActivityIntent, REQUEST_LAST_NAME);

        // Запуск активности для результата по-новому
        childActivityLauncher.launch(childActivityIntent);
    }


    public void showChooser(View view) {

    }
}
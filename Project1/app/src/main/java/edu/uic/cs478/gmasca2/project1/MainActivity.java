package edu.uic.cs478.gmasca2.project1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void firstButtonPressed(View view) {
        Intent intent = new Intent(this, PhoneInput.class);
        startActivityForResult(intent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                (findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle extras = data.getExtras();
                        String userInputCorrect = extras.getString("keyName");

                        Uri number = Uri.parse("tel:" + userInputCorrect);
                        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                        startActivity(callIntent);
                    }
                });
            }
            else if (resultCode == RESULT_CANCELED)
            {
                Button button = (Button) findViewById(R.id.button2);
                button.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Bundle extras = data.getExtras();
                        String userInputWrong = extras.getString("keyName");

                        Toast.makeText(getApplicationContext(), "You entered an incorrect number: " + userInputWrong, Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }
}

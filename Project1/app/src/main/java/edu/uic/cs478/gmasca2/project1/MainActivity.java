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

        (findViewById(R.id.input_number_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PhoneInput.class);
                startActivityForResult(intent, 1);
            }
        });
    }



    public void onActivityResult(int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && data != null) {
            Bundle extras = data.getExtras();
            final String userInput = extras.getString("keyName");

            findViewById(R.id.call_number_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (resultCode == RESULT_OK){
                        Uri number = Uri.parse("tel:" + userInput);
                        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                        startActivity(callIntent);
                    }

                    else if (resultCode == RESULT_CANCELED){
                        Toast.makeText(getApplicationContext(), "You entered an incorrect number: " + userInput, Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}

package edu.uic.cs478.gmasca2.project1;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;

import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PhoneInput extends Activity {
    public EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_input);

        input = findViewById(R.id.number_input);

        input.setOnEditorActionListener(new TextView.OnEditorActionListener () {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if((event!=null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    String s = input.getText().toString();

                    Intent intent = getIntent();
                    intent.putExtra("keyName", s);

                    Pattern p = Pattern.compile("\\s*\\([0-9][0-9][0-9]\\) [0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]\\s*");
                    Matcher m = p.matcher(s);
                    if(m.matches())
                        setResult(RESULT_OK, intent);
                    else
                        setResult(RESULT_CANCELED, intent);

                    finish();
                }
                return false;
            }
        });
    }

}


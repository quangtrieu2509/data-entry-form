package com.trieudq194388.data_entry_form;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String EMPTY_STRING = "";
    private EditText sIDInput, nameInput, IDInput, phoneInput, emailInput, dobInput;
    private EditText homeInput, resInput;
    private RadioGroup major;
    private Button submitBtn;
    private CalendarView calendarView;
    private CheckBox agreeCheckBox;
    private TextView sIDRequire, nameRequire, IDRequire, phoneRequire, emailRequire;
    private String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapping();


        sIDInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                removeRequireWarning(sIDInput, sIDRequire);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        IDInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                removeRequireWarning(IDInput, IDRequire);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        nameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                removeRequireWarning(nameInput, nameRequire);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        phoneInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                removeRequireWarning(phoneInput, phoneRequire);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        emailInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                removeRequireWarning(emailInput, emailRequire);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = dayOfMonth + "/" + month + "/" + year;
            }
        });


    }

    private void mapping(){
        sIDInput = findViewById(R.id.sIDInput);
        nameInput = findViewById(R.id.nameInput);
        IDInput = findViewById(R.id.IDInput);
        phoneInput = findViewById(R.id.phoneNumInput);
        emailInput = findViewById(R.id.emailInput);
        dobInput = findViewById(R.id.dobInput);
        submitBtn = findViewById(R.id.submitBtn);
        agreeCheckBox = findViewById(R.id.agreeCheckBox);
        sIDRequire = findViewById(R.id.sIDRequire);
        nameRequire = findViewById(R.id.nameRequire);
        IDRequire = findViewById(R.id.IDRequire);
        phoneRequire = findViewById(R.id.phoneNumRequire);
        emailRequire = findViewById(R.id.emailRequire);
        calendarView = findViewById(R.id.calendarView);

        homeInput = findViewById(R.id.homeInput);
        resInput = findViewById(R.id.resInput);
        major = findViewById(R.id.majorContainer);
    }

    public void submitBtnOnClick(View view) {
        boolean check1 = checkRequire(sIDInput, sIDRequire);
        boolean check2 = checkRequire(IDInput, IDRequire);
        boolean check3 = checkRequire(nameInput, nameRequire);
        boolean check4 = checkRequire(phoneInput, phoneRequire);
        boolean check5 = checkRequire(emailInput, emailRequire);

        if( check1 && check2 && check3 && check4 && check5 ){
            Toast.makeText(view.getContext(), "Submit successfully", Toast.LENGTH_LONG).show();
            resetData();
        }
        else Toast.makeText(view.getContext(), "Submit failed: re-check your input data", Toast.LENGTH_LONG).show();

        hideSoftKeyboard(this);
    }
    private void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if(activity.getCurrentFocus() != null)
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }


    private boolean checkRequire(EditText editText, TextView requiredString){
        String text = editText.getText().toString();
        if(text.equals(EMPTY_STRING)){
            editText.setBackgroundResource(R.drawable.error_input_background);
            requiredString.setVisibility(View.VISIBLE);
            return false;
        }
        return true;
    }
    private void removeRequireWarning(EditText editText, TextView requiredString){
        editText.setBackgroundResource(R.drawable.input_background);
        requiredString.setVisibility(View.GONE);
    }



    public void agreeCheckBoxOnClick(View view) {
        submitBtn.setEnabled(agreeCheckBox.isChecked());
    }

    public void pickADateOnClick(View view) {
        if(selectedDate != null){
            dobInput.setText(selectedDate);
        }
    }

    private void resetData(){
        major.clearCheck();
        CheckBox c = findViewById(R.id.c); c.setChecked(false);
        CheckBox java = findViewById(R.id.java); java.setChecked(false);
        CheckBox python = findViewById(R.id.python); python.setChecked(false);
        agreeCheckBox.setChecked(false); agreeCheckBoxOnClick(this.agreeCheckBox);

        sIDInput.setText(EMPTY_STRING);
        nameInput.setText(EMPTY_STRING);
        IDInput.setText(EMPTY_STRING);
        phoneInput.setText(EMPTY_STRING);
        emailInput.setText(EMPTY_STRING);
        dobInput.setText(EMPTY_STRING);
        homeInput.setText(EMPTY_STRING);
        resInput.setText(EMPTY_STRING);

    }
}
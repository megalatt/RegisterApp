package com.example.registerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OTPVerification extends AppCompatActivity {
    EditText otp1, otp2, otp3, otp4, otp5;

    private TextView resendBtn;
    private boolean resenenabled = false;
    private int resendTime = 60;
    private int selectedETPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);
        otp1 = findViewById(R.id.otpET1);
        otp2 = findViewById(R.id.otpET2);
        otp3 = findViewById(R.id.otpET3);
        otp4 = findViewById(R.id.otpET4);
        otp5 = findViewById(R.id.otpET5);
        resendBtn = findViewById(R.id.resendBtn);
        final TextView email = findViewById(R.id.textveemail);
        final TextView mobile = findViewById(R.id.textvwphone);
        final Button verifyBtn = findViewById(R.id.verifyBtn);
        final String getemail = getIntent().getStringExtra("email");
        final String getmobile = getIntent().getStringExtra("mobile");
        email.setText(getemail);
        mobile.setText(getmobile);
        otp1.addTextChangedListener(textWatcher);
        otp2.addTextChangedListener(textWatcher);
        otp3.addTextChangedListener(textWatcher);
        otp4.addTextChangedListener(textWatcher);
        otp5.addTextChangedListener(textWatcher);
        showKeyboard(otp1);
        startCountDownTimer();
        resendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (resenenabled) {
                    startCountDownTimer();
                }
            }
        });

        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String generateOtp = otp1.getText().toString() + otp2.getText().toString() + otp3.getText().toString() + otp4.getText().toString() + otp5.getText().toString();
                if (generateOtp.length() == 5) {

                }
            }
        });
    }

    private void showKeyboard(EditText otp1) {
        otp1.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(otp1, InputMethodManager.SHOW_IMPLICIT);

    }

    private void startCountDownTimer() {
        resenenabled = false;
        resendBtn.setTextColor(Color.parseColor("#99000000"));
        new CountDownTimer(resendTime * 1000, 1000) {

            @Override
            public void onTick(long millisunitFinished) {
                resendBtn.setText("Resend code(" + (millisunitFinished / 1000) + ")");
            }

            @Override
            public void onFinish() {
                resenenabled = true;
                resendBtn.setText("Resend code");
                resendBtn.setTextColor(getResources().getColor(R.color.my_secondary));

            }
        }.start();

    }

    private final TextWatcher textWatcher = new PhoneNumberFormattingTextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s)
        {
            if(s.length()>0)
            {
                if(selectedETPosition==0){
                    selectedETPosition=1;
                    showKeyboard(otp2);
                    
                } else if (selectedETPosition==1) {
                    selectedETPosition=2;
                    showKeyboard(otp3);
                    
                } else if (selectedETPosition==2) {
                    selectedETPosition=3;
                    showKeyboard(otp4);
                } else if (selectedETPosition==3) {
                    selectedETPosition=4;
                    showKeyboard(otp5);
                }
            }
        }
    };

public boolean onKeyup(int Keycode , KeyEvent event)
{
if (Keycode==KeyEvent.KEYCODE_DEL){
if(selectedETPosition==4)
    {
        selectedETPosition=3;
        showKeyboard(otp4);}

else if(selectedETPosition==3)
{
    selectedETPosition=2;
    showKeyboard(otp3);
}
else if(selectedETPosition==2){
    selectedETPosition=1;
    showKeyboard(otp2);
} else if (selectedETPosition==1) {
    selectedETPosition=1;
    showKeyboard(otp1);
}
return true;
}
else {
return  super.onKeyUp(Keycode,event);
}}}

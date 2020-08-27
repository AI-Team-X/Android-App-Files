package com.teamx.bottomnav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.teamx.bottomnav.Buyer.ui.BuyerMainActivity;
import com.teamx.bottomnav.Expert.ui.ui.ExpertMainActivity;

public class SignIn extends AppCompatActivity {
    EditText email2, password2;
    Button btnLogin;
    TextView tvSignup, fPwd;
    FirebaseAuth loginAuth;
    String email, password;
    RadioGroup radioGroup;
    //FirebaseAuth mfirebaseAuth;
    //private  FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

       /* email2 = (EditText) findViewById(R.id.signin_email);
        password2 = findViewById(R.id.sign_in_password);
        btnLogin = findViewById(R.id.btn_signin);
        tvSignup = findViewById(R.id.newAccount);
        fPwd = findViewById(R.id.textView3);
        initView();
        addClickListener();
        initView();

        */try{
            if(TextUtils.isEmpty(loginAuth.getCurrentUser().getUid())) {
                setContentView(R.layout.activity_sign_in);

                // Uncheck or reset the radio buttons initially
                //radioGroup.clearCheck();
                email2 = (EditText) findViewById(R.id.signin_email);
                password2 = findViewById(R.id.sign_in_password);
                btnLogin = findViewById(R.id.btn_signin);
                tvSignup = findViewById(R.id.newAccount);
                fPwd = findViewById(R.id.textView3);
                initView();
                addClickListener();
            }else{
                startActivity(new Intent(SignIn.this, Enter.class));
            }
        } catch (Exception e) {
            setContentView(R.layout.activity_sign_in);


            // Uncheck or reset the radio buttons initially

            email2 = (EditText) findViewById(R.id.signin_email);
            password2 = findViewById(R.id.sign_in_password);
            btnLogin = findViewById(R.id.btn_signin);
            tvSignup = findViewById(R.id.newAccount);
            fPwd = findViewById(R.id.textView3);
            initView();
            addClickListener();
        }




    }
    private void initView(){
        loginAuth=FirebaseAuth.getInstance();

    }
    private void defineView(){


    }
    private void addClickListener(){
        // Add the Listener to the RadioGroup



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid())
                    login();

            }
        });

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signupIntent=new Intent(SignIn.this,GetStarted.class);
                startActivity(signupIntent);
            }
        });
    }
    private boolean isValid(){
        boolean isValid=false;

        email = email2.getText().toString();
        password = password2.getText().toString();

        if (TextUtils.isEmpty(email)) {
            email2.setError("Please enter email");
            email2.requestFocus();
        }
        else if (TextUtils.isEmpty(password)) {
            password2.setError("Please enter your password");
            password2.requestFocus();
        }
        else
            isValid=true;
        return isValid;
    }
    private void login(){
        loginAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignIn.this, "Login Succesful", Toast.LENGTH_SHORT).show();
                  //  String options = getIntent().getExtras().get("Choice").toString();
                    Toast.makeText(SignIn.this, "Main Activity", Toast.LENGTH_SHORT).show();
                    Intent mainIntent=new Intent(SignIn.this,MainActivity.class);
                    startActivity(mainIntent);
                    RadioGroup rg = (RadioGroup) findViewById(R.id.radio);
                    rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                    {
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            switch(checkedId){
                                case R.id.radiof:
                                    // do operations specific to this selection
                                    Toast.makeText(SignIn.this, "Main Activity", Toast.LENGTH_SHORT).show();
                                    Intent mainIntent=new Intent(SignIn.this,MainActivity.class);
                                    startActivity(mainIntent);
                                    break;
                                case R.id.radiob:
                                    // do operations specific to this selection
                                    Toast.makeText(SignIn.this, "BuyerMainActivity", Toast.LENGTH_SHORT).show();
                                    Intent mainIntent1=new Intent(SignIn.this, BuyerMainActivity.class);
                                    startActivity(mainIntent1);
                                    break;
                                case R.id.radioe:
                                    // do operations specific to this selection
                                    Toast.makeText(SignIn.this, "ExpertMainActivity", Toast.LENGTH_SHORT).show();
                                    Intent mainIntent2=new Intent(SignIn.this, ExpertMainActivity.class);
                                    startActivity(mainIntent2);
                                    break;
                            }
                        }
                    });



                  /*  Bundle bundle = new Bundle();
                    final String options = bundle.getString("Choice");
                    if (options == "Farmer Account"){
                        Toast.makeText(SignIn.this, "Main Activity", Toast.LENGTH_SHORT).show();
                        Intent mainIntent=new Intent(SignIn.this,MainActivity.class);
                        startActivity(mainIntent);
                    }
                    else if (options == "Buyer Account"){
                        Toast.makeText(SignIn.this, "BuyerMainActivity", Toast.LENGTH_SHORT).show();
                        Intent mainIntent1=new Intent(SignIn.this, BuyerMainActivity.class);
                        startActivity(mainIntent1);
                    }
                    else if (options== "Expert Account"){
                        Toast.makeText(SignIn.this, "ExpertMainActivity", Toast.LENGTH_SHORT).show();
                        Intent mainIntent2=new Intent(SignIn.this, ExpertMainActivity.class);
                        startActivity(mainIntent2);
                    }*/
                    // When submit button is clicked,
                    // Ge the Radio Button which is set
                    // If no Radio Button is set, -1 will be returned


                }else{
                    Toast.makeText(SignIn.this, "error:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }


        });
    }


}
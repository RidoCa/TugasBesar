package com.rido.tugasbesar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "RegisterActivity";
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private DatabaseReference mDatabase1;
    private FirebaseAuth mAuth1;
    private EditText edtEmail1;
    private EditText edtPass1;

    private Button btnDaftar1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //variabel tadi untuk memanggil fungsi
        mDatabase1 = FirebaseDatabase.getInstance().getReference();
        mAuth1 = FirebaseAuth.getInstance();

        // diatur sesuai id komponennya
        edtEmail1 = (EditText)findViewById(R.id.tv_email1);
        edtPass1 = (EditText)findViewById(R.id.tv_pass1);
        btnDaftar1 = (Button)findViewById(R.id.btn_daftar1);

        //nambahin method onClick, biar tombolnya bisa diklik

        btnDaftar1.setOnClickListener(this);
    }

    private void signUp() {
        Log.d(TAG, "signUp");
        if (!validateForm()) {
            return;
        }

        //showProgressDialog();
        String email = edtEmail1.getText().toString();
        String password = edtPass1.getText().toString();

        mAuth1.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUser:onComplete:" + task.isSuccessful());
                        //hideProgressDialog();

                        if (task.isSuccessful()) {
                            onAuthSuccess(task.getResult().getUser());
                        } else {
                            Toast.makeText(Main2Activity.this, "Sign Up Failed, Check your email and password",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void onAuthSuccess(FirebaseUser user) {
        String username = usernameFromEmail(user.getEmail());

        // membuat User admin baru
        writeNewAdmin(user.getUid(), username, user.getEmail(),edtPass1.getText().toString());

        // Go to MainActivity
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }

    //fungsi untuk memvalidasi EditText email dan password agar tak kosong dan sesuai format
    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(edtEmail1.getText().toString())) {
            edtEmail1.setError("Harus di isi!");
            result = false;
        } else {
            edtEmail1.setError(null);
        }

        if (TextUtils.isEmpty(edtPass1.getText().toString())) {
            edtPass1.setError("Harus di isi!");
            result = false;
        } else {
            edtPass1.setError(null);
        }

        return result;
    }

    // menulis ke Database
    private void writeNewAdmin(String userId, String name, String email, String password) {
        Admin admin = new Admin(name, email, password);

        mDatabase1.child("admins").child(userId).setValue(admin);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_daftar1) {
            signUp();
        }
    }

}

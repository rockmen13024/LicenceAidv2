package com.example.licenseaid;

import static dbcomplete.LogCredentials.database;
import static dbcomplete.LogCredentials.name;
import static dbcomplete.LogCredentials.pass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import dao.Account;
import dao.DAOAccount;
import dao.Variable;

public class create_account extends AppCompatActivity {
    public Variable overallVariable = new Variable(); final Variable var = new Variable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    public void createAccount(View view) throws InterruptedException {
        RadioButton r1 = findViewById(R.id.radioButton);
        RadioButton r2 = findViewById(R.id.radioButton2);
        TextView lastNameView = findViewById(R.id.editTextTextPersonLastName);
        TextView firstNameView = findViewById(R.id.editTextTextPersonFirstName);
        TextView emailView = findViewById(R.id.editTextTextEmail);
        TextView passwordView = findViewById(R.id.editTextTextPasswordCreatePassword);
        TextView passwordConfirmView = findViewById(R.id.editTextTextCreatePasswordConfirm);

        Thread th = new Thread(new Runnable(){
            public void run() {
                try {
                    DAOAccount account = new DAOAccount();

                    String type = null;
                    String lastName = lastNameView.getText().toString();
                    String firstName = firstNameView.getText().toString();
                    String email = emailView.getText().toString();
                    String password = passwordView.getText().toString();
                    String passwordConfirm = passwordConfirmView.getText().toString();

                    if (lastName == null){
                        Log.w("CREATEACCOUNT", "Last Name is null");
                        throw new RuntimeException("Last Name is null");
                    }

                    if (firstName == null){
                        Log.w("CREATEACCOUNT", "First Name is null");
                        throw new RuntimeException("First Name is null");
                    }

                    if (email == null){
                        Log.w("CREATEACCOUNT", "Email is null");
                        throw new RuntimeException("Email is null");
                    }

                    if (password == passwordConfirm){
                        Log.w("CREATEACCOUNT", "Passwords don't match");
                        throw new RuntimeException("Passwords don't match");
                    }

                    if (r1.isChecked()){
                        type = "Student";
                    }
                    else if(r2.isChecked()){
                        type = "Professor";
                    }else{
                        Log.w("CREATEACCOUNT", "Radio empty");
                        throw new RuntimeException("Radio empty");
                    }

                    if (account.accountExists(email)) {
                        Log.w("CREATEACCOUNT", "User exists");
                        throw new RuntimeException("User exists");
                    }

                    Account a = new Account(lastName, firstName, email, password, type);
                    account.save(a);

                    var.isOk = true;

                } catch (Exception throwables) {
                    Log.w("CREATEACCOUNT", "HOPA");
                    throwables.printStackTrace();
                    var.isOk = false;
                }
            }
        });

        th.start();
        th.join();

        if (var.isOk) {
            Toast toast = Toast.makeText(getApplicationContext(), "Account Created!", Toast.LENGTH_SHORT);
            toast.show();
            setContentView(R.layout.activity_main);
        }else{
            Log.w("CREATEACCOUNT", "Suntem aici");
            Toast toast = Toast.makeText(getApplicationContext(), "Email already used", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
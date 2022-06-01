package com.example.licenseaid;

import static dbcomplete.LogCredentials.pass;
import static dbcomplete.LogCredentials.name;
import static dbcomplete.LogCredentials.database;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.DAOAccount;
import dao.Variable;

public class MainActivity extends AppCompatActivity {
    public Connection conn;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private final Variable var = new Variable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Thread th = new Thread(new Runnable(){
            public void run() {
                try {
                    conn = DriverManager.getConnection(database, name, pass);
                    //conn =  DriverManager.getConnection("jdbc:mysql://192.168.43.110:3306/licenceaid_db?user=root&password=nicu");
                    Statement st = conn.createStatement();
                    String sql = "insert into students(StudentID) values(15)";
                    //st.executeUpdate(sql);
                    st.close();
                    Log.w("DATABASE", "Succes");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    Log.w("DATABASE", "Hmm");
                }
            }
        });
        th.start();*/
    }

    public void createAccount(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, create_account.class);
        startActivity(intent);
    }

    public void logIn(View view) throws InterruptedException{
        TextView email = findViewById(R.id.editTextTextEmailAddress);
        TextView passwd = findViewById(R.id.editTextTextPassword);
        //TextView errorText = findViewById(R.id.errorLoginText);

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                String emailValue = email.getText().toString();
                String passValue = passwd.getText().toString();;

                DAOAccount account = new DAOAccount();

                var.isOk = account.accountExists(emailValue, passValue);
            }
        });

        th.start();
        th.join();

        if (var.isOk){
            Toast toast = Toast.makeText(getApplicationContext(), "Login Succes", Toast.LENGTH_SHORT);
            toast.show();

            Intent intent = new Intent(this, MainPageStudent.class);
            //setContentView(R.layout.activity_main_page_student);
            startActivity(intent);
        }else{
            Toast toast = Toast.makeText(getApplicationContext(), "ERROR: Email or password don't match", Toast.LENGTH_SHORT);
            toast.show();
            //errorText.setText("Login failed!");
        }
    }

}
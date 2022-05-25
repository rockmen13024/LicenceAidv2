package com.example.licenseaid;

import static dbcomplete.LogCredentials.pass;
import static dbcomplete.LogCredentials.name;
import static dbcomplete.LogCredentials.database;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    public Connection conn;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread th = new Thread(new Runnable(){
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
        th.start();
    }

    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, create_account.class);
        startActivity(intent);
    }

    public void logIn(View view){

    }

}
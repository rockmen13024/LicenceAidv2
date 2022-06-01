package com.example.licenseaid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import dao.Article;
import dao.DAOAccount;
import dao.DAOArticle;
import dao.Variable;

public class MainPageStudent extends AppCompatActivity {
    private ListView lv;
    private Variable v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_student);

        try {
            this.loadArticles();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void loadArticles() throws InterruptedException{
        TextView author = findViewById(R.id.AuthorText);
        TextView title = findViewById(R.id.ArticleText);
        lv = (ListView) findViewById(R.id.listView);

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                DAOArticle article = new DAOArticle();
                v.articleList = article.getAll();
            }
        });

        th.start();
        th.join();

        ArrayAdapter<Article> arrayAdapter = new ArrayAdapter<Article>(
                this,
                //TODO schimbă layout-ul aici după ce îl faci
                android.R.layout.simple_list_item_1,
                v.articleList);

        lv.setAdapter(arrayAdapter);
    }
    public void openLink(View view){
        String link = "https://www.sciencedirect.com/science/article/pii/S0002937819306659";
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(browserIntent);
    }


}
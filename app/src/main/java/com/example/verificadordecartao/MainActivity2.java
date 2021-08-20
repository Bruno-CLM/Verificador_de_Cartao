package com.example.verificadordecartao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView mTextViewEmissor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String text = intent.getStringExtra(MainActivity.EXTRA_EMISSOR);

        final ImageView imagem = (ImageView) findViewById(R.id.imageView2);

        if (text.equals("VISA")) {
            imagem.setImageResource(R.drawable.download);
        } else if (text.equals("MASTER")) {
            imagem.setImageResource(R.drawable.download2);
        } else if (text.equals("JCB")){
            imagem.setImageResource(R.drawable.download3);
        } else if(text.equals("AMERICAN EXPRESS")){
            imagem.setImageResource(R.drawable.download4);
        } else if(text.equals("DISCOVER")){
            imagem.setImageResource(R.drawable.download5);
        } else{
            imagem.setImageResource(R.drawable.download6);
        }
        mTextViewEmissor = findViewById(R.id.textView);
        mTextViewEmissor.setText(text);
    }
}
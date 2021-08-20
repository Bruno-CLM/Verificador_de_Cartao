package com.example.verificadordecartao;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mEditText, mEditText2;
    private Button mButtonVeri, mButtonClean;

    public static String BandeiraCartao = "";

    public static final String EXTRA_EMISSOR = "package com.example.verificadordecartao.EXTRA_EMISSOR";

    public static int somaDigitos(int numero){
        if (numero < 9){
            return numero;
        }
        else {
            return (1+ (numero%10));
        }
    }

    public static boolean checkValidade(String numeroCartao) {

        int somaPar = 0;
        int somaImpar = 0;
        int aux = 0;

        //Pares
        for (int j = numeroCartao.length() - 2; j >= 0; j = j - 2) {
            aux = Integer.parseInt(numeroCartao.charAt(j) + "");
            somaPar = somaPar + somaDigitos(aux * 2);
        }

        //Impares
        for (int i = numeroCartao.length() -1; i >=0; i = i -2){
            aux = Integer.parseInt(numeroCartao.charAt(i) + "");
            somaImpar = somaImpar + aux;
        }

        //Verificar se a soma par e impar é divisivel por 20
        if ((somaPar + somaImpar) % 10 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static String checkBandeiraCartao(String num1IdEmissor, String num2IdEmissor) {

        if (num2IdEmissor.equals("37")) {
            return "AMERICAN EXPRESS";
        } else if (num2IdEmissor.equals("35" )) {
            return "JCB";
        } else if (num1IdEmissor.equals("4")) {
            return "VISA";
        } else if (num1IdEmissor.equals("5")) {
            return "MASTER";
        } else if (num1IdEmissor.equals("6")){
            return "DISCORVER";
        } else{
          return "Operadora desconhecida";
        }
    }


    public void newVerify(){
        if(TextUtils.isEmpty(mEditText.getText().toString())){
            Toast.makeText(this, "Erro de preenchimento", Toast.LENGTH_SHORT).show();
        }else{
            if(mEditText.getText().length() >= 13 && mEditText.getText().length() <=16){
                boolean flag = checkValidade(mEditText.getText().toString());
                 if (flag){
//                     Toast.makeText(this, "Cartão Válido",Toast.LENGTH_SHORT).show();
                    BandeiraCartao = checkBandeiraCartao(mEditText.getText().toString().substring(0,1) , mEditText.getText().toString().substring(0,2));
//                     Toast.makeText(this, "A bandeira do cartão:" + BandeiraCartao, Toast.LENGTH_SHORT).show();
                     opeActivity2(BandeiraCartao);
                 }else{
                     Toast.makeText(this, "Cartão Inválido", Toast.LENGTH_SHORT).show();
                 }
            }else{
                Toast.makeText(this, "Número de Cartão Inválido", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void cleanInputs(){

        mEditText.setText("");

    }

    public class NewVerifyClick implements View.OnClickListener{
        @Override
        public void onClick(View view){
            newVerify();
        }
    }

    public class CleanClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            cleanInputs();
        }
    }

    public void opeActivity2 (String BandeiraCartao){
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra(EXTRA_EMISSOR, BandeiraCartao);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = findViewById(R.id.editTextCard);


        mButtonVeri = findViewById(R.id.buttonVerify);
        mButtonVeri.setOnClickListener(new NewVerifyClick());

        mButtonClean = findViewById(R.id.buttonClean);
        mButtonClean.setOnClickListener(new CleanClick());
    }
}
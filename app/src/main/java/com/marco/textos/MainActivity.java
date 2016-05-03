package com.marco.textos;

import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends Activity implements TextToSpeech.OnInitListener {

    private TextToSpeech tts; //lo q

    private EditText texto;
    private Button btn1;

    public MainActivity() {
    }

    @Override //sobre escribe
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //c vez que se ejecute

        tts = new TextToSpeech(this,this);

        texto=(EditText)findViewById(R.id.texto); //cast to
        btn1=(Button)findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0){
                dime_algo(texto.getText().toString());
            }
        });
    }

    private void dime_algo(String texto){
        tts.speak(texto, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onInit(int status){
        if(status==TextToSpeech.SUCCESS){
            int result = tts.setLanguage(Locale.getDefault());

            if(result == TextToSpeech.LANG_MISSING_DATA) {
                btn1.setEnabled(false);
            }
            else{
                btn1.setEnabled(true);
            }
        }
        else{
            Log.e("TTS","No se inicializó");
        }
    }

}
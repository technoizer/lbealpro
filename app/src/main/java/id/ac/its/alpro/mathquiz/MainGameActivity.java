package id.ac.its.alpro.mathquiz;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MainGameActivity extends AppCompatActivity {

    TextView timer;
    String jawaban;
    String [] pilihan = {"a","b","c","d"};
    Button btnA, btnB, btnC, btnD;
    TextView soal;
    int countSoal;
    int totalBenar;
    int noSalah[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnA = (Button) findViewById(R.id.btnA);
        btnB = (Button) findViewById(R.id.btnB);
        btnC = (Button) findViewById(R.id.btnC);
        btnD = (Button) findViewById(R.id.btnD);
        timer = (TextView) findViewById(R.id.timerClock);

        new CountDownTimer(3000, 1000) {


            public void onTick(long millisUntilFinished) {
                timer.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timer.setText("done");
            }
        }.start();

        generateSoal();

    }

    public void submitAnswer(View view) {
        String jawabanUser = (String) view.getTag();
        if (jawabanUser.equals(jawaban)){
            Toast.makeText(getApplicationContext(),"Jawaban anda Benar", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Jawaban anda Salah", Toast.LENGTH_SHORT).show();
        }
        generateSoal();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void generateSoal(){
        Random rnd = ThreadLocalRandom.current();
        for (int i = pilihan.length - 1; i > 0; i--){
            int index = rnd.nextInt(i + 1);

            String a = pilihan[index];
            pilihan[index] = pilihan[i];
            pilihan[i] = a;
        }
        btnA.setText(pilihan[0]);
        btnB.setText(pilihan[1]);
        btnC.setText(pilihan[2]);
        btnD.setText(pilihan[3]);

        btnA.setTag(pilihan[0]);
        btnB.setTag(pilihan[1]);
        btnC.setTag(pilihan[2]);
        btnD.setTag(pilihan[3]);

        jawaban = "c";
    }
}

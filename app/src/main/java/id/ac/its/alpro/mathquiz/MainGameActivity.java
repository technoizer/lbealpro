package id.ac.its.alpro.mathquiz;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import id.ac.its.alpro.mathquiz.utility.Question;

public class MainGameActivity extends AppCompatActivity {

    TextView timerTxt;
    String jawaban;
    Button btnA, btnB, btnC, btnD;
    TextView soal, counterSoal;
    CountDownTimer timer;
    int currentQuestion = 0;
    List<Question> questions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        questions = (List<Question>)getIntent().getSerializableExtra("question");
//        Log.d("Tes", questions.get(0).getJawaban1());
        setContentView(R.layout.activity_main_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnA = (Button) findViewById(R.id.btnA);
        btnB = (Button) findViewById(R.id.btnB);
        btnC = (Button) findViewById(R.id.btnC);
        btnD = (Button) findViewById(R.id.btnD);
        soal = (TextView) findViewById(R.id.soal);
        counterSoal = (TextView) findViewById(R.id.counterSoal);
        timerTxt = (TextView) findViewById(R.id.timerClock);

        timer = new CountDownTimer(30000, 1000) {


            public void onTick(long millisUntilFinished) {
                timerTxt.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                gameOver();
            }
        }.start();

        generateSoal();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    public void submitAnswer(View view) {
        String jawabanUser = (String) view.getTag();
        if (jawabanUser.equals(jawaban)){
            questions.get(currentQuestion-1).setStatus(1);
//            Toast.makeText(getApplicationContext(),"Jawaban anda Benar", Toast.LENGTH_SHORT).show();
        }
        else{
            questions.get(currentQuestion-1).setStatus(0);
//            Toast.makeText(getApplicationContext(),"Jawaban anda Salah", Toast.LENGTH_SHORT).show();
        }
        if (currentQuestion < questions.size())
            generateSoal();
        else
            gameOver();

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void generateSoal(){
        currentQuestion++;
        counterSoal.setText("Soal "+currentQuestion);
        String [] pilihan = new String[4];

        pilihan[0] = questions.get(currentQuestion-1).getJawaban1();
        pilihan[1] = questions.get(currentQuestion-1).getJawaban2();
        pilihan[2] = questions.get(currentQuestion-1).getJawaban3();
        pilihan[3] = questions.get(currentQuestion-1).getJawaban4();
        jawaban = questions.get(currentQuestion-1).getKunci();
        soal.setText(questions.get(currentQuestion-1).getKonten());

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
    }

    public void gameOver(){
        timer.cancel();
        Intent intent = new Intent(this,GameOverActivity.class);
        List<Question> subquestion = new ArrayList<>();
        for (int i = 0;i < currentQuestion;i++){
            subquestion.add(questions.get(i));
        }
        intent.putExtra("question",(Serializable) subquestion);
        startActivity(intent);
        finish();
    }
}

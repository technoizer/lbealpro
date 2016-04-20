package id.ac.its.alpro.mathquiz;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.ac.its.alpro.mathquiz.R;
import id.ac.its.alpro.mathquiz.adaptor.CatagoryAdapter;
import id.ac.its.alpro.mathquiz.adaptor.ResultQuestionAdapter;
import id.ac.its.alpro.mathquiz.database.ScoreDbHelper;
import id.ac.its.alpro.mathquiz.utility.Question;
import id.ac.its.alpro.mathquiz.utility.Score;

public class GameOverActivity extends AppCompatActivity {

    List<Question> questions = new ArrayList<>();
    int score;
    TextView scoreTxt;
    RecyclerView rv;
    Dialog dialog;
    EditText namaTxt;
    Button saveBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        rv = (RecyclerView)findViewById(R.id.catagoryList);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        saveBtn = (Button) findViewById(R.id.saveBtn);


        questions = (List<Question>)getIntent().getSerializableExtra("question");
        scoreTxt = (TextView) findViewById(R.id.score);
        score = 0;
//        Log.d("Hasil", questions.toString());
        if (questions.size() > 0) {
            for (int i = 0; i < questions.size(); i++) {
                if (questions.get(i).getStatus() != null && questions.get(i).getStatus() == 1)
                    score += 10;
            }
        }
        scoreTxt.setText("Score Anda : " + score);

        ResultQuestionAdapter adapter = new ResultQuestionAdapter(questions);
        rv.setAdapter(adapter);

    }

    public void viewDetail(View view) {
        Question question = (Question) view.getTag();
        Dialog dialog = new Dialog(this);
        dialog.setTitle("Detail Soal");
        dialog.setContentView(R.layout.dialog_detail);
        TextView text = (TextView) dialog.findViewById(R.id.dialog_detail);
        text.setText(question.getKonten());
        dialog.show();
    }

    public void saveScore(View view) {
        dialog = new Dialog(this);
        dialog.setTitle("Input Nama");
        dialog.setContentView(R.layout.dialog_save_score);
        final Button save = (Button) dialog.findViewById(R.id.save);
        Button cancel = (Button) dialog.findViewById(R.id.cancel);
        namaTxt = (EditText) dialog.findViewById(R.id.nama);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScoreDbHelper db = new ScoreDbHelper(getApplicationContext());
                Score tmp = new Score();
                tmp.setPoint(score);
                if (namaTxt.getText().toString().trim().equals("") || namaTxt.getText().toString().trim() == null) {
                    Toast.makeText(getApplicationContext(), "Isikan nama terlebih dahulu", Toast.LENGTH_SHORT).show();
                } else {
                    tmp.setName(namaTxt.getText().toString().trim());
                    db.insert(tmp);
                    dialog.dismiss();
                    saveBtn.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Score Berhasil Disimpan", Toast.LENGTH_SHORT).show();

                }

            }
        });

        dialog.show();
    }

    DialogInterface.OnClickListener homeClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    dialog.dismiss();
                    break;
            }
        }
    };

    DialogInterface.OnClickListener scoreClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    Intent intent = new Intent(getApplicationContext(), HighscoreActivity.class);
                    startActivity(intent);
                    finish();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    dialog.dismiss();
                    break;
            }
        }
    };

    public void goHome(View view) {

        if (saveBtn.getVisibility() == view.GONE){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Anda Belum Menyimpan Skor Anda, apakah yakin ?").setPositiveButton("Yes", homeClickListener).setNegativeButton("No", homeClickListener).show();
        }
    }


    public void goHiscore(View view) {
        if (saveBtn.getVisibility() == view.GONE){
            Intent intent = new Intent(this, HighscoreActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Anda Belum Menyimpan Skor Anda, apakah yakin ?").setPositiveButton("Yes", scoreClickListener).setNegativeButton("No", scoreClickListener).show();
        }
    }
}

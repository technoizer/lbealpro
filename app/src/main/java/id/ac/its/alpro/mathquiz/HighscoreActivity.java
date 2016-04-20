package id.ac.its.alpro.mathquiz;

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
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.ac.its.alpro.mathquiz.adaptor.ResultQuestionAdapter;
import id.ac.its.alpro.mathquiz.adaptor.ScoreAdapter;
import id.ac.its.alpro.mathquiz.database.ScoreDbHelper;
import id.ac.its.alpro.mathquiz.utility.Score;

public class HighscoreActivity extends AppCompatActivity {
    RecyclerView rv;
    List<Score> scores = new ArrayList<>();
    AlertDialog dialogAlert;
    TextView empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        rv = (RecyclerView)findViewById(R.id.catagoryList);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        empty = (TextView) findViewById(R.id.empty);
        refreshContent();
    }

    public void clearScore(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        dialogAlert = builder.setMessage("Apakah anda yakin ?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();

    }

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    ScoreDbHelper db = new ScoreDbHelper(getApplicationContext());
                    db.clearData();
                    refreshContent();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    dialogAlert.dismiss();
                    break;
            }
        }
    };

    public void refreshContent(){
        ScoreDbHelper db = new ScoreDbHelper(getApplicationContext());
        List<Score> tmp = db.getAll();
        if (tmp.size() == 0){
            rv.setVisibility(View.GONE);
            empty.setVisibility(View.VISIBLE);
        }
        else{
            empty.setVisibility(View.GONE);
            rv.setVisibility(View.VISIBLE);
        }
        if (tmp.size() > 5) {
            for (int i = 0; i < 5; i++) {
                scores.add(tmp.get(i));
            }
        }
        else
            scores = tmp;

        ScoreAdapter adapter = new ScoreAdapter(scores);
        rv.setAdapter(adapter);
    }

    public void goHome(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void goHiscore(View view) {
//        Intent intent = new Intent(this, HighscoreActivity.class);
//        startActivity(intent);
//        finish();
    }
}

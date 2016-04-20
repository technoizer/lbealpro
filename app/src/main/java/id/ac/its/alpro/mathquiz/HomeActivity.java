package id.ac.its.alpro.mathquiz;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import id.ac.its.alpro.mathquiz.adaptor.CatagoryAdapter;
import id.ac.its.alpro.mathquiz.utility.Category;
import id.ac.its.alpro.mathquiz.utility.Question;

public class HomeActivity extends AppCompatActivity {

    List<Category> categories = new ArrayList<>();
    List<Question> questions = new ArrayList<>();
    RecyclerView rv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rv = (RecyclerView)findViewById(R.id.catagoryList);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        new GetCategory().execute("start");
    }

    public void startGame(View view) {
        Category category = (Category) view.getTag();
        questions.clear();
        new GetQuestion().execute(category.getCategory_id() + "");
    }


    private class GetCategory extends AsyncTask<String, Integer, String> {
        private ProgressDialog dialog;
        int status, error = 0;

        public GetCategory(){
            dialog = new ProgressDialog(HomeActivity.this);
        }
        @Override
        protected String doInBackground(String... params) {
            postData();
            return null;
        }

        protected void onPostExecute(String res) {
            dialog.dismiss();
            if(error == 1){
                Toast.makeText(HomeActivity.this,"Terjadi Kesalahan, coba lagi dalam beberapa saat", Toast.LENGTH_SHORT).show();
            }
            else {
                CatagoryAdapter adapter = new CatagoryAdapter(categories);
                rv.setAdapter(adapter);
            }
        }

        protected void onProgressUpdate(Integer... progress) {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please Wait a Moment...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @TargetApi(Build.VERSION_CODES.KITKAT)
        public void postData() {
            HttpClient httpclient = new DefaultHttpClient();
            String url = "http://coders-note.xyz/public/list/categories";
            HttpGet httpPost = new HttpGet(url);

            Log.d("URL", url);

            try {
                HttpResponse response = httpclient.execute(httpPost);
                status = response.getStatusLine().getStatusCode();
                if (status == 200){
                    Reader reader = new InputStreamReader(response.getEntity().getContent(), "UTF-8");
//                    Log.d("TES", getStringFromInputStream(reader));
                    Gson baru = new Gson();
                        Category[] result = baru.fromJson(reader, Category[].class);

                    for(int i=0;i<result.length;i++)
                        categories.add(result[i]);
                }
            } catch (Exception e) {
                error = 1;

            }
        }
    }

    private class GetQuestion extends AsyncTask<String, Integer, String> {
        private ProgressDialog dialog;
        int status, error = 0;

        public GetQuestion(){
            dialog = new ProgressDialog(HomeActivity.this);
        }
        @Override
        protected String doInBackground(String... params) {
            postData(params[0]);
            return null;
        }

        protected void onPostExecute(String res) {
            dialog.dismiss();
            if (error == 1){
                Toast.makeText(HomeActivity.this,"Terjadi Kesalahan, coba lagi dalam beberapa saat", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(getApplicationContext(), MainGameActivity.class);
                intent.putExtra("question", (Serializable) questions);
                startActivity(intent);
                Log.d("Pertanyaan", questions.toString());
            }
        }

        protected void onProgressUpdate(Integer... progress) {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please Wait a Moment...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @TargetApi(Build.VERSION_CODES.KITKAT)
        public void postData(String pos) {
            HttpClient httpclient = new DefaultHttpClient();
            String url = "http://coders-note.xyz/public/list/questions/"+pos;
            HttpGet httpPost = new HttpGet(url);

            Log.d("URL", url);

            try {
                HttpResponse response = httpclient.execute(httpPost);
                status = response.getStatusLine().getStatusCode();
                if (status == 200){
                    Reader reader = new InputStreamReader(response.getEntity().getContent(), "UTF-8");
//                    Log.d("TES", getStringFromInputStream(reader));
                    Gson baru = new Gson();
                    Question[] result = baru.fromJson(reader, Question[].class);
                    for(int i=0;i<result.length;i++)
                        questions.add(result[i]);
                }
            } catch (Exception e) {
                error = 1;
            }
        }
    }
}

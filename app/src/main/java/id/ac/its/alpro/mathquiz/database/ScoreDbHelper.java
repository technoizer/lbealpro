package id.ac.its.alpro.mathquiz.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import id.ac.its.alpro.mathquiz.utility.Score;

/**
 * Created by ALPRO on 24/12/2015.
 */
public class ScoreDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ScoreDB";
    // Books table name
    private static final String TABLE_SCORE = "score";

    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_POINT = "point";

    private static final String[] COLUMNS = {KEY_ID,KEY_NAMA,KEY_POINT};


    public ScoreDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TUGAS_TABLE = "CREATE TABLE tugas ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nama_tugas TEXT, " +
                "point INTEGER ";
        db.execSQL(CREATE_TUGAS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS score");
        this.onCreate(db);
    }

    public void insert(Score score){

        Log.d("insert", score.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAMA, score.getName());
        values.put(KEY_POINT, score.getPoint());
        db.insert(TABLE_SCORE, null, values);
        db.close();
    }

    public Score get(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =
                db.query(TABLE_SCORE, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[]{String.valueOf(id)}, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit
        if (cursor != null)
            cursor.moveToFirst();

        Score score = new Score();
        score.setId(Integer.parseInt(cursor.getString(0)));
        score.setName(cursor.getString(1));
        score.setPoint(Integer.parseInt(cursor.getString(2)));
        Log.d("get(" + id + ")", score.toString());
        return score;
    }

    public List<Score> getAll() {
        List<Score> scores = new LinkedList<Score>();
        String query = "SELECT  * FROM " + TABLE_SCORE + " ORDER BY point desc";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Score tugas = null;
        if (cursor.moveToFirst()) {
            do {
                tugas = new Score();
                tugas.setId(Integer.parseInt(cursor.getString(0)));
                tugas.setName(cursor.getString(1));
                tugas.setPoint(Integer.parseInt(cursor.getString(2)));
                scores.add(tugas);
            } while (cursor.moveToNext());
        }

        Log.d("getAll()", scores.toString());
        return scores;
    }

    public int update(Score score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAMA, score.getName());
        values.put(KEY_POINT, score.getPoint());
        int i = db.update(TABLE_SCORE, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(score.getId()) }); //selection args
        db.close();

        return i;
    }

    public void delete(Score score) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SCORE, //table name
                KEY_ID + " = ?",  // selections
                new String[]{String.valueOf(score.getId())}); //selections args
        db.close();
        Log.d("delete", score.toString());

    }
}

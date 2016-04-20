package id.ac.its.alpro.mathquiz.utility;

import java.io.Serializable;

/**
 * Created by ALPRO on 19/04/2016.
 */
public class Question implements Serializable {

    Integer question_id, category_id, status;
    String konten, jawaban1, jawaban2, jawaban3, jawaban4, kunci;

    public Question(Integer question_id, Integer category_id, Integer status, String konten, String jawaban1, String jawaban2, String jawaban3, String jawaban4, String kunci) {
        this.question_id = question_id;
        this.category_id = category_id;
        this.status = 0;
        this.konten = konten;
        this.jawaban1 = jawaban1;
        this.jawaban2 = jawaban2;
        this.jawaban3 = jawaban3;
        this.jawaban4 = jawaban4;
        this.kunci = kunci;
    }

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public String getJawaban1() {
        return jawaban1;
    }

    public void setJawaban1(String jawaban1) {
        this.jawaban1 = jawaban1;
    }

    public String getJawaban2() {
        return jawaban2;
    }

    public void setJawaban2(String jawaban2) {
        this.jawaban2 = jawaban2;
    }

    public String getJawaban3() {
        return jawaban3;
    }

    public void setJawaban3(String jawaban3) {
        this.jawaban3 = jawaban3;
    }

    public String getJawaban4() {
        return jawaban4;
    }

    public void setJawaban4(String jawaban4) {
        this.jawaban4 = jawaban4;
    }

    public String getKunci() {
        return kunci;
    }

    public void setKunci(String kunci) {
        this.kunci = kunci;
    }

    @Override
    public String toString() {
        return getQuestion_id() + " " + getKunci();
    }
}

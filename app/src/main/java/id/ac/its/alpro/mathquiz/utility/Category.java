package id.ac.its.alpro.mathquiz.utility;

import java.io.Serializable;

/**
 * Created by ALPRO on 19/04/2016.
 */
public class Category implements Serializable {

    Integer category_id;
    String category_nama;

    public Category(Integer category_id, String category_nama) {
        this.category_id = category_id;
        this.category_nama = category_nama;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getCategory_nama() {
        return category_nama;
    }

    public void setCategory_nama(String category_nama) {
        this.category_nama = category_nama;
    }

    @Override
    public String toString() {
        return getCategory_nama() + " " + getCategory_id();
    }
}

package id.ac.its.alpro.mathquiz.utility;

import java.io.Serializable;

/**
 * Created by ALPRO on 19/04/2016.
 */
public class Score implements Serializable {
    Integer point, id;
    String name;

    public Score(Integer point, Integer id, String name) {
        this.point = point;
        this.id = id;
        this.name = name;
    }

    public Score(){

    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName() + " " + getPoint();
    }
}

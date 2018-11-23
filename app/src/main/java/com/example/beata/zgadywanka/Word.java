package com.example.beata.zgadywanka;

/**
 * @author Beata Humeniuk
 * @version 1.0 23/11/2018
 */

public class Word {

    /**
     * @param content - wyraz
     * @param points - liczba punktów
     * @param hint - podpowiedź
     */

    private String content;
    private int points;
    private String hint;


    public Word(String content, int points, String hint) {
        this.content = content;
        this.points = points;
        this.hint = hint;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}

package net.ossrs.yasea.demo.Activity.Uitls;

/**
 * Created by kang on 2018/4/3.
 */

public class info_array_Util
{
    private String title,text,ct;
    private int type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return
                "title=" + title +
                ", text=" + text   +
                ", ct=" + ct +
                ", type=" + type;
    }
}

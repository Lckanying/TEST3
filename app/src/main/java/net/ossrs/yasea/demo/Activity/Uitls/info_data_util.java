package net.ossrs.yasea.demo.Activity.Uitls;

/**
 * Created by kang on 2018/4/3.
 */

public class info_data_util
{
   private String contentlist;
   private int allPages,ret_code;

    public String getContentlist() {
        return contentlist;
    }

    public void setContentlist(String contentlist) {
        this.contentlist = contentlist;
    }

    public int getAllPages() {
        return allPages;
    }

    public void setAllPages(int allPages) {
        this.allPages = allPages;
    }

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    @Override
    public String toString() {
        return
                "contentlist=" + contentlist+
                ", allPages=" + allPages +
                ", ret_code=" + ret_code ;
    }
}

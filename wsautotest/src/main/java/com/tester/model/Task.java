package com.tester.model;

import lombok.Data;

@Data
public class Task {
    private int id;
    private int plan_id;
    private String task_name;
    private String remark;
    private String user_id;
    private String user_name;
    private String tag_name;
    private int tag_id;
    private String content;
    private int creat_at;
    private int update_at;
    private int isdelete;


    @Override
    public String toString() {
        return ("id" + ":" + id + "," +
                "plan_id" + ":" + plan_id + "," +
                "task_name" + ":" + task_name + "," +
                "remark" + ":" + remark + "," +
                "user_id" + ":" + user_id + "," +
                "tag_name" + ":" + tag_name + "," +
                "tag_id" + ":" + tag_id + "," +
                "content" + ":" + content + "," +
                "creat_at" + ":" + creat_at + "," +
                "update_at" + ":" + update_at + "," +
                "isdelete" + ":" + isdelete + ","
        );
    }


}



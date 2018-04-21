package com.example.root.test;

public class Courses {

    public String name;
    public int image;
    public String teacher;

    public void setData(String name, int image,String teacher) {
        this.name = name;
        this.image = image;
        this.teacher = teacher;
    }

    public String getName(){
        return name;
    }
    public int getImage(){
        return image;
    }
    public String getTeacher(){
        return teacher;
    }



}



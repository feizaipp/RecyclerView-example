package com.example.recyclerview;

public class StudentEntity {
    private String name;
    private Integer age;

    public StudentEntity() {
    }

    public StudentEntity(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

package com.sparta.week01_homework.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity

public class Person extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String job;

    @Column(nullable = false)
    private String address;

    public Person(String name, int age, String job, String address) {
        this.name = name;
        this.age = age;
        this.job = job;
        this.address = address;
    }

    public Person(PersonRequestDto personRequestDto) {
        this.name = personRequestDto.getName();
        this.age = personRequestDto.getAge();
        this.job = personRequestDto.getJob();
        this.address = personRequestDto.getAddress();
    }

    //@Getter
//    public String getName() {
//        return this.name;
//    }
//
//    public int getAge() {
//        return this.age;
//    }
//
//    public String getJob() {
//        return this.job;
//    }
//
//    public String getAddress() {
//        return this.address;
//    }

    //@Setter
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public void setJob(String job) {
//        this.job = job;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }

    //#UPDATE
    public void update(PersonRequestDto requestDto) {
        this.name = requestDto.getName();
        this.age = requestDto.getAge();
        this.job = requestDto.getJob();
        this.address = requestDto.getAddress();
    }
}


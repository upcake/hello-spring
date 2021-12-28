package com.upcake.hellospring.domain;

public class Member {

    private Long id;        //시스템이 정하는 key 값
    private String name;    //고객의 이름

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

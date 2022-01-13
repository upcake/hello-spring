package com.upcake.hellospring.domain;

import javax.persistence.*;

//@Entity : JPA가 관리하는 Entity
@Entity
public class Member {

    //@Id : PK
    //IDENTITY : DB가 자동으로 생성해주는 값
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;        //시스템이 정하는 key 값

    //@Column(name = "username") 변수명이 칼럼명과 달라서 칼럼명이 username일 경우에 이렇게 어노테이션을 달아준다.
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

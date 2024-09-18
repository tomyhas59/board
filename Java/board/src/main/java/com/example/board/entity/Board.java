package com.example.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

//JPA 설정된 Entity 읽음
@Entity // = table
@Data
public class Board {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY) //mariaDB 사용
    private  Integer id;

    private  String title;

    private  String content;

}

package com.example.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

//JPA가 설정된 Entity 읽어서 처리
@Entity // = table
@Data
public class Board {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY) //mariaDB 사용
    private  Integer id;

    private  String title;

    private  String content;

    private String filename;

    private String filepath;
}

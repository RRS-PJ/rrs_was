//package com.korit.projectrrs.entity;
//
//import jakarta.persistence.*;
//
//import java.util.Date;
//
//@Entity
//@Table(name = "Todos")
//public class Todo {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    int todoID;
//
//    @JoinColumn(name = "id", nullable = false)
//    int id;
//
//    @Column(nullable = false)
//    String todoPreparationContent;
//
//    @Column(nullable = false)
//    Date todoCreateAt;
//}
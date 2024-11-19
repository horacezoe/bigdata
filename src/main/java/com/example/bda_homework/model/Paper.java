package com.example.bda_homework.model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "papers") // 映射到已存在的数据库表
public class Paper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 论文ID

    @Column(nullable = false)
    private String title; // 论文标题

    @Column(name = "abstract",columnDefinition = "TEXT", nullable = false)
    private String abstractText; // 论文摘要

    @Column(name = "category", nullable = false)
    private String category; // 论文分类（领域）

    @Column(name = "year", nullable = false)
    private int year; // 发表年份

    @Column(name = "paper_id_list", columnDefinition = "TEXT" , nullable = true)
    public String paperIdList; // 引用的论文ID列表

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstractText() {
        return abstractText;
    }

    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPaperIdList(String referencedPaperIds) {
        this.paperIdList = referencedPaperIds;
    }
}


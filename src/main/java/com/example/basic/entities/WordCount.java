package com.example.basic.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "word_count")
public class WordCount {
    @Id
    private String word;
    private Long count;

    public WordCount(String word, Long count) {
        this.word = word;
        this.count = count;
    }

    public WordCount() {

    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}

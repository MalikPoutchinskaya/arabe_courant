package com.poutchinskaya.malik.sotunisia.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 *
 */
@Entity(createInDb = false, nameInDb = "score")
public class Score {
    @Id
    @Property(nameInDb = "id")
    private Long id;
    private float value;
    private long date;
    @Property(nameInDb = "category_id")
    private long categoryId;
    @Property(nameInDb = "language_id")
    private long languageId;

    public Score(float value, long date, long categoryId, long languageId) {
        this.value = value;
        this.date = date;
        this.categoryId = categoryId;
        this.languageId = languageId;
    }

    @Generated(hash = 409141393)
    public Score(Long id, float value, long date, long categoryId, long languageId) {
        this.id = id;
        this.value = value;
        this.date = date;
        this.categoryId = categoryId;
        this.languageId = languageId;
    }

    @Generated(hash = 226049941)
    public Score() {
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getLanguageId() {
        return this.languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public Long getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public void setLanguageId(long languageId) {
        this.languageId = languageId;
    }
}

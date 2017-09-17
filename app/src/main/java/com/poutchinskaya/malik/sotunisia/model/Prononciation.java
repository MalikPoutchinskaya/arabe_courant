package com.poutchinskaya.malik.sotunisia.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * POJO Prononciation
 */

@Entity(createInDb = false, nameInDb = "prononciation")
public class Prononciation {
    @Id
    @Property(nameInDb = "id")
    private Long id;
    @Property(nameInDb = "classic_arabic")
    private String classicArabic;
    @Property(nameInDb = "arabic")
    private String arabic;
    @Property(nameInDb = "audio")
    private String audio;
    @Property(nameInDb = "description")
    private String description;
    
    @Generated(hash = 1175447982)
    public Prononciation(Long id, String classicArabic, String arabic, String audio,
                         String description) {
        this.id = id;
        this.classicArabic = classicArabic;
        this.arabic = arabic;
        this.audio = audio;
        this.description = description;
    }

    @Generated(hash = 682025640)
    public Prononciation() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassicArabic() {
        return this.classicArabic;
    }

    public void setClassicArabic(String classicArabic) {
        this.classicArabic = classicArabic;
    }

    public String getArabic() {
        return this.arabic;
    }

    public void setArabic(String arabic) {
        this.arabic = arabic;
    }

    public String getAudio() {
        return this.audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package com.poutchinskaya.malik.sotunisia.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Test Word Tunisien4
 * 
 */

@Entity(createInDb = false, nameInDb = "words")
public class Word implements Cloneable, SearchSuggestion {
    @Id
    @Property(nameInDb = "id")
    private Long id;
    @Property(nameInDb = "french")
    private String french;
    @Property(nameInDb = "arabic")
    private String arabic;
    @Property(nameInDb = "classic_arabic")
    private String classicArabic;
    @Property(nameInDb = "id_category")
    private long categoryId;
    @Property(nameInDb = "id_country")
    private long languageId;

    @Transient
    private boolean question;

    public static final Parcelable.Creator<Word> CREATOR = new Parcelable.Creator<Word>()
    {
        @Override
        public Word createFromParcel(Parcel source)
        {
            return new Word(source);
        }

        @Override
        public Word[] newArray(int size)
        {
            return new Word[size];
        }
    };

    public Word(Parcel in) {
        this.french = in.readString();
    }

    @Generated(hash = 734206180)
    public Word(Long id, String french, String arabic, String classicArabic,
            long categoryId, long languageId) {
        this.id = id;
        this.french = french;
        this.arabic = arabic;
        this.classicArabic = classicArabic;
        this.categoryId = categoryId;
        this.languageId = languageId;
    }

    @Generated(hash = 3342184)
    public Word() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrench() {
        return this.french;
    }

    public void setFrench(String french) {
        this.french = french;
    }

    public String getArabic() {
        return this.arabic;
    }

    public void setArabic(String arabic) {
        this.arabic = arabic;
    }

    public String getClassicArabic() {
        return this.classicArabic;
    }

    public void setClassicArabic(String classicArabic) {
        this.classicArabic = classicArabic;
    }

    public long getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object obj) {
        return this.id.equals(((Word) obj).getId());
    }

    public boolean isQuestion() {
        return question;
    }

    public void setQuestion(boolean question) {
        this.question = question;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public long getLanguageId() {
        return this.languageId;
    }

    public void setLanguageId(long languageId) {
        this.languageId = languageId;
    }

    @Override
    public String getBody() {
        return french;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(french);
    }
}

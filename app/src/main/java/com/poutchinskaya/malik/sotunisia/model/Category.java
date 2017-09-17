package com.poutchinskaya.malik.sotunisia.model;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.poutchinskaya.malik.sotunisia.R;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

/**
 * Categorie du mot
 */
@Entity(createInDb = false, nameInDb = "categories")
public class Category {
    @Id
    @Property(nameInDb = "id")
    private Long id;

    /**
     * Titre de la categorie
     */
    @Property(nameInDb = "name")
    private String title;

    /**
     * image de la categorie
     */
    @Property(nameInDb = "thumbnail_url")
    private String thumbnail;


    /**
     * liste de mots
     */
    @Transient
    private List<Word> words;


    /**
     * Score de la categorie
     */
    @Transient
    private Score score;


    @Generated(hash = 1866996435)
    public Category(Long id, String title, String thumbnail) {
        this.id = id;
        this.title = title;
        this.thumbnail = thumbnail;
    }

    @Generated(hash = 1150634039)
    public Category() {
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .fitCenter()
                .centerCrop()
                .placeholder(R.drawable.default_image)
                .into(view);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}

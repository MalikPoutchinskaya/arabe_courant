package com.poutchinskaya.malik.sotunisia.model;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 *
 */

@Entity(createInDb = false, nameInDb = "countries")
public class Language {
    @Id
    @Property(nameInDb = "id")
    private long id;
    @Property(nameInDb = "name")
    private String name;

    @Generated(hash = 911065577)
    public Language(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @BindingAdapter({"bind:imageName"})
    public static void loadImage(ImageView view, String pictureName) {
        if(pictureName != null) {
            int resID = view.getContext().getResources().
                    getIdentifier(pictureName.toLowerCase(), "drawable", view.getContext().getPackageName());

            Glide.with(view.getContext())
                    .load(resID)
                    .fitCenter()
                    .centerCrop()
                    .into(view);
        }


    }


    @Generated(hash = 1478671802)
    public Language() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

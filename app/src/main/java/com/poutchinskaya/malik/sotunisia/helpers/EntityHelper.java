package com.poutchinskaya.malik.sotunisia.helpers;

import android.app.Application;
import android.content.Context;

import com.poutchinskaya.malik.sotunisia.appmanagement.App;
import com.poutchinskaya.malik.sotunisia.appmanagement.Storage;
import com.poutchinskaya.malik.sotunisia.model.Category;
import com.poutchinskaya.malik.sotunisia.model.CategoryDao;
import com.poutchinskaya.malik.sotunisia.model.DaoSession;
import com.poutchinskaya.malik.sotunisia.model.Language;
import com.poutchinskaya.malik.sotunisia.model.LanguageDao;
import com.poutchinskaya.malik.sotunisia.model.Prononciation;
import com.poutchinskaya.malik.sotunisia.model.PrononciationDao;
import com.poutchinskaya.malik.sotunisia.model.Score;
import com.poutchinskaya.malik.sotunisia.model.ScoreDao;
import com.poutchinskaya.malik.sotunisia.model.Word;
import com.poutchinskaya.malik.sotunisia.model.WordDao;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Helper pour chercher les objets clé de l'app en base
 */

public class EntityHelper {
    /**
     * Context
     */
    private Context context;
    /**
     * Dao session
     */
    private DaoSession daoSession;
    //query
    private Query<Prononciation> prononciationQuery;
    private Query<Word> wordQuery;
    private Query<Category> categoryQuery;
    private Query<Language> languageQuery;
    private Query<Score> scoreQuery;
    //dao
    private PrononciationDao prononciationDao;
    private WordDao wordDao;
    private CategoryDao categoryDao;
    private LanguageDao languageDao;
    private ScoreDao scoreDao;
    //list
    private List<Word> words;
    private List<Language> languages;
    private List<Category> categories;
    private List<Score> scores;
    private List<String> prononciations;
    private HashMap<String, List<String>> detailsPronociation;

    /**
     * Constructeur
     *
     * @param context
     * @param application
     */
    public EntityHelper(Context context, Application application) {
        this.context = context;
        daoSession = ((App) application).getDaoSession();

    }


    public List<Category> getCategories() {
        categories = new ArrayList<>();
        categoryDao = daoSession.getCategoryDao();
        categoryQuery = categoryDao.queryBuilder()
                .orderAsc(CategoryDao.Properties.Title)
                .build();
        for (Category category : categoryQuery.list()) {
            category.setWords(this.getWords(category.getId()));
            category.setScore(this.getBestScore(category.getId()));
            if (!categories.contains(category) && category.getWords().size() != 0) {
                categories.add(category);
            }
        }

        return categories;
    }

    public List<Category> getCategoriesSortByAscTitle(List<Category> categoriesToSort) {
        Collections.sort(categoriesToSort, new Comparator<Category>() {
            @Override
            public int compare(Category s1, Category s2) {
                return s1.getTitle().compareToIgnoreCase(s2.getTitle());
            }
        });

        return categoriesToSort;
    }

    public List<Category> getCategoriesSortByDescTitle(List<Category> categoriesToSort) {
        Collections.reverse(categoriesToSort);
        return categoriesToSort;
    }

    public List<Category> getCategoriesSortByAscStars(List<Category> categoriesToSort) {

//        List<Category> categoriesToSort = getCategories();

        Collections.sort(categoriesToSort, new Comparator<Category>() {
            @Override
            public int compare(Category c1, Category c2) {
                return Math.round(c1.getScore().getValue() - c2.getScore().getValue()); // Ascending
            }
        });

        return categoriesToSort;
    }

    public List<Category> getCategoriesSortByDescStars(List<Category> categoriesToSort) {

//        List<Category> categoriesToSort = getCategories();

        Collections.sort(categoriesToSort, new Comparator<Category>() {
            @Override
            public int compare(Category c1, Category c2) {
                return Math.round(c2.getScore().getValue() - c1.getScore().getValue()); // Descending
            }
        });

        return categoriesToSort;
    }

    public List<Category> getCategoriesSortByAscDates(List<Category> categoriesToSort) {

//        List<Category> categoriesToSort = getCategories();

        Collections.sort(categoriesToSort, new Comparator<Category>() {
            @Override
            public int compare(Category c1, Category c2) {
                return Math.round(c1.getScore().getDate() - c2.getScore().getDate()); // Ascending
            }
        });

        return categoriesToSort;
    }

    public List<Category> getCategoriesSortByDescDates(List<Category> categoriesToSort) {

//        List<Category> categoriesToSort = getCategories();

        Collections.sort(categoriesToSort, new Comparator<Category>() {
            @Override
            public int compare(Category c1, Category c2) {
                return Math.round(c2.getScore().getDate() - c1.getScore().getDate()); // Descending
            }
        });

        return categoriesToSort;
    }


    public Category getCategory(Long categoryId) {
        categories = new ArrayList<>();
        categoryDao = daoSession.getCategoryDao();
        categoryQuery = categoryDao.queryBuilder().where(CategoryDao.Properties.Id.eq(categoryId))
                .orderAsc(CategoryDao.Properties.Title)
                .build();
        Category category = categoryQuery.list().get(0);
        category.setWords(this.getWords(categoryId));
        category.setScore(this.getBestScore(categoryId));

        return category;
    }

    /**
     * retourne la liste de words pour une category et une langue données
     *
     * @param categoryId
     * @return
     */
    public List<Word> getWords(Long categoryId) {
        words = new ArrayList<>();
        wordDao = daoSession.getWordDao();
        wordQuery = wordDao.queryBuilder()
                .where(WordDao.Properties.CategoryId.eq(categoryId),
                        WordDao.Properties.LanguageId.eq(new Storage(context).getLanguage()))
                .orderAsc(WordDao.Properties.French)
                .build();
        for (Word word : wordQuery.list()) {
            words.add(word);
        }

        return words;
    }

    /**
     * recupère les mots par une recherche par nom français
     * @param name mot français recherché
     * @return la liste des mots trouvés
     */
    public List<Word> getWordsByName(String name) {
        words = new ArrayList<>();
        wordDao = daoSession.getWordDao();
        wordQuery = wordDao.queryBuilder()
                .where(WordDao.Properties.French.like("%" + name + "%"),
                        WordDao.Properties.LanguageId.eq(new Storage(context).getLanguage()))
                .orderAsc(WordDao.Properties.French)
                .build();
        for (Word word : wordQuery.list()) {
            words.add(word);
        }

        return words;
    }

    public Score getBestScore(Long categoryId) {
        scores = new ArrayList<>();
        scoreDao = daoSession.getScoreDao();
        scoreQuery = scoreDao.queryBuilder()
                .where(ScoreDao.Properties.CategoryId.eq(categoryId),
                        ScoreDao.Properties.LanguageId.eq(new Storage(context).getLanguage()))
                .orderDesc(ScoreDao.Properties.Value)
                .build();
        if (scoreQuery.list().isEmpty()) {
            return new Score();
        } else {
            return scoreQuery.list().get(0);
        }
    }

    public List<Score> getScoresByLanguage() {
        scores = new ArrayList<>();
        scoreDao = daoSession.getScoreDao();
        scoreQuery = scoreDao.queryBuilder()
                .where(ScoreDao.Properties.LanguageId.eq(new Storage(context).getLanguage()))
                .orderAsc(ScoreDao.Properties.Date)
                .build();
        for (Score score : scoreQuery.list()) {
            scores.add(score);
        }

        return scores;
    }

    public List<Language> getLanguages() {
        languages = new ArrayList<>();
        languageDao = daoSession.getLanguageDao();
        languageQuery = languageDao.queryBuilder().build();
        for (Language language : languageQuery.list()) {
            languages.add(language);
        }

        return languages;
    }

    public Language getLanguageSelected(Long languageId) {
        languages = new ArrayList<>();
        languageDao = daoSession.getLanguageDao();
        languageQuery = languageDao.queryBuilder().where(LanguageDao.Properties.Id.eq(languageId)).build();
        return languageQuery.list().get(0);
    }

    public List<String> getPrononciations() {
        prononciationDao = daoSession.getPrononciationDao();
        prononciationQuery = prononciationDao.queryBuilder().build();
        prononciations = new ArrayList<>();
        detailsPronociation = new HashMap<>();
        for (Prononciation prononciation : prononciationQuery.list()) {
            List<String> itemsToAdd = new ArrayList<String>();
            itemsToAdd.add(prononciation.getDescription());
            prononciations.add(prononciation.getArabic());
            detailsPronociation.put(prononciation.getArabic(), itemsToAdd); // Header, Child data
        }
        String[] simpleArray = new String[prononciations.size()];
        prononciations.toArray(simpleArray);
        return prononciations;
    }

    public HashMap<String, List<String>> getDetailsPrononciation() {
        for (Prononciation prononciation : prononciationQuery.list()) {
            List<String> itemsToAdd = new ArrayList<String>();
            itemsToAdd.add(prononciation.getDescription());
            detailsPronociation.put(prononciation.getArabic(), itemsToAdd); // Header, Child data
        }
        return detailsPronociation;
    }

    public void saveScore(float value, long categoryId) {
        long languageId = new Storage(context).getLanguage();
        Score score = new Score(value, System.currentTimeMillis() / 1000, categoryId, languageId);
        scoreDao = daoSession.getScoreDao();
        scoreDao.insert(score);
    }

    public boolean isANewRecord(long idCategory, Float scoreMade) {
        return (getBestScore(idCategory).getValue() < scoreMade);
    }
}

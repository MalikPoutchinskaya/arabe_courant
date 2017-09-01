package com.poutchinskaya.malik.sotunisia.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.poutchinskaya.malik.sotunisia.R;
import com.poutchinskaya.malik.sotunisia.activities.WordActivity;
import com.poutchinskaya.malik.sotunisia.adapters.ExerciceListAdapter;
import com.poutchinskaya.malik.sotunisia.appmanagement.AppParamEnum;
import com.poutchinskaya.malik.sotunisia.model.Word;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ExerciceFragment extends Fragment {
    /**
     * Nombre de faux mots
     */
    private static int MAX_FALSE_WORDS = 3;
    /**
     * nombre d'étoile de la ratingbar
     */
    private static int MAX_RATINGBAR = 5;
    /**
     * liste de mots issue de l'activité
     */
    private List<Word> words;
    /**
     * liste de mots à trouver (diminue au file de l'exercice)
     */
    private List<Word> wordsToFind;
    /**
     * Liste de mots à afficher pour chaque exercice
     */
    private List<Word> wordsToDisplay;
    /**
     * le mot à trouver pour l'exercice
     */
    private Word wordToFind;
    /**
     * Word français à traduire
     */
    private TextView frenchWord;
    /**
     * avancement de l'exercice
     */
    private ProgressBar progressBar;
    /**
     * score de l'exercice
     */
    private int score;
    /**
     * bouton pour sexercice suivant
     */
    private Button button;

    private Random rand;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ExerciceListAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercice, container, false);
//        final FragmentExerciceBinding binding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_exercice);
        // instances
        rand = new Random();
        wordsToDisplay = new ArrayList<>();
        //TODO: binding du mot à trouver
//        binding.setVariable(BR.word, wordToFind);
        //récupération UI
        frenchWord = (TextView) view.findViewById(R.id.exercice_french_word);
        progressBar = (ProgressBar) view.findViewById(R.id.exercice_progressbar);
        button = (Button) view.findViewById(R.id.exercice_button_next);

        //setup du recyclerview
        recyclerView = (RecyclerView) view.findViewById(R.id.exercice_recycler_words);
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        //creation de l'adapter avec le call back de reponse à l'exercice
        adapter = new ExerciceListAdapter(getContext(), wordsToDisplay, wordToFind, new IExerciceResponse() {
            @Override
            public void onResponseSelected(int position, Word response) {
                addScore(response);
                //on dégrise le bouton suivant
                updateButtonAccess();
            }
        });
        recyclerView.setAdapter(adapter);

        //l'user ne doit pas pouvoir clicker une fois la reponse affiché
        recyclerView.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                //le recyclerview fait l'inverse du bouton
                return button.isEnabled();
            }
        });
        //on récupère la liste déja chargé par l'activité
        words = ((WordActivity) getActivity()).getWords();
        //on défini le max de le progressbar
        progressBar.setMax(words.size() + 1);
        //au début de l'exercice: on doit trouver tous les mots
        try {
            wordsToFind = cloneList(words);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        //On initialize l'exercice
        displayNextExercice();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //si il reste des mots à trouver, on continu l'exercice
                if (wordsToFind.size() > 0) {
                    displayNextExercice();
                    //on actualise la progress bar
                    int progress = (words.size() - wordsToFind.size() + 1);
                    progressBar.setProgress(progress);
                } else {
                    //sinon on affiche le résultat
                    Bundle mBundle = new Bundle();
                    mBundle.putFloat(AppParamEnum.SCORE.name(), getRatingScore());
                    mBundle.putLong(AppParamEnum.CATEGORY.name(), ((WordActivity) getActivity()).getCategoryId());
                    Fragment newFragment = new ExerciceDoneFragment();
                    newFragment.setArguments(mBundle);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    transaction.replace(android.R.id.content, newFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });

        return view;
    }

    /**
     * Décrémente les mots à trouver et update la liste de mots à afficher
     */
    private void displayNextExercice() {
        //clean des mots à afficher
        wordsToDisplay.clear();
        //on récupère un nouveu mot à trouver
        wordToFind = randomWord(wordsToFind);
        wordToFind.setQuestion(true);
        frenchWord.setText(wordToFind.getFrench());
        //On l'ajoute à la liste à afficher
        wordsToDisplay.add(wordToFind);
        //On le retire des futurs mots à trouver
        wordsToFind.remove(wordToFind);
        //On ajoute les faux mots
        getFalseWords();
        //update du recycler view
        adapter.notifyDataSetChanged();
        //on grise le boutton suivant
        updateButtonAccess();
    }

    /**
     * Récupère une liste de faux mot pour l'exercice
     */
    private void getFalseWords() {
        //On récupère un mot au hasard de la liste complete
        Word falseWord = randomWord(words);
        //On itère sur le nombre de faux mots
        for (int i = 0; i < MAX_FALSE_WORDS; i++) {
            //tant que le faux mot récuperé est déjà dans la liste, on en récupère un nouveau
            while (wordsToDisplay.contains(falseWord)) {
                falseWord = randomWord(words);
            }
            //on l'ajoute ensuite à la liste à montrer
            wordsToDisplay.add(falseWord);
            // on randomiez la liste
            Collections.shuffle(wordsToDisplay);
        }
    }

    /**
     * Récupère un mot depuis une liste de mots
     *
     * @param words une liste de mots
     * @return un mot
     */
    private Word randomWord(List<Word> words) {
        if (words.size() == 1) {
            return words.get(0);
        } else {
            return words.get(rand.nextInt(words.size() - 1) + 1);
        }
    }

    private void addScore(Word response) {
        if (response.equals(wordToFind)) {
            score++;
        }
    }

    private float getRatingScore() {
        return (float)(MAX_RATINGBAR * score / words.size());
    }

    private void updateButtonAccess() {
        if (button.isEnabled()) {
            button.setEnabled(false);
        } else {
            button.setEnabled(true);
        }
    }

    //TODO: externaliser cette fonction et la rendre generic
    /**
     * Clone la liste
     * @param list
     * @return
     * @throws CloneNotSupportedException
     */
    public static List<Word> cloneList(List<Word> list) throws CloneNotSupportedException {
        List<Word> clone = new ArrayList<>(list.size());
        for (Word item : list){
            clone.add((Word) item.clone());
        }
        return clone;
    }
}
package com.poutchinskaya.malik.sotunisia.appmanagement;

import android.app.Application;

import com.poutchinskaya.malik.sotunisia.model.DaoMaster;
import com.poutchinskaya.malik.sotunisia.model.DaoSession;

import org.greenrobot.greendao.database.Database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * custom application
 */

public class App extends Application {
    /**
     * A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher.
     */
    public static final boolean ENCRYPTED = false;

    private static String DB_NAME = "DBVocab_V2.sqlite";

    private DaoSession daoSession;


    @Override
    public void onCreate() {
        super.onCreate();
        PrefManager prefManager = new PrefManager(this);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "notes-db-encrypted" : DB_NAME);
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();


        // a faire apres creation de la base
        if (prefManager.isFirstTimeLaunch()) {
//        if (true) {
            try {
                copyDataBase(DB_NAME);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    /**
     * Copies your database from your local assets-folder to the just created
     * empty database in the system folder, from where it can be accessed and
     * handled. This is done by transfering bytestream.
     */
    private void copyDataBase(String dbname) throws IOException {
        // Open your local db as the input stream
        InputStream myInput = getAssets().open(dbname);
        // Path to the just created empty db
        File outFileName = getDatabasePath(dbname);
        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);
        // transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

}

package ca.httpstmarysorthodoxchurch.tools;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by roneythomas on 2016-08-14.
 */

public class Utils {
    private static FirebaseDatabase mDatabase;

    public static FirebaseDatabase getmDatabase() {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
            mDatabase.setPersistenceEnabled(true);
        }
        return mDatabase;
    }


}

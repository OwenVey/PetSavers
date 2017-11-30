package visualbasics.petsavers;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Animal.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE = null;

    public abstract AnimalDao animalDao();

    private static final String DATABASE_NAME = "animals.db";

    synchronized static AppDatabase get(Context context) {
        if (INSTANCE == null) {
            INSTANCE = create(context, false);
        }

        return (INSTANCE);
    }

    static AppDatabase create(Context context, boolean memoryOnly) {

        RoomDatabase.Builder<AppDatabase> builder;

        if (memoryOnly) {
            builder = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class);
        } else {
            builder = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME);
        }

        return (builder.build());
    }

    public static void destroy() {
        INSTANCE = null;
    }
}

package visualbasics.petsavers;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CatDao {

    @Query("SELECT * FROM cats")
    List<Dog> getAll();

    @Insert
    void insertCat(Cat cat);

    @Insert
    void insertDogs(Cat ... cats);

    @Delete
    void delete(Cat cat);
}

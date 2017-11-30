package visualbasics.petsavers;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DogDao {

    @Query("SELECT * FROM dogs")
    List<Dog> getAll();

    @Query("SELECT * FROM dogs WHERE name LIKE :name LIMIT 1")
    Dog findByName(String name);

    @Insert
    void insertDog(Dog dog);

    @Insert
    void insertDogs(Dog ... dogs);

    @Delete
    void delete(Dog dog);

    @Delete
    void deleteAll(Dog ... dogs);

}

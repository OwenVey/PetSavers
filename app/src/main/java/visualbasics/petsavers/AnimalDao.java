package visualbasics.petsavers;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface AnimalDao {

    @Query("SELECT * FROM animals")
    List<Animal> getAll();

    @Query("SELECT * FROM animals WHERE name IS :name LIMIT 1")
    Animal findByName(String name);

    @Query("SELECT * FROM animals WHERE type IS :type")
    List<Animal> findByType(String type);


    @Query("SELECT * FROM animals WHERE id IS :id LIMIT 1")
    Animal findById(int id);

    @Query("SELECT * FROM animals WHERE " +
            "type = COALESCE(:animalType, type) AND " +
            "breed = COALESCE(:breed, breed) AND " +
            "age = COALESCE(:age, age) AND " +
            "gender = COALESCE(:gender, gender) AND " +
            "weight BETWEEN :minWeight AND :maxWeight AND " +
            "color = COALESCE(:color, color)")
    List<Animal> filteredSearch(String animalType, String breed, String age, String gender, int minWeight, int maxWeight, String color);

    @Query("SELECT * FROM animals WHERE favorited IS 1")
    List<Animal> getFavorites();

    @Update
    void update(Animal... animals);

    @Insert
    void insert(Animal... animals);

    @Delete
    void delete(Animal... animals);

    @Query("DELETE FROM animals")
    void deleteAll();

}

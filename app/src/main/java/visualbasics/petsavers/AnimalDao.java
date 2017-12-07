package visualbasics.petsavers;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverter;

import java.util.List;

@Dao
public interface AnimalDao {

    @Query("SELECT * FROM animals")
    List<Animal> getAll();

    @Query("SELECT * FROM animals WHERE name LIKE :name LIMIT 1")
    Animal findByName(String name);

    @Query("SELECT * FROM animals WHERE type LIKE :type")
    List<Animal> findByType(String type);

    @Query("SELECT * FROM animals WHERE id LIKE :id LIMIT 1")
    Animal findById(int id);

    //@Query("SELECT * FROM animals WHERE type LIKE :animalType AND breed LIKE :breed AND age LIKE :age AND gender LIKE :gender and  AND")
    //Animal filteredSearch(String animalType, String breed, String age, String gender, String size, String color);

    @Insert
    void insert(Animal... animals);

    @Delete
    void delete(Animal... animals);

    @Query("DELETE FROM animals")
    void deleteAll();

}

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

    @Insert
    void insert(Animal... animals);

    @Delete
    void delete(Animal... animals);

}
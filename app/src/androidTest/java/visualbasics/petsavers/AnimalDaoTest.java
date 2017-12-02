package visualbasics.petsavers;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class AnimalDaoTest {

    private AnimalDao animalDao;
    private AppDatabase database;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        database = AppDatabase.create(context, true);
        animalDao = database.animalDao();
    }

    @After
    public void closeDb() throws IOException {
        database.close();
    }

    @Test
    public void insertAnimal() throws Exception {
        Animal animal = new Animal("Jerry", "Dog", "dog1", "Samoyed", "3 years", "Male", 10, "Black");
        animalDao.insert(animal);

        Animal result = animalDao.findByName("Jerry");

        assertEquals(1, result.id);
        assertEquals(animal.name, result.name);
    }

    @Test
    public void insertTwoAnimals() throws Exception {
        Animal animal1 = new Animal("Jerry", "Dog", "dog1", "Samoyed", "3 years", "Male", 10, "Black");
        Animal animal2 = new Animal("Sadie", "Dog", "dog2", "Black Lab", "2 years", "Female", 15, "White");
        animalDao.insert(animal1);
        animalDao.insert(animal2);

        Animal result1 = animalDao.findByName("Jerry");
        Animal result2 = animalDao.findByName("Sadie");

        assertEquals(1, result1.id);
        assertEquals(animal1.name, result1.name);

        assertEquals(2, result2.id);
        assertEquals(animal2.name, result2.name);
    }

    @Test
    public void insertMultipleAnimals() throws Exception {
        Animal animal1 = new Animal("Jerry", "Dog", "dog1", "Samoyed", "3 years", "Male", 10, "Black");
        Animal animal2 = new Animal("Sadie", "Dog", "dog2", "Black Lab", "2 years", "Female", 15, "White");
        Animal animal3 = new Animal("Tim", "Dog", "dog3", "Corgi", "3 years", "Male", 23, "Tan");
        animalDao.insert(animal1, animal2, animal3);

        List<Animal> animals = animalDao.getAll();

        assertEquals(3, animals.size());
    }

    @Test
    public void deleteAnimal() throws Exception {
        Animal animal = new Animal("Jerry", "Dog", "dog1", "Samoyed", "3 years", "Male", 10, "Black");
        animalDao.insert(animal);

        List<Animal> animals = animalDao.getAll();

        assertEquals(1, animals.size());
        animalDao.delete(animals.get(0));
        animals = animalDao.getAll();
        assertEquals(0, animals.size());
    }

    @Test
    public void deleteAllAnimals() throws Exception {
        Animal animal1 = new Animal("Jerry", "Dog", "dog1", "Samoyed", "3 years", "Male", 10, "Black");
        Animal animal2 = new Animal("Sadie", "Dog", "dog2", "Black Lab", "2 years", "Female", 15, "White");
        Animal animal3 = new Animal("Tim", "Dog", "dog3", "Corgi", "3 years", "Male", 23, "Tan");
        animalDao.insert(animal1, animal2, animal3);

        List<Animal> animals = animalDao.getAll();

        assertEquals(3, animals.size());
        animalDao.delete(animals.get(0), animals.get(1), animals.get(2));
        animals = animalDao.getAll();
        assertEquals(0, animals.size());
    }

    @Test
    public void findByType() throws Exception {
        Animal animal1 = new Animal("Jerry", "Dog", "dog1", "Samoyed", "3 years", "Male", 10, "Black");
        Animal animal2 = new Animal("Sadie", "Dog", "dog2", "Black Lab", "2 years", "Female", 15, "White");
        Animal animal3 = new Animal("Tim", "Cat", "dog3", "Corgi", "3 years", "Male", 23, "Tan");
        animalDao.insert(animal1, animal2, animal3);

        List<Animal> dogs = animalDao.findByType("Dog");
        List<Animal> cats = animalDao.findByType("Cat");

        assertEquals(2, dogs.size());
        assertEquals(1, cats.size());
    }

}

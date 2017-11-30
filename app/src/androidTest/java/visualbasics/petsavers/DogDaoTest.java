package visualbasics.petsavers;

import android.arch.persistence.room.Room;
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
public class DogDaoTest {

    private DogDao dogDao;
    private AppDatabase mDb;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        dogDao = mDb.dogDao();
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    @Test
    public void insertDog() throws Exception {
        Dog dog = new Dog("test");
        dogDao.insertDog(dog);

        Dog result = dogDao.findByName("test");

        assertEquals(1, result.getAnimalId());
        assertEquals(dog.getName(), result.getName());
    }

    @Test
    public void insertTwoDogs() throws Exception {
        Dog dog1 = new Dog("dog1");
        Dog dog2 = new Dog("dog2");
        dogDao.insertDog(dog1);
        dogDao.insertDog(dog2);

        Dog result1 = dogDao.findByName("dog1");
        Dog result2 = dogDao.findByName("dog2");

        assertEquals(1, result1.getAnimalId());
        assertEquals(dog1.getName(), result1.getName());

        assertEquals(2, result2.getAnimalId());
        assertEquals(dog2.getName(), result2.getName());
    }

    @Test
    public void insertMultipleDogs() throws Exception {
        Dog dog1 = new Dog("dog1");
        Dog dog2 = new Dog("dog2");
        Dog dog3 = new Dog("dog3");
        dogDao.insertDogs(dog1, dog2, dog3);

        List<Dog> dogs = dogDao.getAll();

        assertEquals(3, dogs.size());
    }

    @Test
    public void deleteDog() throws Exception {
        Dog dog = new Dog("dog");
        dogDao.insertDog(dog);

        List<Dog> dogs = dogDao.getAll();

        assertEquals(1, dogs.size());
        dogDao.delete(dogs.get(0));
        dogs = dogDao.getAll();
        assertEquals(0, dogs.size());
    }

    @Test
    public void deleteAllDog() throws Exception {
        Dog dog1 = new Dog("dog1");
        Dog dog2 = new Dog("dog2");
        Dog dog3 = new Dog("dog3");
        dogDao.insertDogs(dog1, dog2, dog3);

        List<Dog> dogs = dogDao.getAll();

        assertEquals(3, dogs.size());
        dogDao.deleteAll(dogs.get(0), dogs.get(1), dogs.get(2));
        dogs = dogDao.getAll();
        assertEquals(0, dogs.size());
    }


}

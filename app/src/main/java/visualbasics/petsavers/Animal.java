package visualbasics.petsavers;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "animals")
public class Animal {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String type;
    public String imageName;
    public String breed;
    public String age;
    public String gender;

    public Animal(String name, String type, String imageName, String breed, String age, String gender) {
        this.name = name;
        this.type = type;
        this.imageName = imageName;
        this.breed = breed;
        this.age = age;
        this.gender = gender;
    }

}
package visualbasics.petsavers;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {

    AnimalDao animalDao;
    Context context = null;

    private List<Animal> animalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        context = this;

        String searchType = getIntent().getStringExtra("searchType");
        String animalType = getIntent().getStringExtra("animalType");
        setTitle(animalType + "s");

        if (searchType.equals("Filtered")) {
            filteredSearch();
        } else if (searchType.equals("Category")) {
            categorySearch();
        }

    }

    private void setAdapter() {
        if (animalList.isEmpty()) {
            TextView noResults = findViewById(R.id.noResults);
            noResults.setVisibility(View.VISIBLE);
        } else {
            AnimalRecyclerViewAdapter adapter = new AnimalRecyclerViewAdapter(animalList);
            RecyclerView recyclerView = findViewById(R.id.animalRecyclerView);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.addItemDecoration(new android.support.v7.widget.DividerItemDecoration(SearchResultActivity.this, LinearLayoutManager.VERTICAL));
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(SearchResultActivity.this));
        }
    }

    private void filteredSearch() {
        Intent intent = getIntent();

        String animalType = intent.getStringExtra("animalType");
        String breed = intent.getStringExtra("breed");
        String age = intent.getStringExtra("age");
        String gender = intent.getStringExtra("gender");
        int minWeight = intent.getIntExtra("minWeight", 0);
        int maxWeight = intent.getIntExtra("maxWeight", 0);
        String color = intent.getStringExtra("color");

        if (animalType.equals("All"))
            animalType = null;
        if (breed.equals("All"))
            breed = null;
        if (age.equals("All"))
            age = null;
        if (gender.equals("All"))
            gender = null;
        if (color.equals("All"))
            color = null;

        new LoadFilteredAnimals(animalType, breed, age, gender, minWeight, maxWeight, color).execute();
    }

    private void categorySearch() {
        String animalType = getIntent().getStringExtra("animalType");

        new LoadAllAnimalsOfType(animalType).execute();
    }

    private class LoadFilteredAnimals extends AsyncTask<Void, Void, List<Animal>> {

        String animalType;
        String breed;
        String age;
        String gender;
        int minWeight;
        int maxWeight;
        String color;

        public LoadFilteredAnimals(String animalType, String breed, String age, String gender, int minWeight, int maxWeight, String color) {
            this.animalType = animalType;
            this.breed = breed;
            this.age = age;
            this.gender = gender;
            this.minWeight = minWeight;
            this.maxWeight = maxWeight;
            this.color = color;
        }

        @Override
        protected List<Animal> doInBackground(Void... params) {
            animalDao = AppDatabase.get(context).animalDao();

            animalDao.deleteAll();
            Animal animal1 = new Animal("Max", "Dog", "dog1", "Samoyed", "Puppy", "Male", 10, "White");
            Animal animal2 = new Animal("Sadie", "Dog", "dog2", "Golden Retriever", "Puppy", "Female", 15, "Golden");
            Animal animal3 = new Animal("Cooper", "Dog", "dog3", "Brown Lab", "Young", "Male", 55, "Brown");
            Animal animal4 = new Animal("Smokey", "Cat", "cat1", "Persian", "4 Years", "Male", 9, "Tan");
            animalDao.insert(animal1, animal2, animal3, animal4);


            return animalDao.filteredSearch(animalType, breed, age, gender, minWeight, maxWeight, color);
        }

        @Override
        protected void onPostExecute(List<Animal> animals) {
            animalList = animals;
            setAdapter();
        }
    }

    private class LoadAllAnimalsOfType extends AsyncTask<Void, Void, List<Animal>> {

        String animalType;

        public LoadAllAnimalsOfType(String animalType) {
            this.animalType = animalType;
        }

        @Override
        protected List<Animal> doInBackground(Void... params) {
            animalDao = AppDatabase.get(context).animalDao();

            animalDao.deleteAll();
            Animal animal1 = new Animal("Max", "Dog", "dog1", "Samoyed", "Puppy", "Male", 10, "White");
            Animal animal2 = new Animal("Sadie", "Dog", "dog2", "Golden Retriever", "Puppy", "Female", 15, "Golden");
            Animal animal3 = new Animal("Cooper", "Dog", "dog3", "Brown Lab", "Young", "Male", 55, "Brown");
            Animal animal4 = new Animal("Smokey", "Cat", "cat1", "Persian", "4 Years", "Male", 9, "Tan");
            animalDao.insert(animal1, animal2, animal3, animal4);

            return animalDao.findByType(animalType);
        }

        @Override
        protected void onPostExecute(List<Animal> animals) {
            animalList = animals;
            setAdapter();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}




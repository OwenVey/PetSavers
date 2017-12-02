package visualbasics.petsavers;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {

    private List<Animal> animalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        String animalType = getIntent().getStringExtra("animalType");
        new LoadAllAnimalsOfType(this, animalType).execute();

    }

    private void setAdapter()
    {
        AnimalRecyclerViewAdapter adapter = new AnimalRecyclerViewAdapter(animalList);
        RecyclerView recyclerView = findViewById(R.id.animalRecyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new android.support.v7.widget.DividerItemDecoration(SearchResultActivity.this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(SearchResultActivity.this));

        if (animalList.isEmpty())
        {
            TextView noResults = findViewById(R.id.noResults);
            noResults.setVisibility(View.VISIBLE);
        }
    }

    private class LoadAllAnimalsOfType extends AsyncTask<Void, Void, List<Animal>> {

        private Context context;
        String animalType;

        public LoadAllAnimalsOfType (Context context, String animalType){
            this.context = context;
            this.animalType = animalType;
        }

        @Override
        protected List<Animal> doInBackground(Void... params) {
            AnimalDao animalDao = AppDatabase.get(context).animalDao();
            animalDao.deleteAll();
            Animal animal1 = new Animal("Jerry", "Dog", "dog1", "Samoyed", "3 years", "Male", 10, "Black");
            Animal animal2 = new Animal("Sadie", "Dog", "dog2", "Black Lab", "2 years", "Female", 15, "White");
            Animal animal3 = new Animal("Tim", "Dog", "dog3", "Corgi", "3 years", "Male", 23, "Tan");
            Animal animal4 = new Animal("Tommy", "Cat", "cat1", "Persian", "4 years", "Male", 10, "White");
            animalDao.insert(animal1, animal2, animal3, animal4);
            return animalDao.findByType(animalType);
        }

        @Override
        protected void onPostExecute(List<Animal> animals) {
            animalList = animals;
            setAdapter();
        }
    }


}




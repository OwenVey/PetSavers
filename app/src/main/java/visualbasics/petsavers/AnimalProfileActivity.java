package visualbasics.petsavers;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AnimalProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_profile);

        int animalId = getIntent().getIntExtra("animalId", -1);
        new FindAnimalById(this, animalId).execute();
    }

    private class FindAnimalById extends AsyncTask<Void, Void, Animal> {

        private Context context;
        int animalId;

        public FindAnimalById (Context context, int animalId){
            this.context = context;
            this.animalId = animalId;
        }

        @Override
        protected Animal doInBackground(Void... params) {
            AnimalDao animalDao = AppDatabase.get(context).animalDao();
            return animalDao.findById(animalId);
        }

        @Override
        protected void onPostExecute(Animal animal) {
            setTitle(animal.name);
        }
    }
}

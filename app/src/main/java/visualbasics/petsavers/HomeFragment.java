package visualbasics.petsavers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void showDogs() {
        Intent intent = new Intent(getActivity(), SearchResultActivity.class);
        intent.putExtra("title", "Dogs");
        intent.putExtra("searchType", "Category");
        intent.putExtra("animalType", "Dog");
        startActivity(intent);
    }

    public void showCats() {
        Intent intent = new Intent(getActivity(), SearchResultActivity.class);
        intent.putExtra("title", "Cats");
        intent.putExtra("searchType", "Category");
        intent.putExtra("animalType", "Cat");
        startActivity(intent);
    }

    public void showHamsters() {
        Intent intent = new Intent(getActivity(), SearchResultActivity.class);
        intent.putExtra("title", "Hamsters");
        intent.putExtra("searchType", "Category");
        intent.putExtra("animalType", "Hamster");
        startActivity(intent);
    }

    public void showHedgehogs() {
        Intent intent = new Intent(getActivity(), SearchResultActivity.class);
        intent.putExtra("title", "Hedgehogs");
        intent.putExtra("searchType", "Category");
        intent.putExtra("animalType", "Hedgehog");
        startActivity(intent);
    }

    public void showRabbits() {
        Intent intent = new Intent(getActivity(), SearchResultActivity.class);
        intent.putExtra("title", "Rabbits");
        intent.putExtra("searchType", "Category");
        intent.putExtra("animalType", "Rabbit");
        startActivity(intent);
    }

    public void showFish() {
        Intent intent = new Intent(getActivity(), SearchResultActivity.class);
        intent.putExtra("title", "Fish");
        intent.putExtra("searchType", "Category");
        intent.putExtra("animalType", "Fish");
        startActivity(intent);
    }

    public void showReptiles() {
        Intent intent = new Intent(getActivity(), SearchResultActivity.class);
        intent.putExtra("title", "Reptiles");
        intent.putExtra("searchType", "Category");
        intent.putExtra("animalType", "Reptile");
        startActivity(intent);
    }

    public void showBirds() {
        Intent intent = new Intent(getActivity(), SearchResultActivity.class);
        intent.putExtra("title", "Birds");
        intent.putExtra("searchType", "Category");
        intent.putExtra("animalType", "Bird");
        startActivity(intent);
    }

    public void showFarmAnimals() {
        Intent intent = new Intent(getActivity(), SearchResultActivity.class);
        intent.putExtra("title", "Farm Animals");
        intent.putExtra("searchType", "Category");
        intent.putExtra("animalType", "FarmAnimal");
        startActivity(intent);
    }


}
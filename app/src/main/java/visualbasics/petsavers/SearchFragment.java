package visualbasics.petsavers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

public class SearchFragment extends Fragment {

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    public void search() {
        SearchableSpinner animalTypeSpinner = getActivity().findViewById(R.id.animal_type_spinner);
        SearchableSpinner breedSpinner = getActivity().findViewById(R.id.breed_spinner);
        SearchableSpinner ageSpinner = getActivity().findViewById(R.id.age_spinner);
        SearchableSpinner genderSpinner = getActivity().findViewById(R.id.gender_spinner);
        SearchableSpinner sizeSpinner = getActivity().findViewById(R.id.size_spinner);
        SearchableSpinner colorSpinner = getActivity().findViewById(R.id.color_spinner);

        String animalType = animalTypeSpinner.getSelectedItem().toString();
        String breed = breedSpinner.getSelectedItem().toString();
        String age = ageSpinner.getSelectedItem().toString();
        String gender = genderSpinner.getSelectedItem().toString();
        String size = sizeSpinner.getSelectedItem().toString();
        String color = colorSpinner.getSelectedItem().toString();

        int minWeight = 0, maxWeight = 0;
        switch (size) {
            case "Small (25 lbs or less)":
                minWeight = 0;
                maxWeight = 25;
                break;
            case "Medium (26-60 lbs)":
                minWeight = 26;
                maxWeight = 60;
                break;
            case "Large (61-100 lbs)":
                minWeight = 61;
                maxWeight = 100;
                break;
            case "X-Large (101 lbs or more)":
                minWeight = 101;
                maxWeight = 1000;
                break;
            default:
                minWeight = 0;
                maxWeight = 1000;
                break;
        }

        Intent intent = new Intent(getActivity(), SearchResultActivity.class);
        intent.putExtra("searchType", "Filtered");
        intent.putExtra("animalType", animalType);
        intent.putExtra("breed", breed);
        intent.putExtra("age", age);
        intent.putExtra("gender", gender);
        intent.putExtra("minWeight", minWeight);
        intent.putExtra("maxWeight", maxWeight);
        intent.putExtra("color", color);
        startActivity(intent);
    }

    public void clearFilters() {
        SearchableSpinner animalTypeSpinner = getActivity().findViewById(R.id.animal_type_spinner);
        SearchableSpinner breedSpinner = getActivity().findViewById(R.id.breed_spinner);
        SearchableSpinner ageSpinner = getActivity().findViewById(R.id.age_spinner);
        SearchableSpinner genderSpinner = getActivity().findViewById(R.id.gender_spinner);
        SearchableSpinner sizeSpinner = getActivity().findViewById(R.id.size_spinner);
        SearchableSpinner colorSpinner = getActivity().findViewById(R.id.color_spinner);

        animalTypeSpinner.setSelection(0);
        breedSpinner.setSelection(0);
        ageSpinner.setSelection(0);
        genderSpinner.setSelection(0);
        sizeSpinner.setSelection(0);
        colorSpinner.setSelection(0);
    }
}
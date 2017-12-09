package visualbasics.petsavers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

public class SearchFragment extends Fragment {

    SearchableSpinner animalTypeSpinner;
    SearchableSpinner breedSpinner;
    SearchableSpinner ageSpinner;
    SearchableSpinner genderSpinner;
    SearchableSpinner sizeSpinner;
    SearchableSpinner colorSpinner;

    boolean animalTypeSpinnerInitalized = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        animalTypeSpinner = view.findViewById(R.id.animal_type_spinner);
        breedSpinner = view.findViewById(R.id.breed_spinner);
        ageSpinner = view.findViewById(R.id.age_spinner);
        genderSpinner = view.findViewById(R.id.gender_spinner);
        sizeSpinner = view.findViewById(R.id.size_spinner);
        colorSpinner = view.findViewById(R.id.color_spinner);

        animalTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (animalTypeSpinnerInitalized) {
                    String animalType = adapterView.getItemAtPosition(i).toString();
                    adjustFilters(animalType);
                }
                animalTypeSpinnerInitalized = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    public void search() {

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
        animalTypeSpinner.setSelection(0);
        breedSpinner.setSelection(0);
        ageSpinner.setSelection(0);
        genderSpinner.setSelection(0);
        sizeSpinner.setSelection(0);
        colorSpinner.setSelection(0);
    }

    public void adjustFilters(String animalType) {

        LinearLayout dogFilters = getView().findViewById(R.id.dogFilters);

        if (animalType.equals("Dog")) {
            dogFilters.setVisibility(View.VISIBLE);
        } else {
            dogFilters.setVisibility(View.GONE);
        }
    }
}
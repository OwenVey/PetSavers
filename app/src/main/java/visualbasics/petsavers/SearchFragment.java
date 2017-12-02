package visualbasics.petsavers;

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

    public void clearFilters()
    {
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
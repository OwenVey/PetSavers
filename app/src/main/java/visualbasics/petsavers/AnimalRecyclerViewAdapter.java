package visualbasics.petsavers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class AnimalRecyclerViewAdapter extends RecyclerView.Adapter<AnimalRecyclerViewAdapter.AnimalViewHolder> {

    private List<Animal> animals;
    Context context;

    public class AnimalViewHolder extends RecyclerView.ViewHolder {
        public TextView name, breed, gender, age, size;
        public ImageView animalImage, favoriteImage;

        public AnimalViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.animalName);
            breed = view.findViewById(R.id.animalBreed);
            gender = view.findViewById(R.id.animalGender);
            age = view.findViewById(R.id.animalAge);
            size = view.findViewById(R.id.animalSize);
            animalImage = view.findViewById(R.id.animalImage);
            favoriteImage = view.findViewById(R.id.favoriteImage);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, AnimalDetailsActivity.class);
                    intent.putExtra("animalId", animals.get(getPosition()).id);
                    context.startActivity(intent);
                }
            });

            favoriteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    name.setText(Integer.toString(getPosition()));
                    favoriteImage.setImageResource(R.drawable.ic_favorite);
                }
            });
        }
    }

    public AnimalRecyclerViewAdapter(List<Animal> animals) {
        this.animals = animals;
    }

    public AnimalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.animal_row, parent, false);
        return new AnimalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AnimalViewHolder holder, int position) {
        Animal animal = animals.get(position);

        // sets image of animal
        int imageResourceId = (context.getResources().getIdentifier(animal.imageName, "drawable", context.getPackageName()));
        Picasso.with(context).load(imageResourceId).transform(new CropCircleTransformation()).noFade().into(holder.animalImage);

        holder.name.setText(animal.name);
        holder.breed.setText(animal.breed);
        holder.gender.setText(animal.gender + " • ");
        holder.age.setText(animal.age + " • ");
        holder.size.setText(weightToSize(animal.weight));
    }

    @Override
    public int getItemCount()
    {
        return animals.size();
    }

    // converts weight of animal to appropriate size
    private String weightToSize(int weight)
    {
        String size = null;
        if (weight <= 25)
            size = "Small";
        else if (weight >= 26 && weight <= 60)
            size = "Medium";
        else if (weight >= 61 && weight <= 100)
            size = "Large";
        else if (weight >= 101)
            size = "X-Large";
        return size;
    }

}

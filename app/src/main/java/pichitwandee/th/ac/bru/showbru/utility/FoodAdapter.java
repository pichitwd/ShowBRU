package pichitwandee.th.ac.bru.showbru.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import pichitwandee.th.ac.bru.showbru.R;

public class FoodAdapter extends BaseAdapter{

    private Context context;
    private String[] imageStrings, foodStrings, priceStrings, detailStrings;

    public FoodAdapter(Context context,
                       String[] imageStrings,
                       String[] foodStrings,
                       String[] priceStrings,
                       String[] detailStrings) {
        this.context = context;
        this.imageStrings = imageStrings;
        this.foodStrings = foodStrings;
        this.priceStrings = priceStrings;
        this.detailStrings = detailStrings;
    }

    @Override
    public int getCount() {
        return foodStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.listview_food, viewGroup, false);

        TextView foodTextView = view1.findViewById(R.id.txtFood);
        TextView priceTextView = view1.findViewById(R.id.txtPrice);
        TextView detailTextView = view1.findViewById(R.id.txtDetail);
        ImageView imageView = view1.findViewById(R.id.imvFood);

        foodTextView.setText(foodStrings[i]);
        priceTextView.setText(priceStrings[i]);
        detailTextView.setText(detailStrings[i]);

        Picasso.get().load(imageStrings[i]).into(imageView);

        return view1;
    }
}

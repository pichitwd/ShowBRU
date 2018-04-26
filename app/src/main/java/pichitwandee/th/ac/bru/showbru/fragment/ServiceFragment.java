package pichitwandee.th.ac.bru.showbru.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import pichitwandee.th.ac.bru.showbru.R;
import pichitwandee.th.ac.bru.showbru.utility.FoodAdapter;
import pichitwandee.th.ac.bru.showbru.utility.GetAllData;
import pichitwandee.th.ac.bru.showbru.utility.MyConstant;

// Fragment support v.4
public class ServiceFragment extends Fragment{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Create ListView

        createListView();
        //Ctrl+Alt+M


    } //Main method

    private void createListView() {
        ListView listView = getView().findViewById(R.id.ListViewFood);
        MyConstant myConstant = new MyConstant();

        try {

            GetAllData getAllData = new GetAllData(getActivity());
            getAllData.execute(myConstant.getUrlGetAllFood());

            String jsonString = getAllData.get();

            JSONArray jsonArray = new JSONArray(jsonString);

            String[] foodString = new String[jsonString.length()];
            String[] priceString = new String[jsonString.length()];
            String[] detailString = new String[jsonString.length()];
            String[] imageString = new String[jsonString.length()];

            for (int i=0; i<jsonArray.length(); i+=1){

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                foodString[i] = jsonObject.getString("NameFood");
                priceString[i] = jsonObject.getString("Price");
                detailString[i] = jsonObject.getString("Detail");
                imageString[i] = jsonObject.getString("ImagePath");

            }

            FoodAdapter foodAdapter = new FoodAdapter(getActivity(),
                    imageString, foodString, priceString, detailString);
            listView.setAdapter(foodAdapter);

        } catch (Exception e) {
            //Ctrl+Alt+Enter
            e.printStackTrace();
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_service, container, false);
        return  view;


    }
}

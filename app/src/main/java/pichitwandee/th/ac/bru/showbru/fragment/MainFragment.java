package pichitwandee.th.ac.bru.showbru.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import pichitwandee.th.ac.bru.showbru.R;
import pichitwandee.th.ac.bru.showbru.utility.GetAllData;
import pichitwandee.th.ac.bru.showbru.utility.MyAlert;
import pichitwandee.th.ac.bru.showbru.utility.MyConstant;

/**
 * Created by PChit-BRU on 25/4/2561.
 */

public class MainFragment extends Fragment{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    // Register Controller
        registerController();

        //Login Controller

        loginController();
        //Ctrl + Alt + M

    }   //Main Method

    private void loginController() {
        Button button = getView().findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText userEditText = getView().findViewById(R.id.edtUser);
                EditText passEditText = getView().findViewById(R.id.edtPassword);

                String userString = userEditText.getText().toString().trim();
                String passwordString = passEditText.getText().toString().trim();


                //Shift + Ctrl + Enter
                if (userString.isEmpty() || passwordString.isEmpty()) {

                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.namalDialog("Have Space",
                            "Please Fill Ever Blank");

                } else {
                    //Not Space
                    MyConstant myConstant = new MyConstant();
                    boolean b = true;

                    //Define Variable
                    String truePass = null, nameUser = null;

                    MyAlert myAlert = new MyAlert(getActivity());


                    try {
                        GetAllData getAllData = new GetAllData(getActivity());
                        getAllData.execute(myConstant.getUrlGetAllUser());

                        //Define Variable
                        String jsonString = getAllData.get();
                        Log.d("26AprilV1", "JSON ==>" + jsonString);

                        JSONArray jsonArray = new JSONArray(jsonString);

                        for (int i=0; i<jsonArray.length(); i+=1){

                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            if (userString.equals(jsonObject.getString("User"))) {

                                b = false;
                                truePass = jsonObject.getString("Password");

                                nameUser = jsonObject.getString("Name");

                            }

                        }

                        if (b) {

                            myAlert.namalDialog("User False",
                                    "No User in DataBase");
                        }
                        //Shift +Ctrl +Enter
                        else if (passwordString.equals(truePass)) {

                            Toast.makeText(getActivity(),"Welcome" + nameUser,
                                    Toast.LENGTH_SHORT).show();

                            getActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.contentMainFragment, new ServiceFragment())
                                    .commit();

                        } //Shift +Ctrl +Enter
                        else {

                            myAlert.namalDialog("Password False",
                                    "Please Try Again Password False");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }
        });
    }

    private void registerController() {
        TextView textView = getView().findViewById(R.id.txtRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Replace Fragment
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentMainFragment, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        
        View view =inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }
}   //Main Class

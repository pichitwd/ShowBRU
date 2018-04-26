package pichitwandee.th.ac.bru.showbru.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import pichitwandee.th.ac.bru.showbru.MainActivity;
import pichitwandee.th.ac.bru.showbru.R;
import pichitwandee.th.ac.bru.showbru.utility.AddNewUserToServer;
import pichitwandee.th.ac.bru.showbru.utility.MyAlert;
import pichitwandee.th.ac.bru.showbru.utility.MyConstant;

/**
 * Created by PChit-BRU on 25/4/2561.
 */

public class RegisterFragment extends Fragment{
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    //Create toolbar
        createToolbar();


    }   //Main Method
        //Alt+Insert
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Shift+Ctrl+Enter
        if (item.getItemId() == R.id.itemUpload) {

            //Create Method by Alt+Enter
            uploadValueToServer();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    //Create Method by Alt+Enter
    private void uploadValueToServer() {

        //get value from EditText
        EditText nameEditText = getView().findViewById(R.id.edtName);
        EditText userEditText = getView().findViewById(R.id.edtUser);
        EditText passwordEditText = getView().findViewById(R.id.edtPassword);

        //Chane Data Type From EditText to String
        //Duplicate Ctrl+D , Ctrl+L
        String nameString = nameEditText.getText().toString().trim();
        String userString = userEditText.getText().toString().trim();
        String passwordString = passwordEditText.getText().toString().trim();

        //Check Space //Shift + Ctrl + Enter
        if (nameString.isEmpty() || userString.isEmpty() || passwordString.isEmpty()) {
            //true // Have Space
            MyAlert myAlert = new MyAlert(getActivity());
            myAlert.namalDialog("Have Space", "Please Fill All Blank");
            //Shift + Ctrl + Enter
        } else {

            //False // Not Space
            try {
                MyConstant myConstant = new MyConstant();
                AddNewUserToServer addNewUserToServer = new AddNewUserToServer(getActivity());
                addNewUserToServer.execute(nameString, userString, passwordString,
                myConstant.getUrlAddUser());

                String result = addNewUserToServer.get();
                Log.d("26AprilV1", "result ==>" + result);
                //Alt+6 logcat

                if (Boolean.parseBoolean(result)) {
                    //Shift + Ctrl + Enter
                    getActivity().getSupportFragmentManager().popBackStack();
                } else {
                    Toast.makeText(getActivity(),"Error Cannot Upload",
                            Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                //Shift+Ctrl+Enter
                e.printStackTrace();
            }

        }



    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_register, menu);

    }

    private void createToolbar() {
        Toolbar toolbar = getActivity().findViewById(R.id.toobarRegister);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);

        //setup title
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Register");
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle("Please Fill All Blank");

        //Setup Navigation Icon Arrow
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        setHasOptionsMenu(true); // set to use menu bar


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return  view;
    }
}

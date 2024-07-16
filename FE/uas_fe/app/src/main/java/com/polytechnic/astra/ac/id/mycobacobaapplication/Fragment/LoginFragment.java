package com.polytechnic.astra.ac.id.mycobacobaapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.polytechnic.astra.ac.id.mycobacobaapplication.API.Repository.UserRepository;
import com.polytechnic.astra.ac.id.mycobacobaapplication.API.VO.UserVO;
import com.polytechnic.astra.ac.id.mycobacobaapplication.R;
import com.polytechnic.astra.ac.id.mycobacobaapplication.ViewModel.UserViewModel;

public class LoginFragment extends Fragment {

    private final String TAG = "LoginFragment";

    private EditText mEdtUsername, mEdtPassword;

    private Button mBtnLogin;

    private UserViewModel mUserViewModel;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserRepository.initialize(requireContext());
        mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        mEdtUsername = view.findViewById(R.id.edt_username);
        mEdtPassword = view.findViewById(R.id.edt_password);
        mBtnLogin = view.findViewById(R.id.btn_login);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mEdtUsername.getText().toString().trim();
                String password = mEdtPassword.getText().toString().trim();

                mUserViewModel.getUserLogin(username, password);
                mUserViewModel.getLoginResponse().observe(getViewLifecycleOwner(), new Observer<UserVO>() {
                    @Override
                    public void onChanged(UserVO userVO) {
                        if (userVO != null && userVO.getData() != null) {
                            if ("Customer".equals(userVO.getData().getProdi())) {
                                Toast.makeText(getContext(), "Anda tidak memiliki akses", Toast.LENGTH_SHORT).show();
                            } else if ("Manajemen Informatika".equals(userVO.getData().getProdi())) {
                                navigateToList();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Login salah, Akun Tidak Ditemukan", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

        return view;
    }

    private void navigateToList(){
        System.out.println("sini");
        Fragment listUserFragment = new ListUserFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_main, listUserFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
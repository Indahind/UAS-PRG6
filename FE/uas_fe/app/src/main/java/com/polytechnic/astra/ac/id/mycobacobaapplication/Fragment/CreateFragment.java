package com.polytechnic.astra.ac.id.mycobacobaapplication.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.polytechnic.astra.ac.id.mycobacobaapplication.API.Repository.UserRepository;
import com.polytechnic.astra.ac.id.mycobacobaapplication.API.VO.UserListVO;
import com.polytechnic.astra.ac.id.mycobacobaapplication.Model.UserModel;
import com.polytechnic.astra.ac.id.mycobacobaapplication.R;
import com.polytechnic.astra.ac.id.mycobacobaapplication.ViewModel.UserViewModel;

import java.util.List;

public class CreateFragment extends Fragment {

    private EditText edtUsername;
    private EditText edtPassword;
    private EditText edtNama;
    private EditText edtJabatan;
    private Button btnTambah;
    private UserViewModel mUserViewModel;
    private UserListVO userListVO;

    public CreateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_list_user, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.btn_new_user){
            //navigateToAddUser();
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserRepository.initialize(requireContext());
        mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_user, container, false);

        edtUsername = view.findViewById(R.id.tb_username);
        edtPassword = view.findViewById(R.id.tb_password);
        edtNama = view.findViewById(R.id.tb_nama);
        edtJabatan = view.findViewById(R.id.tb_jabatan);
        btnTambah = view.findViewById(R.id.btn_tambah);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                String nama = edtNama.getText().toString().trim();
                String jabatan = edtJabatan.getText().toString().trim();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(nama) || TextUtils.isEmpty(jabatan)) {
                    Toast.makeText(getContext(), "All fields must be filled", Toast.LENGTH_SHORT).show();
                    return;
                }

                mUserViewModel.getAllUserResponse().observe(getViewLifecycleOwner(), new Observer<UserListVO>() {
                    @Override
                    public void onChanged(UserListVO userListVO) {
                        if (userListVO == null) {
                            Toast.makeText(getContext(), "Failed to load user data", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        List<UserModel> dataList = userListVO.getData();
                        boolean isDuplicate = false;
                        int akuntansiCount = 0;

                        if (dataList != null && !dataList.isEmpty()) {
                            for (UserModel model : dataList) {
                                if (username.equalsIgnoreCase(model.getNim())) {
                                    isDuplicate = true;
                                    break;
                                }

                                if (model.getProdi().equalsIgnoreCase("akuntansi")) {
                                    akuntansiCount++;
                                }
                            }
                        } else {
                            Log.d("Detail Name", "Daftar data detail transaksi kosong atau null");
                        }

                        if (isDuplicate) {
                            Toast.makeText(getContext(), "Error duplikasi NIM", Toast.LENGTH_LONG).show();
                        } else if (akuntansiCount >= 3 && jabatan.equalsIgnoreCase("akuntansi")) {
                            Toast.makeText(getContext(), "Akuntansi tidak boleh lebih dari 3", Toast.LENGTH_LONG).show();
                        } else {
                            UserModel createUser = new UserModel();
                            createUser.setNim(username);
                            createUser.setPassword(password);
                            createUser.setNama(nama);
                            createUser.setProdi(jabatan);

                            mUserViewModel.saveUser(createUser);

                            mUserViewModel.getSuccessMessage().observe(getViewLifecycleOwner(), message -> {
                                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                                navigateToListUser();
                            });

                            mUserViewModel.getErrorMessage().observe(getViewLifecycleOwner(), error -> {
                                Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
                            });
                        }
                    }
                });

                mUserViewModel.getAllUser();
            }
        });

        return view;
    }
    private void navigateToListUser(){
        Fragment listFragment = new ListUserFragment();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_main, listFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
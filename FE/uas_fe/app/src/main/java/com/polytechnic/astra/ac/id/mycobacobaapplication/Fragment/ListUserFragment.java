package com.polytechnic.astra.ac.id.mycobacobaapplication.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.polytechnic.astra.ac.id.mycobacobaapplication.API.Repository.UserRepository;
import com.polytechnic.astra.ac.id.mycobacobaapplication.API.VO.UserListVO;
import com.polytechnic.astra.ac.id.mycobacobaapplication.Model.UserModel;
import com.polytechnic.astra.ac.id.mycobacobaapplication.R;
import com.polytechnic.astra.ac.id.mycobacobaapplication.ViewModel.UserViewModel;

public class ListUserFragment extends Fragment {

    private ListUserAdapter userAdapter;
    private RecyclerView userRecyclerView;
    private UserViewModel mUserViewModel;
    private static final String TAG = "ListUserAdapter";

    public ListUserFragment() {
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
        if (itemId == R.id.btn_new_user) {
            navigateToAddUser();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserRepository.initialize(requireContext());
        mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        setHasOptionsMenu(true);

        // Load initial user data
        mUserViewModel.getAllUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_user, container, false);

        userRecyclerView = view.findViewById(R.id.list_data_user);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        userAdapter = new ListUserAdapter();
        userRecyclerView.setAdapter(userAdapter);

        userAdapter.setOnItemClickListener(user -> navigateToUpdateUser(user));

        Log.i(TAG, "Calling getAllUser() in onCreateView()");
        mUserViewModel.getAllUser();
        mUserViewModel.getAllUserResponse().observe(getViewLifecycleOwner(), new Observer<UserListVO>() {
            @Override
            public void onChanged(UserListVO userListVO) {
                if (userListVO != null) {
                    userAdapter.setUserList(userListVO.getData());
                    Log.d(TAG, "userAdapter: " + userAdapter.toString());

                } else {
                    userAdapter.setUserList(new ArrayList<>());
                }
            }
        });

        return view;
    }

    private class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.ListUserHolder> {

        private List<UserModel> userList = new ArrayList<>();
        private OnItemClickListener onItemClickListener;

        @NonNull
        @Override
        public ListUserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item_list_user, parent, false);
            return new ListUserHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ListUserHolder holder, int position) {
            UserModel user = userList.get(position);
            holder.bind(user);
        }

        @Override
        public int getItemCount() {
            return userList.size();
        }

        public void setUserList(List<UserModel> userList) {
            Log.d(TAG, "setUserList: " + userList.toString());
            this.userList.clear();
            this.userList.addAll(userList);
            Log.d(TAG, "setUserList2: " + this.userList.toString());
            notifyDataSetChanged();
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        public class ListUserHolder extends RecyclerView.ViewHolder {

            private TextView mTxtUsername, mTxtNama, mTxtJabatan;

            public ListUserHolder(@NonNull View itemView) {
                super(itemView);
                mTxtUsername = itemView.findViewById(R.id.tb_username);
                mTxtNama = itemView.findViewById(R.id.tb_nama_user);
                mTxtJabatan = itemView.findViewById(R.id.tb_jabatan);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onItemClickListener != null) {
                            onItemClickListener.onItemClick(userList.get(getAdapterPosition()));
                        }
                    }
                });
            }

            public void bind(UserModel user) {
                mTxtUsername.setText("Username : " + user.getNim());
                mTxtNama.setText("Nama : " + user.getNama());
                mTxtJabatan.setText(user.getProdi());
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(UserModel user);
    }

    private void navigateToAddUser() {
        Fragment addFragment = new CreateFragment();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_main, addFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void navigateToUpdateUser(UserModel user) {
        Fragment updateFragment = new UpdateFragment();
        Bundle args = new Bundle();
        args.putParcelable("User", user);
        updateFragment.setArguments(args);
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_main, updateFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

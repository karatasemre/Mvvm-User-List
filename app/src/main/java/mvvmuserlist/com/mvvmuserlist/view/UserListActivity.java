package mvvmuserlist.com.mvvmuserlist.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Observable;
import java.util.Observer;

import mvvmuserlist.com.mvvmuserlist.R;
import mvvmuserlist.com.mvvmuserlist.databinding.ActivityUserListBinding;
import mvvmuserlist.com.mvvmuserlist.view.adapter.UserItemAdapter;
import mvvmuserlist.com.mvvmuserlist.viewmodel.UserListViewModel;

public class UserListActivity extends AppCompatActivity implements Observer {
    private ActivityUserListBinding activityUserListBinding;
    private UserListViewModel userListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initBinding();
        setupToolbar();
        setupUserListView(activityUserListBinding.listUser);
        setupObserver(userListViewModel);

    }

    private void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    private void setupUserListView(RecyclerView listUser) {
        UserItemAdapter userItemAdapter = new UserItemAdapter();
        listUser.setAdapter(userItemAdapter);
        listUser.setLayoutManager(new LinearLayoutManager(this));

    }

    private void initBinding() {
        activityUserListBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_list);
        userListViewModel = new UserListViewModel(this);
        activityUserListBinding.setUserListViewModel(userListViewModel);
    }

    private void setupToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("User List");
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof UserListViewModel){
            UserListViewModel userListViewModel = (UserListViewModel) o;
            UserItemAdapter userItemAdapter = (UserItemAdapter) activityUserListBinding.listUser.getAdapter();
            userItemAdapter.setUserList(userListViewModel.getAllUsers());
        }
    }
}

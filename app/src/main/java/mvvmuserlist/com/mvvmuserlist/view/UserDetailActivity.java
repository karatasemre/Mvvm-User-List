package mvvmuserlist.com.mvvmuserlist.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import mvvmuserlist.com.mvvmuserlist.R;
import mvvmuserlist.com.mvvmuserlist.databinding.ActivityUserDetailBinding;
import mvvmuserlist.com.mvvmuserlist.model.User;
import mvvmuserlist.com.mvvmuserlist.viewmodel.UserDetailViewModel;

public class UserDetailActivity extends AppCompatActivity {

    private ActivityUserDetailBinding activityUserDetailBinding;

    public static final String TAG = "EXTRA_OBJECT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityUserDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_detail);

        setupToolbar();
        getDataAndDisplay();
    }

    private void setupToolbar() {
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("User Details");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void getDataAndDisplay() {
        User user = (User) getIntent().getSerializableExtra(TAG);
        UserDetailViewModel userDetailViewModel = new UserDetailViewModel(user);
        activityUserDetailBinding.setUserDetailViewModel(userDetailViewModel);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
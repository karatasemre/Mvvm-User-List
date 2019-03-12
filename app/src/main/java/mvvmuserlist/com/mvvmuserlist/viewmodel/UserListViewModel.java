package mvvmuserlist.com.mvvmuserlist.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import mvvmuserlist.com.mvvmuserlist.data.ApiClient;
import mvvmuserlist.com.mvvmuserlist.data.ApiInterface;
import mvvmuserlist.com.mvvmuserlist.data.UserResponseHandler;
import mvvmuserlist.com.mvvmuserlist.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListViewModel extends Observable {
    public ObservableInt emptyViewVisibility;
    public ObservableInt recyclerViewVisibility;
    public ObservableField<String> emptyViewText;
    public static final int TOTAL_RESULT = 100;

    public Context context;
    private List<User> userList;

    public UserListViewModel(Context context) {
        this.context = context;
        this.userList = new ArrayList<>();
        emptyViewVisibility = new ObservableInt(View.VISIBLE);
        recyclerViewVisibility = new ObservableInt(View.GONE);
        emptyViewText = new ObservableField<>("Press FAB button to load users");

    }

    public void onClickFAB(View view) {
        fetchUsersList();
    }

    public void fetchUsersList() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponseHandler> userCall = apiInterface.getUserList(TOTAL_RESULT);
        userCall.enqueue(new Callback<UserResponseHandler>() {
            @Override
            public void onResponse(Call<UserResponseHandler> call, Response<UserResponseHandler> response) {
                emptyViewVisibility.set(View.GONE);
                recyclerViewVisibility.set(View.VISIBLE);
                changeUserDataSet(response.body().getResults());
            }

            @Override
            public void onFailure(Call<UserResponseHandler> call, Throwable t) {
                emptyViewVisibility.set(View.VISIBLE);
                recyclerViewVisibility.set(View.GONE);
            }
        });
    }

    public void changeUserDataSet(List<User> users) {
        userList.addAll(users);
        setChanged();
        notifyObservers();
    }

    public List<User> getAllUsers() {
        return userList;
    }
}

package mvvmuserlist.com.mvvmuserlist.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import mvvmuserlist.com.mvvmuserlist.R;
import mvvmuserlist.com.mvvmuserlist.databinding.ItemUserBinding;
import mvvmuserlist.com.mvvmuserlist.model.User;
import mvvmuserlist.com.mvvmuserlist.viewmodel.UserItemViewModel;

public class UserItemAdapter extends RecyclerView.Adapter<UserItemAdapter.ViewHolder> {

    private List<User> userList;

    public UserItemAdapter() {
        this.userList = Collections.emptyList();
    }
    public UserItemAdapter(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemUserBinding itemUserBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_user
                , parent , false);
        return new ViewHolder(itemUserBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindPeople(userList.get(position));
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ItemUserBinding itemUserBinding;

        public ViewHolder(ItemUserBinding itemUserBinding) {
            super(itemUserBinding.itemUser);
            this.itemUserBinding = itemUserBinding;
        }

        void bindPeople(User user) {
            if (itemUserBinding.getItemUserViewModel() == null) {
                itemUserBinding.setItemUserViewModel(new UserItemViewModel(itemView.getContext(), user));
            } else {
                itemUserBinding.getItemUserViewModel().setUser(user);
            }
        }
    }
}

package com.example.carrentalprototype.admin.ui.userlist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class adminUserListModel extends ViewModel {

    private MutableLiveData<String> mText;

    public adminUserListModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
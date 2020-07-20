package com.example.carrentalprototype.ui.myAccount;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class myAccountViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public myAccountViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
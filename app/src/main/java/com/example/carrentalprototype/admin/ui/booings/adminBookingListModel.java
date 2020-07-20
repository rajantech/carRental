package com.example.carrentalprototype.admin.ui.booings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class adminBookingListModel extends ViewModel {

    private MutableLiveData<String> mText;

    public adminBookingListModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is bokings fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
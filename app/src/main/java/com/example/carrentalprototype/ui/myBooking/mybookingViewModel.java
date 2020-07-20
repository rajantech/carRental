package com.example.carrentalprototype.ui.myBooking;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class mybookingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public mybookingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is mybooking fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
package com.example.carrentalprototype.ui.bookingDetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class clientBookingDetailsModel extends ViewModel {

    private MutableLiveData<String> mText;

    public clientBookingDetailsModel() {
        //mText = new MutableLiveData<>();
        //mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
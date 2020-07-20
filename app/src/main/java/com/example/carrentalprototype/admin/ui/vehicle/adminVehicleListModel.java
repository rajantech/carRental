package com.example.carrentalprototype.admin.ui.vehicle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class adminVehicleListModel extends ViewModel {

    private MutableLiveData<String> mText;

    public adminVehicleListModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is vehicle list fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}

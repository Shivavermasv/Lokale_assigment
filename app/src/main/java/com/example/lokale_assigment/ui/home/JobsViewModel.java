package com.example.lokale_assigment.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class JobsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public JobsViewModel() {
        mText = new MutableLiveData<> ();
        mText.setValue ( "This is home fragment" );
    }

    public LiveData<String> getText() {
        return mText;
    }
}
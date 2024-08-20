package com.example.lokale_assigment.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BookMarkViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public BookMarkViewModel() {
        mText = new MutableLiveData<> ();
        mText.setValue ( "This is dashboard fragment" );
    }

    public LiveData<String> getText() {
        return mText;
    }
}
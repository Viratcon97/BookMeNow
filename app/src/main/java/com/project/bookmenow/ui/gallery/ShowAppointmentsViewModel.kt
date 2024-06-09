package com.project.bookmenow.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShowAppointmentsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Show Appointments Fragment"
    }
    val text: LiveData<String> = _text
}
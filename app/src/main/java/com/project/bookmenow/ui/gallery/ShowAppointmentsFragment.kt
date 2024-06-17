package com.project.bookmenow.ui.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.project.bookmenow.databinding.FragmentShowAppointmentsBinding
import com.project.bookmenow.model.BookAppointment

class ShowAppointmentsFragment : Fragment() {

    private var _binding: FragmentShowAppointmentsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val showAppointmentsViewModel =
            ViewModelProvider(this).get(ShowAppointmentsViewModel::class.java)

        _binding = FragmentShowAppointmentsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGallery
        showAppointmentsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db.collection("BookAppointments")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {

                    Log.d("TAG", "ShowAppointmentsFragment Data - ${document.id} => ${document.data}")
                    lateinit var data : BookAppointment
                    data.name = document.data["name"].toString()
                    Log.d("TAG", "ShowAppointmentsFragment Data email- ${data.name}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
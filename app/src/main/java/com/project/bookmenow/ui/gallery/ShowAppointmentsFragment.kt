package com.project.bookmenow.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.project.bookmenow.databinding.FragmentShowAppointmentsBinding

class ShowAppointmentsFragment : Fragment() {

    private var _binding: FragmentShowAppointmentsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.project.bookmenow.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.project.bookmenow.adapter.TimeSlotsGridAdapter
import com.project.bookmenow.databinding.FragmentHomeBinding
import com.project.bookmenow.utils.Constants

class HomeFragment : Fragment(), TimeSlotsGridAdapter.OnItemClickListener {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var gridAdapter: TimeSlotsGridAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/

        /*String[] quarterHours = {"00","15","30","45"};
        List<String> times = new ArrayList<String>; // <-- List instead of array

        for(int i = 0; i < 24; i++) {
            for(int j = 0; j < 4; j++) {
            String time = i + ":" + quarterHours[j];
            if(i < 10) {
                time = "0" + time;
            }
            times.add("Today " + time); // <-- no need to care about indexes
        }
        }*/

        val times = ArrayList<String>()

        for ( i in Constants.startTime until  Constants.endTime){
            for (j in 0 until 4){
                var time = "$i : ${Constants.quarterHours[j]}"
                if(i < 10){
                    time = "0$time"
                }
                times.add(time)
                Log.d("TAG",time)
            }
        }

        gridAdapter = TimeSlotsGridAdapter(requireActivity(),times,this)
        _binding!!.gridLayoutTimeSlots.adapter = gridAdapter
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(selectedValue: String) {
       
    }
}
package com.project.bookmenow.ui.book_appointment

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.applandeo.materialcalendarview.CalendarDay
import com.applandeo.materialcalendarview.listeners.OnCalendarDayClickListener
import com.project.bookmenow.R
import com.project.bookmenow.adapter.TimeSlotsGridAdapter
import com.project.bookmenow.databinding.FragmentBookAppointmentBinding
import com.project.bookmenow.utils.Constants

class BookAppointmentFragment : Fragment(), TimeSlotsGridAdapter.OnItemClickListener {

    private var _binding: FragmentBookAppointmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var gridAdapter: TimeSlotsGridAdapter

    private var selectedDate : String? = null
    private var selectedTime : String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentBookAppointmentBinding.inflate(inflater, container, false)
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

        //Adapter
        gridAdapter = TimeSlotsGridAdapter(requireActivity(),times,this)
        _binding!!.gridLayoutTimeSlots.adapter = gridAdapter

        //Calendar click event
        _binding!!.calendarView.setOnCalendarDayClickListener(object : OnCalendarDayClickListener{
            override fun onClick(calendarDay: CalendarDay) {
                Log.d("TAG","${calendarDay.calendar.time}")
                selectedDate = calendarDay.calendar.time.toString()
            }
        })

        //Submit click event
        _binding!!.button.setOnClickListener {

            //Check if date and time is selected or not
            if(selectedDate.equals(null) || selectedTime.equals(null)){
                Toast.makeText(requireActivity(),"Please select Date and Time!",Toast.LENGTH_LONG).show()
            }else{
                //Open a dialog and get name and contact number
                val dialog = Dialog(requireActivity())
                dialog.setContentView(R.layout.dialog_confirm_appointment)
                dialog.setCancelable(true)
                val button = dialog.findViewById<Button>(R.id.btnSubmit)
                button.setOnClickListener {

                }
                dialog.show()
            }

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(selectedValue: String) {
       selectedTime = selectedValue
    }
}
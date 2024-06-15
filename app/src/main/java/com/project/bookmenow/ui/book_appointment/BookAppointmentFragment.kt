package com.project.bookmenow.ui.book_appointment

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.applandeo.materialcalendarview.CalendarDay
import com.applandeo.materialcalendarview.listeners.OnCalendarDayClickListener
import com.project.bookmenow.R
import com.project.bookmenow.adapter.TimeSlotsGridAdapter
import com.project.bookmenow.databinding.FragmentBookAppointmentBinding
import com.project.bookmenow.utils.Constants
import java.time.LocalDate
import java.util.Calendar

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
        val bookAppointmentViewModel =
            ViewModelProvider(this).get(BookAppointmentViewModel::class.java)

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
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -1)
        _binding!!.calendarView.setMinimumDate(cal)
        _binding!!.calendarView.setOnCalendarDayClickListener(object : OnCalendarDayClickListener{
            override fun onClick(calendarDay: CalendarDay) {
                selectedDate = calendarDay.calendar.time.toString()
            }
        })


        //Submit click event
        _binding!!.button.setOnClickListener {

            //Check if Time is in past or not
            val current = LocalDate.now()
            Log.d("TAG","Day ${current.dayOfMonth.toString()}")
            Log.d("TAG","Current $current")
            Log.d("TAG","Date - $selectedDate")

            //Check if date and time is selected or not
            if(selectedDate.equals(null) || selectedTime.equals(null)){
                Toast.makeText(requireActivity(),"Please select Date and Time!",Toast.LENGTH_LONG).show()
            }else{
                //Open a dialog and get name and contact number
                val dialog = Dialog(requireActivity())
                dialog.setContentView(R.layout.dialog_confirm_appointment)
                dialog.setCancelable(false)
                val button = dialog.findViewById<Button>(R.id.btnSubmit)
                val closeIv = dialog.findViewById<ImageView>(R.id.ivClose)
                val nameEt = dialog.findViewById<EditText>(R.id.etName)
                val emailEt = dialog.findViewById<EditText>(R.id.etEmail)
                val phoneNumberEt = dialog.findViewById<EditText>(R.id.etPhoneNumber)

                closeIv.setOnClickListener {
                    dialog.dismiss()
                }
                button.setOnClickListener {
                    //Validation
                    if(nameEt.text.toString().isEmpty() || emailEt.text.toString().isEmpty() || phoneNumberEt.text.toString().isEmpty()){
                        Toast.makeText(requireActivity(),"Please fill the form!",Toast.LENGTH_LONG).show()
                    }else{
                        //Submit Data

                    }
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
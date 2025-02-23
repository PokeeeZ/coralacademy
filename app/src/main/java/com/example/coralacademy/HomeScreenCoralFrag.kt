package com.example.coralacademy

import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.coralacademy.databinding.FragmentHomeScreenCoralBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeScreenCoralFrag.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeScreenCoralFrag : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var dateTV: TextView
    private lateinit var tmMembers: TextView
    private lateinit var scheduleCalendar: CalendarView
    private lateinit var adminPanel: Button

    private var _binding: FragmentHomeScreenCoralBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeScreenCoralBinding.inflate(inflater, container, false)

        dateTV = binding.dateTV
        scheduleCalendar = binding.scheduleCalendar
        tmMembers = binding.tmMembers
        adminPanel = binding.adminPanelButton

        scheduleCalendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
                val date = (dayOfMonth.toString() + "-" + (month + 1) + "-" + year)
                dateTV.text = date

                val selected = Calendar.getInstance()
                selected.set(year, month, dayOfMonth)
                val weekDay = selected.get(Calendar.DAY_OF_WEEK)

                val mon = "Gwak, James, Jesse, Shirley [Morning Break]"
                val tue = "Kaden, Yolanda, Shirley [US Lunch] | Addison, Tiffany, Edwina [After School]"
                val wed = "Gwak, Conan [Morning Break] | Edward, Isaac [After School]"
                val thur = "Gwak, Conan, Isabella [Morning Break] | Addison, Hilary [After School]"
                val fri = "Kaden, Victoria (US Lunch), with Tobin on P8 for any unfinished business"
                val weekend = "No Tank Maintenance today!"

                when (weekDay) {
                    Calendar.MONDAY -> tmMembers.text = mon
                    Calendar.TUESDAY -> tmMembers.text = tue
                    Calendar.WEDNESDAY -> tmMembers.text = wed
                    Calendar.THURSDAY -> tmMembers.text = thur
                    Calendar.FRIDAY -> tmMembers.text = fri
                    else -> tmMembers.text = weekend
                }
            }

        binding.adminPanelButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreenCoralFrag_to_CoralAdminPanel)
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeScreenCoralFrag().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

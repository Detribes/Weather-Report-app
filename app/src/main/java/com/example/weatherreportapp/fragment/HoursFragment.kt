package com.example.weatherreportapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherreportapp.MainViewModel
import com.example.weatherreportapp.R
import com.example.weatherreportapp.adapters.WeatherAdapter
import com.example.weatherreportapp.adapters.WeatherModel
import com.example.weatherreportapp.databinding.FragmentHoursBinding
import org.json.JSONArray
import org.json.JSONObject

class HoursFragment : Fragment() {

    private lateinit var _binding: FragmentHoursBinding
    private lateinit var _adapter: WeatherAdapter
    private val _model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHoursBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
        _model.liveDataCurrent.observe(viewLifecycleOwner){
            _adapter.submitList(getHoursList(it))
        }
    }

    private fun initRcView() = with(_binding){
        rcView.layoutManager = LinearLayoutManager(activity)
        _adapter = WeatherAdapter(null)
        rcView.adapter = _adapter
    }

    private fun getHoursList(weatherItem: WeatherModel): List<WeatherModel> {
        val hoursArray = JSONArray(weatherItem.hours)
        val list = ArrayList<WeatherModel>()
        for (i in 0 until hoursArray.length()) {
            val item = WeatherModel(
                weatherItem.city,
                (hoursArray[i] as JSONObject).getString("time"),
                (hoursArray[i] as JSONObject).getJSONObject("condition").getString("text"),
                (hoursArray[i] as JSONObject).getString("temp_c")+"°С",
                "",
                "",
                (hoursArray[i] as JSONObject).getJSONObject("condition").getString("icon"),
                ""
                )
            list.add(item)
        }
        return list
    }
    companion object {
        @JvmStatic
        fun newInstance() = HoursFragment()
    }
}
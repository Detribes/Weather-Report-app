package com.example.weatherreportapp.fragment

import android.location.GnssAntennaInfo
import android.os.Bundle
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
import com.example.weatherreportapp.databinding.FragmentDaysBinding

class DaysFragment : Fragment(), WeatherAdapter.Listener {

    private lateinit var _binding: FragmentDaysBinding
    private lateinit var _adapter: WeatherAdapter
    private val _model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDaysBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
        _model.liveDataList.observe(viewLifecycleOwner){
            _adapter.submitList(it){
                _adapter.submitList((it.subList(1, it.size)))
            }
        }
    }
    private fun initRcView() = with(_binding) {
        _adapter = WeatherAdapter(this@DaysFragment)
        rcView.layoutManager = LinearLayoutManager(activity)
        rcView.adapter = _adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = DaysFragment()
    }

    override fun onClick(item: WeatherModel) {
        _model.liveDataCurrent.value = item
    }
}
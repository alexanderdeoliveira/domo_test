package br.com.domotest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.domotest.databinding.FragmentDateBinding

class DateFragment: Fragment() {

    private var _binding: FragmentDateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentDateBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView() {
        binding.popOverCustomView.apply {
            setOnDateChangedListener { year, month, dayOfMonth ->
                binding.tvDate.text = "$dayOfMonth/${month?.plus(1)}/$year"
            }
            setOnTimeChangedListener { hourOfDay, minute ->
                binding.tvTime.text = "$hourOfDay:$minute"
            }
        }

    }
}
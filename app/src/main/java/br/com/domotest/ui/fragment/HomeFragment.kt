package br.com.domotest.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.domotest.R
import br.com.domotest.databinding.FragmentHomeBinding
import br.com.domotest.model.TodoModel
import br.com.domotest.ui.viewmodel.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView() {
        homeViewModel.getTodoList()
        setupListeners()
        setupObservers()
    }

    private fun setupListeners() {
        binding.apply {
            tvMessage.setOnClickListener {
                homeViewModel.changeText()
            }
            switchMock.setOnCheckedChangeListener { _, isChecked ->
                homeViewModel.enableMock(isChecked)
            }
        }
    }

    private fun setupObservers() {
        homeViewModel.apply {
            todoList.observe(viewLifecycleOwner) { todoList ->
                if (todoList.isNotEmpty()) {
                    val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
                    homeViewModel.startTextViewAnimation(
                        bottomNav?.height ?: 0,
                        binding.tvMessage
                    )
                }
            }

            currentTodo.observe(viewLifecycleOwner) { currentTodo ->
                binding.tvMessage.text = when(currentTodo) {
                    is TodoModel.TodoLocal -> currentTodo.message
                    is TodoModel.TodoRemote -> currentTodo.message
                }

                homeViewModel.saveTodo(currentTodo)
            }

            textViewColor.observe(viewLifecycleOwner) { RGBColor ->
                binding.tvMessage.setTextColor(
                    Color.rgb(
                        RGBColor.red,
                        RGBColor.green,
                        RGBColor.blue
                    )
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPause() {
        super.onPause()
        homeViewModel.pauseAnimation()
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.resumeAnimation()
    }

    override fun onDestroy() {
        super.onDestroy()
        homeViewModel.cancelAnimation()
    }
}
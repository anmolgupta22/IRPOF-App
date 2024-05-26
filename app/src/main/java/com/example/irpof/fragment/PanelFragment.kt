package com.example.irpof.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.irpof.adapter.PageAdapter
import com.example.irpof.databinding.FragmentPanelBinding

class PanelFragment : Fragment() {
    private var _binding: FragmentPanelBinding? = null
    private val binding get() = _binding!!
    private lateinit var pageAdapter: PageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPanelBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        pageAdapter.setData(
            mutableListOf(
                "DITS",
                "Panel > Group A",
                "Panel > JAG",
                "Incumbency Board"
            )
        )
    }

    private fun setupRecyclerView() {
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        pageAdapter = PageAdapter()
        binding.rvOrganization.apply {
            layoutManager = linearLayoutManager
            adapter = pageAdapter
            itemAnimator = null
            setHasFixedSize(true)
        }
    }
}
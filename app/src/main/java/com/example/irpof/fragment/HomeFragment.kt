package com.example.irpof.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.irpof.adapter.ImageViewAdapter
import com.example.irpof.adapter.PageAdapter
import com.example.irpof.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var imageViewAdapter: ImageViewAdapter? = null
    private val handler = Handler(Looper.getMainLooper())
    private var currentPage = 0
    private val delay: Long = 3000 // 3 seconds delay
    private lateinit var pageAdapter: PageAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        val urlList = listOf(
            "https://irpof.vercel.app/homeimage1.jpeg",
            "https://irpof.vercel.app/homeimage2.jpeg",
            "https://irpof.vercel.app/homeimage3.jpeg"
        )
        imageViewAdapter = ImageViewAdapter(requireActivity(), urlList)
        val viewPager = binding.liveChatViewPager
        viewPager.adapter = imageViewAdapter
        binding.markMissionDotsIndicator.setViewPager(viewPager)
        binding.headline.isSelected = true

        // Start automatic scrolling
        startAutoScroll()

        pageAdapter.setData(
            mutableListOf(
                "Who we are: IRPOF",
                "Mission & Vision",
                " Recent Events",
                "Images",
                "Videos"
            )
        )
    }

    private fun setupRecyclerView() {
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        pageAdapter = PageAdapter()
        binding.rvPages.apply {
            layoutManager = linearLayoutManager
            adapter = pageAdapter
            itemAnimator = null
            setHasFixedSize(true)
        }
    }

    private val runnable = object : Runnable {
        override fun run() {
            if (imageViewAdapter?.count == 0) return

            currentPage = (currentPage + 1) % (imageViewAdapter?.count ?: 0)
            binding.liveChatViewPager.setCurrentItem(currentPage, true)
            handler.postDelayed(this, delay)
        }
    }

    private fun startAutoScroll() {
        handler.postDelayed(runnable, delay)
    }

    private fun stopAutoScroll() {
        handler.removeCallbacks(runnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        stopAutoScroll()
    }
}
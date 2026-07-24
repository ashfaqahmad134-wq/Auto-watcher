package com.autoviwer.watcher.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.autoviwer.watcher.R
import com.autoviwer.watcher.databinding.FragmentPlatformBinding
import com.autoviwer.watcher.service.ViewCounterService
import com.autoviwer.watcher.utils.PlatformManager
import kotlinx.coroutines.launch

class YouTubeFragment : Fragment() {
    
    private lateinit var binding: FragmentPlatformBinding
    private val platformManager = PlatformManager("YOUTUBE")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlatformBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupUI()
        loadSavedLink()
        observeStats()
    }

    private fun setupUI() {
        binding.apply {
            platformName.text = "YouTube"
            platformIcon.setImageResource(R.drawable.ic_youtube)
            
            btnAddLink.setOnClickListener { addLink() }
            btnClear.setOnClickListener { clearLink() }
            btnSaveLink.setOnClickListener { saveLink() }
            btnStartViewing.setOnClickListener { startViewing() }
            btnStopViewing.setOnClickListener { stopViewing() }
        }
    }

    private fun addLink() {
        val link = binding.linkInput.text.toString().trim()
        
        if (link.isEmpty()) {
            Toast.makeText(context, "Please enter a link", Toast.LENGTH_SHORT).show()
            return
        }
        
        if (!isValidYouTubeLink(link)) {
            Toast.makeText(context, "Invalid YouTube link", Toast.LENGTH_SHORT).show()
            return
        }
        
        binding.linkDisplay.text = "Link: $link"
        platformManager.setLink(link)
        Toast.makeText(context, "Link added successfully", Toast.LENGTH_SHORT).show()
    }

    private fun isValidYouTubeLink(link: String): Boolean {
        return link.contains("youtube.com") || link.contains("youtu.be")
    }

    private fun clearLink() {
        binding.linkInput.text.clear()
        binding.linkDisplay.text = "No link added"
        platformManager.clearLink()
        Toast.makeText(context, "Link cleared", Toast.LENGTH_SHORT).show()
    }

    private fun saveLink() {
        val link = binding.linkInput.text.toString().trim()
        
        if (link.isEmpty()) {
            Toast.makeText(context, "Please enter a link first", Toast.LENGTH_SHORT).show()
            return
        }
        
        platformManager.saveLink(link)
        Toast.makeText(context, "Link saved to device", Toast.LENGTH_SHORT).show()
    }

    private fun loadSavedLink() {
        viewLifecycleOwner.lifecycleScope.launch {
            val savedLink = platformManager.getSavedLink()
            if (!savedLink.isNullOrEmpty()) {
                binding.linkInput.setText(savedLink)
                binding.linkDisplay.text = "Link: $savedLink"
            }
        }
    }

    private fun startViewing() {
        val link = binding.linkInput.text.toString().trim()
        
        if (link.isEmpty()) {
            Toast.makeText(context, "Please add a link first", Toast.LENGTH_SHORT).show()
            return
        }
        
        binding.btnStartViewing.isEnabled = false
        binding.btnStopViewing.isEnabled = true
        
        val viewsToGenerate = binding.inputViews.text.toString().toIntOrNull() ?: 100
        
        platformManager.startViewCounter(
            context = requireContext(),
            link = link,
            targetViews = viewsToGenerate
        )
        
        Toast.makeText(context, "View generation started!", Toast.LENGTH_SHORT).show()
    }

    private fun stopViewing() {
        platformManager.stopViewCounter()
        binding.btnStartViewing.isEnabled = true
        binding.btnStopViewing.isEnabled = false
        Toast.makeText(context, "View generation stopped", Toast.LENGTH_SHORT).show()
    }

    private fun observeStats() {
        viewLifecycleOwner.lifecycleScope.launch {
            platformManager.getStats().collect { stats ->
                binding.apply {
                    statsViews.text = "Views: ${stats.views}"
                    statsSuccessRate.text = "Success: ${stats.successRate}%"
                    statsRuntime.text = "Runtime: ${stats.runtime}s"
                    statsSpeed.text = "Speed: ${stats.speed} v/s"
                }
            }
        }
    }
}

package com.example.fitnessapp.workout

import android.content.Context
import android.net.Uri
import android.widget.MediaController
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import com.example.fitnessapp.databinding.FragmentVideoInstructionBinding

class VideoInstructionFragment : Fragment() {

    private lateinit var binding: FragmentVideoInstructionBinding
    private lateinit var videoView: VideoView
    private lateinit var videoUrl: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideoInstructionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createUrl()
        if (videoUrl != "") {
            videoView = binding.videoView
            val uri: Uri = Uri.parse(videoUrl)
            videoView.setVideoURI(uri)
            val mediaController = MediaController(requireContext())
            videoView.setMediaController(mediaController)
            mediaController.setAnchorView(videoView)
            videoView.start()
        } else {
            binding.textView2.text = "По данному упражнению нет видеоинструкции"
        }
    }

    private fun createUrl() {
        val prefs = context?.getSharedPreferences("themes", Context.MODE_PRIVATE)
        when (prefs!!.getInt("training", 0)) {
            0 -> videoUrl =
                "https://drive.google.com/uc?export=download&confirm=no_antivirus&id=1cTOPbac8cH68DzP6VXhY-cdOajlb-FQc"
            1 -> videoUrl =
                "https://drive.google.com/uc?export=download&confirm=no_antivirus&id=1zpl4EONNkCjv7s4ZRfUxKEgNRVIeSSyU"
            2 -> videoUrl =
                "https://drive.google.com/uc?export=download&confirm=no_antivirus&id=1y4qqeVvVZdF1wUi9f3FsMHaEvfVXU351"
            3 -> videoUrl =
                "https://drive.google.com/uc?export=download&confirm=no_antivirus&id=1pUo5AR4iHuRfJ-eMykTQFUeGVSRdVjMI"
            4 -> videoUrl =
                "https://drive.google.com/uc?export=download&confirm=no_antivirus&id=1YrHfrktlVXSn_ugPrXz2qmRhlRfR7HuF"
            5 -> videoUrl =
                "https://drive.google.com/uc?export=download&confirm=no_antivirus&id=1FnRUP_pRSDvnMgZwMOV0yzGQSgdhVsAx"
            6 -> videoUrl = ""
            7 -> videoUrl =
                "https://drive.google.com/uc?export=download&confirm=no_antivirus&id=1cw7H2fJcnnW6tSLBXTceGGvJhLw3sU8S"
            8 -> videoUrl =
                "https://drive.google.com/uc?export=download&confirm=no_antivirus&id=1_LxJjc1UPqWc8rVRT3bKztvCfrqQ_uuY"
            9 -> videoUrl =
                "https://drive.google.com/uc?export=download&confirm=no_antivirus&id=1OSeOYjFSssuaeodAGkHAH_lZ8vrZo49j"
            10 -> videoUrl =
                "https://drive.google.com/uc?export=download&confirm=no_antivirus&id=1GekZsTKsMdrndnJZLTFxD4TIGw_sRU4o"
            11 -> videoUrl = ""
        }
    }

}
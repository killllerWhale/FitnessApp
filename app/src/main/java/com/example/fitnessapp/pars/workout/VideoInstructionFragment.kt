package com.example.fitnessapp.pars.workout

import android.content.Context
import android.net.Uri
import android.widget.MediaController
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import com.example.fitnessapp.R
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
        val flag = prefs!!.getInt("training", 0)
        when (flag) {
            0 -> {
                videoUrl =
                    "https://downloader.disk.yandex.ru/disk/1ffebf8d2c44ce1b66d5f71c845ae17e8cad761cc87bb8d066a057b48b4b2962/6421d3bb/Uq4NOwV6SHstpKS9AmWEY3kzVDdct5XtdYRXVcWFzk4L5aCWh8XNEsLavS8ATTr2t80RPjjxruCa6WkytUopTQ%3D%3D?uid=413402358&filename=training_0.mp4&disposition=attachment&hash=&limit=0&content_type=video%2Fmp4&owner_uid=413402358&fsize=2458860&hid=ba56d78ce0509d5181e18b77c31eb7d7&media_type=video&tknv=v2&etag=a239e674cb871d37a88d4faa8cb4d890"
            }
            1 -> {
                videoUrl =
                    "https://downloader.disk.yandex.ru/disk/5abd95156eff6a117ee675476cde0294c3dbd6b71e461b38248f4c05873089c7/6421d3ff/Uq4NOwV6SHstpKS9AmWEY-TeT1Ryqv5RbdJ6lFzLuwQZG8ZyIoGukO6Ro6R38kX4YQdOYeA_FjbCL2f7j3FuAQ%3D%3D?uid=413402358&filename=training_1.mp4&disposition=attachment&hash=&limit=0&content_type=video%2Fmp4&owner_uid=413402358&fsize=12473804&hid=3cf0a9b7e3eeb89d8fe47ca0a5e3dce8&media_type=video&tknv=v2&etag=f7e27cebb4cc7db5c3e3d82aade781d5"
            }
            2 -> {
                videoUrl =
                    "https://downloader.disk.yandex.ru/disk/414af255596bda056082640b0a3fc569652028aeab3a648a47183f54868118f6/6421d44f/Uq4NOwV6SHstpKS9AmWEYzDZdwfDX3jeg6mse5sQDeM0SQSCL9kRXN0NnNyqJVPOUPZDF6FX6lK-Fg0_lTAKxg%3D%3D?uid=413402358&filename=training_2.mp4&disposition=attachment&hash=&limit=0&content_type=video%2Fmp4&owner_uid=413402358&fsize=3988280&hid=b902ae513ace580639b542565d21a020&media_type=video&tknv=v2&etag=75767ebd3e086eac819f075a36c71f95"
            }
            3 -> {
                videoUrl =
                    "https://downloader.disk.yandex.ru/disk/6bba493d21dcc3bee3937ea208d96ebe106de02956b43a0b36803c06fa81fad3/6421d451/Uq4NOwV6SHstpKS9AmWEY0r34JTfBt0qSP0LM3WhzaR2LQduWbcZkjpQ1uD6UDZlsgrduIaE6d66So90pPMqag%3D%3D?uid=413402358&filename=training_3.mp4&disposition=attachment&hash=&limit=0&content_type=video%2Fmp4&owner_uid=413402358&fsize=5835242&hid=f4204ce6a22d99592eceac2e8fd1d2b5&media_type=video&tknv=v2&etag=6dc05e259e673590374252bce2b87bf6"
            }
            4 -> {
                videoUrl =
                    "https://downloader.disk.yandex.ru/disk/3fa6d1cb325e9a35f570138d57a079e8380cbe0796d862817a3fb0141a45b4b0/6421d456/9rGGZjtDv3H6wxiUoDskMC69pS-TfkjIcNrDotGX9fmklIG0dOpV4e-hGIEOKetkOe3FNr0-ZdPmHTzxTSFpGQ%3D%3D?uid=413402358&filename=training_4.mp4&disposition=attachment&hash=&limit=0&content_type=video%2Fmp4&owner_uid=413402358&fsize=3455541&hid=3d4af012ee64f97b8bf7c8d6e9948c27&media_type=video&tknv=v2&etag=ae604dae27f7960148e641854a849cf9"
            }
            5 -> {
                videoUrl =
                    "https://downloader.disk.yandex.ru/disk/7dab487da3b31ed85aa3193be49d717743f9d0b579a7afeafd4db26576e53a1a/6421d457/Uq4NOwV6SHstpKS9AmWEYyD7KOzkAv5i97CMALe813RN5n2drPUjI1eDDOlckB14qQWtTu7owdWexYBxLVABSA%3D%3D?uid=413402358&filename=training_5.mp4&disposition=attachment&hash=&limit=0&content_type=video%2Fmp4&owner_uid=413402358&fsize=2039015&hid=115c9e4f08130c959ac4a39130008906&media_type=video&tknv=v2&etag=0b30722c9754e9ff6a9e258ff98d3bf4"
            }
            6 -> {
                videoUrl = ""
            }
            7 -> {
                videoUrl =
                    "https://downloader.disk.yandex.ru/disk/823291fa7fd084b61091f83029ccce91c1ac9236f712f565a4f2cbe42cf852a9/6421d45b/Uq4NOwV6SHstpKS9AmWEYzrKVpsgI-t_uWtmM-OzRJxcZcTQRzQoBg9Mu6-uu6IiIwhyUjFiLFnAYUSh5KCkLg%3D%3D?uid=413402358&filename=training_7.mp4&disposition=attachment&hash=&limit=0&content_type=video%2Fmp4&owner_uid=413402358&fsize=3062379&hid=e3e319af709b9b25a7cd70aa6f4fb262&media_type=video&tknv=v2&etag=66e1d55720eb4d33318de7b2efff0077"
            }
            8 -> {
                videoUrl =
                    "https://downloader.disk.yandex.ru/disk/8dabf219f075924c5c81f56fe0a4b894f0c5353c9cef4eedd592ac7811df6a51/6421d45d/Uq4NOwV6SHstpKS9AmWEY_Pc-XBYfQNqlJpJV0POCDSlabD8xV5_oLaCI3gB0IUVKt3l0h0T6IaPiEkA5IHDAg%3D%3D?uid=413402358&filename=traning_8.mp4&disposition=attachment&hash=&limit=0&content_type=video%2Fmp4&owner_uid=413402358&fsize=31488154&hid=0f2bf3b8305df16ba1946f8459cb19b7&media_type=video&tknv=v2&etag=8a12750b9cc4d55562d200712de4a1b8"
            }
            9 -> {
                videoUrl =
                    "https://downloader.disk.yandex.ru/disk/c9ced1c0e04a4629c50a3dda83ee726cd0d192c31be553b26759d7d865cccb8d/6421d45e/Uq4NOwV6SHstpKS9AmWEY9RTtQyGLRbwEns8Wn8IN6pKR7oQxbJF8bDS7ke7n4t5CYJ5dG6HVcoTE_j7pVmSrA%3D%3D?uid=413402358&filename=training_9.mp4&disposition=attachment&hash=&limit=0&content_type=video%2Fmp4&owner_uid=413402358&fsize=1323300&hid=09e7e61d0465f14e1f70eeeb15c822f1&media_type=video&tknv=v2&etag=af4c767da862332f8a26dd6675a0cff2"
            }
            10 -> {
                videoUrl =
                    "https://downloader.disk.yandex.ru/disk/6d79e1ed3cc6d97b1460f0b290a9ff32f6e6ddd16e75d831bf30f3554e97cb3f/6421d461/Uq4NOwV6SHstpKS9AmWEY7K0lwO6WrCtlBbjlgPLgMVlVdeezwXynsLaj1LB4VB8LGqDuKEShBxh6X5ouFGsZw%3D%3D?uid=413402358&filename=training_10.mp4&disposition=attachment&hash=&limit=0&content_type=video%2Fmp4&owner_uid=413402358&fsize=4925583&hid=78a4407867502f9bfb99fd0150efffc8&media_type=video&tknv=v2&etag=07a29f0059675eaa55a364902a38491c"
            }
            11 -> {
                videoUrl = ""
            }
        }
    }

}
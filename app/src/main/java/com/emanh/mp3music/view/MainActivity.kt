package com.emanh.mp3music.view

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.emanh.mp3music.R
import com.emanh.mp3music.adapter.ItemMusicAdapter
import com.emanh.mp3music.databinding.ActivityMainBinding
import com.emanh.mp3music.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainViewModel = viewModel
        binding.lifecycleOwner = this

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        initBottom()
        initRecyclerView()
    }

    private fun initBottom() {
        binding.forward.setOnClickListener { viewModel.forwardMusic(this) }
        binding.rewind.setOnClickListener { viewModel.rewindMusic(this) }
        binding.control.setOnClickListener { viewModel.controlMusic(this) }

        viewModel.isPlaying.observe(this) {
            binding.control.setImageResource(
                if (it) R.drawable.stop else R.drawable.play
            )
        }
    }

    private fun initRecyclerView() {
        binding.listMusic.layoutManager = LinearLayoutManager(this)
        viewModel.listMusic.observe(this, Observer {
            binding.listMusic.adapter = ItemMusicAdapter(it.toMutableList(), viewModel)
        })
    }
}

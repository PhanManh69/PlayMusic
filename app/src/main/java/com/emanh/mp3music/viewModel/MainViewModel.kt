package com.emanh.mp3music.viewModel

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.emanh.mp3music.database.DataMusic
import com.emanh.mp3music.model.MusicModel
import com.emanh.mp3music.service.MusicService

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val musicList = DataMusic().dataMusic()

    private val _currentMusic = MutableLiveData<MusicModel>()
    val currentMusic: LiveData<MusicModel> get() = _currentMusic

    private val _isPlaying = MutableLiveData<Boolean>()
    val isPlaying: LiveData<Boolean> get() = _isPlaying

    private val _listMusic = MutableLiveData<MutableList<MusicModel>>()
    val listMusic: LiveData<MutableList<MusicModel>> get() = _listMusic

    private var currentIndex = 0

    init {
        _currentMusic.value = musicList[currentIndex]
        _isPlaying.value = false
        _listMusic.value = musicList
    }

    fun forwardMusic(context: Context) {
        listMusic.value?.let {
            if (currentIndex < it.size - 1) {
                currentIndex++
            } else {
                currentIndex = 0
            }
            _currentMusic.value = it[currentIndex]
            intentMusicResId(context)
            _isPlaying.value = true
        }
    }

    fun rewindMusic(context: Context) {
        listMusic.value?.let {
            if (currentIndex > 0) {
                currentIndex--
            } else {
                currentIndex = it.size - 1
            }
            _currentMusic.value = it[currentIndex]
            intentMusicResId(context)
            _isPlaying.value = true
        }
    }

    fun controlMusic(context: Context) {
        intentMusicResId(context)
        _isPlaying.value = !_isPlaying.value!!
    }

    fun selectItem(context: Context, id: Int, url: Int) {
        listMusic.value?.let {
            _currentMusic.value = it[id]
            val intent = Intent(context, MusicService::class.java).apply {
                putExtra("MUSIC_RES_ID", url)
            }
            context.stopService(intent)
            context.startService(intent)
            _isPlaying.value = true
        }
    }

    private fun intentMusicResId(context: Context) {
        val musicResId = _currentMusic.value?.url ?: return
        val intent = Intent(context, MusicService::class.java).apply {
            putExtra("MUSIC_RES_ID", musicResId)
        }
        context.startService(intent)
    }
}
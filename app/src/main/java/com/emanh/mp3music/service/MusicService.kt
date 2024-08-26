package com.emanh.mp3music.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class MusicService : Service() {
    private var myMusic: MediaPlayer? = null
    private var currentMusicResId: Int = 0

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val musicResId = intent?.getIntExtra("MUSIC_RES_ID", 0) ?: 0

        if (musicResId != currentMusicResId) {
            myMusic?.release()
            myMusic = MediaPlayer.create(this, musicResId)
            myMusic?.isLooping = true
            currentMusicResId = musicResId
        }

        if (myMusic?.isPlaying == true) {
            myMusic?.pause()
        } else {
            myMusic?.start()
        }

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        myMusic?.stop()
        myMusic?.release()
    }
}
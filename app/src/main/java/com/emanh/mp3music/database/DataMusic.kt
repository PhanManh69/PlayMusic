package com.emanh.mp3music.database

import com.emanh.mp3music.R
import com.emanh.mp3music.model.MusicModel

class DataMusic {
    fun dataMusic(): MutableList<MusicModel> {
        return mutableListOf(
            MusicModel(0, "Yên bình có quá đắt không", "Khiem", R.raw.yen_binh_co_qua_dat_khong),
            MusicModel(1, "0 phải hôm nay", "QNT, HuyR", R.raw.khong_phai_hom_nay),
            MusicModel(2, "Sau cơn mưa", "Coolkid, Rhyder", R.raw.sau_con_mua),
            MusicModel(3, "Trên chuyến xe", "Phạm Nguyên Ngọc, Dick", R.raw.tren_chuyen_xe),
            MusicModel(4, "Tell the kids i love them", "Obito, Shiki", R.raw.tell_the_kids_i_love_them)
        )
    }
}

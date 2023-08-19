package com.example.TakeMeWithYou.data

import com.example.TakeMeWithYou.R
import com.example.TakeMeWithYou.model.PostContentModel

class PostContentData {
    companion object {  // 싱글턴
        @Volatile
        private var instance: PostContentData? = null
        fun getInstance(): PostContentData { // 다 같은 interface를 사용하게 유도
            return instance ?: synchronized(this) {
                instance ?: PostContentData().also { instance = it }
            }
        }
    }

    var contentDataList: ArrayList<PostContentModel> = arrayListOf(
        PostContentModel(
            R.drawable.content_img_202308180959,
            "wowo",
            23,
            "원데이 클래스, 그림 그리는 거 어렵다 앞으로는 Ai한테 그려달라고 해야겠다"
        ),
    )

    fun addItem(item: PostContentModel) {
        contentDataList.add(item)
    }

    fun getAllItem(): ArrayList<PostContentModel> {
        return contentDataList
    }

    fun addLikeCount(position: Int) {
        contentDataList[position].copy(likeCount = contentDataList[position].likeCount + 1)
    }

    fun subLikeCount(position: Int) {
        contentDataList[position].copy(likeCount = contentDataList[position].likeCount - 1)
    }
}
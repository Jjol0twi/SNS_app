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

    val userData = UserList.getInstance()
    var postContentData: ArrayList<PostContentModel> = arrayListOf(
        PostContentModel(
            R.drawable.content_img_202308180959,
            userData.getUserId(4),
            23,
            "원데이 클래스, 그림 그리는 거 어렵다 앞으로는 Ai한테 그려달라고 해야겠다"
        ),
    )

    init {
        postContentData.add(
            PostContentModel(
                R.drawable.kakaotalk_20230818_095529133,
                userData.getUserId(2),
                23,
                "미미야 내가 더 잘할 수 있어 비켜봐 \uD83D\uDE97"
            )
        )
        postContentData.add(
            PostContentModel(
                R.drawable.kakaotalk_20230818_100636554,
                userData.getUserId(2),
                19,
                "여수밤바다 \uD83C\uDF03\uD83C\uDF0A"
            )
        )
        postContentData.add(
            PostContentModel(
                R.drawable.kakaotalk_20230820_143539872_01,
                userData.getUserId(3),
                21,
                "탐나요? 제 물통"
            )
        )
        postContentData.add(
            PostContentModel(
                R.drawable.kakaotalk_20230820_143539872_02,
                userData.getUserId(3),
                134,
                "오징어볶음"
            )
        )
        postContentData.add(
            PostContentModel(
                R.drawable.s20221110_212704_1,
                userData.getUserId(5),
                45,
                "삼엽충들아 모여라 내가 그 삼엽충이다"
            )
        )
        postContentData.add(
            PostContentModel(
                R.drawable.s20230709_194708,
                userData.getUserId(5),
                11,
                "이거 먹고 다이어트 해야지"
            )
        )
        postContentData.add(
            PostContentModel(
                R.drawable.img_4348,
                userData.getUserId(1),
                233,
                "농어 촵촵"
            )
        )
        postContentData.add(
            PostContentModel(
                R.drawable.img_5578,
                userData.getUserId(1),
                64,
                "아프다…..낀다ㅣ….좁다…"
            )
        )
        postContentData.add(
            PostContentModel(
                R.drawable.dydwnssla_1,
                userData.getUserId(4),
                134,
                "일산호수공원 잘 꾸며놨더라,  다음에 또 가야겠다"
            )
        )
    }

    fun addItem(item: PostContentModel) {
        postContentData.add(item)
    }

    fun getAllItem(): ArrayList<PostContentModel> {
        return postContentData
    }

    fun addLikeCount(position: Int) {
        postContentData[position].copy(likeCount = postContentData[position].likeCount + 1)
    }

    fun subLikeCount(position: Int) {
        postContentData[position].copy(likeCount = postContentData[position].likeCount - 1)
    }
}
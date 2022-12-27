package com.kyungeun.dualscreensample.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kyungeun.dualscreensample.data.PostData
import com.kyungeun.dualscreensample.model.Post

class MainViewModel : ViewModel() {

    // post list
    private var _postsData: ArrayList<Post> = ArrayList()
    val postsData: ArrayList<Post>
        get() = _postsData

    // post
    private var _currentPost: MutableLiveData<Post> = MutableLiveData<Post>()
    val currentPost: LiveData<Post>
        get() = _currentPost

    init {
        _postsData = PostData.getData()
        _currentPost.value = _postsData[0]
    }

    fun updatePost(post: Post) {
        _currentPost.value = post
    }
}

package com.kyungeun.dualscreensample.data

import com.bumptech.glide.load.engine.Resource
import com.kyungeun.dualscreensample.R
import com.kyungeun.dualscreensample.model.Post

object PostData {
    fun getData(): ArrayList<Post> {
        return arrayListOf(
            Post(
                id = 1,
                title = "Sample Title 1",
                subTitle = "Sample Subtitle 1",
                content = "Sample Content 1",
                image = R.drawable.post_1
            ),
            Post(
                id = 2,
                title = "Sample Title 2",
                subTitle = "Sample Subtitle 2",
                content = "Sample Content 2",
                image = R.drawable.post_2
            ),
            Post(
                id = 3,
                title = "Sample Title 3",
                subTitle = "Sample Subtitle 3",
                content = "Sample Content 3",
                image = R.drawable.post_3
            ),
            Post(
                id = 4,
                title = "Sample Title 4",
                subTitle = "Sample Subtitle 4",
                content = "Sample Content 4",
                image = R.drawable.post_4
            ),
            Post(
                id = 5,
                title = "Sample Title 5",
                subTitle = "Sample Subtitle 5",
                content = "Sample Content 5",
                image = R.drawable.post_5
            ),
            Post(
                id = 6,
                title = "Sample Title 6",
                subTitle = "Sample Subtitle 6",
                content = "Sample Content 6",
                image = R.drawable.post_6
            ),
            Post(
                id = 7,
                title = "Sample Title 7",
                subTitle = "Sample Subtitle 7",
                content = "Sample Content 7",
                image = R.drawable.post_7
            ),
            Post(
                id = 8,
                title = "Sample Title 8",
                subTitle = "Sample Subtitle 8",
                content = "Sample Content 8",
                image = R.drawable.post_8
            )
        )
    }
}
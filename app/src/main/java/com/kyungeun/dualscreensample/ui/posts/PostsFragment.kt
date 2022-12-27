package com.kyungeun.dualscreensample.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.kyungeun.dualscreensample.R
import com.kyungeun.dualscreensample.databinding.FragmentPostBinding
import com.kyungeun.dualscreensample.ui.main.MainActivity
import com.kyungeun.dualscreensample.ui.main.MainViewModel

class PostsFragment : Fragment() {

    lateinit var mainActivity: MainActivity

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.mainActivity = activity as MainActivity
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentPostBinding.bind(view)

        val adapter = PostAdapter { // Recyclerview onItemClicked
            viewModel.updatePost(it)

            // Show the detail or secondary pane.
            mainActivity.binding.slidingPaneLayout.openPane()
        }

        binding.recyclerView.adapter = adapter
        adapter.submitList(viewModel.postsData)
    }
}

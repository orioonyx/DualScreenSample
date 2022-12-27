package com.kyungeun.dualscreensample.ui.postdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.kyungeun.dualscreensample.R
import com.kyungeun.dualscreensample.databinding.FragmentPostDetailBinding
import com.kyungeun.dualscreensample.ui.main.MainActivity
import com.kyungeun.dualscreensample.ui.main.MainViewModel
import com.kyungeun.dualscreensample.util.TwoPaneOnBackPressedCallback

class PostDetailFragment : Fragment() {

    private lateinit var mainActivity: MainActivity

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.mainActivity = activity as MainActivity
        return inflater.inflate(R.layout.fragment_post_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentPostDetailBinding.bind(view)

        viewModel.currentPost.observe(viewLifecycleOwner) {
            binding.apply {
                binding.title.text = viewModel.currentPost.value?.title.toString()
                binding.subTitle.text = viewModel.currentPost.value?.subTitle.toString()
                binding.content.text = viewModel.currentPost.value?.content.toString()

                Glide.with(binding.root)
                    .load(viewModel.currentPost.value?.image)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.image)
            }
        }

        // Connect the SlidingPaneLayout to the system back button.
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            TwoPaneOnBackPressedCallback(mainActivity.binding.slidingPaneLayout)
        )
    }
}

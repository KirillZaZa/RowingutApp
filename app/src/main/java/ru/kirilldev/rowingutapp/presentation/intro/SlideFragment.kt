package ru.kirilldev.rowingutapp.presentation.intro

import android.os.Bundle
import android.telecom.Call
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import kotlinx.coroutines.*
import ru.kirilldev.rowingutapp.R
import kotlin.properties.Delegates

class SlideFragment: Fragment() {

    private var page by Delegates.notNull<Int>()
    private var layoutResId by Delegates.notNull<Int>()

    companion object {
        private const val ARG_PAGE = "page"

        fun newInstance(page: Int): SlideFragment {
            val slideFragment = SlideFragment()
            val args = Bundle()

            args.putInt(ARG_PAGE, page)
            slideFragment.arguments = args

            return slideFragment
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null && requireArguments().containsKey(ARG_PAGE)) {
            page = requireArguments().getInt(ARG_PAGE)
            layoutResId = when (page) {
                0 -> R.layout.intro_page_1
                1 -> R.layout.intro_page_2
                2 -> R.layout.intro_page_3
                else -> R.layout.intro_page_1
            }

        }
    }

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutResId, container, false)
    }


}
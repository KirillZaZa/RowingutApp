package ru.kirilldev.rowingutapp.presentation.registration

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.kirilldev.rowingutapp.R
import kotlin.properties.Delegates

class CurrentRegistrationFragment : Fragment() {

    private var page by Delegates.notNull<Int>()
    private var layoutResId by Delegates.notNull<Int>()

    companion object {

        private const val ARG_PAGE = "argPage"

        fun newInstance(page: Int): CurrentRegistrationFragment {
            val args = Bundle()
            val fragment = CurrentRegistrationFragment()

            args.putInt(ARG_PAGE, page)
            fragment.arguments = args

            return fragment
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null && requireArguments().containsKey(ARG_PAGE)) {
            page = requireArguments().getInt(ARG_PAGE)

            layoutResId = when(page){
                0 -> R.layout.fragment_sign_in
                1 -> R.layout.fragment_sign_up
                else -> R.layout.fragment_sign_in
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutResId, container, false)
    }


    override fun onStop() {
        Log.d("$page", "onStop")

        super.onStop()
    }

    override fun onStart() {
        Log.d("$page", "onStart")

        super.onStart()
    }

    override fun onResume() {
        Log.d("$page", "onResume")
        super.onResume()
    }

}
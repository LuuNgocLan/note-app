package lanltn.com.note_app.features.home

import BaseFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import lanltn.com.note_app.R
import lanltn.com.note_app.databinding.FragmentHomeBinding
import lanltn.com.note_app.features.addnote.AddNoteFragment


class HomeFragment : BaseFragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        debugLog("onCreateView")
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAdd.setOnClickListener {
            parentFragmentManager.commit {
                replace<AddNoteFragment>(
                    R.id.container_view,
                    tag = AddNoteFragment::class.java.simpleName,
                )
                setReorderingAllowed(true)
                addToBackStack("home-to-add-note")
            }
        }

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}

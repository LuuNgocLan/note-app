package lanltn.com.note_app.features.login

import BaseFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import lanltn.com.note_app.R
import lanltn.com.note_app.databinding.FragmentLoginBinding
import lanltn.com.note_app.features.home.HomeFragment

class LoginFragment : BaseFragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val vm by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        debugLog("onCreateView")
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onEvent()
        onObserver()
    }

    private fun onEvent() {
        binding.edtUsername.doAfterTextChanged {
            checkEnabledLoginButton()
        }
        binding.edtPassword.doAfterTextChanged {
            checkEnabledLoginButton()
        }
        binding.btnLogin.setOnClickListener {
            vm.onLogin(binding.edtUsername.text.toString(), binding.edtPassword.text.toString())
        }
    }

    private fun checkEnabledLoginButton() {
        (binding.edtUsername.text.isNotEmpty() && binding.edtPassword.text.isNotEmpty()).also { enabled ->
            binding.btnLogin.apply {
                isEnabled = enabled
                alpha = if (enabled) 1f else 0.5f
            }
        }

    }

    private fun onObserver() {
        vm.username.observe(viewLifecycleOwner) { username ->
            binding.edtUsername.setText(username)
        }
        vm.password.observe(viewLifecycleOwner) { password ->
            binding.edtPassword.setText(password)
        }
        vm.isLoggedIn.observe(viewLifecycleOwner) { isLoggedin ->
            if (isLoggedin) {
                parentFragmentManager.commit {
                    replace<HomeFragment>(
                        R.id.container_view,
                        tag = HomeFragment::class.java.simpleName,
                    )
                    setReorderingAllowed(true)
                }
                parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}

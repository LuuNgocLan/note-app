package lanltn.com.note_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import lanltn.com.note_app.databinding.ActivityMainBinding
import lanltn.com.note_app.features.login.LoginFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState === null) {
            supportFragmentManager.commit {
                replace<LoginFragment>(
                    R.id.container_view,
                    tag = LoginFragment::class.java.simpleName,
                )
                setReorderingAllowed(true)
            }
        }
    }
}
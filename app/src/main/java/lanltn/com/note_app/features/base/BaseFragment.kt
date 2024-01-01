import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {
    private val logTag by lazy(LazyThreadSafetyMode.NONE) { this::class.java.simpleName }

    protected fun debugLog(message: String) {
        Log.d(logTag, "$this: $message")
    }

    @CallSuper
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        debugLog("onResume")
    }

    @CallSuper
    override fun onPause() {
        super.onPause()
        debugLog("onPause")
    }

    @CallSuper
    override fun onStop() {
        super.onStop()
        debugLog("onStop")
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        debugLog("onDestroyView")
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        debugLog("onDestroy")
    }

    @CallSuper
    override fun onDetach() {
        super.onDetach()
        debugLog("onDetach")
    }

}
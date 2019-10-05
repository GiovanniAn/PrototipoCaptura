package balam.app.protocaptura

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import balam.app.protocaptura.Utils.Utils
import balam.app.protocaptura.fragment.CapturaCamaraFragment

class MainActivity : AppCompatActivity() {


    private var fragmentManager: FragmentManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentManager = supportFragmentManager


        // If savedinstnacestate is null then replace login fragment
        if (savedInstanceState == null) {
            fragmentManager!!.beginTransaction().replace(R.id.frameContainer, CapturaCamaraFragment(), Utils.Login_Fragment).commit()
        }

    }
    companion object {

        private const val RC_SIGN_IN = 123
    }

    fun replaceLoginFragment() {


        fragmentManager?.beginTransaction()?.setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                ?.replace(R.id.frameContainer, CapturaCamaraFragment(), Utils.Login_Fragment)?.commit()

        /**fragmentManager!!.beginTransaction().setCustomAnimations(R.anim.left_enter, R.anim.right_out)
        .replace(R.id.frameContainer, Login_Fragment(), Utils.Login_Fragment).commit()**/
    }

    override fun onBackPressed() {

        // Find the tag of signup and forgot password fragment
        val SignUp_Fragment = fragmentManager!!.findFragmentByTag(Utils.SignUp_Fragment)
        val ForgotPassword_Fragment = fragmentManager!!.findFragmentByTag(Utils.ForgotPassword_Fragment)

        // Check if both are null or not
        // If both are not null then replace login fragment else do backpressed
        // task

        if (SignUp_Fragment != null)
            replaceLoginFragment()
        else if (ForgotPassword_Fragment != null)
            replaceLoginFragment()
        else
            super.onBackPressed()
    }



}

package balam.app.protocaptura

import android.Manifest
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import balam.app.protocaptura.Utils.Utils
import balam.app.protocaptura.fragment.CapturaCamaraFragment
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.pm.PackageManager
import android.speech.RecognizerIntent
import android.util.Log
import balam.app.protocaptura.fragment.CapturaCamaraFragment.REQUEST_CAMERA
import balam.app.protocaptura.fragment.CaputuraPorVozFragment
import balam.app.protocaptura.interfaces.CallbackVozListener
import balam.app.protocaptura.interfaces.SendData


class MainActivity : AppCompatActivity(), CallbackVozListener {

    private var sendDataFragment: SendData? = null
    private val RECONOCIMIENTO_VOZ_ACTIVITY = 1


    override fun onClickListener() {
        val intentActionRecognizeSpeech = Intent(
                RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        // Configura el Lenguaje (Español-México)
        intentActionRecognizeSpeech.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL, "es-MX")
        try {
            startActivityForResult(intentActionRecognizeSpeech,
                    RECONOCIMIENTO_VOZ_ACTIVITY)
        } catch (a: ActivityNotFoundException) {
            Toast.makeText(applicationContext,
                    "Tú dispositivo no soporta el reconocimiento por voz",
                    Toast.LENGTH_SHORT).show()
        }
    }



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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val articleFrag = supportFragmentManager.findFragmentById(R.id.frameContainer) as CaputuraPorVozFragment?
        when (requestCode) {
            RECONOCIMIENTO_VOZ_ACTIVITY ->
                if (resultCode == Activity.RESULT_OK && null != data) {
                    val speech = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    val strSpeech2Text = speech[0]
                    Log.i("TAG", strSpeech2Text)
                    articleFrag?.mostrarTexto(strSpeech2Text)
                }


        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CAMERA) {
            if (permissions[0].equals(Manifest.permission.CAMERA)
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            }
        }
    }

}

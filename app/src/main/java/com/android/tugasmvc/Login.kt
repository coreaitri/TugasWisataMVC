package com.android.tugasmvc

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.tugasmvc.Galery.GaleryActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import org.json.JSONException
import org.json.JSONObject

class Login : AppCompatActivity() {
    internal lateinit var pDialog: ProgressDialog
    internal lateinit var btn_login: FloatingActionButton
    internal lateinit var btn_register: TextView

    internal lateinit var txt_email: EditText
    internal lateinit var txt_password: EditText
    internal lateinit var intent: Intent

    internal var success: Int = 0
    internal lateinit var conMgr: ConnectivityManager

    private val url = Server.URL + "login.php"

    internal var tag_json_obj = "json_obj_req"

    internal lateinit var sharedpreferences: SharedPreferences
    internal var session: Boolean? = false
    internal var id: String? = null
    internal var email: String? = null
    internal var password:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        conMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        run {
            if (conMgr.activeNetworkInfo != null
                && conMgr.activeNetworkInfo.isAvailable
                && conMgr.activeNetworkInfo.isConnected) {
            } else {
                longToast("No Connection Internet")
            }
        }

        btn_login = findViewById<View>(R.id.btn_done_login) as FloatingActionButton
        btn_register = findViewById<View>(R.id.txt_signupready) as TextView
        txt_email = findViewById<View>(R.id.add_email) as EditText
        txt_password = findViewById<View>(R.id.add_password) as EditText

        btn_register.setOnClickListener {
            startActivity(intentFor<Register>())
//            val intent = Intent(this@Login, Register.javaClass)
//            startActivity(intent)
        }


        // Cek session login jika TRUE maka langsung buka MainActivity
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE)
        session = sharedpreferences.getBoolean(session_status, false)
        id = sharedpreferences.getString(TAG_ID, null)
        email = sharedpreferences.getString(TAG_EMAIL, null)
        password = sharedpreferences.getString(TAG_PASSWORD, null)

        if (session!!) {
            startActivity(intentFor<GaleryActivity>())
            finish()
        }


        btn_login.setOnClickListener {
            // TODO Auto-generated method stub
            val email = txt_email.text.toString()
            val password = txt_password.text.toString()

            // mengecek kolom yang kosong
            if (email.trim { it <= ' ' }.isNotEmpty() && password.trim { it <= ' ' }.isNotEmpty()) {
                if (conMgr.activeNetworkInfo != null
                    && conMgr.activeNetworkInfo.isAvailable
                    && conMgr.activeNetworkInfo.isConnected) {
                    checkLogin(email, password)
                } else {
                    toast("No internet Connection")
//                    Toast.makeText(applicationContext, "No Internet Connection", Toast.LENGTH_LONG).show()
                }
            } else {
                longToast("Columns Cannot be Empty!")
            }
        }

        btn_register.setOnClickListener {
            // TODO Auto-generated method stub
            startActivity(intentFor<Register>())
            finish()
        }


    }

    private fun checkLogin(email: String, password: String) {
        pDialog = ProgressDialog(this)
        pDialog.setCancelable(false)
        pDialog.setMessage("Logging in ...")
        showDialog()

        val strReq = object : StringRequest(Request.Method.POST, url, Response.Listener { response ->
            Log.e(TAG, "Login Response: $response")
            hideDialog()

            try {
                val jObj = JSONObject(response)
                success = jObj.getInt(TAG_SUCCESS)

                // Check for error node in json
                if (success == 1) {
                    val email = jObj.getString(TAG_EMAIL)
                    val password = jObj.getString(TAG_PASSWORD)


                    Log.e("Successfully Login!", jObj.toString())

                    toast(jObj.getString(TAG_MESSAGE))
//                    Toast.makeText(applicationContext, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show()

                    // menyimpan login ke session
                    val editor = sharedpreferences.edit()
                    editor.putBoolean(session_status, true)
                    editor.putString(TAG_ID, id)
                    editor.putString(TAG_EMAIL, email)
                    editor.putString(TAG_PASSWORD, password)
                    editor.commit()

                    // Memanggil main activity
                    val intent = Intent(this@Login, GaleryActivity::class.java)
                    intent.putExtra(TAG_ID, id)
                    intent.putExtra(TAG_EMAIL, email)
                    intent.putExtra(TAG_PASSWORD, password)
                    finish()
                    startActivity(intent)
                } else {
                    toast(jObj.getString(TAG_MESSAGE))
//                    Toast.makeText(applicationContext,
//                        jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show()

                }
            } catch (e: JSONException) {
                // JSON error
                e.printStackTrace()
            }
        }, Response.ErrorListener { error ->
            Log.e(TAG, "Login Error: " + error.message)
            toast((error.message).toString())
//            Toast.makeText(applicationContext,
//                error.message, Toast.LENGTH_LONG).show()
            hideDialog()
        }) {

            override fun getParams(): Map<String, String> {
                // Posting parameters to login url
                val params = HashMap<String, String>()
                params["email"] = email
                params["password"] = password

                return params
            }

        }

        // Adding request to request queue
        AppController.instance?.addToRequestQueue(strReq, tag_json_obj)
    }

    private fun showDialog() {
        if (!pDialog.isShowing)
            pDialog.show()
    }

    private fun hideDialog() {
        if (pDialog.isShowing)
            pDialog.dismiss()
    }

    companion object {

        private val TAG = Login::class.java!!.getSimpleName()

        private val TAG_SUCCESS = "success"
        private val TAG_MESSAGE = "message"

        val TAG_EMAIL = "email"
        val TAG_PASSWORD = "password"
        val TAG_ID = "id"
        val my_shared_preferences = "my_shared_preferences"
        val session_status = "session_status"
    }

}
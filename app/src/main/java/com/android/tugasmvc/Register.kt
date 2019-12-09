package com.android.tugasmvc

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import org.json.JSONException
import org.json.JSONObject

class Register : AppCompatActivity() {

    lateinit var pDialog: ProgressDialog
    lateinit var btn_register: FloatingActionButton
    lateinit var btn_login: TextView

    lateinit var txt_username: EditText
    lateinit var txt_email: EditText
    lateinit var txt_password: EditText

    internal lateinit var intent: Intent

    internal var success: Int = 0
    internal lateinit var conMgr: ConnectivityManager

    private val url = Server.URL + "register.php"

    internal var tag_json_obj = "json_obj_req"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        conMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        run {
            if (conMgr.activeNetworkInfo != null
                && conMgr.activeNetworkInfo.isAvailable
                && conMgr.activeNetworkInfo.isConnected) {
            } else {
                toast("No Internet Connection")
//                Toast.makeText(applicationContext, "No Internet Connection",
//                    Toast.LENGTH_LONG).show()
            }
        }

        btn_login = findViewById<View>(R.id.sign_in) as TextView
        btn_register = findViewById<View>(R.id.btn_done_register) as FloatingActionButton
        txt_username = findViewById<View>(R.id.add_username_register) as EditText
        txt_email = findViewById<View>(R.id.add_email_register) as EditText
        txt_password = findViewById<View>(R.id.add_password_register) as EditText


        btn_login.setOnClickListener {
            // TODO Auto-generated method stub
            startActivity(intentFor<Login>())
//            intent = Intent(this@Register, Login::class.java)
            finish()
//            startActivity(intent)
        }

        btn_register.setOnClickListener {
            // TODO Auto-generated method stub
            val username = txt_username.text.toString()
            val email = txt_email.text.toString()
            val password = txt_password.text.toString()


            if (conMgr.activeNetworkInfo != null && conMgr.activeNetworkInfo.isAvailable
                && conMgr.activeNetworkInfo.isConnected) {
                checkRegister(username, email, password)

//                startActivity(Intent(this, Login::class.java))
                startActivity(intentFor<Login>())
            } else if(username.isEmpty() || email.isEmpty() || password.isEmpty()){
                longToast("Tidak boleh kosong")

            }else if (password.length > 7){
                longToast("Password maksimal 7 huruf")

            }else{
                toast("No Internet Connection")
//                Toast.makeText(applicationContext, "No Internet Connection", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun checkRegister(username: String, email: String, password: String) {
        pDialog = ProgressDialog(this)
        pDialog.setCancelable(false)
        pDialog.setMessage("Register ...")
        showDialog()

        val strReq = object : StringRequest(Method.POST, url, Response.Listener { response ->
            Log.e(TAG, "Register Response: $response")
            hideDialog()

            try {
                val jObj = JSONObject(response)
                success = jObj.getInt(TAG_SUCCESS)

                // Check for error node in json
                if (success == 1) {

                    Log.e("Successfully Register!", jObj.toString())

                    toast(jObj.getString(TAG_MESSAGE))

//                    Toast.makeText(applicationContext,
//                        jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show()

                    txt_username.setText("")
                    txt_email.setText("")
                    txt_password.setText("")


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
                params["username"] = username
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

    override fun onBackPressed() {

        startActivity(intentFor<Login>())
        finish()
//        intent = Intent(this@Register, Login::class.java)
//        finish()
//        startActivity(intent)
    }

    companion object {

        private val TAG = Register::class.java!!.getSimpleName()

        private val TAG_SUCCESS = "success"
        private val TAG_MESSAGE = "message"
    }
}
package com.example.androidquiz

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

object Method {
    fun logE(tag: String, message: String, tr: Throwable? = null){
        if(BuildConfig.DEBUG)
            android.util.Log.e(tag, if(message.length>500) message.substring(0, 500) else message, tr)
    }

    @JvmStatic
    fun requestPermission(activity: Activity, vararg permissions: String): Boolean {
        return if (!hasPermissions(activity, *permissions)) {
            ActivityCompat.requestPermissions(activity, permissions, 0)
            false
        } else
            true
    }

    private fun hasPermissions(context: Context, vararg permissions: String): Boolean {
        for (permission in permissions)
            if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
                return false
        return true
    }

    fun showKeyBoard(activity: AppCompatActivity, ed: EditText){
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(ed, 0)
    }

    fun hideKeyBoard(activity: AppCompatActivity) {
        activity.currentFocus?.let {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken,0)
        }
    }

    fun hideKeyBoard(context: Context, view: View) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken,0)
    }
}
package com.moneybox.minimb.data.presentation


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var sharedPreferences : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*if (!sharedPreferences.getBoolean(ConstantsClass.Constants.IS_LOGGED_IN.name, false)) {*/
            startActivity(
                Intent("com.moneybox.minimb.feature.login.open")
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    .setPackage(this@MainActivity.packageName))
        /*}*/
        /*else{
            // Go to Products
            val intent = Intent()
            intent.setClassName(this@MainActivity, "nbl.core.gpsattendance.MainActivity")
            startActivity(intent)
        }*/
    }
}
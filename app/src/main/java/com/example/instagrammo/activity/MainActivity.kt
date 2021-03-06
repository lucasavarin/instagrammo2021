package com.example.instagrammo


import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.instagrammo.fragment.*
import com.example.instagrammo.interfaces.InterfaceBack
import com.example.instagrammo.network.ApiClient
import com.example.instagrammo.response.PostNumberResponse
import com.example.instagrammo.service.ForegroundService
import com.example.instagrammo.view.prefs
import retrofit2.Callback
import kotlinx.android.synthetic.main.main_activity.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(), InterfaceBack {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

initService()
        navigation.add(MeowBottomNavigation.Model(0, R.drawable.ic_home))
        navigation.add(MeowBottomNavigation.Model(1, R.drawable.ic_search))
        navigation.add(MeowBottomNavigation.Model(2, R.drawable.ic_add))
        navigation.add(MeowBottomNavigation.Model(3, R.drawable.ic_favorite))
        navigation.add(MeowBottomNavigation.Model(4, R.drawable.ic_person))

        addFragment(HomeFragment(), R.id.fragment_container)


        navigation.setOnClickMenuListener {
            when (it.id) {
                0 -> replaceFragment(HomeFragment(), R.id.fragment_container)
                1 -> replaceFragment(SearchFragment(), R.id.fragment_container)
                2 -> replaceFragment(AddFragment(), R.id.fragment_container)
                3 -> replaceFragment(FavouritesFragment(), R.id.fragment_container)
                4 -> replaceFragment(ProfileFragment(), R.id.fragment_container)
                else -> false

            }
            calcPostNumber()
        }
    }



    private fun initService(){
        val postNumberService = Intent(applicationContext, ForegroundService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(postNumberService)
        }else{
            startService(postNumberService)
        }

    }

    private fun calcPostNumber() {
        Handler().postDelayed(object : Runnable {
            override fun run() {
                if (prefs.postNumber != 0) {
                    navigation.setCount(0, prefs.postNumber.toString())
                } else {

                }
                Handler().postDelayed(this, 5000)
            }
        }, 5000)
    }

    override fun back(fragment: Fragment) {
        supportFragmentManager.popBackStackImmediate()
        supportFragmentManager.beginTransaction().apply {
            remove(fragment)
            commit()

        }
        val inputManager: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(
            currentFocus?.windowToken,
            InputMethodManager.SHOW_FORCED
        )

    }
}

fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { add(frameId, fragment) }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { replace(frameId, fragment) }
}

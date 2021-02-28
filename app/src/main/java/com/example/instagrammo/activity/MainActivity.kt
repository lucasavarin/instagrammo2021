package com.example.instagrammo


import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.instagrammo.fragment.*
import com.example.instagrammo.fragment.secondFragment.ModifyProfileFragment
import com.example.instagrammo.network.ApiClient
import com.example.instagrammo.response.PostNumberResponse
import com.example.instagrammo.service.ForegroundService
import com.example.instagrammo.view.prefs
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Callback
import kotlinx.android.synthetic.main.main_activity.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(), InterfaceApp {

    val handler = Handler()
    val run = object : Runnable {
        override fun run() {
            getNumberForService()
            //handler.postDelayed(this, 5000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        handler.postDelayed(run, 5000)


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
        }
    }

    fun getNumberForService() {
        var post_number = 0
        ApiClient.getClient.getListPostNumber()
            .enqueue(object : Callback<PostNumberResponse> {
                override fun onFailure(call: Call<PostNumberResponse>, t: Throwable) {
                    Log.i("info", t.message)
                }

                override fun onResponse(
                    call: Call<PostNumberResponse>,
                    response: Response<PostNumberResponse>
                ) {

                    post_number = response.body()?.payload!!.toInt()
                    ForegroundService.startService(
                        this@MainActivity, post_number.toString() )

                        if (post_number != 0) {
                            navigation.setCount(0, post_number.toString())
                        }

                }


            })

    }


    override fun backToProfile() {
        supportFragmentManager.beginTransaction().apply {
            remove(ModifyProfileFragment.newInstance)
            commit()
        }
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

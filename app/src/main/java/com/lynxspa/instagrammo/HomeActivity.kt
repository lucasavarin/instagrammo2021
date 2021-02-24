 package com.lynxspa.instagrammo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.lynxspa.instagrammo.fragment.*
import kotlinx.android.synthetic.main.activity_home.*
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.Fragment
import com.lynxspa.instagrammo.retrofit.Profile
import kotlinx.android.synthetic.main.fragment_profile.*

 class HomeActivity: AppCompatActivity(), ProfileFragment.ProfileInterfaceFragment, ModificaProfiloFragment.BackToProfile {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        btnEditProfile.setOnClickListener {

        }


        addFragment(HomeFragment.makeIstance(),R.id.fragmentContainer)

        navView.setOnNavigationItemSelectedListener{ item ->
            when (item.itemId) {
                R.id.homeButton ->  {
                    replaceFragment(HomeFragment.makeIstance(), R.id.fragmentContainer)
                    true
                }
                R.id.followButton -> {
                    replaceFragment(FollowFragment.makeIstance(), R.id.fragmentContainer)
                    true
                }
                R.id.addButton -> {
                    replaceFragment(AddFragment.makeIstance(), R.id.fragmentContainer)
                    true
                }
                R.id.searchButton-> {
                    replaceFragment(SearchFragment.makeIstance(), R.id.fragmentContainer)
                    true
                }
                R.id.profileButton -> {
                    replaceFragment(ProfileFragment.makeIstance(), R.id.fragmentContainer)
                    true
                }
                else -> false
            }
        }
    }
     inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
         val fragmentTransaction = beginTransaction()
         fragmentTransaction.func()
         fragmentTransaction.commit()
     }

     protected fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
         supportFragmentManager.inTransaction { add(frameId, fragment) }
     }

     protected fun replaceFragment(fragment: Fragment, frameId: Int) {
         supportFragmentManager.inTransaction { replace(frameId, fragment) }
     }

     override fun modificaProfiloFragment() {
         addFragment(ModificaProfiloFragment.makeIstance(),R.id.fragmentContainer)
     }

     override fun closeModificaProfiloFragment() {
         supportFragmentManager.beginTransaction().apply {
             remove(ModificaProfiloFragment.makeIstance())
             commit()
         }
     }
 }
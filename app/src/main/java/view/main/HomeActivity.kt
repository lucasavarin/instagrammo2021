package view.main


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.instagrammo.R
import fragments.*
import interfaces.ButtonBackToProfile
import interfaces.ButtonEditProfileListener
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : BaseActivity(), ButtonEditProfileListener, ButtonBackToProfile {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val homeFragment = HomeFragment()
        val searchFragment = SearchFragment()
        val addFragment = AddFragment()
        val followFragment = FollowFragment()
        val profileFragment = ProfileFragment()

        makeCurrentFragment(homeFragment)

        navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    makeCurrentFragment(homeFragment)
                    true
                }
                R.id.navigation_search -> {
                    makeCurrentFragment(searchFragment)
                    true
                }
                R.id.navigation_add -> {
                    makeCurrentFragment(addFragment)
                    true
                }
                R.id.navigation_follow -> {
                    makeCurrentFragment(followFragment)
                    true
                }
                R.id.navigation_profile -> {
                    makeCurrentFragment(profileFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }

    //bottone per aprire modifica profilo
    override fun onClickListenerEditButtonProfile(pressed: Boolean) {
        addFragment(EditProfileFragment.newInstance, R.id.fl_wrapper)
    }

    //bottone per salvare le modifiche fatte in modifica profilo
    //TODO: ancora da finire di implementare
    /*override fun OnClickListenerSaveEditButtonProfile(pressed: Boolean) {
        replaceFragment(EditProfileFragment.newInstance, R.id.fl_wrapper)
        removeFragment(EditProfileFragment.newInstance)
    }*/

    //bottone per tornare indietro, da modifica profilo a profilo
    override fun onClickListenerBackToProfile() {
        supportFragmentManager.beginTransaction().apply {
            remove(EditProfileFragment.newInstance)
            commit()
        }
    }

}
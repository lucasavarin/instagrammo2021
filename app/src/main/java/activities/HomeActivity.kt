package activities


import android.graphics.Insets.add
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.instagrammo.R
import com.example.instagrammo.views.BaseActivity
import fragments.*
import interfaces.ButtonEditProfileListener
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : BaseActivity(), ButtonEditProfileListener {

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
    override fun OnClickListenerEditButtonProfile(pressed: Boolean) {
        addFragment(EditProfileFragment.newInstance, R.id.fl_wrapper)
        removeFragment(ProfileFragment.newInstance)
    }

    //bottone per salvare le modifiche fatte in modifica profilo
    //TODO: ancora da finire di implementare
    /*override fun OnClickListenerSaveEditButtonProfile(pressed: Boolean) {
        replaceFragment(EditProfileFragment.newInstance, R.id.fl_wrapper)
        removeFragment(EditProfileFragment.newInstance)
    }*/

}
package activities

import activities.fragments.*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instagrammo.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
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
}
package com.mobile.azrinurvani.fragmentviewbinding

import android.os.Bundle
import android.view.MenuItem

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import com.mobile.azrinurvani.fragmentviewbinding.databinding.ActivityMainBinding

//TODO 10 - Implement NavigationUI dan OnFragmentButtonSelected
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainFragment.OnFragmentButtonSelected {

    private lateinit var binding : ActivityMainBinding
    private lateinit var drawerLayout : DrawerLayout
    private lateinit var fragmentManager : FragmentManager
    private lateinit var fragmentTransaction : FragmentTransaction
    private  lateinit var toolbar : Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toolbar =binding.incToolbar.drawerToolbar

        //set default toolbar title
        toolbar.title = "Home"
        setSupportActionBar(toolbar)

        drawerLayout = binding.drawerParent

        binding.navigationView.setNavigationItemSelectedListener(this)

        val actionBarDrawerToggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.isDrawerIndicatorEnabled = true
        actionBarDrawerToggle.syncState()

        //load default fragment
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_container, MainFragment())
        fragmentTransaction.commit()


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout.closeDrawer(GravityCompat.START)
        when(item.itemId){
            R.id.home->{
                fragmentManager = supportFragmentManager
                fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fragment_container,MainFragment())
                fragmentTransaction.commit()
                toolbar.title = "Home"
            }
            R.id.secondMenu->{
                fragmentManager = supportFragmentManager
                fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fragment_container,SecondFragment())
                fragmentTransaction.commit()
                toolbar.title = "Second"
            }
        }
        return true
    }

    override fun onButtonSelected() {
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container,SecondFragment())
        fragmentTransaction.commit()
    }

}
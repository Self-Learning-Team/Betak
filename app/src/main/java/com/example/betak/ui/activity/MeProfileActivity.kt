package com.example.betak.ui.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.betak.MainActivity
import com.example.betak.R
import com.example.betak.databinding.ActivityMeProfileBinding
import com.example.betak.model.viewModel.ProfileMeViewModel
import com.example.betak.ui.fragment.BottomSheetFragment.Companion.TAG
import com.example.betak.ui.fragment.BottomSheetFragment.Companion.newInstance
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MeProfileActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var navView: NavigationView
    private lateinit var binding: ActivityMeProfileBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var mAuth: FirebaseAuth
    private lateinit var uid: String
    private lateinit var viewModel : ProfileMeViewModel


    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            viewModel.getCurrentUserInfo(uid)
        }

        val headerView = binding.navView.getHeaderView(0)

        viewModel.employee.observe(this , Observer {
            binding.name.text = it.getName()
            binding.phone.text = it.getPhone()
            binding.whatsApp.text = it.getWhatsApp()

            binding.location.text = it.getArea() + "," + it.getGovernator()
            if(it.getImagePath()!=""){
                Glide
                        .with(this)
                        .load(it.getImagePath())
                        .centerCrop()
                        .placeholder(R.drawable.profile)
                        .into(binding.profileImg);
            }
            headerView.findViewById<TextView>(R.id.name).text = it.getName()

            Glide
                    .with(this)
                    .load(it.getImagePath())
                    .centerCrop()
                    .placeholder(R.drawable.profile)
                    .into(headerView.findViewById<CircleImageView>(R.id.image));
        })
    }

    override fun onRestart() {
        super.onRestart()
        GlobalScope.launch {
            viewModel.getCurrentUserInfo(uid)
        }

        val headerView = binding.navView.getHeaderView(0)

        viewModel.employee.observe(this , Observer {
            binding.name.text = it.getName()
            binding.phone.text = it.getPhone()
            binding.whatsApp.text = it.getWhatsApp()

            binding.location.text = it.getArea() + "," + it.getGovernator()
            if(it.getImagePath()!=""){
                Glide
                        .with(this)
                        .load(it.getImagePath())
                        .centerCrop()
                        .placeholder(R.drawable.profile)
                        .into(binding.profileImg);
            }
            headerView.findViewById<TextView>(R.id.name).text = it.getName()

            Glide
                    .with(this)
                    .load(it.getImagePath())
                    .centerCrop()
                    .placeholder(R.drawable.profile)
                    .into(headerView.findViewById<CircleImageView>(R.id.image));
        })
    }

    override fun onResume() {
        super.onResume()
        GlobalScope.launch {
            viewModel.getCurrentUserInfo(uid)
        }

        val headerView = binding.navView.getHeaderView(0)

        viewModel.employee.observe(this , Observer {
            binding.name.text = it.getName()
            binding.phone.text = it.getPhone()
            binding.whatsApp.text = it.getWhatsApp()

            binding.location.text = it.getArea() + "," + it.getGovernator()
            if(it.getImagePath()!=""){
                Glide
                        .with(this)
                        .load(it.getImagePath())
                        .centerCrop()
                        .placeholder(R.drawable.profile)
                        .into(binding.profileImg);
            }
            headerView.findViewById<TextView>(R.id.name).text = it.getName()

            Glide
                    .with(this)
                    .load(it.getImagePath())
                    .centerCrop()
                    .placeholder(R.drawable.profile)
                    .into(headerView.findViewById<CircleImageView>(R.id.image));
        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_me_profile)
        navView = binding.navView
        drawerLayout = binding.drawerLayout
        mAuth = FirebaseAuth.getInstance()
        uid = mAuth.currentUser!!.uid
        viewModel = ViewModelProviders.of(this).get(ProfileMeViewModel::class.java)

        setNavigationViewListener()


        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        supportActionBar!!.elevation= 0.0F


        GlobalScope.launch {
        viewModel.getCurrentUserInfo(uid)
        }

       val headerView = binding.navView.getHeaderView(0)
        
        viewModel.employee.observe(this , Observer {
        binding.name.text = it.getName()
        binding.phone.text = it.getPhone()
        binding.whatsApp.text = it.getWhatsApp()

        binding.location.text = it.getArea() + "," + it.getGovernator()
        if(it.getImagePath()!=""){
            Glide
                .with(this)
                .load(it.getImagePath())
                .centerCrop()
                .placeholder(R.drawable.profile)
                .into(binding.profileImg);
        }
        headerView.findViewById<TextView>(R.id.name).text = it.getName()

                    Glide
                    .with(this)
                    .load(it.getImagePath())
                    .centerCrop()
                    .placeholder(R.drawable.profile)
                    .into(headerView.findViewById<CircleImageView>(R.id.image));
        })

        binding.modify.setOnClickListener {
          val intent = Intent(this , ModifyActivity::class.java)

          intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
          intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
          startActivity(intent)
        }

        //make actions here
        binding.imgBack.setOnClickListener {
            finish();
        }

        binding.imgMenu.setOnClickListener {
        drawerLayout.openDrawer(GravityCompat.END)
        }

        binding.opinion.setOnClickListener {
            val showOpinions = newInstance

            val bundle = Bundle()
            bundle.putString("id", uid)
            showOpinions.arguments = bundle

            showOpinions.show(supportFragmentManager, TAG)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.logout -> {
                mAuth.signOut()
                val intent = Intent(this , MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }

            R.id.delete ->{
                viewModel.deleteCurrentUser(uid)
                val intent = Intent(this , MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }

        }
        drawerLayout.closeDrawer(GravityCompat.END)
        return true
    }

    private fun setNavigationViewListener() {
        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
    }

}
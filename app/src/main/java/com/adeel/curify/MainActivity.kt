package com.adeel.curify

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.adeel.AdminModule.AdminMainScreen
import com.adeel.AuthenticationModule.Forgetpassword
import com.adeel.DataSources.CloudinaryUploadHelper.Companion.initializeCloudinary
import com.adeel.ViewModels.AuthViewModel
import com.adeel.curify.databinding.ActivityMainBinding
import com.adeel.repositories.AuthRepository
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: AuthViewModel
    companion object {
        var adminUid = "TEKfgTjdU1cfBIRshfADn279lWu1"
        var useremail = ""
        var isAdmin = false
        var names=""

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val currentUserUid = FirebaseAuth.getInstance().currentUser?.uid
//
//        val userad: FirebaseUser = AuthRepository().getcurrentuser()!!
//        if (userad.email.equals("curify90@gmail.com") && adminUid== currentUserUid) {
//            startActivity(Intent(this@MainActivity, AdminMainScreen::class.java))
//            finish()
//
//
//        }
        initializeCloudinary(this)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val user: FirebaseUser = AuthRepository().getcurrentuser()!!
        names=user.displayName!!
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val bottomNavigationView = findViewById<BottomNavigationView>(
            R.id.bottomNavigation
        )
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navHostFragment.navController)

    }
}
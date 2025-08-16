package com.adeel.AdminModule

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adeel.AuthenticationModule.Forgetpassword
import com.adeel.AuthenticationModule.LoginActivity
import com.adeel.curify.R
import com.adeel.curify.databinding.ActivityAddMedicineBinding
import com.adeel.curify.databinding.ActivityAdminMainScreenBinding
import com.adeel.curify.databinding.FragmentDoctorsBinding

class AdminMainScreen : AppCompatActivity() {
    lateinit var binding: ActivityAdminMainScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
binding.logoutad.setOnClickListener{
    startActivity(Intent(this@AdminMainScreen, LoginActivity::class.java))
    finish()

}
        binding.managemed.setOnClickListener{
            startActivity(Intent(this@AdminMainScreen, AdminManageMedicine::class.java))

        }
    }
}
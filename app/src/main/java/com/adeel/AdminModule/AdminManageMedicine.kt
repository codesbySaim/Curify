package com.adeel.AdminModule

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adeel.AuthenticationModule.LoginActivity
import com.adeel.curify.R
import com.adeel.curify.databinding.ActivityAdminMainScreenBinding
import com.adeel.curify.databinding.ActivityAdminManageMedicineBinding

class AdminManageMedicine : AppCompatActivity() {
    lateinit var binding: ActivityAdminManageMedicineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminManageMedicineBinding.inflate(layoutInflater)
        setContentView(binding.root)
binding.addmed.setOnClickListener{

    startActivity(Intent(this, AdminUADMedicine::class.java))

}

    }
}
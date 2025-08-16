package com.adeel.curify

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adeel.AuthenticationModule.LoginActivity
import com.adeel.AuthenticationModule.SignupActivity
import com.adeel.curify.databinding.ActivityStartScreenBinding

class start_screen : AppCompatActivity() {
    lateinit var binding:ActivityStartScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityStartScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

binding.signbtn.setOnClickListener{
    startActivity(Intent(this, SignupActivity::class.java))
    finish()
}
        binding.loginbtn.setOnClickListener{
    startActivity(Intent(this, LoginActivity::class.java))
            finish()
}
    }
}
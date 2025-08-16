package com.adeel.PaymentModule

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adeel.curify.R
import com.adeel.curify.databinding.ActivityAddedPaymentMethodBinding
import com.adeel.curify.databinding.ActivityPaymentDetailBinding

class PaymentDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityPaymentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityPaymentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.name.text = PaymentMethods.nameq
        binding.number.text = PaymentMethods.numberq
        if(PaymentMethods.jazzcash) {
            binding.pymticon.setImageResource(R.drawable.jazzcash)

        }
        if(PaymentMethods.easypaisa) {
            binding.pymticon.setImageResource(R.drawable.easypaisa)

        }
        binding.changepymtbtn.setOnClickListener{
            startActivity(Intent(this@PaymentDetailActivity, PaymentMethods::class.java))
            finish()
        }


    }
}
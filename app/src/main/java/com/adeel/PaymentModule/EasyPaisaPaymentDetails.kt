package com.adeel.PaymentModule

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adeel.curify.PaymentViewModel
import com.adeel.curify.R
import com.adeel.curify.databinding.ActivityEasyPaisaPaymentDetailsBinding

class EasyPaisaPaymentDetails : AppCompatActivity() {
    lateinit var binding: ActivityEasyPaisaPaymentDetailsBinding
    lateinit var viewModel: PaymentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEasyPaisaPaymentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = PaymentViewModel()
        binding.addbtnep.setOnClickListener {
            val name = binding.holdernameep.text.toString()
            val phone = binding.holderphonenumberep.text.toString()
            if (!name.matches(Regex("^[a-zA-Z ]+$"))) {
                Toast.makeText(this, "Name must contain only letters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (phone.isEmpty() || !phone.startsWith("92") || phone.length < 12) {
                Toast.makeText(
                    this,
                    "Phone number must start with 92 and be valid",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            val paymenttype = PaymentTypeData()
            paymenttype.name = name
            paymenttype.phonenumber = phone
            paymenttype.Method="EasyPaisa"
            viewModel.savepaymenttype(paymenttype)
            Toast.makeText(
                this@EasyPaisaPaymentDetails,
                "Account Added Successfully",
                Toast.LENGTH_SHORT
            ).show()
            JazzcashPaymentDetails.paymentmethodadded=true
            PaymentMethods.jazzcash=false
            PaymentMethods.easypaisa=true
            PaymentMethods.nameq=name
            PaymentMethods.numberq=phone
            PaymentMethods.selectedImageResId= R.drawable.easypaisa
            startActivity(Intent(this@EasyPaisaPaymentDetails, PaymentDetailActivity::class.java))

            finish()

        }
    }
}
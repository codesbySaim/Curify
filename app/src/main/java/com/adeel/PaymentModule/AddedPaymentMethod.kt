package com.adeel.PaymentModule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.adeel.curify.PaymentViewModel
import com.adeel.curify.databinding.ActivityAddedPaymentMethodBinding
import kotlinx.coroutines.launch

class AddedPaymentMethod : AppCompatActivity() {
    lateinit var binding: ActivityAddedPaymentMethodBinding
    lateinit var viewModel: PaymentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddedPaymentMethodBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = PaymentViewModel()
        viewModel.readpetshop()

        lifecycleScope.launch {
            viewModel.data.collect {
                it?.let {
                    binding.name.text = it.name
                    binding.number.text = it.phonenumber
                    binding.method.text = it.Method
                } ?: run {
                    // Handle case when no data is available
                    binding.name.text = "No data"
                    binding.number.text = ""
                    binding.method.text = ""
                }
            }
        }




    }
}
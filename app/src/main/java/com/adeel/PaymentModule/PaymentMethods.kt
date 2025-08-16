package com.adeel.PaymentModule

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.adeel.curify.PaymentViewModel
import com.adeel.curify.R
import com.adeel.curify.databinding.ActivityPaymentMethodsBinding
import kotlinx.coroutines.launch

class PaymentMethods : AppCompatActivity() {
    lateinit var binding: ActivityPaymentMethodsBinding
    lateinit var viewModel: PaymentViewModel
    companion object {
        var jazzcash = false
        var easypaisa = false
        var nameq = ""
        var selectedImageResId: Int = R.drawable.jazzcash
        var numberq=""


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPaymentMethodsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = PaymentViewModel()

        lifecycleScope.launch {
            viewModel.isSuccessfullySaved.collect{
                it?.let {
                    if(it==true)
                        Toast.makeText(this@PaymentMethods, "Account Added Successfully", Toast.LENGTH_SHORT).show()
                }
            }
        }
        lifecycleScope.launch {
            viewModel.failureMessage.collect{
                it?.let {

                        Toast.makeText(this@PaymentMethods, it, Toast.LENGTH_SHORT).show()
                }
            }
        }

      binding.jazzcashbtn.setOnClickListener{
    startActivity(Intent(this@PaymentMethods, JazzcashPaymentDetails::class.java))
          finish()


}
        binding.easypaisabtn.setOnClickListener{
    startActivity(Intent(this@PaymentMethods, EasyPaisaPaymentDetails::class.java))
            finish()


}



    }
}
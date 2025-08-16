package com.adeel.curify

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.adeel.AuthenticationModule.LoginActivity
import com.adeel.PaymentModule.AddedPaymentMethod
import com.adeel.PaymentModule.JazzcashPaymentDetails
import com.adeel.PaymentModule.PaymentDetailActivity
import com.adeel.PaymentModule.PaymentMethods
import com.adeel.ViewModels.AuthViewModel
import com.adeel.curify.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding
    lateinit var viewModel: AuthViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.name.text=MainActivity.names
        viewModel = AuthViewModel()

binding.paymentmethodbtn.setOnClickListener{
    if(PaymentMethods.easypaisa || PaymentMethods.jazzcash) {
        startActivity(Intent(context, PaymentDetailActivity::class.java))
    }
    else {
        startActivity(Intent(context, PaymentMethods::class.java))
    }

}
        binding.mycartbtn.setOnClickListener{
            startActivity(Intent(context, MyCart::class.java))
        }

        binding.logoutbtn.setOnClickListener{

            viewModel.logout(true)


        }
        lifecycleScope.launch {
            viewModel.islogoutsuccess.collect {
                it?.let {
                    if (it == true) {
                        Toast.makeText(context, "logged out", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(context, LoginActivity::class.java))
                        requireActivity().finish()
                    }
                }
            }
        }
    }
}
package com.adeel.curify

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.adeel.AdminModule.AdminAdapter
import com.adeel.AdminModule.CartAdapter
import com.adeel.MedicalStoreModule.MedicineViewModel
import com.adeel.MedicalStoreModule.MyCartViewModel
import com.adeel.PaymentModule.PaymentDetailActivity
import com.adeel.PaymentModule.PaymentMethods
import com.adeel.curify.databinding.ActivityAddMedicineBinding
import com.adeel.curify.databinding.ActivityMyCartBinding
import com.adeel.repositories.MyCartRepository
import kotlinx.coroutines.launch

class MyCart : AppCompatActivity() {
    lateinit var binding: ActivityMyCartBinding
    lateinit var adapter: CartAdapter
    lateinit var viewModel: MyCartViewModel
    private var medicineList = ArrayList<MyCartData>()
    var items=ArrayList<MyCartData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

//if(PaymentMethods.jazzcash) {
//    binding.img.setImageResource(R.drawable.jazzcash)
//    binding.addpaymentmtd.visibility = View.GONE
//}
//        if(PaymentMethods.easypaisa) {
//            binding.img.setImageResource(R.drawable.easypaisa)
//            binding.addpaymentmtd.visibility = View.GONE
//        }
        binding.img.setImageResource(PaymentMethods.selectedImageResId)
if(!PaymentMethods.jazzcash&&!PaymentMethods.easypaisa){
    binding.img.visibility= View.GONE
    binding.changepaymentmethodtxt.visibility= View.GONE
    binding.addpaymentmtd.visibility= View.VISIBLE

}
        else
{
    binding.img.visibility= View.VISIBLE
    binding.changepaymentmethodtxt.visibility= View.VISIBLE
    binding.addpaymentmtd.visibility= View.GONE

}
        adapter = CartAdapter(items)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this@MyCart, LinearLayoutManager.VERTICAL, false)

binding.addpaymentmtd.setOnClickListener{
    startActivity(Intent(this@MyCart, PaymentMethods::class.java))
    finish()

}


binding.changepaymentmethodtxt.setOnClickListener{
    startActivity(Intent(this@MyCart, PaymentMethods::class.java))
    finish()
}
        viewModel = MyCartViewModel()
        lifecycleScope.launch {
            viewModel.failuremessge.collect {
                it?.let {

                    Toast.makeText(this@MyCart, it, Toast.LENGTH_SHORT).show()

                }
            }
        }
        lifecycleScope.launch {
            viewModel.data.collect {
                it?.let {

                    items.clear()
                    items.addAll(0, it)
                    adapter.notifyDataSetChanged()

                    val totalPrice = viewModel.getTotalPrice()
                    binding.totaltxt1.text = "PKR $totalPrice"
                    binding.totaltxt2.text = "PKR $totalPrice"

                }
            }

        }
        fun updateTotalPrice() {
            val total = medicineList.sumOf {
                val pricePerUnit = it.price.toDoubleOrNull() ?: 0.0
                val quantity = it.quantity.toIntOrNull() ?: 0
                pricePerUnit * quantity
            }

            // Now update UI, e.g., a TextView showing total price:
            binding.totaltxt2.text = "PKR $total"
            binding.totaltxt1.text = "PKR $total"
        }

    }
}
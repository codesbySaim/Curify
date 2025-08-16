package com.adeel.MedicalStoreModule

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adeel.MedicalStoreModule.UpdateMedicine.Companion.medid
import com.adeel.curify.Medicine
import com.adeel.curify.MyCartData
import com.adeel.curify.R
import com.adeel.curify.databinding.ActivityAddMedicineBinding
import com.adeel.curify.databinding.ActivityMedicineDetailBinding
import com.google.gson.Gson

class MedicineDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityMedicineDetailBinding
    lateinit var medicine: Medicine
    private var count = 1
    lateinit var viewModel:MyCartViewModel
    private var price = 0
    private var addprice =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = MyCartViewModel()
        binding = ActivityMedicineDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        medicine = Gson().fromJson(intent.getStringExtra("data"), Medicine::class.java)
        medid = medicine.id

        binding.medname.setText(medicine.title)
        binding.meddesc.setText(medicine.description)
        binding.medprice.setText("PKR "+medicine.price)
        price=medicine.price.toIntOrNull()!!
        addprice=medicine.price.toIntOrNull()!!
        binding.medweight.setText(medicine.weight)

        binding.plus.setOnClickListener {
            if(count < medicine.quantity.toIntOrNull()!!) {
                count++
price=price+addprice
                binding.inputquantity.text = count.toString()
                binding.medprice.setText("PKR "+price)
            }
        }

        binding.minus.setOnClickListener {
            if (count > 1) {
                count--
                price=price-addprice
                binding.inputquantity.text = count.toString()
                binding.medprice.setText("PKR "+price)
            }

        }
        binding.addtocartbtn.setOnClickListener{
            val mycart = MyCartData()
            mycart.title =medicine.title
            mycart.price=medicine.price
            mycart.quantity=medicine.quantity
            mycart.weight=medicine.weight
            mycart.image=medicine.image
            viewModel.savemycart(mycart)
        }
    }
}
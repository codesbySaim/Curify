package com.adeel.AdminModule

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
import com.adeel.AuthenticationModule.LoginActivity
import com.adeel.DataSources.CloudinaryUploadHelper.Companion.initializeCloudinary
import com.adeel.MedicalStoreModule.AddMedicine
import com.adeel.MedicalStoreModule.MedicineViewModel
import com.adeel.curify.Medicine
import com.adeel.curify.MedicineFragmentViewModel
import com.adeel.curify.ProjectAdapter
import com.adeel.curify.R
import com.adeel.curify.databinding.ActivityAdminManageMedicineBinding
import com.adeel.curify.databinding.ActivityAdminUadmedicineBinding
import kotlinx.coroutines.launch

class AdminUADMedicine : AppCompatActivity() {
    lateinit var binding: ActivityAdminUadmedicineBinding
    lateinit var adapter: AdminAdapter
    lateinit var viewModelp: MedicineViewModel
    lateinit var viewModel: MedicineFragmentViewModel
    var items=ArrayList<Medicine>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModelp= MedicineViewModel()
        binding = ActivityAdminUadmedicineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeCloudinary(this)
        adapter = AdminAdapter(items)
binding.addmed.setOnClickListener{
    startActivity(Intent(this, AddMedicine::class.java))

}
        binding.adminrecyclerview.adapter = adapter
        binding.adminrecyclerview.layoutManager = LinearLayoutManager(this@AdminUADMedicine, LinearLayoutManager.VERTICAL, false)
        viewModel = MedicineFragmentViewModel()
        lifecycleScope.launch {
            viewModel.failuremessge.collect {
                it?.let {

                    Toast.makeText(this@AdminUADMedicine, it, Toast.LENGTH_SHORT).show()

                }
            }
        }
        lifecycleScope.launch {
            viewModel.data.collect {
                it?.let {

                    items.clear()
                    items.addAll(0, it)
                    adapter.notifyDataSetChanged()


                }
            }
        }

    }

}
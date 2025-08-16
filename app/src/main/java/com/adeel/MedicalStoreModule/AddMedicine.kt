package com.adeel.MedicalStoreModule

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.adeel.curify.Medicine
import com.adeel.curify.R
import com.adeel.curify.databinding.ActivityAddMedicineBinding
import com.adeel.curify.databinding.ActivityStartScreenBinding
import com.adeel.curify.databinding.FragmentMedicineBinding
import com.bumptech.glide.Glide
import com.google.gson.Gson

class AddMedicine : AppCompatActivity() {
    lateinit var binding: ActivityAddMedicineBinding
    lateinit var viewModel:MedicineViewModel

    private var uri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    //    var update=false
//        val json = intent.getStringExtra("data")
//        if (json != null) {
//            update=true
//            val medicine = Gson().fromJson(json, Medicine::class.java)
//            binding.medname.setText(medicine.title)
//            binding.medweight.setText(medicine.weight)
//            binding.meddesc.setText(medicine.description)
//            binding.medquantity.setText(medicine.quantity)
//            binding.medprice.setText(medicine.price)
//            Glide.with(this)
//                .load(medicine.image)
//                .placeholder(R.drawable.logo_curify)
//                .error(R.drawable.logo_curify)
//                .into(binding.medicon)
//
//
//
//
//            // Now you can use `medicine` object
//            // Example: binding.medname.setText(medicine.title)
//        } else {
//            Toast.makeText(this, "No data received", Toast.LENGTH_SHORT).show()
//            finish()
//        }
//        if(update){
//            binding.addmed.text="Update"
//        }
//        else {
//            binding.addmed.text="Add"
//        }
        viewModel = MedicineViewModel()
        binding = ActivityAddMedicineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.addmed.setOnClickListener {

            val title = binding.medname.text.toString().trim()
            val description = binding.meddesc.text.toString().trim()
            val weightmed = binding.medweight.text.toString().trim()
            val medquantity = binding.medquantity.text.toString().trim()
            // Inside your Activity or Fragment after binding is initialized



            val selectedRadioButtonId = binding.medicineTypeRadioGroup.checkedRadioButtonId
            var selectedMedicineType=""
            if (selectedRadioButtonId != -1) {  // Check if any radio button is selected
                val selectedRadioButton =
                    binding.root.findViewById<RadioButton>(selectedRadioButtonId)
                 selectedMedicineType = selectedRadioButton.text.toString()

                // Now you can use selectedMedicineType variable
                //Toast.makeText(this, "Selected Medicine: $selectedMedicineType", Toast.LENGTH_SHORT)
                 //   .show()
            } else {
                Toast.makeText(this, "No medicine type selected", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }

            val priceText = binding.medprice.text.toString().trim()

            // Validate the input fields
            if (title.isEmpty() || description.isEmpty() || weightmed.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            val price = priceText.toIntOrNull()
//
//            if (price == null) {
//                Toast.makeText(this, "Please enter a valid price", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//            val quantity = medquantity.toIntOrNull()
//
//            if (quantity == null) {
//                Toast.makeText(this, "Please enter a valid price", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }

            val medicine = Medicine()
            medicine.title = title
            medicine.description = description
            medicine.status = "In Stock"
            medicine.price=priceText
            medicine.quantity=medquantity
            medicine.weight=weightmed
            medicine.type=selectedMedicineType
          //  if(update){
               // viewModel.updatepetshop(medicine)
             //   Toast.makeText(this, "Medicine Updated Successfully", Toast.LENGTH_SHORT).show()
           // }
          //  else {
                if (uri == null)
                    viewModel.savemedicine(medicine)
                else
                    viewModel.uploadImageAndmedicine(getRealPathFromURI(uri!!)!!, medicine)
                Toast.makeText(this, "Medicine Added Successfully", Toast.LENGTH_SHORT).show()
          //  }
            finish()

        }

        binding.medicon.setOnClickListener {
            chooseImageFromGallery()
        }

    }
    private fun chooseImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryLauncher.launch(intent)
    }
    private val galleryLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            uri = result.data?.data
            if (uri != null) {
                binding.medicon.setImageURI(uri)
            } else {
                Log.e("Gallery", "No image selected")
            }
        }
    }

    private fun getRealPathFromURI(uri: Uri): String? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        contentResolver.query(uri, projection, null, null, null)?.use { cursor ->
            val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            if (cursor.moveToFirst()) {
                return cursor.getString(columnIndex)
            }
        }
        return null
    }


}

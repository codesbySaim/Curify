package com.adeel.curify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.adeel.curify.databinding.FragmentHomeBinding
import com.adeel.curify.databinding.FragmentMedicineBinding
import kotlinx.coroutines.launch
import java.util.Locale


class MedicineFragment : Fragment() {

    lateinit var binding: FragmentMedicineBinding
    lateinit var adapter: ProjectAdapter
    lateinit var viewModel: MedicineFragmentViewModel
    private val medicineList = ArrayList<Medicine>()
    //private val medicineList = mutableListOf<Medicine>()
   // var items=ArrayList<Medicine>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMedicineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ProjectAdapter(medicineList)
binding.name.clearFocus()

        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        viewModel = MedicineFragmentViewModel()
        lifecycleScope.launch {
            viewModel.failuremessge.collect {
                it?.let {

                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

                }
            }
        }
        lifecycleScope.launch {
            viewModel.data.collect { data ->
                data?.let {
                    medicineList.clear()
                    medicineList.addAll(it.take(10))
                    adapter.notifyDataSetChanged()
                }
            }
        }
        binding.name.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })
    }

    private fun filterList(query: String?) {
        if (query.isNullOrBlank()) {
            // If search is cleared, show full list
            adapter.setFilteredList(medicineList)
            return
        }

        val filteredList = ArrayList<Medicine>()
        for (medicine in medicineList) {
            if (medicine.title?.lowercase(Locale.ROOT)?.contains(query.lowercase()) == true) {
                filteredList.add(medicine)
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(context, "No medicine found", Toast.LENGTH_SHORT).show()
        }

        adapter.setFilteredList(filteredList)
    }

}



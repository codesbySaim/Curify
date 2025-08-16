package com.adeel.AdminModule

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adeel.MedicalStoreModule.AddMedicine
import com.adeel.MedicalStoreModule.MedicineDetailActivity
import com.adeel.MedicalStoreModule.MedicineViewModel
import com.adeel.MedicalStoreModule.UpdateMedicine
import com.adeel.curify.Medicine
import com.adeel.curify.R
import com.adeel.curify.databinding.ItemMedicineAdminBinding
import com.adeel.curify.databinding.ItemMedicineBinding
import com.bumptech.glide.Glide
import com.google.gson.Gson


class AdminAdapter(val items: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       // if (viewType == 0) {
            val binding =
                ItemMedicineAdminBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return AdminMedicineViewHolder(binding)
        }

        //else if(viewType==1){
        //  val binding =
        //    ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //return OrderViewHolder(binding)
        // }else{
        //   val binding =
        //     ItemBidBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        // return BidViewHolder(binding)
        //}


  //  }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        if (items.get(position) is Medicine) return 0
     //   if (items.get(position) is Order) return 1
      //  if (items.get(position) is biditem) return 2
        return 3
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is AdminMedicineViewHolder) {
            val medicine = items.get(position) as Medicine
            holder.binding.title.text = medicine.title
            holder.binding.weight.text = medicine.weight
            holder.binding.status.text = medicine.status
//            if (medicine.status == "Out of stock")
//                holder.binding.status.setTextColor(Color.RED)
//            else
          //      holder.binding.status.setTextColor(Color.GREEN)
            holder.binding.price.text = medicine.price.toString()

            Glide.with(holder.itemView.context)
                .load(medicine.image)
                .error(R.drawable.logo_curify)
                .placeholder(R.drawable.logo_curify)
                .into(holder.binding.productImage)

            holder.itemView.setOnLongClickListener {
                val context = holder.itemView.context
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Select Action")
                builder.setMessage("Do you want to update or delete this medicine?")


                builder.setPositiveButton("Update") { dialog, _ ->
                    context.startActivity(
                        Intent(context,UpdateMedicine::class.java)
                            .putExtra("data", Gson().toJson(medicine))
                    )
                    dialog.dismiss()
                }


                builder.setNegativeButton("Delete") { dialog, _ ->
                    // Assuming you have a ViewModel in your Activity/Fragment
                    if (context is AdminUADMedicine) {
                        context.viewModelp.deletePetShop(medicine.id)
                    }
                    dialog.dismiss()
                }

                builder.setNeutralButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }

                builder.show()
                true
            }
        }


        }
//
//        else if (holder is OrderViewHolder) {
//            val order = items.get(position) as Order
//            holder.binding.productTitle.text = order.item?.title
//
//            Glide.with(holder.itemView.context)
//                .load(order.image)
//                .error(R.drawable.paws)
//                .placeholder(R.drawable.paws)
//                .into(holder.binding.productImage)
//            holder.binding.productPrice.text=order.price
//            holder.binding.status.text =order.status
//
//            holder.itemView.setOnClickListener {
//                holder.itemView.context.startActivity(
//                    Intent(
//                        holder.itemView.context,
//                        OrderDetailsActivity::class.java
//                    ).putExtra("data", Gson().toJson(order))
//                )
//            }
//
//
//        }
//        else if (holder is BidViewHolder) {
//            val bid = items.get(position) as biditem
//
//            holder.binding.biddername.text=bid.username
//            holder.binding.bidderprice.text=bid.bidamount
//        }
//
//
//    }
    }

package com.gilbram.whatsapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gilbram.whatsapp.R
import com.gilbram.whatsapp.listerner.ContactsClickListener
import com.gilbram.whatsapp.util.Contact
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_contact.*

class ContactsAdapter (val contacts : ArrayList<Contact>):
    RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>(){

    private var clickedListener: ContactsClickListener? = null

    class ContactsViewHolder(override val containerView: View):RecyclerView.ViewHolder(containerView),LayoutContainer {

        fun bindItem(contact:Contact,listerner:ContactsClickListener?){
            txt_contact_name.text = contact.nama
            txt_contact_number.text = contact.phone
            itemView.setOnClickListener{
                listerner?.onContactClicked(contact.nama,contact.phone)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ContactsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_contact,parent,false))

    override fun getItemCount()= contacts.size

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.bindItem(contacts[position],clickedListener)
    }

    fun setOnItemClickListener(listerner: ContactsClickListener){
        clickedListener = listerner
        notifyDataSetChanged()

    }
}
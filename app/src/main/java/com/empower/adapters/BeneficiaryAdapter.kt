package com.empower.adapters

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.empower.R
import com.empower.model.Beneficiary
import com.empower.utils.Constants

class BeneficiaryAdapter(
    private val beneficiaries: List<Beneficiary>, private val onClick: (Beneficiary) -> Unit
) : RecyclerView.Adapter<BeneficiaryAdapter.BeneficiaryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeneficiaryViewHolder {
        val textView = TextView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setPadding(16, 16, 16, 16)
            textSize = 20f  // Increased text size
        }
        return BeneficiaryViewHolder(textView)
    }

    override fun onBindViewHolder(holder: BeneficiaryViewHolder, position: Int) {
        val beneficiary = beneficiaries[position]
        val designation = when (beneficiary.designationCode) {
            Constants.DESIGNATION_CODE_PRIMARY -> Constants.DESIGNATION_PRIMARY
            Constants.DESIGNATION_CODE_CONTINGENT -> Constants.DESIGNATION_CONTINGENT
            else -> Constants.DESIGNATION_UNKNOWN
        }

        val firstAndLastName = "${beneficiary.firstName} ${beneficiary.lastName}"
        val text = "$firstAndLastName (${beneficiary.beneType} - $designation)"
        val spannable = SpannableString(text)

        // Apply bold style to the name
        val nameEndIndex = firstAndLastName.length
        spannable.setSpan(
            StyleSpan(Typeface.BOLD), 0, nameEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // Apply color to the entire text
        spannable.setSpan(
            ForegroundColorSpan(holder.textView.context.getColor(android.R.color.black)), 0, spannable.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        holder.textView.text = spannable

        // Alternate row color
        val backgroundColor = if (position % 2 == 0) {
            holder.textView.context.getColor(android.R.color.white)
        } else {
            holder.textView.context.getColor(R.color.light_gray)  // Use light_gray
        }
        holder.textView.setBackgroundColor(backgroundColor)

        holder.textView.setOnClickListener { onClick(beneficiary) }
    }

    override fun getItemCount() = beneficiaries.size

    class BeneficiaryViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
}

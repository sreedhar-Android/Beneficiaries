package com.empower.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.empower.utils.Constants
import com.empower.utils.DateUtils.formatDate
import com.empower.viewmodels.BeneficiaryViewModel
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.text.style.ForegroundColorSpan
import android.graphics.Typeface

class BeneficiaryDetailFragment : Fragment() {

    private lateinit var viewModel: BeneficiaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity())[BeneficiaryViewModel::class.java]

        val layout = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            )
            setPadding(16, 16, 16, 16)
        }

        viewModel.selectedBeneficiary.observe(viewLifecycleOwner) { beneficiary ->
            if (beneficiary != null) {
                layout.addView(createTextView("Name: ", "${beneficiary.firstName} ${beneficiary.middleName ?: ""} ${beneficiary.lastName}"))
                layout.addView(createTextView("Type: ", beneficiary.beneType))
                layout.addView(createTextView("Designation: ", if (beneficiary.designationCode == Constants.DESIGNATION_CODE_PRIMARY) Constants.DESIGNATION_PRIMARY else Constants.DESIGNATION_CONTINGENT))
                layout.addView(createTextView("SSN: ", beneficiary.socialSecurityNumber))
                layout.addView(createTextView("Date of Birth: ", formatDate(beneficiary.dateOfBirth)))
                layout.addView(createTextView("Phone: ", beneficiary.phoneNumber))
                layout.addView(createTextView("Address: ", "${beneficiary.beneficiaryAddress.firstLineMailing}, ${beneficiary.beneficiaryAddress.city}, ${beneficiary.beneficiaryAddress.stateCode}, ${beneficiary.beneficiaryAddress.zipCode}, ${beneficiary.beneficiaryAddress.country}"))
            }
        }

        return layout
    }

    private fun createTextView(label: String, value: String): TextView {
        return TextView(requireContext()).apply {
            val spannable = SpannableString("$label$value")
            spannable.setSpan(StyleSpan(Typeface.BOLD), 0, label.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannable.setSpan(ForegroundColorSpan(requireContext().getColor(android.R.color.black)), 0, spannable.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            text = spannable
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setPadding(0, 8, 0, 8)
            textSize = 20f  // Set text size to 20sp
        }
    }
}

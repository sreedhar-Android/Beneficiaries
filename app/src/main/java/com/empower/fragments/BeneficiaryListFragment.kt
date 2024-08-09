package com.empower.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.empower.adapters.BeneficiaryAdapter
import com.empower.utils.JsonUtils.loadJsonFromAssets
import com.empower.utils.JsonUtils.parseBeneficiaries
import com.empower.viewmodels.BeneficiaryViewModel

class BeneficiaryListFragment : Fragment() {

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
        }

        val recyclerView = RecyclerView(requireContext()).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            )
            layoutManager = LinearLayoutManager(requireContext())
            setPadding(16, 16, 16, 16)
        }
        layout.addView(recyclerView)

        val beneficiaries = parseBeneficiaries(loadJsonFromAssets(requireContext()))

        val adapter = BeneficiaryAdapter(beneficiaries) { beneficiary ->
            viewModel.selectBeneficiary(beneficiary)
            val detailFragment = BeneficiaryDetailFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace((requireView().parent as View).id, detailFragment)
                .addToBackStack(null)
                .commit()
        }
        recyclerView.adapter = adapter

        return layout
    }
}

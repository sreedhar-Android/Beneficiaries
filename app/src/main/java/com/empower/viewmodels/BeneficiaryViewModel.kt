package com.empower.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.empower.model.Beneficiary

class BeneficiaryViewModel : ViewModel() {
    private val _selectedBeneficiary = MutableLiveData<Beneficiary>()
    val selectedBeneficiary: LiveData<Beneficiary> get() = _selectedBeneficiary

    fun selectBeneficiary(beneficiary: Beneficiary) {
        _selectedBeneficiary.value = beneficiary
    }
}

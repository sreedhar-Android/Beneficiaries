package com.empower.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Beneficiary(
    val firstName: String,
    val lastName: String,
    val middleName: String?,
    val designationCode: String,
    val beneType: String,
    val socialSecurityNumber: String,
    val dateOfBirth: String,
    val phoneNumber: String,
    val beneficiaryAddress: Address
) : Parcelable
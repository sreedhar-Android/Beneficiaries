package com.empower.utils

import android.content.Context
import com.empower.model.Address
import com.empower.model.Beneficiary
import org.json.JSONArray

object JsonUtils {

    fun parseBeneficiaries(jsonString: String): List<Beneficiary> {
        val beneficiaries = mutableListOf<Beneficiary>()
        val jsonArray = JSONArray(jsonString)
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val addressObject = jsonObject.getJSONObject("beneficiaryAddress")

            val address = Address(
                addressObject.getString("firstLineMailing"),
                addressObject.optString("scndLineMailing"),
                addressObject.getString("city"),
                addressObject.getString("zipCode"),
                addressObject.getString("stateCode"),
                addressObject.getString("country")
            )

            val beneficiary = Beneficiary(
                jsonObject.getString("firstName"),
                jsonObject.getString("lastName"),
                jsonObject.optString("middleName"),
                jsonObject.getString("designationCode"),
                jsonObject.getString("beneType"),
                jsonObject.getString("socialSecurityNumber"),
                jsonObject.getString("dateOfBirth"),
                jsonObject.getString("phoneNumber"),
                address
            )

            beneficiaries.add(beneficiary)
        }
        return beneficiaries
    }

    fun loadJsonFromAssets(context: Context): String {
        return context.assets.open(Constants.JSON_FILE_NAME).bufferedReader().use {
            it.readText()
        }
    }
}

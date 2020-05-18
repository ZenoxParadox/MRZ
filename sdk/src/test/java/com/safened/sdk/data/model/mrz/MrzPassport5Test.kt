package com.safened.sdk.data.model.mrz

import com.safened.sdk.data.enums.MrzType
import com.safened.sdk.data.model.Mrz
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MrzPassport5Test {

    private lateinit var mrz: Mrz

    @BeforeEach
    fun setUp() {
        mrz = Mrz(
            "I<NLDXI020DF236123456783<<<<<<\n" +
            "7208148F1108268NLD<<<<<<<<<<<4\n" +
            "VAN<DER<STEEN<<MARIANNE<LOUISE"
        )
    }

    @Test
    fun getType() {
        Assertions.assertEquals(MrzType.TYPE_TD1, mrz.type)
    }

    @Test
    fun getDocumentNumber() {
        Assertions.assertEquals("XI020DF23", mrz.documentNumber)
    }

    @Test
    fun getFirstName() {
        Assertions.assertEquals("Marianne Louise", mrz.firstName)
    }

    @Test
    fun getDocumentCode() {
        Assertions.assertEquals("I", mrz.documentCode)
    }

    @Test
    fun getExpirationDate() {
        Assertions.assertEquals("110826", mrz.expirationDate)
    }

    @Test
    fun getLastName() {
        Assertions.assertEquals("Van der steen", mrz.lastName)
    }

    @Test
    fun getCountry() {
        Assertions.assertEquals("NLD", mrz.country)
    }

    @Test
    fun getNationality() {
        Assertions.assertEquals("NLD", mrz.nationality)
    }

    @Test
    fun getSex() {
        Assertions.assertEquals("F", mrz.sex)
    }

    @Test
    fun isValid() {
        Assertions.assertTrue(mrz.isValid())
    }
}
package com.safened.sdk.data.model.mrz

import com.safened.sdk.data.enums.MrzType
import com.safened.sdk.data.model.Mrz
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MrzPassport2Test {

    private lateinit var mrz: Mrz

    @BeforeEach
    fun setUp() {
        mrz = Mrz(
            "P<GBRSTONE<<SARAH<<<<<<<<<<<<<<<<<<<<<<<<<<<\n" +
            "0689349234GBR3708248F1601013<<<<<<<<<<<<<<06"
        )
    }

    @Test
    fun getType() {
        Assertions.assertEquals(MrzType.TYPE_TD3, mrz.type)
    }

    @Test
    fun getDocumentNumber() {
        Assertions.assertEquals("068934923", mrz.documentNumber)
    }

    @Test
    fun getDocumentCode() {
        Assertions.assertEquals("P", mrz.documentCode)
    }

    @Test
    fun getExpirationDate() {
        Assertions.assertEquals("160101", mrz.expirationDate)
    }

    @Test
    fun getFirstName() {
        Assertions.assertEquals("Sarah", mrz.firstName)
    }

    @Test
    fun getLastName() {
        Assertions.assertEquals("Stone", mrz.lastName)
    }

    @Test
    fun getNationality() {
        Assertions.assertEquals("GBR", mrz.nationality)
    }

    @Test
    fun isValid() {
        Assertions.assertTrue(mrz.isValid())
    }
}
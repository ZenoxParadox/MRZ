package com.safened.sdk.data.model.mrz

import com.safened.sdk.data.enums.MrzType
import com.safened.sdk.data.model.Mrz
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MrzPassport4Test {

    private lateinit var mrz: Mrz

    @BeforeEach
    fun setUp() {
        mrz = Mrz(
            "P<GBRROY<<TINA<FOLKS<LE<<<<<<<<<<<<<<<<<<<<<\n" +
            "2083617517GBR3701065M1601013<<<<<<<<<<TEST40"
        )
    }

    @Test
    fun getType() {
        Assertions.assertEquals(MrzType.TYPE_TD3, mrz.type)
    }

    @Test
    fun getDocumentNumber() {
        Assertions.assertEquals("208361751", mrz.documentNumber)
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
        Assertions.assertEquals("Tina Folks Le", mrz.firstName)
    }

    @Test
    fun getLastName() {
        Assertions.assertEquals("Roy", mrz.lastName)
    }

    @Test
    fun getNationality() {
        Assertions.assertEquals("GBR", mrz.nationality)
    }

    @Test
    fun getSex() {
        Assertions.assertEquals("M", mrz.sex)
    }

    @Test
    fun isValid() {
        Assertions.assertTrue(mrz.isValid())
    }
}
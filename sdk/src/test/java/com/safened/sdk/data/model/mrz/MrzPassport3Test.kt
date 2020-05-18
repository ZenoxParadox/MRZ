package com.safened.sdk.data.model.mrz

import com.safened.sdk.data.enums.MrzType
import com.safened.sdk.data.model.Mrz
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MrzPassport3Test {

    private lateinit var mrz: Mrz

    @BeforeEach
    fun setUp() {
        mrz = Mrz(
            "P<UTOERIKSSON<<ANNA<MARIA<<<<<<<<<<<<<<<<<<<\n" +
            "L898902C36UTO7408122F1204159ZE184226B<<<<<10"
        )
    }

    @Test
    fun getType() {
        Assertions.assertEquals(MrzType.TYPE_TD3, mrz.type)
    }

    @Test
    fun getDocumentNumber() {
        Assertions.assertEquals("L898902C3", mrz.documentNumber)
    }

    @Test
    fun getDocumentCode() {
        Assertions.assertEquals("P", mrz.documentCode)
    }

    @Test
    fun getExpirationDate() {
        Assertions.assertEquals("120415", mrz.expirationDate)
    }

    @Test
    fun getFirstName() {
        Assertions.assertEquals("Anna Maria", mrz.firstName)
    }

    @Test
    fun getLastName() {
        Assertions.assertEquals("Eriksson", mrz.lastName)
    }

    @Test
    fun getNationality() {
        Assertions.assertEquals("UTO", mrz.nationality)
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
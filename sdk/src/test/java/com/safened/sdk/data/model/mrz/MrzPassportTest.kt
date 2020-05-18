package com.safened.sdk.data.model.mrz

import com.safened.sdk.data.enums.MrzType
import com.safened.sdk.data.model.Mrz
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MrzPassportTest {

    private lateinit var mrz: Mrz

    @BeforeEach
    fun setUp() {
        mrz = Mrz(
            "P<GBRMASON<<DELILAH<<<<<<<<<<<<<<<<<<<<<<<<<\n" +
            "8703385446GBR4808259F1601013<<<<<<<<<<<<<<06"
        )
    }

    @Test
    fun getType() {
        Assertions.assertEquals(MrzType.TYPE_TD3, mrz.type)
    }

    @Test
    fun getDocumentNumber() {
        Assertions.assertEquals("870338544", mrz.documentNumber)
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
        Assertions.assertEquals("Delilah", mrz.firstName)
    }

    @Test
    fun getLastName() {
        Assertions.assertEquals("Mason", mrz.lastName)
    }

    @Test
    fun getNationality() {
        Assertions.assertEquals("GBR", mrz.nationality)
    }

    @Test
    fun getDateOfBirth() {
        Assertions.assertEquals("480825", mrz.dateOfBirth)
    }

    @Test
    fun getSex() {
        Assertions.assertEquals("F", mrz.sex)
    }

    /* ********** [ checksum ] ********** */

    @Test
    fun hasValidDocumentNumber() {
        Assertions.assertTrue(mrz.hasValidChecksumDocumentNumber())
    }

    @Test
    fun hasValidChecksumBirthday() {
        Assertions.assertTrue(mrz.hasValidChecksumBirthday())
    }

    @Test
    fun hasValidChecksumExpirationDate() {
        Assertions.assertTrue(mrz.hasValidChecksumExpirationDate())
    }

    @Test
    fun hasValidChecksumOptionalData() {
        Assertions.assertTrue(mrz.hasValidChecksumOptionalData())
    }

    @Test
    fun hasValidChecksumOverall() {
        Assertions.assertTrue(mrz.hasValidChecksumOverall())
    }

    /* ********** [ validity ] ********** */

    @Test
    fun isValid() {
        Assertions.assertTrue(mrz.isValid())
    }
}
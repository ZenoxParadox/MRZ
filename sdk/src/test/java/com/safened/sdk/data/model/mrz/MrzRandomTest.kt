package com.safened.sdk.data.model.mrz

import com.safened.sdk.data.enums.MrzType
import com.safened.sdk.data.model.Mrz
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MrzRandomTest {

    private lateinit var mrz: Mrz

    @BeforeEach
    fun setUp() {
        mrz = Mrz("!@#^&*()!@#%^&*()!@#^&*()!@#%^\n" +
                        "@#^&*()!@#%^&*()!@#^&*()!@#%^!\n" +
                        "#^&*()!@#%^&*()!@#^&*()!@#%^!@")
    }

    @Test
    fun isValid() {
        Assertions.assertFalse(mrz.isValid())
    }

    @Test
    fun hasValidDocumentNumber() {
        Assertions.assertFalse(mrz.hasValidChecksumDocumentNumber())
    }

    @Test
    fun hasValidChecksumBirthday() {
        Assertions.assertFalse(mrz.hasValidChecksumBirthday())
    }

    @Test
    fun hasValidChecksumExpirationDate() {
        Assertions.assertFalse(mrz.hasValidChecksumExpirationDate())
    }

    @Test
    fun hasValidChecksumOverall() {
        Assertions.assertFalse(mrz.hasValidChecksumOverall())
    }

    @Test
    fun getType() {
        Assertions.assertEquals(MrzType.TYPE_UNKNOWN, mrz.type)
    }

    @Test
    fun getDocumentNumber() {
        Assertions.assertEquals("", mrz.documentNumber)
    }

    @Test
    fun getDocumentCode() {
        Assertions.assertEquals("", mrz.documentCode)
    }

    @Test
    fun getExpirationDate() {
        Assertions.assertEquals("", mrz.expirationDate)
    }

    @Test
    fun getFirstName() {
        Assertions.assertEquals("", mrz.firstName)
    }

    @Test
    fun getLastName() {
        Assertions.assertEquals("", mrz.lastName)
    }

    @Test
    fun getNationality() {
        Assertions.assertEquals("", mrz.nationality)
    }

    @Test
    fun getDateOfBirth() {
        Assertions.assertEquals("", mrz.dateOfBirth)
    }

    @Test
    fun getSex() {
        Assertions.assertEquals("", mrz.sex)
    }

}
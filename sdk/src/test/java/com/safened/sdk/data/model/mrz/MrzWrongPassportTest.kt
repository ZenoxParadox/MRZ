package com.safened.sdk.data.model.mrz

import com.safened.sdk.data.model.Mrz
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MrzWrongPassportTest {

    private lateinit var mrz: Mrz

    @BeforeEach
    fun setUp() {
        mrz = Mrz(
            "P<GBRMASON<<DELILAH<<<<<<<<<<<<<<<<<<<<<<<<<\n" +
            "8703385446GBR4808259F1601019<<<<<<<<<<<<<<06"
        )
    }

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
        Assertions.assertFalse(mrz.hasValidChecksumExpirationDate())
    }

    @Test
    fun hasValidChecksumOverall() {
        Assertions.assertFalse(mrz.hasValidChecksumOverall())
    }

    /* ********** [] ********** */

    @Test
    fun isValid() {
        Assertions.assertFalse(mrz.isValid())
    }
}
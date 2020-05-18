package com.safened.sdk.data.model.mrz

import com.safened.sdk.data.model.Mrz
import com.safened.sdk.data.enums.MrzType
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MrzEmptyTest {

    private lateinit var mrz: Mrz

    @BeforeEach
    fun setUp() {
        mrz = Mrz("")
    }

    @Test
    fun getType() {
        Assertions.assertEquals(MrzType.TYPE_UNKNOWN, mrz.type)
    }

    @Test
    fun isValid() {
        Assertions.assertFalse(mrz.isValid())
    }
}
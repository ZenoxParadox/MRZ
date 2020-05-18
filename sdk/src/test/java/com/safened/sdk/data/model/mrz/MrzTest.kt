package com.safened.sdk.data.model.mrz

import com.safened.sdk.data.model.Mrz
import com.safened.sdk.charValue
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MrzTest {

    private lateinit var mrz: Mrz

    @BeforeEach
    fun setUp() {
        mrz = Mrz("")
    }

    @Test
    fun simpleValue1() {
        Assertions.assertEquals(0, '0'.charValue())
    }

    @Test
    fun simpleValue2() {
        Assertions.assertEquals(1, '1'.charValue())
    }

    @Test
    fun simpleValue3() {
        Assertions.assertEquals(9, '9'.charValue())
    }

    @Test
    fun letterValue1() {
        Assertions.assertEquals(10, 'A'.charValue())
    }

    @Test
    fun letterValue2() {
        Assertions.assertEquals(20, 'K'.charValue())
    }

    @Test
    fun letterValue3() {
        Assertions.assertEquals(35, 'Z'.charValue())
    }

    @Test
    fun spacerValue() {
        Assertions.assertEquals(0, '<'.charValue())
    }

    @Test
    fun throws() {
        Assertions.assertThrows(IllegalArgumentException::class.java) { '!'.charValue() }
    }


}
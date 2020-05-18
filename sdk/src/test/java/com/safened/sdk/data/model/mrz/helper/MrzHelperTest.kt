package com.safened.sdk.data.model.mrz.helper

import com.safened.sdk.data.model.MrzHelper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MrzHelperTest {

    private lateinit var helper: MrzHelper

    @BeforeEach
    fun setUp() {
        helper =
            MrzHelper(
                "!@#^&*()!@#%^&*()!@#^&*()!@#%^\n" +
                        "@#^&*()!@#%^&*()!@#^&*()!@#%^!\n" +
                        "#^&*()!@#%^&*()!@#^&*()!@#%^!@"
            )
    }

    /* ********** [ word based ] ********** */

    @Test
    fun topLine() {
        Assertions.assertEquals("!@#^&*()!@#%^&*()!@#^&*()!@#%^", helper.top)
    }

    @Test
    fun bottomLine() {
        Assertions.assertEquals("@#^&*()!@#%^&*()!@#^&*()!@#%^!", helper.bottom)
    }

    @Test
    fun optionalLine() {
        Assertions.assertEquals("#^&*()!@#%^&*()!@#^&*()!@#%^!@", helper.optional)
    }

    /* ********** [ number based ] ********** */

    @Test
    fun topNumberLine() {
        Assertions.assertEquals("!@#^&*()!@#%^&*()!@#^&*()!@#%^", helper.getLine(0))
    }

    @Test
    fun bottomNumberLine() {
        Assertions.assertEquals("@#^&*()!@#%^&*()!@#^&*()!@#%^!", helper.getLine(1))
    }

    @Test
    fun optionalNumberLine() {
        Assertions.assertEquals("#^&*()!@#%^&*()!@#^&*()!@#%^!@", helper.getLine(2))
    }

    @Test
    fun invalidNumberLine() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            helper.getLine(3)
        }
    }

}
package com.safened.sdk.data.enums

import com.safened.sdk.Logger
import com.safened.sdk.data.enums.config.EMPTY_CHECKSUMFIELDS
import com.safened.sdk.data.enums.config.EMPTY_PARSEMAP
import com.safened.sdk.data.enums.config.TYPE1_CHECKSUMFIELDS
import com.safened.sdk.data.enums.config.TYPE1_PARSEMAP
import com.safened.sdk.data.enums.config.TYPE3_CHECKSUMFIELDS
import com.safened.sdk.data.enums.config.TYPE3_PARSEMAP
import com.safened.sdk.data.model.MrzHelper
import com.safened.sdk.data.model.Range

/**
 * Gets (or guesses?) the type based on the [helper] inputs first character and line-length.
 */
fun getType(helper: MrzHelper): MrzType {
    val length = helper.getLineLength()
    helper.getFirstChar()?.let { idChar ->
        for (type in MrzType.values()) {

            if (type.letters.contains(idChar)) {
                if (type.length == length) {
                    return type
                }
            }
        }
    }

    return MrzType.TYPE_UNKNOWN
}

/**
 * Configuration class that can parse [MrzHelper]. The configuration is done using hardcoded ranges where the values
 * are located.
 *
 * NOTE: For cleanliness they are stored in external files (CompositeChecksumFields, and ParseMaps)
 *
 * @see MrzField
 *
 * Created on 20-6-2019 at 21:36.
 */
enum class MrzType(
    val length: Int,
    val letters: CharArray,
    private val parseMap: HashMap<MrzField, Range>,
    val checksumFields: List<MrzField>
) {
    TYPE_UNKNOWN(0, charArrayOf(),
        EMPTY_PARSEMAP,
        EMPTY_CHECKSUMFIELDS
    ),
    TYPE_TD1(30, charArrayOf('I', 'A', 'C'),
        TYPE1_PARSEMAP,
        TYPE1_CHECKSUMFIELDS
    ),
    TYPE_TD2(36, charArrayOf('I', 'A', 'C'),
        EMPTY_PARSEMAP,
        EMPTY_CHECKSUMFIELDS
    ),
    TYPE_TD3(44, charArrayOf('P'),
        TYPE3_PARSEMAP,
        TYPE3_CHECKSUMFIELDS
    ),
    TYPE_MRV_A(44, charArrayOf('V'),
        EMPTY_PARSEMAP,
        EMPTY_CHECKSUMFIELDS
    ),
    TYPE_MRV_B(36, charArrayOf('V'),
        EMPTY_PARSEMAP,
        EMPTY_CHECKSUMFIELDS
    );

    private val logger = Logger(enabled = false)

    /**
     * Parses the value from [field] using the raw input given by [helper]
     */
    fun parseField(helper: MrzHelper, field: MrzField): String {
        logger.enter("parseField(type: $this, field:$field)")

        if (parseMap.containsKey(field)) {
            val range = parseMap.getValue(field)
            logger.info("range: $range)")

            val row = helper.getLine(range.line)
            return row.substring(range.begin, range.end)
        }

        return ""
    }
}
package com.safened.sdk.data.enums.config

import com.safened.sdk.data.enums.MrzField

/**
 * Configuration per mrz-type of what fields have checksums. This is to be used in conjunction with
 * the [MrzField.FINALHASH].
 *
 * TODO: Add the other types.
 *
 * @see https://pypi.org/project/mrz/
 *
 * Created on 21-6-2019 at 21:36.
 */
val TYPE1_CHECKSUMFIELDS = listOf(
    MrzField.DOCUMENTNUMBER,
    MrzField.HASH1,
    MrzField.BIRTHDATE,
    MrzField.HASH2,
    MrzField.EXPIRYDATE,
    MrzField.HASH3,
    MrzField.OPTIONALDATA2
)

val TYPE3_CHECKSUMFIELDS = listOf(
    MrzField.DOCUMENTNUMBER,
    MrzField.HASH1,
    MrzField.BIRTHDATE,
    MrzField.HASH2,
    MrzField.EXPIRYDATE,
    MrzField.HASH3,
    MrzField.OPTIONALDATA,
    MrzField.HASH4
)

val EMPTY_CHECKSUMFIELDS = listOf<MrzField>()

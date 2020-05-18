package com.safened.sdk.data.enums.config

import com.safened.sdk.data.enums.MrzField
import com.safened.sdk.data.model.Range

/**
 * A collection of maps of how to parse MRZ strings
 *
 * TODO: Map other MRZ types
 *
 * @see [Range]
 * @see https://pypi.org/project/mrz/
 *
 * Created on 21-6-2019 at 21:36.
 */
val TYPE1_PARSEMAP = hashMapOf(
    Pair(MrzField.DOC, Range(0, 0, 2)),
    Pair(MrzField.COUNTRY, Range(0, 2, 5)),
    Pair(MrzField.DOCUMENTNUMBER, Range(0, 5, 14)),
    Pair(MrzField.HASH1, Range(0, 14, 15)), // single char
    Pair(MrzField.OPTIONALDATA1, Range(0, 15, 30)),

    Pair(MrzField.BIRTHDATE, Range(1, 0, 6)),
    Pair(MrzField.HASH2, Range(1, 6, 7)),
    Pair(MrzField.SEX, Range(1, 7, 8)),
    Pair(MrzField.EXPIRYDATE, Range(1, 8, 14)),
    Pair(MrzField.HASH3, Range(1, 14, 15)),
    Pair(MrzField.NATIONALITY, Range(1, 15, 18)),
    Pair(MrzField.OPTIONALDATA2, Range(1, 18, 29)),
    Pair(MrzField.FINALHASH, Range(1, 29, 30)),

    Pair(MrzField.SURNAMEGIVENNAME, Range(2, 0, 30))
)

val TYPE3_PARSEMAP = hashMapOf(
    Pair(MrzField.DOC, Range(0, 0, 2)),
    Pair(MrzField.COUNTRY, Range(0, 2, 5)),
    Pair(MrzField.SURNAMEGIVENNAME, Range(0, 5, 44)),

    Pair(MrzField.DOCUMENTNUMBER, Range(1, 0, 9)),
    Pair(MrzField.HASH1, Range(1, 9, 10)),
    Pair(MrzField.NATIONALITY, Range(1, 10, 13)),
    Pair(MrzField.BIRTHDATE, Range(1, 13, 19)),
    Pair(MrzField.HASH2, Range(1, 19, 20)),
    Pair(MrzField.SEX, Range(1, 20, 21)),
    Pair(MrzField.EXPIRYDATE, Range(1, 21, 27)),
    Pair(MrzField.HASH3, Range(1, 27, 28)),
    Pair(MrzField.OPTIONALDATA, Range(1, 28, 42)),
    Pair(MrzField.HASH4, Range(1, 42, 43)),
    Pair(MrzField.FINALHASH, Range(1, 43, 44))
)

val EMPTY_PARSEMAP = hashMapOf<MrzField, Range>()

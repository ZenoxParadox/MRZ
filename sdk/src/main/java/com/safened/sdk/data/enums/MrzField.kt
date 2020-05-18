package com.safened.sdk.data.enums

/**
 * All fields that can be used in Mrz data
 *
 * @see https://pypi.org/project/mrz/
 *
 * Created on 21-6-2019 at 21:36.
 */
enum class MrzField {

    DOC,
    COUNTRY,
    DOCUMENTNUMBER,
    HASH1,
    HASH2,
    HASH3,
    HASH4,
    FINALHASH,
    OPTIONALDATA, // NOTE: only when one optional data is present
    OPTIONALDATA1,
    OPTIONALDATA2,
    BIRTHDATE,
    SEX,
    EXPIRYDATE,
    NATIONALITY,
    SURNAMEGIVENNAME

}
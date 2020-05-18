package com.safened.sdk.data.model

/**
 * A notation for where (what [line], where the data should [begin] and where to [end]) to find
 * [com.safened.sdk.data.enums.MrzField] data fields
 */
data class Range(val line: Int, val begin: Int, val end: Int)
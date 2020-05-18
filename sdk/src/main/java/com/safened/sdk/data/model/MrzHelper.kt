package com.safened.sdk.data.model

/**
 * Helper class that helps give the lines of the raw mrz string.
 *
 * NOTE: This is to keep the null values as much out of the [Mrz] functionality; this class can throw exceptions.
 *
 * Created on 21-6-2019 at 20:53.
 */
class MrzHelper(mrz: String) {

    val top: String
    val bottom: String
    val optional: String?

    init {
        if (mrz.isNotBlank()) {
            mrz.split("\n").let { lines ->
                val top = lines[0]
                val bottom = lines[1]

                val optional: String?
                if (lines.size == 3) {
                    optional = lines[2]
                } else {
                    optional = null
                }

                if (top.isEmpty() || bottom.isEmpty()) {
                    throw IllegalArgumentException("invalid scan (top: $top & bottom: $bottom)")
                }

                if (top.length != bottom.length) {
                    throw IllegalArgumentException("line length should be equal (top: ${top.length} & bottom: ${bottom.length})")
                }

                optional?.let { line ->
                    if (line.length != top.length) {
                        throw IllegalArgumentException("line length should be equal (top: ${top.length} & bottom: ${bottom.length} & optional: ${line.length})")
                    }
                }

                this.top = top
                this.bottom = bottom
                this.optional = optional

            }
        } else {
            this.top = ""
            this.bottom = ""
            this.optional = null
        }
    }

    fun getLineLength(): Int {
        return top.length
    }

    fun getFirstChar(): Char? {
        if (top.isBlank()) {
            return null
        }

        return top[0]
    }

    fun getLine(number: Int): String {
        when (number) {
            0 -> return top
            1 -> return bottom
            2 -> return optional ?: ""
        }

        throw IllegalArgumentException("invalid line: $number")
    }

}
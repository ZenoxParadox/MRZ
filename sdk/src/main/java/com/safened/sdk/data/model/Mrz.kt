package com.safened.sdk.data.model

import android.support.annotation.VisibleForTesting
import com.safened.sdk.Logger
import com.safened.sdk.capitalizeWords
import com.safened.sdk.data.enums.MrzField
import com.safened.sdk.data.enums.MrzType
import com.safened.sdk.data.enums.getType
import com.safened.sdk.sanitize
import com.safened.sdk.weightSum

/**
 * http://www.highprogrammer.com/cgi-bin/uniqueid/mrzpr
 *
 * @see https://www.icao.int/publications/Documents/9303_p3_cons_en.pdf
 * @see https://pypi.org/project/mrz/
 * @see https://en.wikipedia.org/wiki/Machine-readable_passport
 */
class Mrz(text: String) {

    private val charsetRegex = Regex("^[A-Z0-9<]{30,44}$")

    private val helper: MrzHelper = MrzHelper(text)

    private val logger = Logger()

    val type: MrzType

    val documentNumber: String
    val documentCode: String

    val firstName: String
    val lastName: String

    val country: String
    val nationality: String
    val sex: String

    val dateOfBirth: String
    val expirationDate: String

    init {
        type = getType(helper)

        documentCode = type.parseField(helper, MrzField.DOC).sanitize()
        documentNumber = type.parseField(helper, MrzField.DOCUMENTNUMBER)

        // Firstname lastname
        val nameField = type.parseField(helper, MrzField.SURNAMEGIVENNAME)
        firstName = parseFirstName(nameField)
        lastName = parseLastName(nameField)

        country = type.parseField(helper, MrzField.COUNTRY)
        nationality = type.parseField(helper, MrzField.NATIONALITY)
        sex = type.parseField(helper, MrzField.SEX)

        dateOfBirth = type.parseField(helper, MrzField.BIRTHDATE)

        expirationDate = type.parseField(helper, MrzField.EXPIRYDATE)
    }

    /**
     * Parses the given name(s). It's not known what names are to be considered 'surname' simply based on delimiting
     * on spaces. Hence this method gives ALL the given names.
     *
     * FAMILIYNAME<<NAME<FOO<BAR<BAZ
     */
    private fun parseFirstName(raw: String): String {
        logger.enter("parseFirstName($raw)")
        if (raw.isBlank()) {
            return ""
        }

        var givenNames = raw.split("<<")[1]
        logger.info("givenNames: $givenNames")

        givenNames = givenNames.replace('<', ' ')

        return givenNames.toLowerCase().capitalizeWords()
    }

    /**
     * Parses the family name.
     */
    private fun parseLastName(raw: String): String {
        logger.enter("parseLastName($raw)")

        if (raw.isBlank()) {
            return ""
        }

        val rawLastName = raw.split("<<")[0].toLowerCase()
        logger.info("rawLastName: $rawLastName")

        return rawLastName.replace('<', ' ').capitalize()
    }

    /**
     * Validate the checksum digit on the [documentNumber], returning <code>true</code> when valid, false otherwise.
     */
    @VisibleForTesting
    fun hasValidChecksumDocumentNumber(): Boolean {
        return hasValidChecksum(documentNumber, MrzField.HASH1)
    }

    /**
     * Validate the checksum digit on the [dateOfBirth], returning <code>true</code> when valid, false otherwise.
     */
    @VisibleForTesting
    fun hasValidChecksumBirthday(): Boolean {
        return hasValidChecksum(dateOfBirth, MrzField.HASH2)
    }

    /**
     * Validate the checksum digit on the [expirationDate], returning <code>true</code> when valid, false otherwise.
     */
    @VisibleForTesting
    fun hasValidChecksumExpirationDate(): Boolean {
        return hasValidChecksum(expirationDate, MrzField.HASH3)
    }

    /**
     * Validate the checksum digit on the optional data, returning <code>true</code> when valid, false otherwise.
     * Only valid when the [type] is of [MrzType.TYPE_TD3] because it is the only type with a checksum for optional
     * data.
     *
     * Note: that all other types will return <code>false</code> because they do not have the [MrzField.OPTIONALDATA]
     * field.
     */
    @VisibleForTesting
    fun hasValidChecksumOptionalData(): Boolean {
        // Only TYPE_TD3 has checksum for optional data
        if (type == MrzType.TYPE_TD3) {
            val data = type.parseField(helper, MrzField.OPTIONALDATA)

            val valid = hasValidChecksum(data, MrzField.HASH4)
            if (valid) {
                return true
            }

            logger.info("checksum failed for [$data]")
            return false
        }

        // All others are considered invalid by definition, because they are absent
        return false
    }

    /**
     * Validate the checksum digit on all data fields that have a checksum, returning <code>true</code> when valid,
     * <code>false</code> otherwise.
     */
    @VisibleForTesting
    fun hasValidChecksumOverall(): Boolean {
        val builder = StringBuilder()
        for (field in type.checksumFields) {
            builder.append(type.parseField(helper, field))
        }

        val total = builder.toString()
        return hasValidChecksum(total, MrzField.FINALHASH)
    }

    /**
     * Check is the checksum in [data] matches the data that comes from [field]. Returns <code>true</code> when the
     * checksum is valid, <code>false</code> otherwise.
     */
    private fun hasValidChecksum(data: String, field: MrzField): Boolean {
        val sum = data.toCharArray().weightSum()

        val hash = type.parseField(helper, field)
        if (hash.isBlank()) {
            return false
        }
        if (validSum(sum, hash.toInt())) {
            return true
        }

        return false
    }

    /**
     * Checks if the [sum] matches [hash] after sum mod(10)
     */
    private fun validSum(sum: Int, hash: Int): Boolean {
        val rem = sum.rem(10)
        if (rem == hash) {
            return true
        }
        logger.info("checksum failed; is [$hash] should be [$rem]")

        return false
    }

    /**
     * Checks the validity of the data whether the correct charset is used and if each checksum is valid.
     */
    fun isValid(): Boolean {
        logger.enter("isValid()")

        val regexBirthDay = Regex("^[0-9]{6}$")
        if (!dateOfBirth.matches(regexBirthDay)) {
            logger.info("invalid dateOfBirth [$dateOfBirth]")
            return false
        }

        if (!hasValidChecksumBirthday()) {
            logger.info("failed checksum: birthday")
            return false
        }

        if (!hasValidChecksumDocumentNumber()) {
            logger.info("failed checksum: document number")
            return false
        }

        if (!hasValidChecksumExpirationDate()) {
            logger.info("failed checksum: expiration date")
            return false
        }

        // charset check
        if (!charsetRegex.matches(helper.top)) {
            logger.info("top line [${helper.top}] has invalid chars")
            return false
        }

        if (!charsetRegex.matches(helper.bottom)) {
            logger.info("bottom line [${helper.bottom}] has invalid chars")
            return false
        }

        helper.optional?.let { line ->
            if (!charsetRegex.matches(line)) {
                logger.info("optional line [$line] has invalid chars")
                return false
            }
        }

        if (type == MrzType.TYPE_TD3) {
            if (!hasValidChecksumOptionalData()) {
                logger.info("failed checksum: optional data")
                return false
            }
        }

        if (!hasValidChecksumOverall()) {
            logger.info("failed checksum: overall")
            return false
        }

        return true
    }

}
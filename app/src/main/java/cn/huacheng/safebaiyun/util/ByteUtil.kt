package cn.huacheng.safebaiyun.util

/**
 *
 *@description:
 *@author: guangzhou
 *@create: 2024-03-04
 */
object ByteUtil {

    private val hexChars = "0123456789abcdef".toCharArray()

    fun byteToHex(b: Byte): String {
        return "${hexChars[(b.toInt() shr 4) and 0x0F]}${hexChars[b.toInt() and 0x0F]}"
    }

    fun bytesToHex(bytes: ByteArray): String {
        val result = StringBuilder(bytes.size * 2)
        bytes.forEach {
            val octet = it.toInt()
            result.append(hexChars[(octet shr 4) and 0x0F])
            result.append(hexChars[octet and 0x0F])
        }
        return result.toString()
    }

    fun hexToBytes(hexString: String): ByteArray {
        if (hexString.isBlank()) return byteArrayOf()
        val result = ByteArray(hexString.length / 2)
        for (i in hexString.indices step 2) {
            val byteValue = hexString.substring(i, i + 2).toInt(16)
            result[i / 2] = byteValue.toByte()
        }
        return result
    }

}


package cn.huacheng.safebaiyun.util

import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

/**
 *
 *@description:
 *@author: guangzhou
 *@create: 2024-03-04
 */

object FDes {

    fun encryptData(data: ByteArray, key: ByteArray): ByteArray {
        return try {
            val cipher = Cipher.getInstance("DES/ECB/NoPadding")
            cipher.init(Cipher.ENCRYPT_MODE, generateSecretKeySpec(key))
            cipher.doFinal(data)
        } catch (exception: Exception) {
            byteArrayOf()
        }
    }

    private fun generateSecretKeySpec(key: ByteArray): SecretKeySpec {
        val keyBytes = ByteArray(8)
        System.arraycopy(key, 0, keyBytes, 0, minOf(key.size, 8))
        return SecretKeySpec(keyBytes, "DES")
    }
}

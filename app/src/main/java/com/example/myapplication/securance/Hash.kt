package com.example.myapplication.securance

import java.security.MessageDigest

fun String.hash(algorithm: String = "SHA-256"): String {
    return hashString(this, algorithm)
}

private fun hashString(input: String, algorithm: String): String {
    return MessageDigest
        .getInstance(algorithm)
        .digest(input.toByteArray())
        .fold("") { str, it -> str + "%02x".format(it) }
}
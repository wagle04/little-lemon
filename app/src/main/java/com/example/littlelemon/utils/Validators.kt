package com.example.littlelemon.utils

class Validators {
    companion object {
        fun validateString(text: String, requiredLength: Int = 1): Boolean {
            return text.length <= requiredLength
        }

        fun validateEmail(email: String): Boolean {
            val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
            return !email.matches(emailRegex.toRegex())
        }


    }
}
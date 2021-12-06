package com.example.simpledbfirebase2

data class Faq(
    val idFaq: String = "",
    val question: String = "",
    val trueAns: String = "",
    val falseAns: ArrayList<String> = ArrayList()
)

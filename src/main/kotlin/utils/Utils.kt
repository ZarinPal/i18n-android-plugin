package io.github.imanx.utils

import java.text.SimpleDateFormat
import java.util.*

class Utils {

    companion object {
        private val keywords = arrayOf(
            "abstract", "assert", "boolean",
            "break", "byte", "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else", "extends", "false",
            "final", "finally", "float", "for", "goto", "if", "implements",
            "import", "instanceof", "int", "interface", "long", "native",
            "new", "null", "package", "private", "protected", "public",
            "return", "short", "static", "strictfp", "super", "switch",
            "synchronized", "this", "throw", "throws", "transient", "true",
            "try", "void", "volatile", "while"
        )


        fun isJavaKeyword(keyword: String) = (Arrays.binarySearch(keywords, keyword) >= 0)
        val now get() = SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(Date())
    }
}
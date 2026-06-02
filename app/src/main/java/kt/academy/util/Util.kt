package kt.academy.util

fun loremIpsum(length: Int): String = "Lorem ipsum ".repeat(length / 10 + 1).take(length)
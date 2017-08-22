package io.mattsams.umbrella.data.api

object IconProvider {
    private const val URL_TEMPLATE = "http://nerdery-umbrella.s3.amazonaws.com/%s%s.png"

    fun url(icon: String, highlighted: Boolean): String =
            URL_TEMPLATE.format(icon, if (highlighted) "-selected" else "")
}
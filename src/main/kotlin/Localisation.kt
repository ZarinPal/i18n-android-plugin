package io.github.imanx

sealed class Localisation(private val region: Region = Region.fa_IR, val whichFile: String) {

    object Auth : Localisation(whichFile = "auth.json")
    object Common : Localisation(whichFile = "common.json")
    object BankAccount : Localisation(whichFile = "bank_account.json")
    object Product : Localisation(whichFile = "product.json")
    object Status : Localisation(whichFile = "status.json")
    object Ticket : Localisation(whichFile = "ticket.json")
    object Title : Localisation(whichFile = "title.json")
    object User : Localisation(whichFile = "user.json")


    val url: String
        get() {
            return "$END_POINT/${region.value}/$whichFile"
        }

    companion object {
        private const val END_POINT = "https://raw.githubusercontent.com/ZarinPal/Localisation/main"
        val arrayOfLocalisations = arrayOf(Auth, Common, BankAccount, Product, Status, Ticket, Title, User)
    }
}
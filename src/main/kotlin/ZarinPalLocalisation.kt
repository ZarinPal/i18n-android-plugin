package io.github.imanx

sealed class ZarinPalLocalisation(private val region: Region = Region.fa_IR, val whichFile: String) {

    object Auth : ZarinPalLocalisation(whichFile = "auth.json")
    object Common : ZarinPalLocalisation(whichFile = "common.json")
    object BankAccount : ZarinPalLocalisation(whichFile = "bank_account.json")
    object Product : ZarinPalLocalisation(whichFile = "product.json")
    object Status : ZarinPalLocalisation(whichFile = "status.json")
    object Ticket : ZarinPalLocalisation(whichFile = "ticket.json")
    object Title : ZarinPalLocalisation(whichFile = "title.json")
    object User : ZarinPalLocalisation(whichFile = "user.json")


    val url: String
        get() {
            return "$END_POINT/${region.value}/$whichFile"
        }

    companion object {
        private const val END_POINT = "https://raw.githubusercontent.com/ZarinPal/Localisation/main"
        val arrayOfLocalisations = arrayOf(Auth, Common, BankAccount, Product, Status, Ticket, Title, User)
    }
}
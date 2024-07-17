// src/test/scala/ReceiptPrinterTest.scala
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ReceiptPrinterSpec extends AnyWordSpec with Matchers {
  val coffeeConnectionCafe = new CafeDetails(
    "The Coffee Connection",
    "123 Lakeside Way",
    "16503600708",
    Map(
      "Cafe Latte" -> 4.75,
      "Flat White" -> 4.75,
      "Cappuccino" -> 3.85,
      "Single Espresso" -> 2.05,
      "Double Espresso" -> 3.75,
      "Americano" -> 3.75,
      "Cortado" -> 4.55,
      "Tea" -> 3.65,
      "Choc Mudcake" -> 6.40,
      "Choc Mousse" -> 8.20,
      "Affogato" -> 14.80,
      "Tiramisu" -> 11.40,
      "Blueberry Muffin" -> 4.05,
      "Chocolate Chip Muffin" -> 4.05,
      "Muffin Of The Day" -> 4.55
    )
  )

  "A ReceiptPrinter" should {
    "format a receipt" which {
      val printer = new ReceiptPrinter(
        coffeeConnectionCafe,
        Map("Cafe Latte" -> 1)
      )

      "contains the name of the cafe" in {
        printer.receipt should include ("The Coffee Connection")
      }

      "contains the address of the cafe" in {
        printer.receipt should include ("123 Lakeside Way")
      }

      "contains the phone number of the cafe" in {
        printer.receipt should include ("Tel: 16503600708")
      }

      printer.addItem("Tea")

      "one added item is shown" in {
        printer.receipt should include ("Tea")
      }

      "one added item is shown with quantity" in {
        printer.receipt should include ("Tea 1")
      }

      "two added items are shown with quantity" in {
        printer.addItem("Tea")
        printer.receipt should include ("Tea 2")
      }
    }
  }
}
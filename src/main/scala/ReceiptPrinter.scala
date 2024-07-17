// src/main/scala/ReceiptPrinter.scala
class CafeDetails (
                    val shopName: String,
                    val address: String,
                    val phone: String,
                    val prices: Map[String, Double]
                  )

class ReceiptPrinter(val cafe: CafeDetails, var order: Map[String, Int] = Map()) {

  def receipt: String = {
    val header = formatHeader()
    val items = formatItems()
    val grandTotal = formatGrandTotal()
    header + items + grandTotal
  }

  private def formatHeader(): String = {
    s"${cafe.shopName}\n${cafe.address}\nTel: ${cafe.phone}\n"
  }

  private def formatItems(): String = {
    order.map{ case (item, quantity) =>
      val totalPrice = cafe.prices(item) * quantity
      f"$item $quantity £$totalPrice%.2f"
    }.mkString("\n")
  }

  private def formatGrandTotal(): String = {
    val grandTotal = calculateGrandTotal()
    f"\n Grand Total: £$grandTotal%.2f"
  }

  def addItem(item: String) = {
    if (order.contains(item)) order = order + (item -> (order(item) + 1))
    else order = order + (item -> 1)
  }

  private def calculateGrandTotal(): Double = {
    order.map { case (item, quantity) =>
      cafe.prices(item) * quantity
    }.sum
  }
}
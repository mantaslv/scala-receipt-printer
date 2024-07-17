// src/main/scala/ReceiptPrinter.scala
class CafeDetails (
                    val shopName: String,
                    val address: String,
                    val phone: String,
                    val prices: Map[String, Double]
                  )

class ReceiptPrinter(val cafe: CafeDetails, var order: Map[String, Int] = Map()) {

  def receipt: String = {
    val header = s"${cafe.shopName}\n${cafe.address}\nTel: ${cafe.phone}\n"
    val items = order
      .map{ case (item, quantity) =>
        val totalPrice = cafe.prices(item) * quantity
        f"$item $quantity £$totalPrice%.2f"
      }
      .mkString("\n")
    val grandTotal = calculateGrandTotal()
    header + items + f"\n Grand Total: £$grandTotal%.2f"
  }

  def addItem(item: String) = {
    if (order.contains(item)) order = order + (item -> (order(item) + 1))
    else order = order + (item -> 1)
  }

  def calculateGrandTotal(): Double = {
    order.map { case (item, quantity) =>
      cafe.prices(item) * quantity
    }.sum
  }
}
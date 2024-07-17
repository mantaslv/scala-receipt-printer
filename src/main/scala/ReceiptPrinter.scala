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
    val total = formatTotal()
    header + items + total
  }

  private def formatHeader(): String = {
    s"${cafe.shopName}\n${cafe.address}\nTel: ${cafe.phone}\n"
  }

  private def formatItems(): String = {
    order.map{ case (item, quantity) =>
      val linePrice = cafe.prices(item) * quantity
      f"$item $quantity £$linePrice%.2f"
    }.mkString("\n")
  }

  private def formatTotal(): String = {
    val total = calculateTotal()
    f"\n Total: £$total%.2f"
  }

  def addItem(item: String) = {
    if (order.contains(item)) order = order + (item -> (order(item) + 1))
    else order = order + (item -> 1)
  }

  private def calculateTotal(): Double = {
    order.map { case (item, quantity) =>
      cafe.prices(item) * quantity
    }.sum
  }

  private def calculateTotalPrice() = {

  }
}

// src/main/scala/ReceiptPrinter.scala
class CafeDetails (
                    val shopName: String,
                    val address: String,
                    val phone: String,
                    val prices: Map[String, Double]
                  )

case class OrderItem(name: String, quantity: Int, linePrice: Double)

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
    mapLinePrices()
      .map(item => f"${item.name} ${item.quantity} £${item.linePrice}%.2f")
      .mkString("\n")
  }

  private def formatTotal(): String = {
    val total = calculateTotalPrice()
    f"\n Total: £$total%.2f"
  }

  def addItem(name: String) = {
    if (order.contains(name)) order = order + (name -> (order(name) + 1))
    else order = order + (name -> 1)
  }

  private def calculateTotalPrice(): Double = {
    mapLinePrices().map(_.linePrice).sum
  }

  private def mapLinePrices(): List[OrderItem] = {
    order.map{ case (name, quantity) =>
      val linePrice = cafe.prices(name) * quantity
      OrderItem(name, quantity, linePrice)
    }.toList
  }
}
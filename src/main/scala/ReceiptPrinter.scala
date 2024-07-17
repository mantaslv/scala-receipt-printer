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
      .map{case (item, quantity) => s"$item $quantity £${cafe.prices(item)}"}
      .mkString("\n")
    header + items
  }

  def addItem(item: String) = {
    if (order.contains(item)) order = order + (item -> (order(item) + 1))
    else order = order + (item -> 1)
  }
}
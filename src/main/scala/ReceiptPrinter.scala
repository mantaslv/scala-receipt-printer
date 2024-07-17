// src/main/scala/ReceiptPrinter.scala
class CafeDetails (
                    val shopName: String,
                    val address: String,
                    val phone: String,
                    val prices: Map[String, Double]
                  )

class ReceiptPrinter(val cafe: CafeDetails, var order: Map[String, Int] = Map()) {
  def receipt: String = {
    cafe.shopName + "\n" + cafe.address + "\n" + order.map(_._1).mkString("\n")
  }

  def addItem(item: String) = {
    order = order + (item -> 1)
  }
}
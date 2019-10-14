package example

case class Point(x: Int, y: Int) {
  
  def +(p: Point): Point = Point(x + p.x, y + p.y)
  
  def /(d: Int): Point = Point(x / d, y / d)
}

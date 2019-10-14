package example

/** Co-ordinates of a position on the canvas.
  *
  * @param x X-value (horizontal position, in pixels).
  * @param y Y-value (vertical position, in pixels).
  */
case class Point(x: Int, y: Int) {
  
  /** Returns the result when this position is translated by the input position (interpreted as a vector).
    *
    * The values of the corresponding co-ordinates in the two positions are added together.
    *
    * @param p Input position.
    */
  def +(p: Point): Point = Point(x + p.x, y + p.y)
  
  /** Returns the result when this position (interpreted as a vector) is scaled by the input number.
    *
    * Each co-ordinate is scaled by the input number.
    *
    * @param d Input number.
    */
  def *(d: Int): Point = Point(x * d, y * d)
  
  /** Returns the result when this position (interpreted as a vector) is scaled by the reciprocal of the input number.
    *
    * Each co-ordinate is scaled by the reciprocal of the input number.
    *
    * @param d Input number.
    */
  def /(d: Int): Point = Point(x / d, y / d)
}

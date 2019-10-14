package example

/** Parameters derived from the current program state that are required to update the canvas.
  *
  * @param count Number of positions generated.
  * @param p     Current position on the canvas.
  */
case class DrawParams(count: Int, p: Point)

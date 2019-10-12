package example

/** Parameters derived from the current program state that are required to update the canvas.
  *
  * @param clearFlag True if the canvas should be cleared before plotting the next point and false otherwise.
  * @param p         Current position on the canvas.
  */
case class DrawParams(clearFlag: Boolean, p: Point)

package example

/** Parameters derived from the current program state that are required to update the canvas contents.
  *
  * @param x         Current horizontal position (x-value) on the canvas.
  * @param ys        Current vertical position (y-value) on the canvas for each graph.
  *                  These have been scaled and offset relative to the corresponding x-axes.
  *                  They are arranged in the vertical order in which the graphs will be plotted on the page.
  * @param clearFlag True if the canvas should be cleared before plotting the next point and false otherwise.
  */
case class DrawParams(x: Int, ys: Seq[Int], clearFlag: Boolean)

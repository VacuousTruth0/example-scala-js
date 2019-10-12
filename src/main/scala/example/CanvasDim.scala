package example

import example.util.ConfigProvider.config

/** Contains functions and values relating to the canvas dimensions. */
object CanvasDim {
  
  /** Canvas width, in pixels. */
  val width: Int = config.getInt("canvas.width")
  
  /** Canvas height, in pixels. */
  val height: Int = config.getInt("canvas.height")
  
  /** Maximum x-value on the canvas. This is the horizontal position of the right side, in pixels. */
  val maxX: Int = width - 1
  
  /** Maximum y-value on the canvas. This is the vertical position of the bottom side, in pixels. */
  val maxY: Int = height - 1
  
  /** Default canvas width, in pixels. */
  private val defaultWidth: Int = config.getInt("canvas.defaultWidth")
  
  /** Default canvas height, in pixels. */
  private val defaultHeight: Int = config.getInt("canvas.defaultHeight")
  
  /** Maximum x-value on the default canvas. This is the horizontal position of the right side, in pixels. */
  val defaultMaxX: Int = defaultWidth - 1
  
  /** Maximum y-value on the default canvas. This is the vertical position of the bottom side, in pixels. */
  val defaultMaxY: Int = defaultHeight - 1
  
  /** Returns the equivalent position on the default canvas, given a position on the current canvas.
    *
    * @param p Position on the current canvas.
    */
  def scalePoint(p: Point): Point = {
    
    val newX: Int = (p.x / maxX.toDouble * defaultMaxX).toInt
    val newY: Int = (p.y / maxY.toDouble * defaultMaxY).toInt
    
    Point(newX, newY)
  }
}

package example

import example.util.ConfigProvider.config

/** Contains values relating to the canvas dimensions. */
object CanvasDim {
  
  /** Canvas width, in pixels. */
  val width: Int = config.getInt("canvas.width")
  
  /** Canvas height, in pixels. */
  val height: Int = config.getInt("canvas.height")
  
  /** Maximum x-value on the canvas. This is the horizontal position of the right side, in pixels. */
  val maxX: Int = width - 1
  
  /** Maximum y-value on the canvas. This is the vertical position of the bottom side, in pixels. */
  val maxY: Int = height - 1
}

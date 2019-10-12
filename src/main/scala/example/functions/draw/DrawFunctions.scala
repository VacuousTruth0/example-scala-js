package example.functions.draw

import example.CanvasDim._
import example.util.ConfigProvider.config
import example.{DrawParams, Point}
import org.scalajs.dom

/** Contains functions used to update the canvas. */
object DrawFunctions {
  
  /** RGB component values that define a colour.
    *
    * @param r Intensity of red, from 0 to 255.
    * @param g Intensity of green, from 0 to 255.
    * @param b Intensity of blue, from 0 to 255.
    */
  private case class Rgb(r: Int, g: Int, b: Int)
  
  /** Canvas background colour. */
  private val backgroundColour: String = config.getString("colour.background")
  
  /** Maximum value for RGB intensity. */
  private val maxRgb: Int = config.getInt("colour.maxRgb")
  
  /** Clears the canvas.
    *
    * @param ctx Rendering context for the canvas.
    */
  private def clear(ctx: dom.CanvasRenderingContext2D): Unit = {
    ctx.fillStyle = backgroundColour
    ctx.fillRect(0, 0, width, height)
  }
  
  /** Returns the RGB component values for the current position.
    *
    * @param p Current position.
    */
  private def getRgb(p: Point): Rgb = {
    
    val scaled: Point = scalePoint(p)
    val heightRatio: Double = (maxRgb + defaultMaxY).toDouble / (maxRgb + scaled.y)
    
    val r: Int = (heightRatio * scaled.x).toInt
    val g: Int = (heightRatio * (defaultMaxX - scaled.x)).toInt
    val b: Int = scaled.y
    
    Rgb(r, g, b)
  }
  
  /** Colours in the pixel at the current position on the canvas.
    *
    * @param ctx Rendering context for the canvas.
    * @param p   Current position.
    */
  private def plotPoint(ctx: dom.CanvasRenderingContext2D, p: Point): Unit = {
    val Rgb(r, g, b) = getRgb(p)
    
    ctx.fillStyle = s"rgb($r, $g, $b)"
    ctx.fillRect(p.x, p.y, 1, 1)
  }
  
  /** Updates the canvas, based on the current program state.
    *
    * Clears the canvas if enough points have been plotted.
    * Then plots a point at the current position.
    *
    * @param ctx    Rendering context for the canvas.
    * @param params Parameters derived from the current program state that are required to update the canvas.
    */
  def draw(ctx: dom.CanvasRenderingContext2D, params: DrawParams): Unit = {
    import params.{clearFlag, p}
    
    if (clearFlag) clear(ctx)
    plotPoint(ctx, p)
  }
}

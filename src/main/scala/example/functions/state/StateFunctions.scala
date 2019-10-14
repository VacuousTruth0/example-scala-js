package example.functions.state

import example.functions.state.StateHelperFunctions.nextPosition
import example.{DrawParams, Point}

/** Contains the program state, and functions used to access and update it. */
object StateFunctions {
  
  /** Program state. */
  private object State {
    
    /** Number of positions generated. */
    var count: Int = 0
    
    /** Current position on the canvas. */
    var p: Point = Point(0, 0)
  }
  
  import State.{count, p}
  
  /** Updates the program state.
    *
    * Moves to the next position and increments the number of positions generated.
    */
  def update(): Unit = {
    p = nextPosition(p)
    count += 1
  }
  
  /** Returns the parameters derived from the current program state that are required to update the canvas. */
  def drawParams: DrawParams = DrawParams(count, p)
}

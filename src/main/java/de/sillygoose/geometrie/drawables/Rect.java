package de.sillygoose.geometrie.drawables;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public class Rect extends Drawable {
  private final boolean isFilled;
  private final Point a;
  private final Point b;

  public Rect(final Color color, final boolean isFilled, final Point a, final Point b)  {
    super(color);
    this.isFilled = isFilled;
    this.a = a;
    this.b = b;
  }

  @Override
  public void draw(Graphics gr) {
    gr.setColor(color);

    var smallerX = Math.min(a.getX(), b.getX());
    var smallerY = Math.min(a.getY(), b.getY());
    var width = Math.abs(a.getX() - b.getX());
    var height = Math.abs(a.getY() - b.getY());

    if(isFilled) gr.fillRect(smallerX, smallerY, width, height);
    else  gr.drawRect(smallerX, smallerY, width, height);
  }

  @Override
  public boolean dependsOn(List<Point> selected) {
    return false;
  }
}

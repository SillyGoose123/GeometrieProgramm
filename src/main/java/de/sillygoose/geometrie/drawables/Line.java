package de.sillygoose.geometrie.drawables;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public class Line extends Drawable {
  private final Point a;
  private final Point b;

  public Line(Color color, Point a, Point b)  {
    super(color);
    this.a = a;
    this.b = b;
  }

  @Override
  public void draw(Graphics gr) {
    gr.setColor(color);
    gr.drawLine(a.getX(), a.getY(), b.getX(), b.getY());
  }

  @Override
  public boolean dependsOn(final List<Point> selected) {
    return selected.contains(a) || selected.contains(b);
  }
}

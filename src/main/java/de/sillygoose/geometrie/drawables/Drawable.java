package de.sillygoose.geometrie.drawables;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public abstract class Drawable {
  protected Color color;

  public Drawable(final Color color)
  {
    this.color = color;
  }

  public void setColor(final Color color, final Graphics gr) {
    this.color = color;
    draw(gr);
  }

  public abstract void draw(Graphics gr);

  public abstract boolean dependsOn(final List<Point> selected);
}

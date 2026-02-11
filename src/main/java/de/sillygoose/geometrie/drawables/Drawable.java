package de.sillygoose.geometrie.drawables;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public abstract class Drawable {
  protected Color color;

  public Drawable(Color color)
  {
    this.color = color;
  }

  public void setColor(final Color color) {
    this.color = color;
  }

  public abstract void draw(Graphics gr);

  public abstract boolean dependsOn(final List<Point> selected);
}

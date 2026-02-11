package de.sillygoose.geometrie.drawables;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public class Circle extends Drawable {
  private final Point center;
  private final Point radius;

  public Circle(Color color, Point center, Point radius) {
    super(color);
    this.center = center;
    this.radius = radius;
  }


  @Override
  public void draw(Graphics gr) {
    gr.setColor(color);
  }

  @Override
  public boolean dependsOn(List<Point> selected) {
    return selected.contains(center) || selected.contains(radius);
  }
}

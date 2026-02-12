package de.sillygoose.geometrie.drawables;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public class Circle extends Drawable {
  private final Point center;
  private final Point radius;

  public Circle(final Color color, final Point center, final Point radius) {
    super(color);
    this.center = center;
    this.radius = radius;
  }


  @Override
  public void draw(Graphics gr) {
    radius.setColor(Color.BLUE, gr);
    center.setColor(Color.RED, gr);

    gr.setColor(color);
    var rad = center.abstandZu(radius.getX(), radius.getY());
    gr.drawOval(center.getX() - rad, center.getY() - rad, rad * 2, rad * 2);
  }

  @Override
  public boolean dependsOn(List<Point> selected) {
    return selected.contains(center) || selected.contains(radius);
  }
}

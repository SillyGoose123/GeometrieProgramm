package de.sillygoose.geometrie.drawables;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.List;

public class Polygon extends Drawable {
  private final Point[] points;
  private final boolean isFilled;

  public Polygon(final Color color, final boolean isFilled, final List<Point> points) {
    super(color);
    this.points = points.toArray(new Point[] {});
    this.isFilled = isFilled;
  }

  @Override
  public void draw(Graphics gr) {
    gr.setColor(color);

    var poly = new java.awt.Polygon(
        Arrays.stream(points).mapToInt(Point::getX).toArray(),
        Arrays.stream(points).mapToInt(Point::getY).toArray(),
        points.length
    );

    if (isFilled) gr.fillPolygon(poly);
    else gr.drawPolygon(poly);
  }

  @Override
  public boolean dependsOn(List<Point> selected) {
    for (var point : points) {
      if (selected.contains(point)) {
        return true;
      }
    }
    return false;
  }
}

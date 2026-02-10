package de.sillygoose.geometrie.drawables;

import java.awt.Color;
import java.awt.Graphics;

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
    //TODO
    gr.setColor(color);
  }
}

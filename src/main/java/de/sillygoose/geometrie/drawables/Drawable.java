package de.sillygoose.geometrie.drawables;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Drawable {
  protected Color color;

  public Drawable(Color color)
  {
    this.color = color;
  }

  public abstract void draw(Graphics gr);
}

package de.sillygoose.geometrie.drawables;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Drawable {
  private int x;
  private int y;

  public Point(Color color, int x, int y) {
    super(color);
    this.x = x;
    this.y = y;
  }

  public int abstandZu(int mouseX, int mouseY) {
    return (int) Math.sqrt(Math.pow(mouseY - y, 2) + Math.pow(mouseX - x, 2));
  }

  @Override
  public void draw(Graphics gr) {
    gr.setColor(color);
    gr.fillRect(x - 2, y -2, 4, 4);
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }
}

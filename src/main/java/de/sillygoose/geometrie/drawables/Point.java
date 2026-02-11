package de.sillygoose.geometrie.drawables;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public class Point extends Drawable {
  private int x;
  private int y;
  private boolean isSelected = false;

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

    if (isSelected) {
      gr.drawRect(x - 2, y -2, 4, 4);
    } else  {
      gr.fillRect(x - 2, y -2, 4, 4);
    }
  }

  @Override
  public boolean dependsOn(final List<Point> selected) {
    return false;
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

  public boolean isSelected() {
    return isSelected;
  }

  public void setSelected(boolean selected) {
    isSelected = selected;
  }
}

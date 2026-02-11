package de.sillygoose.geometrie;

/*
 * Zeichenflaeche fuer geometrische Objekte;
 * verwaltet sowohl die Objektliste als
 * auch die Liste der selektierten Objekte
 * sowie die Reaktionen auf Mausklicks und
 * -bewegungen im Zeichenbereich.
 *
 * @version 1.1 vom 21.07.2012
 * @author Tom Schaller, Roland Mechling
 * @version 2.0 vom 23.3.2022
 * @author Tobias Nopper
 */

import de.sillygoose.geometrie.drawables.Drawable;
import de.sillygoose.geometrie.drawables.Line;
import de.sillygoose.geometrie.drawables.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Optional;

import java.util.ArrayList;
import javax.swing.KeyStroke;

public class DrawArea extends JPanel implements MouseListener, MouseMotionListener {
  private final ArrayList<Drawable> drawables = new ArrayList<>();
  private Point dragPoint;
  private boolean selectionMode = false; //TODO: FULLY IMPLEMENT SELECTION SWITCH (GUI DISPLAY??)

  public DrawArea() {
    super();
    addMouseListener(this);
    addMouseMotionListener(this);
    setupKeyBindings();
  }

  // vor Aufgabe 2
  @Override
  public void paint(Graphics gr) {
    super.paint(gr);

    for (var zo : drawables) {
      zo.draw(gr);
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {
    int x = e.getX();
    int y = e.getY();

    Optional<Point> point = filterPoints(pkt -> pkt.abstandZu(x, y) <= 5).findFirst();
    if (point.isEmpty()) {
      point = Optional.of(new Point(Color.BLACK, x, y));
      drawables.add(point.get());
    }

    dragPoint = point.get();
    dragPoint.setSelected(!dragPoint.isSelected());
    repaint();
  }

  public void mouseReleased(MouseEvent e) {
    dragPoint.setSelected(dragPoint.isSelected() && (e.getModifiersEx() & InputEvent.SHIFT_DOWN_MASK) > 0);
    dragPoint = null;
    repaint();
  }

  public void mouseDragged(MouseEvent e) {
    if (dragPoint == null) {
      return;
    }
    dragPoint.setX(e.getX());
    dragPoint.setY(e.getY());
    repaint();
  }

  // Aufgabe 9
  public void createLine(Color color) {
    var selected = getSelected();
    var size = selected.size();
    if (size < 2) {
      showWarning("Zu wenig Punkte ausgewählt 2 sind nötig!");
      return;
    }

    drawables.add(new Line(color, selected.get(size - 2), selected.getLast()));
    repaint();
  }

  // Aufgabe 10
  public void createCircle(Color color) {

  }

  // Aufgabe 11
  public void createPolygon(Color color) {

  }

  // Aufgabe 12
  public void createRect(Color color) {

  }

  // Aufgabe 13
  public void changeColorForSelectedPoints(Color newColor) {
    getSelected().forEach(point -> point.setColor(newColor));
    repaint();
  }

  // Aufgabe 14
  public void removeSelectedPoints() {
    var selected = getSelected();
    //check dependencies of points
    drawables.removeIf(drawable -> drawable.dependsOn(selected));
    drawables.removeAll(selected);
    repaint();
  }

  private Stream<Point> filterPoints(Predicate<Point> pointPredicate) {
    return drawables.stream()
        .filter((Drawable obj) -> obj instanceof Point)
        .map(obj -> (Point) obj)
        .filter(pointPredicate);
  }

  private List<Point> getSelected() {
    return filterPoints(Point::isSelected).toList();
  }

  private void showWarning(final String text) {
    JOptionPane.showMessageDialog(
        this,
        text,
        "Unfall",
        JOptionPane.WARNING_MESSAGE
    );
  }

  private void setupKeyBindings() {
    //Shift release
    getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released SHIFT"), "onShiftRelease");
    getActionMap().put("onShiftRelease", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        getSelected().forEach(point -> point.setSelected(false));
        repaint();
      }
    });

    //Remove points selected
    getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.SHIFT_DOWN_MASK), "onDelete");
    getActionMap().put("onDelete", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        selectionMode = !selectionMode;
      }
    });

  }

  // Bitte weitergehen, hier gibt es nichts zu sehen...
  @Override
  public void mouseEntered(MouseEvent e) {
  }

  @Override
  public void mouseExited(MouseEvent e) {
  }

  @Override
  public void mouseClicked(MouseEvent e) {
  }

  @Override
  public void mouseMoved(MouseEvent e) {
  }
}

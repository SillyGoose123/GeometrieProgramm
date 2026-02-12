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

import de.sillygoose.geometrie.drawables.Circle;
import de.sillygoose.geometrie.drawables.Drawable;
import de.sillygoose.geometrie.drawables.Line;
import de.sillygoose.geometrie.drawables.Point;
import de.sillygoose.geometrie.drawables.Polygon;
import de.sillygoose.geometrie.drawables.Rect;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Optional;

import java.util.ArrayList;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;

public class DrawArea extends JPanel implements MouseListener, MouseMotionListener {
  private final ArrayList<Drawable> drawables = new ArrayList<>();
  private final JToggleButton selectionToggle;
  private final JToggleButton fillToggle;
  private Point dragPoint;

  public DrawArea(final JToggleButton selectionToggle, final JToggleButton fillToggle) {
    super();
    this.selectionToggle = selectionToggle;
    this.fillToggle = fillToggle;
    addMouseListener(this);
    addMouseMotionListener(this);
    setupKeyBindings();
  }

  // vor Aufgabe 2
  @Override
  public void paint(Graphics gr) {
    super.paint(gr);

    if(!selectionToggle.isSelected()) {
      getSelected().forEach(point -> point.setSelected(false));
    }

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
    dragPoint.setSelected(!dragPoint.isSelected() && selectionToggle.isSelected());
    repaint();
  }

  public void mouseReleased(MouseEvent e) {
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

  public void createLine(Color color) {
    createDrawable(2, points -> new Line(color, points.getFirst(), points.get(1)));
  }

  public void createCircle(Color color) {
    createDrawable(2, points -> new Circle(color, points.getFirst(), points.get(1)));
  }

  public void createPolygon(Color color) {
    createDrawable(1, points -> new Polygon(color, fillToggle.isSelected(), points));
  }

  public void createRect(Color color) {
      createDrawable(2, points -> new Rect(color, fillToggle.isSelected(), points.getFirst(), points.get(1)));
  }

  public void changeColorForSelectedPoints(Color newColor) {
    getSelected().forEach(point -> point.setColor(newColor, getGraphics()));
  }

  public void removeSelectedPoints() {
    var selected = getSelected();
    //check dependencies of points
    drawables.removeIf(drawable -> drawable.dependsOn(selected));
    drawables.removeAll(selected);
    repaint();
  }

  public void clear() {
    drawables.clear();
    repaint();
  }

  /* UTILITIES */
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
    getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released SHIFT"), "onShift");
    getActionMap().put("onShift", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        selectionToggle();
      }
    });

    //Remove points selected
    getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "onDelete");
    getActionMap().put("onDelete", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        removeSelectedPoints();
      }
    });

    //fill
    getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released F"), "onFill");
    getActionMap().put("onFill", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        var updated = !fillToggle.isSelected();
        fillToggle.setSelected(updated);
        fillToggle.setText(updated ? "Füllen Beenden" : "Füllen");
        repaint();
      }
    });
  }

  private void createDrawable(final int length, Function<List<Point>, Drawable> constructor) {
    var selected = getSelected();
    var size = selected.size();
    if (size < length) {
      showWarning("Zu wenig Punkte ausgewählt " + length + " sind nötig!");
      return;
    }

    drawables.add(constructor.apply(selected.reversed()));
    selectionToggle();
    repaint();
  }

  private void selectionToggle() {
    var updated = !selectionToggle.isSelected();
    selectionToggle.setSelected(updated);
    selectionToggle.setText(updated ? "Auswählen Beenden" : "Auswählen");
    repaint();
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

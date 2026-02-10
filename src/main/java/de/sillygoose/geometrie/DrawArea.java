package de.sillygoose.geometrie;

/**
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
import de.sillygoose.geometrie.drawables.Point;
import javax.swing.JPanel;
import java.util.Optional;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DrawArea extends JPanel implements MouseListener, MouseMotionListener {
  private final ArrayList<Drawable> drawables = new ArrayList<>();
  private Point selected;
  private ArrayList<Point> selection;

  // Attribute
  // vor Aufgabe 2

  // Aufgabe 5a

  // Aufgabe 8a

  public DrawArea()
  {
    super();
    addMouseListener(this);
    addMouseMotionListener(this);

  }

  // vor Aufgabe 2
  public void paint(Graphics gr)
  {
    super.paint(gr);

    for(var zo : drawables) {
      zo.draw(gr);
    }
  }

  public void mousePressed(MouseEvent e)
  {
    int x = e.getX();
    int y = e.getY();

    Optional<Point> point = drawables.stream()
        .filter((Drawable obj) -> obj instanceof Point)
        .map(obj -> (Point) obj)
        .filter((Point pkt) -> pkt.abstandZu(x, y) <= 5)
        .findFirst();

    if(point.isPresent()) {
      selected = point.get();
      return;
    }

    var pkt = new Point(Color.BLACK, x, y);
    drawables.add(pkt);
    selected = pkt;
    this.repaint();
  }

  public void mouseReleased(MouseEvent e) {
    selected = null;
  }

  public void mouseDragged(MouseEvent e) {
    if(selected == null) return;
    selected.setX(e.getX());
    selected.setY(e.getY());
    this.repaint();
  }

  // Aufgabe 9
  public void erstelleNeueStrecke(Color farbe)
  {

  }

  // Aufgabe 10
  public void erstelleNeuenKreis(Color farbe)
  {

  }

  // Aufgabe 11
  public void erstelleNeuesPolygon(Color farbe)
  {

  }

  // Aufgabe 12
  public void erstelleNeuesRechteck(Color farbe)
  {

  }

  // Aufgabe 13
  public void aendereFarbeDerAusgewaehltenPunkte(Color neueFarbe)
  {

  }

  // Aufgabe 14
  public void entferneAusgewaehltePunkte()
  {

  }

  // Bitte weitergehen, hier gibt es nichts zu sehen...
  public void mouseEntered(MouseEvent e) { }
  public void mouseExited(MouseEvent e) { }
  public void mouseClicked(MouseEvent e) { }
  public void mouseMoved(MouseEvent e) { }
}

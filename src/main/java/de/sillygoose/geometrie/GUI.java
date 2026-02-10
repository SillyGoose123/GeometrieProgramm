package de.sillygoose.geometrie;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Grafische Benutzeroberflaeche
 * fuer geometrische Objekte;
 * verwaltet das Anwendungsfenster
 * und Mausklicks auf die Buttons.
 *
 * @version 1.1 vom 20.07.2012
 * @author Tom Schaller, Roland Mechling
 *
 * @version 1.2 vom 10.02.2026
 * @author Anian Barthel
 */

public class GUI extends JFrame {
  private final DrawArea drawArea = new DrawArea();
  private JButton jBCircle = new JButton();
  private JButton jBRect = new JButton();
  private JButton jBLine = new JButton();
  private JButton jBPolygone = new JButton();
  private JButton jBDelete = new JButton();
  private JButton jBColour = new JButton();

  private JSlider jSR = new JSlider(0,255,0);
  private JSlider jSG = new JSlider(0,255,0);
  private JSlider jSB = new JSlider(0,255,0);
  private JPanel  jPColor = new JPanel();

  public GUI(String title) {
    // Frame-Initialisierung
    super(title);

    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 669;
    int frameHeight = 467;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten

    drawArea.setBounds(0, 0, 537, 425);
    drawArea.setBackground(Color.WHITE);
    cp.add(drawArea);

    jBCircle.setBounds(552, 64, 99, 25);
    jBCircle.setText("Kreis");
    jBCircle.setMargin(new Insets(2, 2, 2, 2));
    jBCircle.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        jBCircle_ActionPerformed(evt);
      }
    });
    cp.add(jBCircle);

    jBRect.setBounds(552, 104, 99, 25);
    jBRect.setText("Rechteck");
    jBRect.setMargin(new Insets(2, 2, 2, 2));
    jBRect.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        jBRect_ActionPerformed(evt);
      }
    });
    cp.add(jBRect);

    jBLine.setBounds(552, 24, 99, 25);
    jBLine.setText("Strecke");
    jBLine.setMargin(new Insets(2, 2, 2, 2));
    jBLine.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        jBLine_ActionPerformed(evt);
      }
    });
    cp.add(jBLine);

    jBPolygone.setBounds(552, 144, 99, 25);
    jBPolygone.setText("Polygon");
    jBPolygone.setMargin(new Insets(2, 2, 2, 2));
    jBPolygone.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        jBPolygone_ActionPerformed(evt);
      }
    });
    cp.add(jBPolygone);

    jBDelete.setBounds(553, 390, 96, 34);
    jBDelete.setText("Objekt loeschen");
    jBDelete.setMargin(new Insets(2, 2, 2, 2));
    jBDelete.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        jBDelete_ActionPerformed(evt);
      }
    });
    jBDelete.setFont(new Font("Dialog", Font.BOLD, 10));
    cp.add(jBDelete);

    jBColour.setBounds(552, 350, 97, 33);
    jBColour.setText("Punkte faerben");
    jBColour.setMargin(new Insets(2, 2, 2, 2));
    jBColour.setFont(new Font("Dialog", Font.BOLD, 10));
    jBColour.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        jBColour_ActionPerformed(evt);
      }
    });
    cp.add(jBColour);

    jSR.setBounds(552, 250, 100, 33);
    jSG.setBounds(552, 280, 100, 33);
    jSB.setBounds(552, 310, 100, 33);

    jSR.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent event) {
        jPColor.setBackground(new Color(jSR.getValue(),jSG.getValue(),jSB.getValue()));
      }
    });
    jSG.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent event) {
        jPColor.setBackground(new Color(jSR.getValue(),jSG.getValue(),jSB.getValue()));
      }
    });
    jSB.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent event) {
        jPColor.setBackground(new Color(jSR.getValue(),jSG.getValue(),jSB.getValue()));
      }
    });

    jPColor.setBounds(552, 200, 100, 33);
    jPColor.setBackground(new Color(0,0,0));
    cp.add(jSR);
    cp.add(jSG);
    cp.add(jSB);
    cp.add(jPColor);

    setTitle("Geometrische Objekte");

    setVisible(true);
  }

  public void jBCircle_ActionPerformed(ActionEvent evt)
  {
    drawArea.erstelleNeuenKreis(jPColor.getBackground());
  }

  public void jBRect_ActionPerformed(ActionEvent evt)
  {
    drawArea.erstelleNeuesRechteck(jPColor.getBackground());
  }

  public void jBLine_ActionPerformed(ActionEvent evt)
  {
    drawArea.erstelleNeueStrecke(jPColor.getBackground());
  }

  public void jBPolygone_ActionPerformed(ActionEvent evt)
  {
    drawArea.erstelleNeuesPolygon(jPColor.getBackground());
  }

  public void jBDelete_ActionPerformed(ActionEvent evt)
  {
    drawArea.entferneAusgewaehltePunkte();
  }

  public void jBColour_ActionPerformed(ActionEvent evt)
  {
    drawArea.aendereFarbeDerAusgewaehltenPunkte(jPColor.getBackground());
  }
}
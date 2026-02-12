package de.sillygoose.geometrie;

import com.formdev.flatlaf.icons.FlatOptionPaneWarningIcon;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Grafische Benutzeroberflaeche
 * fuer geometrische Objekte;
 * verwaltet das Anwendungsfenster
 * und Mausklicks auf die Buttons.
 *
 * @author Tom Schaller, Roland Mechling
 * @author Anian Barthel
 * @version 1.2 vom 10.02.2026
 */

public class GUI extends JFrame {
  private final JToggleButton selectionToggle = new JToggleButton("Auswählen");
  private final JToggleButton fillToggle = new JToggleButton("Füllen");
  private final DrawArea drawArea = new DrawArea(selectionToggle, fillToggle);

  public GUI(String title) {
    // Frame-Initialisierung
    super(title);

    // size window
    int frameWidth = 669;
    int frameHeight = 467;
    setSize(frameWidth, frameHeight);

    // position window
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);

    // customize window toolbar
    setResizable(false);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Geometrische Objekte");

    Container main = getContentPane();
    main.setLayout(new BorderLayout());

    var functions = createFunctionGrid();
    main.add(functions, BorderLayout.EAST);

    // create draw area
    drawArea.setBackground(Color.WHITE);
    main.add(drawArea, BorderLayout.CENTER);

    setVisible(true);
  }

  private JPanel createFunctionGrid() {
    var container = new JPanel();
    container.setLayout(new GridLayout(13, 1, 0, 6));
    container.setBorder(new EmptyBorder(0, 6, 0, 6));

    var colorPreview = new JPanel();
    colorPreview.setBackground(new Color(0, 0, 0));

    //selection mode
    selectionToggle.addActionListener(_ -> {
      selectionToggle.setText(selectionToggle.isSelected() ? "Auswählen Beenden" : "Auswählen");
      if(!selectionToggle.isSelected()) drawArea.repaint();
    });
    container.add(selectionToggle);

    createButton(container, "Kreis", _ -> drawArea.createCircle(colorPreview.getBackground()));
    createButton(container, "Rechteck", _ -> drawArea.createRect(colorPreview.getBackground()));
    createButton(container, "Strecke", _ -> drawArea.createLine(colorPreview.getBackground()));
    createButton(container, "Polygon", _ -> drawArea.createPolygon(colorPreview.getBackground()));

    container.add(colorPreview);
    createColorSliders(container, colorPreview);

    fillToggle.addActionListener(_ -> fillToggle.setText(fillToggle.isSelected() ? "Füllen Beenden" : "Füllen"));
    container.add(fillToggle);

    createButton(container, "Punkte Löschen", _ -> drawArea.removeSelectedPoints());
    createButton(container, "Punkte faerben", _ -> drawArea.changeColorForSelectedPoints(colorPreview.getBackground()));
    createButton(container, "Clear", _ -> askClear());

    return container;
  }

  private void askClear() {
    var result = JOptionPane.showOptionDialog(
        getContentPane(),
        "Sicher das sie alles löschen wollen!",
        "Clear?",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE,
        new FlatOptionPaneWarningIcon(),
        null,
        null

    );
    if(result == JOptionPane.YES_OPTION) {
      drawArea.clear();
      drawArea.repaint();
    }
  }


  private void createButton(final Container container, final String text, final ActionListener onClick) {
    var button = new JButton();
    button.setText(text);
    button.addActionListener(onClick);
    button.setAlignmentX(Component.LEFT_ALIGNMENT);
    container.add(button);
  }

  private void createColorSliders(final Container container, final JPanel colorPreview) {
    JSlider[] sliders = {
        new JSlider(0, 255, 0),
        new JSlider(0, 255, 0),
        new JSlider(0, 255, 0)
    };

    for (JSlider slider : sliders) {
      slider.addChangeListener(_ -> colorPreview.setBackground(
          new Color(sliders[0].getValue(), sliders[1].getValue(), sliders[2].getValue())
      ));
      container.add(slider);
    }
  }
}
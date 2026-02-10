package drawable;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.sillygoose.geometrie.drawables.Point;
import java.util.Random;
import org.junit.jupiter.api.Test;


/**
 * @version 2.0 vom 23.3.2022
 * @author Tobias Nopper
 * @version 2.2 vom 10.2.2026
 * @author Anian Barthel
 */

public class PointTest {
  @Test
  public void abstandZuTest()
  {
    Random r = new Random();
    for (int i = 0; i < 100; i++)
    {
      int ax = r.nextInt(2000)-1000;
      int ay = r.nextInt(2000)-1000;
      int bx = r.nextInt(2000)-1000;
      int by = r.nextInt(2000)-1000;
      var point = new Point(null, ax, ay);
      assertEquals(Math.sqrt(Math.pow(ax-bx, 2) + Math.pow(ay-by, 2)), point.abstandZu(bx, by), 20);
    }
  }
}

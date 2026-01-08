package panter.gmbh.indep;

import java.math.MathContext;
import java.math.RoundingMode;

/**
 * bibliothek mit statischen klassen und objekten fuer die allgemeine nutzung
 * @author martin
 *
 */
public class bibStatics
{
	public static MathContext  MATHCONTEXT_BIG_ROUNDUP = new MathContext(16, RoundingMode.HALF_UP);
	
}

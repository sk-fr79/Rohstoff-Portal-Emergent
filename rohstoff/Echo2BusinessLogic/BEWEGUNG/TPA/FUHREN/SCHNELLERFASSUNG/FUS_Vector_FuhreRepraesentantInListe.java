package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG;

import java.math.BigDecimal;
import java.util.Vector;

import panter.gmbh.indep.exceptions.myException;

public class FUS_Vector_FuhreRepraesentantInListe extends Vector<FUS_FuhreRepraesentantInListe>
{

	public FUS_Vector_FuhreRepraesentantInListe()
	{
		super();
	}

	
	public BigDecimal get_bdSummeAllerPositionen() throws myException
	{
		BigDecimal bdRueck = new BigDecimal(0);
		
		for (FUS_FuhreRepraesentantInListe oFuhre: this)
		{
			bdRueck=bdRueck.add(	(oFuhre.get_bdWiegeMenge()==null||oFuhre.get_bdWiegeMenge().get_bdWert()==null)
										?
									 BigDecimal.ZERO:oFuhre.get_bdWiegeMenge().get_bdWert());
		}
		
		return bdRueck;
	}
	
}

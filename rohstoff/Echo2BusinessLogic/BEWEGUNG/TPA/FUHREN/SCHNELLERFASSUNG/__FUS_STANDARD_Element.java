package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.indep.exceptions.myException;

public interface __FUS_STANDARD_Element
{
	/*
	 * hebt ein feld heraus, das nicht gefuellt war
	 */
	public abstract void mark_MUST_BE_Filled(boolean bMarked) throws myException;
	
	
	/*
	 * gibt eine Meldung heraus, welches feld nicht gefuellt ist
	 */
	public abstract MyE2_String get_InfoMessage() throws myException;
	
	
	
	/*
	 * 
	 */
	public abstract boolean get_bIsCorrectFilled(boolean mark_when_false) throws myException;
	
	
	
	/*
	 * 2 vectoren, einer mit den komponenten, die gefuellt sein muessen, bevor eine aktion auf der besagten komponente ausgefuehrt wird,
	 *             der zweite mit 
	 */
	public abstract Vector<__FUS_STANDARD_Element>  get_KomponentenDieGefuelltSeinMuessen() throws myException;
	public abstract Vector<__FUS_STANDARD_Element>  get_KomponentenDieGeleertWerden() throws myException;

	
	public abstract void clean__Field() throws myException;
	
	/**
	 * 
	 * @return s true wenn links, false wenn rechts, null wenn keine unterscheidung ek-vk
	 * @throws myException
	 */
	public abstract Boolean   get_IS_EK() throws myException;
	
	
	
	/**
	 * allgemeine aktion wenn das maskenfeld gefuellt wurde
	 */
	public abstract void      do_afterFieldWasFilled(String cFillValue) throws myException;
	
}

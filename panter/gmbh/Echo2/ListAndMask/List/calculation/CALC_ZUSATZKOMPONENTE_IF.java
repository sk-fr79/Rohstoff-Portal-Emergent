package panter.gmbh.Echo2.ListAndMask.List.calculation;

import nextapp.echo2.app.Component;
import panter.gmbh.indep.exceptions.myException;

/**
 * interface kann einem CALC_BUTTON mitgegeben werden, dann kann in der titelzeile das ergebnis einer summation angezeigt werden
 * @author martin
 *
 */
public interface CALC_ZUSATZKOMPONENTE_IF {
	
	public abstract Component _GET_ZUSATZ_Komponente();
	public abstract void _FILL_AND_SHOW_ZUSATZ_Komponente(String cErgebnisFromCalcRule, String cTooltips) throws myException;
	public abstract void _RESET_ZUSATZ_Komponente();
	
}

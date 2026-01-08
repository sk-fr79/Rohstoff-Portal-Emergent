/**
 * 
 */
package panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS.SEARCHDEF;

import panter.gmbh.Echo2.components.E2_HelpPopUp;

/**
 * @author martin
 *
 */
public class SD_MASK_compInfoSQL extends E2_HelpPopUp {

	/**
	 * 
	 */
	public SD_MASK_compInfoSQL() {
		super();
		
		this._setWidth(400);
		this._addTextAbschnitt(2,0,100,300, "SQL-Query"
									,"Der Term muss eine komplette Abfrage enthalten, die eine"
									,"ID-Menge der führenden Tabelle der jeweiligen Liste enthält."
									,"Dder Ausdruck #WERT# muss als Platzhalter für"
									,"Benutzereingaben enthalten sein !");
		this._addTextAbschnitt(2,0,100,300,"Bsp1:","id_adresse in (select id_adresse from jt_adresse where zusatzort like '%#WERT#%')");
		this._addTextAbschnitt(2,0,100,300,"Bsp2: ","jt_adresse.name1 like '%#WERT#%'");
		
	}

}

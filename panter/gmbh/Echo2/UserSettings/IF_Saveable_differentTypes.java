package panter.gmbh.Echo2.UserSettings;

import panter.gmbh.indep.exceptions.myException;

public interface IF_Saveable_differentTypes extends IF_Saveable {
	
	/*
	 * damit dynamisch sammlungen funktionieren werden hier addOns vor die werte geschrieben
	 * so kann hier z.b. eine sammlung von checkboxeb mit ids erfasst werden, ohne die reihenfolge zu kennen
	 * abgespeichert wird dann nicht mehr: ..|Y|..
	 * sondern                             ..|1051@Y|..
	 */
	public String 		get_component_key() throws myException;
	
}

package panter.gmbh.Echo2.AgentsAndValidators;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;


/*
 * klassen, die es ermoeglichen, eine maske einzustellen bevor ein
 * bestimmter arbeitsgang erfolgt und nachdem ein bestimmter arbeitsgang zuende ist.
 * Der vector enthaelt MyString - Elemente zur rueckgabe, die die fehlermeldungen darstellen.
 * Deshalb ein leerer Vector das zeichen: alles ok
 */
public abstract class XX_MAP_ValidBeforeSAVE
{
	public MyE2_MessageVector doValidation( E2_ComponentMAP oMap, SQLMaskInputMAP oInputMap, String cSTATUS_MASKE) throws myException
	{
		
		if (cSTATUS_MASKE == null)
			throw new myException("E2_MAP_ValidBeforeSAVE:doValidation:Status NULL is not allowed !!");
		
		
		if (!(	cSTATUS_MASKE.equals(E2_ComponentMAP.STATUS_EDIT) || 
				cSTATUS_MASKE.equals(E2_ComponentMAP.STATUS_VIEW) ||
				cSTATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) ||
				cSTATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_COPY)
			))
			throw new myException("E2_MAP_ValidBeforeSAVE:doValidation:Status is not allowed !!");

		return this._doValidation(oMap,oInputMap,cSTATUS_MASKE);
	}
	
	public abstract MyE2_MessageVector _doValidation(E2_ComponentMAP oMap, SQLMaskInputMAP oInputMap, String cSTATUS_MASKE) throws myException;
	
	
}

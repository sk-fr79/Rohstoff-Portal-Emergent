package rohstoff.utils;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.dataTools.MyDataRecordHashMap;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class MyAdressLIGHT extends MyDataRecordHashMap
{

	public MyAdressLIGHT(String cID_ADRESSE_Unformated) throws myException
	{
		super("SELECT JT_ADRESSE.*, JT_FIRMENINFO.* FROM "+bibE2.cTO()+".JT_ADRESSE,"+bibE2.cTO()+".JT_FIRMENINFO WHERE " +
							" JT_FIRMENINFO.ID_ADRESSE=JT_ADRESSE.ID_ADRESSE AND JT_ADRESSE.ID_ADRESSE="+cID_ADRESSE_Unformated);

	}

	
	
	public String get_LAND_KLARTEXT() throws myException
	{
		String cRueck = bibDB.EinzelAbfrage("SELECT LAENDERCODE FROM "+bibE2.cTO()+".JD_LAND WHERE ID_LAND="+this.get_UnFormatedValue("ID_LAND"),"","","");
		return cRueck;
	}
	
	public String get_SPRACHE_KLARTEXT() throws myException
	{
		String cRueck = bibDB.EinzelAbfrage("SELECT BEZEICHNUNG FROM "+bibE2.cTO()+".JD_SPRACHE WHERE ID_SPRACHE="+this.get_UnFormatedValue("ID_SPRACHE"),"","","");
		return cRueck;
	}

	public String get_WAEHRUNG_KLARTEXT() throws myException
	{
		String cRueck = bibDB.EinzelAbfrage("SELECT KURZBEZEICHNUNG FROM "+bibE2.cTO()+".JD_WAEHRUNG WHERE ID_WAEHRUNG="+this.get_UnFormatedValue("ID_WAEHRUNG"),"","","");
		return cRueck;
	}
	
	public String get_ZAHLUNGSBEDINGUNGEN_KLARTEXT() throws myException
	{
		String cRueck = bibDB.EinzelAbfrage("SELECT BEZEICHNUNG FROM "+bibE2.cTO()+".JT_ZAHLUNGSBEDINGUNGEN WHERE ID_ZAHLUNGSBEDINGUNGEN="+this.get_UnFormatedValue("ID_ZAHLUNGSBEDINGUNGEN"),"","","");
		return cRueck;
	}
	
	public String get_LIEFERBEDINGUNGEN_KLARTEXT() throws myException
	{
		String cRueck = bibDB.EinzelAbfrage("SELECT BEZEICHNUNG FROM "+bibE2.cTO()+".JT_LIEFERBEDINGUNGEN WHERE ID_LIEFERBEDINGUNGEN="+this.get_UnFormatedValue("ID_LIEFERBEDINGUNGEN"),"","","");
		return cRueck;
	}


	public String[][] get_MWST_SAETZE(boolean bMitNullWert) throws myException
	{
		My_MWSTSaetze oSaetze = new My_MWSTSaetze(this.get_UnFormatedValue("ID_ADRESSE"),null);
		
		String[][] cRueck = oSaetze.get_MWST_DropDownArray_AdressMWST(bMitNullWert);
		
		return cRueck;
	}

	
	public boolean get_b_OHNE_SteuerBeiWarenEingang()
	{
		boolean bRueck = true;
		
		if (this.get("OHNE_STEUER_WARENEINGANG")[0]==null || this.get("OHNE_STEUER_WARENEINGANG")[0].equals("N"))
		{
			bRueck = false;
		}
		return bRueck;
	}
	
	public boolean get_b_OHNE_SteuerBeiWarenAusgang()
	{
		boolean bRueck = true;
		
		if (this.get("OHNE_STEUER_WARENAUSGANG")[0]==null || this.get("OHNE_STEUER_WARENAUSGANG")[0].equals("N"))
		{
			bRueck = false;
		}
		return bRueck;
	}

	
}

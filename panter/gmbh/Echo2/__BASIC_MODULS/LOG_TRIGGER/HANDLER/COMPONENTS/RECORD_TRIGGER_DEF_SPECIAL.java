package panter.gmbh.Echo2.__BASIC_MODULS.LOG_TRIGGER.HANDLER.COMPONENTS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_TRIGGER_DEF;
import panter.gmbh.indep.exceptions.myException;

public class RECORD_TRIGGER_DEF_SPECIAL extends RECORD_TRIGGER_DEF
{

	public RECORD_TRIGGER_DEF_SPECIAL(RECORD_TRIGGER_DEF recordOrig)
	{
		super(recordOrig);
	}

	public MyE2_String  get_InfoText_LEER() throws myException
	{
		return new MyE2_String("Änderungsprotokoll des Feldes: <",true,this.get_BESCHREIBUNG_cF_NN("<"+this.get_SPALTE_cUF_NN("-")+"@"+this.get_TABELLE_cUF_NN("-")+">"),false,"> ist leer!",true);
	}

	public MyE2_String  get_InfoText_Titel() throws myException
	{
		return new MyE2_String("Änderungsprotokoll des Feldes: <",true,this.get_BESCHREIBUNG_cF_NN("<"+this.get_SPALTE_cUF_NN("-")+"@"+this.get_TABELLE_cUF_NN("-")+">"),false,">",true);
	}

	public MyE2_String  get_InfoText_ToolTip() throws myException
	{
		return new MyE2_String("Änderungsprotokoll des Feldes: <",true,this.get_BESCHREIBUNG_cF_NN("<"+this.get_SPALTE_cUF_NN("-")+"@"+this.get_TABELLE_cUF_NN("-")+">"),false,"> anzeigen ...",true);
	}

}

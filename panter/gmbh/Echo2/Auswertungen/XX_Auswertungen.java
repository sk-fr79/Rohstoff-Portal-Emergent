package panter.gmbh.Echo2.Auswertungen;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public abstract class XX_Auswertungen 
{
	private MyE2_String  		cAuswertungsNamen 			= null;

	private MyE2_String  		cAuswertungsErlaeuterung 	= null;
	private String  			cNameTempTable              = null;    //tabelle MUSS folgende Felder beinhalten: BENUTZERKUERZEL, REPORTNUMMER
	private String    			cReportName   				= null;
	
	private String      		cALLOW_FLAG  				= null;

	private String              cAuswerteGruppe             = null;
	
	private AW_CheckBox         oCB_Anzeigen                 = null;

	private String 				id_report = null;



	public XX_Auswertungen(	MyE2_String cAuswertungsNamen,
							MyE2_String Auswertungserlaeuterung, 
							String 		ALLOW_FLAG, 
							String 		cNameTempTable,	
							String 		cReportName,
//							String      cGruppe,
							String 		p_id_report) 
	{
		super();
		this.cAuswertungsNamen 			= cAuswertungsNamen;
		this.cAuswertungsErlaeuterung 	= Auswertungserlaeuterung;
		this.cALLOW_FLAG   				= ALLOW_FLAG;
		this.cNameTempTable 			= cNameTempTable;
		this.cReportName 				= cReportName;
//		this.cAuswerteGruppe 			= cGruppe;
		this.id_report 					= p_id_report;
		this.oCB_Anzeigen 				= new AW_CheckBox(this.id_report,true,false);
	}

	public String get_id_report() {
		return id_report;
	}
	
	
	public void DELETE_OLD_TEMPTABLE_VALUES()  throws myException
	{
		if (!bibDB.ExecSQL("DELETE FROM "+bibE2.cTO()+"."+this.cNameTempTable+
					" WHERE UPPER(BENUTZERKUERZEL)=UPPER("+bibALL.MakeSql(bibALL.get_RECORD_USER().get_KUERZEL_cUF())+")", true))
		{
			throw new myException("Error deleting old Values in Table "+this.cNameTempTable);
		}
	}
	

	public String get_NextReportAktionNumber() throws myException
	{
		String ValRueck= bibDB.EinzelAbfrage("SELECT SEQ_REPORTNUMMER.NEXTVAL FROM DUAL");
		
		if (bibALL.isLong(ValRueck))
		{
			return ValRueck;
		}
		else
		{
			throw new myException("Error finding new Value in SEQ_REPORTNUMMER !!");
		}
		
	}

	
	
	public MyE2_String get_cAuswertungsNamen() 
	{
		return cAuswertungsNamen;
	}

	public String get_cALLOW_FLAG() 
	{
		return cALLOW_FLAG;
	}


	public MyE2_String get_cAuswertungsErlaeuterung() 
	{
		return cAuswertungsErlaeuterung;
	}



	public String get_cNameTempTable() 
	{
		return cNameTempTable;
	}



	public String get_cReportName() 
	{
		return cReportName;
	}


	/**
	 * button fuer die listen generieren ...
	 * @return
	 * @throws myException
	 */
	public abstract  MyE2_Button get_ListButton() throws myException;
//	{
//		MyE2_Button oButtonList = new MyE2_Button(this.get_cAuswertungsNamen());
//		oButtonList.setToolTipText(this.get_ToolTip());
//		
//		oButtonList.add_GlobalAUTHValidator_AUTO(this.get_cALLOW_FLAG());
//		oButtonList.setLineWrap(true);
//
//		return oButtonList;
//	}
	
	
	
	/*
	 * Button, der nach auswahl der gewuenschten auswertung angezeigt wird
	 * In diesem Button muss die gesamte logig stecken, d.h. auch evtl. angezeigte popup-fenster fuer
	 * selektionen usw.
	 */
	public abstract 	MyE2_Button 				get_StartButton() 				throws myException;
	
	//hier koennen noch validierer fuer den aufruf-button in der liste links hinterlegt werden
	public abstract    Vector<XX_ActionValidator>   get_GlobalValidators4ListButton()     throws myException;
	

	//2011-08-04: weitere methode um einen breich unterhalb des startbuttons mit 
	//            kontrollelementen zu fuellen, die nach ausfuehrung der auswertung gestartet gefuellt werden kann
	public abstract    Component                    get_Zusatzkomponente()           throws myException;
	
	
	
	//2013-10-10: Auswertungen bekommen noch zusaetzlich einen ToolTip
	public abstract 	String		   				get_ToolTip() throws myException;
	
	public String get_cAuswerteGruppe()
	{
		return cAuswerteGruppe;
	}


	public void set_cAuswerteGruppe(String cAuswerteGruppe)
	{
		this.cAuswerteGruppe = cAuswerteGruppe;
	}


	public AW_CheckBox get_oCB_Anzeigen()
	{
		return oCB_Anzeigen;
	}	
}

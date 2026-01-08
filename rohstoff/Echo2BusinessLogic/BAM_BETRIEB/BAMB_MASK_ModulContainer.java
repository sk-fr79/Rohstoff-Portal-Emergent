package rohstoff.Echo2BusinessLogic.BAM_BETRIEB;


import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.indep.exceptions.myException;

public class BAMB_MASK_ModulContainer extends Project_BasicModuleContainer_MASK
{

	public static final String FIELDNAME_CROSSREFERENCE_BAM_USER = 					"FIELDNAME_CROSSREFERENCE_BAM_USER";
	public static final String FIELDNAME_DAUGHTERTABLE_PRINTPROTOKOLL = 				"FIELDNAME_DAUGHTERTABLE_PRINTPROTOKOLL";
	public static final String FIELDNAME_DEL_BUTTON_DAUGHTERTABLE_PRINTPROTOKOLL = 	"FIELDNAME_DEL_BUTTON_DAUGHTERTABLE_PRINTPROTOKOLL";
	public static final String FIELDNAME_FBAM_SENDE_MAILS_TO_VERTEILER = 				"FIELDNAME_FBAM_SENDE_MAILS_TO_VERTEILER";

	public static final String FIELDNAME_DAUGHTERTABLE_INFOS = 						"FIELDNAME_DAUGHTERTABLE_INFOS";

	
	public static final String FBAM_DRUCK_VERMERK_BAM = 								"Beanstandungsmeldung";
	public static final String FBAM_DRUCK_VERMERK_WEIGER = 							"Weigermeldung";

	/*
	 * sperrliste fuer felder aus der maske, wird im masken-setter benutzt
	 * WEIGERMELDUNG
	 * und alles andere
	 */
	public static final String FBAM_LOCKLIST_WEIGERMELDUNG=":WM_UEBERNAHMEVORSCHLAG:WM_DATUM:WM_ANSPRECHPARTNER_INHOUSE:WM_ABNEHMER:::" +
															":WM_ORT:WM_UHRZEIT:WM_ANSPRECHPARTNER_LIEFERANT:WM_FAXNUMMER::" +
															":WM_ANLIEFERDATUM:WM_LETZTERDRUCK_DATUM:WM_LETZTERDRUCK_UHRZEIT:WM_GESPERRT::" +
															":WM_ZAEHLER_ENTSPERRUNG:WM_ARTBEZ1:WM_MENGE_ABLADEN:";

	public static final String FBAM_LOCKLIST_BAM=		":BAM_GRUND:BAM_DATUM:BAM_ORT:FESTSTELLUNG_BAM:BETREFF_BAM:" +
															":FEHLERURSACHE:FEHLERBESCHREIBUNG:AUSWIRKUNGEN:BEHEB_VORSCHLAG:BEHEB_VERMERK:" +
															":DATUM_AUSSTELLUNG:ID_USER_AUSSTELLUNG:DATUM_BEHEBUNG:ID_USER_BEHEBUNG:DATUM_KONTROLLE:" +
															":ID_USER_KONTROLLE:ABGESCHLOSSEN_BEHEBUNG:ABGESCHLOSSEN_KONTROLLE:" +
															BAMB_MASK_ModulContainer.FIELDNAME_CROSSREFERENCE_BAM_USER+":";

	
	private E2_NavigationList	oNaviList = null;
	
	
	public BAMB_MASK_ModulContainer(E2_NavigationList oNavigationList) throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_BBAM_MASK);
		this.oNaviList = oNavigationList;
		
		BAMB_SQLFieldMAP 			oSQLFieldMAP = 		new BAMB_SQLFieldMAP();
		BAMB_MASK_ComponentMAP 	oE2_ComponentMAP = 	new BAMB_MASK_ComponentMAP(oSQLFieldMAP,this,this.get_MODUL_IDENTIFIER(),this.oNaviList);
		BAMB_Mask 					oMaskComponent	=	new BAMB_Mask(oE2_ComponentMAP);
		
		this.INIT(oE2_ComponentMAP,oMaskComponent,new Extent(1000),new Extent(700));
	}

	
}

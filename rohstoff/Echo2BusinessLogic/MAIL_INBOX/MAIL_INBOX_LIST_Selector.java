package rohstoff.Echo2BusinessLogic.MAIL_INBOX;


import nextapp.echo2.app.Border;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.SELECTOR_COMPONENT_FirmenAuswahl;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_INBOX;
import panter.gmbh.indep.exceptions.myException;

public class MAIL_INBOX_LIST_Selector extends E2_ExpandableRow
{
	
	private SELECTOR_COMPONENT_FirmenAuswahl 		oSelKundenMitPositionen = null; 

	private MyE2_CheckBox  							oCB_ShowDeleted 		= null;
	private MyE2_CheckBox							oCB_ShowCompleted  		= null;
	private MyE2_CheckBox							oCB_ShowAssignedOnly 	= null;
	
	
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	
	private E2_SelectionComponentsVector 	oSelVector = null;
	
	public MAIL_INBOX_LIST_Selector(E2_NavigationList oNavigationList) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);

		
		String sSQLFirmenAlle =   " SELECT DISTINCT   NVL2(JT_ADRESSE.NAME1,JT_ADRESSE.NAME1,'') ||  NVL2(JT_ADRESSE.NAME2,' ' || JT_ADRESSE.NAME2 ,'') ||  NVL2(JT_ADRESSE.NAME3,' ' || JT_ADRESSE.NAME3 ,'')|| '- '||  NVL(JT_ADRESSE.ORT,'-') ||' ('||TO_CHAR(  NVL(JT_ADRESSE.ID_ADRESSE,0))||')' AS NAMEN " +
									" , JT_ADRESSE.ID_ADRESSE " +
									" FROM " + bibE2.cTO() + ".JT_ADRESSE   " +
									" WHERE ID_ADRESSE IN   " +
									"  ( " +
									"   	select distinct JT_EMAIL_INBOX." + RECORD_EMAIL_INBOX.FIELD__ID_ADRESSE_ZUGEORDNET + " " + 
									"		FROM  " + bibE2.cTO() + ".JT_EMAIL_INBOX " +
									"   	where NVL(JT_EMAIL_INBOX." + RECORD_EMAIL_INBOX.FIELD__DELETED + ",'N' )= 'N' " +
									"   ) ";
		
		String sSQLFirmenNurVerarbeiteteMails = sSQLFirmenAlle + 
									" AND ID_ADRESSE NOT IN  " +
									"  ( " +
									"     	SELECT DISTINCT " + RECORD_EMAIL_INBOX.FIELD__ID_ADRESSE_ZUGEORDNET +
									"  		FROM " + bibE2.cTO() + ".JT_EMAIL_INBOX  " +
									"  		WHERE JT_EMAIL_INBOX." + RECORD_EMAIL_INBOX.FIELD__ID_USER_GELESEN + " IS NULL and NVL(JT_EMAIL_INBOX." + RECORD_EMAIL_INBOX.FIELD__DELETED + ",'N' )= 'N'  " +
									"  ) ";
		
		
		
		//2012-06-19: variante mit selector, der alle vorhandenen kunden anzeigt, solche ohne offene Belege aber grau
		oSelKundenMitPositionen = new SELECTOR_COMPONENT_FirmenAuswahl(
											sSQLFirmenAlle + " ORDER BY 1", 
											300, 
											sSQLFirmenNurVerarbeiteteMails,
											this.oSelVector);
		
		
		this.oSelKundenMitPositionen.REFRESH_KundenSelektor();
		
		oSelVector.add(new E2_ListSelectorStandard(this.oSelKundenMitPositionen.get_oSelKunden()," NVL(JT_EMAIL_INBOX." + RECORD_EMAIL_INBOX.FIELD__ID_ADRESSE_ZUGEORDNET + ",0)=#WERT#", null, null));


		// Gelöschte einblenden
		oCB_ShowDeleted 		= new MyE2_CheckBox(new MyE2_String("Gelöschte eMails anzeigen"), new MyE2_String("Bei aktivem Schalter werden gelöschte und ungelöschte eMails angezeigt."));
		oSelVector.add(new E2_ListSelectorStandard(oCB_ShowDeleted,
				"",
				 "nvl(JT_EMAIL_INBOX." + RECORD_EMAIL_INBOX.FIELD__DELETED + ",'N') = 'N'" ));
		
		// Unverarbeitete anzeigen
		oCB_ShowCompleted  		= new MyE2_CheckBox(new MyE2_String("Nur unbestätigte eMails"), new MyE2_String("Es werden zusätzlich zu den anderen Einschränkungen nur eMails ohne Verarbeitungsbestätigung angezeigt"));
		oSelVector.add(new E2_ListSelectorStandard(oCB_ShowCompleted,
				"JT_EMAIL_INBOX." + RECORD_EMAIL_INBOX.FIELD__ID_USER_GELESEN + " IS NULL"  
				,""));
		oCB_ShowCompleted.setSelected(true);
		
		// nur Ohne Adresszuordnung
		oCB_ShowAssignedOnly 	= new MyE2_CheckBox(new MyE2_String("Nur eMails ohne zugeordnete Adresse"), new MyE2_String("Es werden zusätzlich zu den anderen Einschränkungen nur eMails ohne zugeordnete Adresse angezeigt."));
		oSelVector.add(new E2_ListSelectorStandard(oCB_ShowAssignedOnly,
				 "JT_EMAIL_INBOX." + RECORD_EMAIL_INBOX.FIELD__ID_ADRESSE_ZUGEORDNET + " IS NULL" 
				,""));
	
		
		/**
		 * GUI
		 */
		MyE2_Grid oGridInnen = new MyE2_Grid(4,0);
		this.add(oGridInnen, E2_INSETS.I_4_4_4_4);
		
		oGridInnen.add(new MyE2_Label("Zugeordnetet Adresse:"), new Insets(4,2,15,0));
		oGridInnen.add(oSelKundenMitPositionen, new Insets(4,2,15,0));
		oGridInnen.add(new MyE2_Label(""), new Insets(4,2,15,0));
		oGridInnen.add(oCB_ShowDeleted, new Insets(4,2,15,0));

		oGridInnen.add(new MyE2_Label(""), new Insets(4,2,15,0));
		oGridInnen.add(new MyE2_Label(""), new Insets(4,2,15,0));
		oGridInnen.add(new MyE2_Label(""), new Insets(4,2,15,0));
		oGridInnen.add(oCB_ShowAssignedOnly, new Insets(4,2,15,0));
		
		oGridInnen.add(new MyE2_Label(""), new Insets(4,2,15,0));
		oGridInnen.add(new MyE2_Label(""), new Insets(4,2,15,0));
		oGridInnen.add(new MyE2_Label(""), new Insets(4,2,15,0));
		oGridInnen.add(oCB_ShowCompleted, new Insets(4,2,15,0));
		
		
		
		
		/*
		 * Beispiele
		 *
		String cID_USER = bibALL.get_ID_USER(bibE2.get_CurrSession());
		MyE2_CheckBox oCBNurEigene = new MyE2_CheckBox("Nur mit eigener Beteiligung");
		oSelVector.add_(new E2_ListSelectorStandard(oCBNurEigene,"(JT_EMAIL_INBOX.ID_USER="+cID_USER+" OR JT_EMAIL_INBOX.ID_MAIL_INBOX IN (SELECT ID_MAIL_INBOX FROM "+bibE2.cTO()+".JT_EMAIL_INBOX_TEILNEHMER WHERE ID_USER="+cID_USER+"))",""));

		E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_EMAIL_INBOX_WICHTIGKEIT","BESCHREIBUNG","ID_MAIL_INBOX_WICHTIGKEIT","ISTSTANDARD",true);

		MyE2_SelectField	oSelectWichtigkeit = new MyE2_SelectField(oDDWichtigkeit.getDD(),null,false);
		oSelVector.add_(new E2_ListSelectorStandard(oSelectWichtigkeit,"JT_EMAIL_INBOX.ID_MAIL_INBOX_WICHTIGKEIT=#WERT#"));

		
		MyE2_Grid oGridInnen = new MyE2_Grid(4,0);
		this.add(oGridInnen, E2_INSETS.I_4_4_4_4);
		
		oGridInnen.add(new MyE2_Label("Wichtigkeit:"), new Insets(4,2,20,2));
		oGridInnen.add(oSelectWichtigkeit, new Insets(4,2,15,2));
		oGridInnen.add(oCBNurEigene, new Insets(4,2,15,2));
		*/
	}

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}

}

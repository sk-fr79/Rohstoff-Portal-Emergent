/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BAM_FUHREN;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLight;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.ListAndMask.Mask.IF_BaseComponent4Mask;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_Subgrid_4_Mask;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.Echo2.staticStyles.Style_Grid_Normal;
import panter.gmbh.basics4project.DB_ENUMS.FBAM;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class BAMF_Mask extends MyE2_Column implements IF_BaseComponent4Mask
{
	private Vector<IF_ADDING_Allowed>  vMaskGrids = new Vector<IF_ADDING_Allowed>();
	
	public BAMF_Mask(BAMF_MASK_ComponentMAP 	oE2_ComponentMAP) throws myException	
	{
		super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
		
		MyE2_TabbedPane oTabbedPane = new MyE2_TabbedPane(null);
		oTabbedPane.set_bAutoHeight(true);

		
		MyE2_Grid		oGridPage_1	= new MyE2_Grid(new Style_Grid_Normal(0,new Insets(1)));
		MyE2_Grid		oGridPage_2	= new MyE2_Grid(new Style_Grid_Normal(0,new Insets(1)));
		MyE2_Grid		oGridPage_3	= new MyE2_Grid(new Style_Grid_Normal(0,new Insets(1)));
		MyE2_Grid		oGridPage_4	= new MyE2_Grid(new Style_Grid_Normal(0,new Insets(1)));
		MyE2_Grid		oGridPage_5	= new MyE2_Grid(new Style_Grid_Normal(0,new Insets(1)));
		
		
		this.vMaskGrids.add(oGridPage_1);
		this.vMaskGrids.add(oGridPage_2);
		this.vMaskGrids.add(oGridPage_3);
		this.vMaskGrids.add(oGridPage_4);
		this.vMaskGrids.add(oGridPage_5);
		
		
		oTabbedPane.add_Tabb(new MyE2_String("Grundangaben"),				oGridPage_1);
		oTabbedPane.add_Tabb(new MyE2_String("Behebung"),					oGridPage_2);
		oTabbedPane.add_Tabb(new MyE2_String("Weigermeldung"),				oGridPage_3);
		oTabbedPane.add_Tabb(new MyE2_String("Weitere"),					oGridPage_4);
		oTabbedPane.add_Tabb(new MyE2_String("Druckprot."),					oGridPage_5);

		this.add(oTabbedPane);

		oGridPage_1.setColumnWidth(0,new Extent(25,Extent.PERCENT));
		oGridPage_2.setColumnWidth(0,new Extent(25,Extent.PERCENT));
		oGridPage_3.setColumnWidth(0,new Extent(25,Extent.PERCENT));
		oGridPage_4.setColumnWidth(0,new Extent(10,Extent.PERCENT));
		oGridPage_5.setColumnWidth(0,new Extent(10,Extent.PERCENT));
		
		
		Insets oIN = new Insets(2,1,2,1);
		
		Insets oIN2 = new Insets(2,4,2,4);

		
		//	---------------------
		oGridPage_1.setSize(2);
		oGridPage_1.add((Component)oE2_ComponentMAP.get(BAMF__CONST.FIELDNAME_FBAM_MASK_INFO_LINE1),2,E2_INSETS.I_5_5_5_5);

		
//		oGridPage_1.add(new MyE2_Label("Beanstandung"),oIN);			oGridPage_1.add(new E2_ComponentGrid(	3, 	new MyE2_Label("Grund"),
//																													new MyE2_Label("Buchungsnummer"),
//																													new MyE2_Label("ID"),
//																														(Component)oE2_ComponentMAP.get("BAM_GRUND"),
//																														(Component)oE2_ComponentMAP.get("BUCHUNGSNUMMER"),
//																														(Component)oE2_ComponentMAP.get("ID_FBAM")),oIN);

		oGridPage_1.add(new MyE2_Label("Beanstandung"),oIN);			
		oGridPage_1.add(new ownSubGrid(	bibVECTOR.get_Vector("Grund","Buchungsnummer","ID"),bibVECTOR.get_Vector("BAM_GRUND","BUCHUNGSNUMMER","ID_FBAM"),oE2_ComponentMAP),oIN);
		
		
		
//		oGridPage_1.add(new MyE2_Label("Fehler "),oIN);					oGridPage_1.add(new E2_ComponentGrid(	2, new MyE2_Label("Datum"),
//																												new MyE2_Label("Ort:"),
//																												(Component)oE2_ComponentMAP.get("BAM_DATUM"),
//																												(Component)oE2_ComponentMAP.get("BAM_ORT"),
//																												null,null),oIN);
		oGridPage_1.add(new MyE2_Label("Fehler "),oIN);	
		oGridPage_1.add(new ownSubGrid(	bibVECTOR.get_Vector("Datum","Ort"),bibVECTOR.get_Vector("BAM_DATUM","BAM_ORT"),oE2_ComponentMAP),oIN);
		
		oGridPage_1.add(new MyE2_Label("Fehlerbeschreibung / intern"),oIN);		
		oGridPage_1.add(new E2_ComponentGroupHorizontal(0,(Component)oE2_ComponentMAP.get("FEHLERBESCHREIBUNG"),
								  (Component)oE2_ComponentMAP.get(BAMF__CONST.FIELDNAME_BUTTON_COMPOSE_FEHLERBESCHREIBUNG),E2_INSETS.I_0_0_0_0),oIN);

		oGridPage_1.add(new MyE2_Label("Auswirkungen / intern"),oIN);				
		oGridPage_1.add(new E2_ComponentGroupHorizontal(0,(Component)oE2_ComponentMAP.get(_DB.FBAM$AUSWIRKUNGEN), E2_INSETS.I_0_0_0_0),oIN);

		
		
		oGridPage_1.add(new MyE2_Label("Fehlerursache"),oIN);			oGridPage_1.add((Component)oE2_ComponentMAP.get("FEHLERURSACHE"),oIN);
		oGridPage_1.add(new MyE2_Label("Fehler festgestellt bei"),oIN);	oGridPage_1.add((Component)oE2_ComponentMAP.get("FESTSTELLUNG_BAM"),oIN);
		oGridPage_1.add(new MyE2_Label("Fehler betrifft"),oIN);			oGridPage_1.add((Component)oE2_ComponentMAP.get("BETREFF_BAM"),oIN);
		oGridPage_1.add(new MyE2_Label("Ausgestellt von / am"),oIN);	oGridPage_1.add(new E2_ComponentGroupHorizontal(0,(Component)oE2_ComponentMAP.get("ID_USER_AUSSTELLUNG"),
																									(Component)oE2_ComponentMAP.get("DATUM_AUSSTELLUNG"),E2_INSETS.I_0_0_0_0));
		
		
		
		//	---------------------
		
		oGridPage_2.setSize(2);
		oGridPage_2.add((Component)oE2_ComponentMAP.get(BAMF__CONST.FIELDNAME_FBAM_MASK_INFO_LINE2),2,E2_INSETS.I_5_5_5_5);

		oGridPage_2.add(new MyE2_Label("Behebung Vorschlag / intern"),oIN);			oGridPage_2.add((Component)oE2_ComponentMAP.get("BEHEB_VORSCHLAG"),oIN);
		
		//2019-12-27: neue Zeile bearbeitet von verantwortlichem
		oGridPage_2.add(new MyE2_Label("Bearbeitet von Verantwortlichem"),oIN2);		
		oGridPage_2.add(new E2_ComponentGroupHorizontal(0,(Component)oE2_ComponentMAP.get(FBAM.bearbeitung.fn()),
															new MyE2_Label("von"),
															(Component)oE2_ComponentMAP.get(FBAM.id_user_bearbeitung.fn()),
															new MyE2_Label("am"),
															(Component)oE2_ComponentMAP.get(FBAM.bearbeitung_datum.fn()),
															E2_INSETS.I(0, 0, 4, 0)),oIN2);
		//2019-12-27: neue Zeile bearbeitet von verantwortlichem ENDE

		
		
		oGridPage_2.add(new MyE2_Label("Behebung Vermerk / intern "),oIN);			oGridPage_2.add((Component)oE2_ComponentMAP.get("BEHEB_VERMERK"),oIN);
		
		
		
		
		oGridPage_2.add(new MyE2_Label("Behebung abgeschlossen von Ersteller"),oIN2);
		oGridPage_2.add(new E2_ComponentGroupHorizontal(0,(Component)oE2_ComponentMAP.get("ABGESCHLOSSEN_BEHEBUNG"),
																new MyE2_Label("von"),
																(Component)oE2_ComponentMAP.get("ID_USER_BEHEBUNG"),
																new MyE2_Label("am"),
																(Component)oE2_ComponentMAP.get("DATUM_BEHEBUNG"),
																E2_INSETS.I(0, 0, 4, 0)),oIN2);

		oGridPage_2.add(new MyE2_Label("Kontrolle abgeschlossen"),oIN2);			
		oGridPage_2.add(new E2_ComponentGroupHorizontal(0,(Component)oE2_ComponentMAP.get("ABGESCHLOSSEN_KONTROLLE"),
																new MyE2_Label("von"),
																(Component)oE2_ComponentMAP.get("ID_USER_KONTROLLE"),
																new MyE2_Label("am"),
																(Component)oE2_ComponentMAP.get("DATUM_KONTROLLE"),
																E2_INSETS.I(0, 0, 4, 0)),oIN2);
		


		//	--------------------- weigerungsangaben
		oGridPage_3.setSize(2);
		oGridPage_3.add((Component)oE2_ComponentMAP.get(BAMF__CONST.FIELDNAME_FBAM_MASK_INFO_LINE3),2,E2_INSETS.I_5_5_5_5);

		
		oGridPage_3.add(new MyE2_Label("Sorte der Lieferung"),oIN);				oGridPage_3.add((Component)oE2_ComponentMAP.get("WM_ARTBEZ1"),oIN);
		oGridPage_3.add(new MyE2_Label("Abholdatum/Abrechnungsmenge"),oIN);		oGridPage_3.add(
																									new E2_ComponentGroupHorizontal(0,
																									(Component)oE2_ComponentMAP.get("WM_ABHOLDATUM"),
																									(Component)oE2_ComponentMAP.get("WM_MENGE_ABLADEN"),
																									E2_INSETS.I_0_0_0_0),oIN);
				
		oGridPage_3.add(new MyE2_Label("Geweigert von (NUR BEI BEDARF)"),oIN);	oGridPage_3.add((Component)oE2_ComponentMAP.get("WM_ABNEHMER"),oIN);
		oGridPage_3.add(new MyE2_Label("Ansprechpartner Inhouse"),oIN);			oGridPage_3.add((Component)oE2_ComponentMAP.get("WM_ANSPRECHPARTNER_INHOUSE"),oIN);
		oGridPage_3.add(new MyE2_Label("Ansprechpartner Lieferant"),oIN);		oGridPage_3.add((Component)oE2_ComponentMAP.get("WM_ANSPRECHPARTNER_LIEFERANT"),oIN);
		
		oGridPage_3.add(new MyE2_Label("Fehlerbeschreibung / Weigermeldung"),oIN);	
		oGridPage_3.add(new E2_ComponentGroupHorizontal(0,(Component)oE2_ComponentMAP.get(_DB.FBAM$FEHLERINFO_EXTERN), E2_INSETS.I_0_0_0_0),oIN);

		oGridPage_3.add(new MyE2_Label("Übernahme-Vorschl./ Weigermeldung"),oIN);				
		oGridPage_3.add(new E2_ComponentGroupHorizontal(0,(Component)oE2_ComponentMAP.get(_DB.FBAM$WM_UEBERNAHMEVORSCHLAG), E2_INSETS.I_0_0_0_0),oIN);
		
		oGridPage_3.add(new MyE2_Label("Fax Nr"),oIN);							oGridPage_3.add((Component)oE2_ComponentMAP.get("WM_FAXNUMMER"),oIN);
		oGridPage_3.add(new MyE2_Label("Ort"),oIN);								oGridPage_3.add((Component)oE2_ComponentMAP.get("WM_ORT"),oIN);
		oGridPage_3.add(new MyE2_Label("Datum/Uhrzeit"),oIN);					oGridPage_3.add(	new E2_ComponentGroupHorizontal(0,
																									(Component)oE2_ComponentMAP.get("WM_DATUM"),
																									(Component)oE2_ComponentMAP.get("WM_UHRZEIT"),
																									E2_INSETS.I_0_0_0_0),oIN);
		
		oGridPage_3.add(new MyE2_Label("Gesperrt/Zahl Entsperrvorgänge"),oIN);	oGridPage_3.add(	new E2_ComponentGroupHorizontal(0,
																									(Component)oE2_ComponentMAP.get("WM_GESPERRT"),
																									(Component)oE2_ComponentMAP.get("WM_ZAEHLER_ENTSPERRUNG"),
																									(Component)oE2_ComponentMAP.get(BAMF__CONST.FIELDNAME_FBAM_RESET_WEIGERMELDUNGSZAEHLER),
																									E2_INSETS.I_0_0_0_0),oIN);
		
		
		
		oGridPage_3.add(new MyE2_Label("Dat./Uhr. letzt.Druck"),oIN);			oGridPage_3.add(	new E2_ComponentGroupHorizontal(0,
																									(Component)oE2_ComponentMAP.get("WM_LETZTERDRUCK_DATUM"),
																									(Component)oE2_ComponentMAP.get("WM_LETZTERDRUCK_UHRZEIT"),
																									E2_INSETS.I_0_0_0_0),oIN);
		

		
		
		
		//	---------------------
		
		oGridPage_4.setSize(2);
		oGridPage_4.add((Component)oE2_ComponentMAP.get(BAMF__CONST.FIELDNAME_FBAM_MASK_INFO_LINE4),2,E2_INSETS.I_5_5_5_5);

		oGridPage_4.add(new MyE2_Label("Infos an"),oIN);						oGridPage_4.add(new E2_ComponentGroupHorizontal(0,
																					(Component)oE2_ComponentMAP.get(BAMF__CONST.FIELDNAME_CROSSREFERENCE_BAM_USER),
																					(Component)oE2_ComponentMAP.get(BAMF__CONST.FIELDNAME_FBAM_SENDE_MAILS_TO_VERTEILER),
																					E2_INSETS.I_0_0_0_0),
																					oIN);
		
		
		
		// -----------------------
		GridLayoutData innenLayout = new GridLayoutData();
		innenLayout.setBackground(new E2_ColorLight());
		innenLayout.setInsets(new Insets(5,2,0,2));
		
		/*
		 * eine tochterkomponenten fuer die druckprotokolle
		 */
		oGridPage_5.setSize(2);
		oGridPage_5.add((Component)oE2_ComponentMAP.get(BAMF__CONST.FIELDNAME_FBAM_MASK_INFO_LINE5),2,E2_INSETS.I_5_5_5_5);
		
		oGridPage_5.add(new MyE2_Label("Druckprotokoll"),oIN);				oGridPage_5.add((Component)oE2_ComponentMAP.get(BAMF__CONST.FIELDNAME_DAUGHTERTABLE_PRINTPROTOKOLL),innenLayout);
		
		
	}

	@Override
	public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException
	{
		return this.vMaskGrids;
	}
	
	
	
	private class ownSubGrid extends E2_Subgrid_4_Mask {

		public ownSubGrid(Vector<String> vTitelStrings, Vector<String> vComponentNames, E2_ComponentMAP oMAP) throws myException {
			super();
			
			GridLayoutData  oGLTitle = MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(0,1,1,1),new E2_ColorDark(),1,1);
			GridLayoutData  oGLElement = MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(0,1,1,1),new E2_ColorBase(),1,1);
			
			this.setSize(vTitelStrings.size());
			for (String cTitel: vTitelStrings) {
				this.add(new MyE2_Label(new MyE2_String(cTitel), new E2_FontItalic(-2)),oGLTitle);
			}

			for (String cName: vComponentNames) {
				this.add(oMAP.get_Comp(cName),oGLElement);
			}
		}
	}
	
}
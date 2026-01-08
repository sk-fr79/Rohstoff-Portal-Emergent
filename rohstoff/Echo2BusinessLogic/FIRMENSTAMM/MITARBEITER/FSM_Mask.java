/**
 * 
 */
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MITARBEITER;

import java.util.Vector;

import echopointng.Separator;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.ListAndMask.Mask.IF_BaseComponent4Mask;
import panter.gmbh.Echo2.components.E2_Subgrid_4_Mask;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.Echo2.staticStyles.Style_Grid_Normal;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.EN_FS_Fields;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_CONST;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.GPS.FS_MaskGridShowGpsComponents;

public class FSM_Mask extends MyE2_Column  implements IF_BaseComponent4Mask	
{
	private Vector<IF_ADDING_Allowed>  vMaskGrids = new Vector<IF_ADDING_Allowed>();
	
	public FSM_Mask(	FSM_MASK_ComponentMAP 		oE2_MAP_ADRESSE) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
		
		E2_ComponentMAP oMapMitarbeiter = oE2_MAP_ADRESSE.get_E2_ComponentMAP_Mitarbeiter();
		
		MyE2_TabbedPane oTabbedPane = new MyE2_TabbedPane(null);
		oTabbedPane.set_bAutoHeight(true);

		
		MyE2_Grid		oGridPage1	= new MyE2_Grid(new Style_Grid_Normal(0,new Insets(2)));
		MyE2_Grid		oGridPage2	= new MyE2_Grid(new Style_Grid_Normal(0,new Insets(2)));
		MyE2_Grid		oGridPage3	= new MyE2_Grid(new Style_Grid_Normal(0,new Insets(2)));

		MyE2_Grid		oGridPage4	= new MyE2_Grid(new Style_Grid_Normal(0,new Insets(2)));   //NEU_09
	
		
		oTabbedPane.add_Tabb(new MyE2_String("Adresse"),			oGridPage1);
		oTabbedPane.add_Tabb(new MyE2_String("Telefon/Mail"),		oGridPage2);
		oTabbedPane.add_Tabb(new MyE2_String("Infos"),				oGridPage3);
	
		oTabbedPane.add_Tabb(new MyE2_String("Geschenke"),			oGridPage4);    //NEU_09
		
		
		this.add(oTabbedPane);

		oGridPage1.setColumnWidth(0,new Extent(150));
		
		oGridPage2.setColumnWidth(0,new Extent(150));
		oGridPage3.setColumnWidth(0,new Extent(150));
		oGridPage4.setColumnWidth(0,new Extent(150));
		
		
		Insets oIN = new Insets(5,2,2,2);

		E2_MaskFiller oFiller = new E2_MaskFiller(oE2_MAP_ADRESSE,oMapMitarbeiter,null);
		
		
		// Tabreiter adresse
		oGridPage1.setSize(2);
		oFiller.add_Line(oGridPage1,"#ID-Mitarbeiter",1,"ID_MITARBEITER|#  ...  |#ID-Adresse|ID_ADRESSE", 1);
		oFiller.add_Line(oGridPage1,"#Mitarbeitertypen",1,"ID_MITARBEITERTYP|#|ID_MITARBEITERTYP2|#|ID_MITARBEITERTYP3|#|ID_MITARBEITERTYP4|# ",1);
		oFiller.add_Line(oGridPage1,"#Anrede",1,"ID_ANREDE|#   ",1);
		oFiller.add_Line(oGridPage1,"#Vorname",1,"VORNAME",1);
		oFiller.add_Line(oGridPage1,"#Name 1",1,"NAME1",1);
		oFiller.add_Line(oGridPage1,"#Name 2",1,"NAME2",1);
		oFiller.add_Line(oGridPage1,"#Name 3",1,"NAME3",1);
		oFiller.add_Line(oGridPage1,"#Strasse-Hnr",1,"STRASSE|HAUSNUMMER",1);
		oFiller.add_Line(oGridPage1,"#Land-PLZ-Ort",1,"ID_LAND|PLZ|ORT",1);               //NEU_09 Land vor PLZ
		oFiller.add_Line(oGridPage1,"#Ortzusatz",1,"ORTZUSATZ",1);
		oFiller.add_Line(oGridPage1,"#Sprache",1,"ID_SPRACHE|# ",1);                   //NEU_09
		
		
		oFiller.add_Line(oGridPage1,"#Aktiv",1,"|AKTIV|# ",1);

		Insets iHelp = new Insets(10, 2, 10, 2);
		
//		E2_Subgrid_4_Mask  sg1 = new E2_Subgrid_4_Mask(oMapMitarbeiter, "Ist Ansprechpartner ?", 
//				"IST_ANSPRECHPARTNER|WEIHNACHTSGESCHENK|SOMMERGESCHENK", 
//				LayoutDataFactory.get_GridLayoutGridCenter(iHelp), 
//				LayoutDataFactory.get_GridLayoutGridCenter(iHelp));
//
//		oFiller.add_Line(oGridPage1,"#Merkmale",1,sg1.get_InBorderGrid(new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID), null, E2_INSETS.I_1_1_1_1),1);                   //NEU_09
		
		
		E2_Subgrid_4_Mask  sg2 = new E2_Subgrid_4_Mask(oMapMitarbeiter, 
				"Allgemein|Abnahmeanbot/Preisinfo|Angebot|EK-Kontrakt|VK-Kontrakt|Rechnung|Gutschrift|Transportauftrag|Fibu/Mahnwesen", 
				"IST_ANSPRECHPARTNER|ASP_ABNAHMEANGEBOT|ASP_ANGEBOT|ASP_EK_KONTRAKT|ASP_VK_KONTRAKT|ASP_RECHNUNG|ASP_GUTSCHRIFT|ASP_TRANSPORT|ASP_FIBU", 
				LayoutDataFactory.get_GridLayoutGridCenter(iHelp), 
				LayoutDataFactory.get_GridLayoutGridCenter(iHelp));

		oFiller.add_Line(oGridPage1,"#Ansprechpartner für: ",1,sg2.get_InBorderGrid(new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID), null, E2_INSETS.I_1_1_1_1),1);                   //NEU_09
		
		
		//20180129: gps-block
		FS_MaskGridShowGpsComponents gpsGrid = new FS_MaskGridShowGpsComponents(	oE2_MAP_ADRESSE.get_Comp(ADRESSE.longitude.fn())
																,oE2_MAP_ADRESSE.get_Comp(ADRESSE.latitude.fn())
																, oE2_MAP_ADRESSE.get_Comp(EN_FS_Fields.GPS_BUTTON_SEARCH.name())
//																, oE2_MAP_ADRESSE.get_Comp(EN_FS_Fields.GPS_BUTTON_VIEW_IN_MAP.name())
																, oE2_MAP_ADRESSE.get_Comp(EN_FS_Fields.GPS_BUTTON_VIEW_OSM_IN_MAP.name())
																, null
																);
		oFiller.add_Line(oGridPage1,"#GPS-Angaben",1,gpsGrid,1);

		
		
		
//		//test qualifizierer
//		oFiller.add_Line(oGridPage1,"#Qualifizierer: ",1,FS_CONST.MASK_FIELD_DAUGHTER_MITARBEITER_QUALIFIER,1);                   //NEU_09
//		//test qualifizierer
		
		
	
		oGridPage2.add(new MyE2_Label("Telefon- und Fax-Nummern"),2,oIN);
		oGridPage2.add(oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_DAUGHTER_TELEFON),2,oIN);
		
		oGridPage2.add(new Separator(),2,oIN);
		oGridPage2.add(new MyE2_Label("e-Mail-Adressen"),2,oIN);
		oGridPage2.add(oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_DAUGHTER_EMAIL),2,oIN);
	
		
		
		// Tabreiter Mail/Internet
		oGridPage3.setSize(2);
		oFiller.add_Line(oGridPage3,"#Bemerkungen",2);
		oFiller.add_Line(oGridPage3,"BEMERKUNGEN",2);
		
		oGridPage3.add(new Separator(),2,oIN);
		oGridPage3.add(new MyE2_Label("Zusatzinfos zur Adresse"),2,oIN);
		oGridPage3.add(oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_DAUGHTER_MITARBEITER_INFOS),2,oIN);
		
		//NEU_09
		//oGridPage4.add(new Separator(),2,oIN);
		
		//2014-02-21: geschenk-felder
		E2_Subgrid_4_Mask  oGeschenkListe = new E2_Subgrid_4_Mask(
															oMapMitarbeiter, 
															"Beschreibung/Info|Kalender|Wein|Sekt|Spargel",
															_DB.MITARBEITER$ROHSTOFF_GESCHENK_INFO+"|"+_DB.MITARBEITER$GESCHENK_KALENDER+"|"+_DB.MITARBEITER$GESCHENK_WEIN+"|"+_DB.MITARBEITER$GESCHENK_SEKT+"|"+_DB.MITARBEITER$GESCHENK_SPARGEL,
															LayoutDataFactory.get_GridLayoutGridCenter(iHelp), 
															LayoutDataFactory.get_GridLayoutGridCenter(iHelp));
		int iSpalten[] = {300,60,60,60,60};
		oGeschenkListe.set_Spalten(iSpalten);
		
		
		oFiller.add_Line(oGridPage4,"#Geschenkstatus",1,oGeschenkListe.get_InBorderGrid(new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID), null, E2_INSETS.I(1,1,1,1)),1);                   //NEU_09
		oGridPage4.add(new Separator(),2,oIN);
		oFiller.add_Line(oGridPage4,"#Geschenkhistorie",1,oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_DAUGHTER_MITARBEITER_GESCHENKE),1); 
		
//		oGridPage4.add(new MyE2_Label("Geschenkhistorie"),1,oIN);
//		oGridPage4.add(oE2_MAP_ADRESSE.get_Comp(FS_CONST.MASK_FIELD_DAUGHTER_MITARBEITER_GESCHENKE),1,oIN);
//		//NEU_09  -- ende
		
		this.vMaskGrids.add(oGridPage1);
		this.vMaskGrids.add(oGridPage2);
		this.vMaskGrids.add(oGridPage3);
		this.vMaskGrids.add(oGridPage4);
		
	}

	@Override
	public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException
	{
		return this.vMaskGrids;
	}
	
	
}
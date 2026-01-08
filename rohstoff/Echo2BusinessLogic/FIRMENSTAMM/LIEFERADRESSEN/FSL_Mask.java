/**
 * 
 */
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.LIEFERADRESSEN;

import java.util.Vector;

import echopointng.Separator;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.ListAndMask.Mask.IF_BaseComponent4Mask;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.EN_FS_Fields;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_CONST;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.GPS.FS_MaskGridShowGpsComponents;

public class FSL_Mask extends MyE2_Column  implements IF_BaseComponent4Mask	
{
	private Vector<IF_ADDING_Allowed>  vMaskGrids = new Vector<IF_ADDING_Allowed>();
	
	public FSL_Mask(	FSL_MASK_ComponentMAP 		mapAdress) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
		
		E2_ComponentMAP mapLiefAdr = mapAdress.get_E2_ComponentMAP_Lieferadresse();
		
		ownGrid		oGridPage2	= new ownGrid(2);   //NEU_09
		
		int[] iSpalten3 = {200,230,260,210};
		ownGrid		oGridPage3	= new ownGrid(iSpalten3);
		
		//2013-06-03: neue tochtertabelle: lieferadressen haben zertifizierte avv-codes
		int[] iSpalten4 = {600,60,60};
		ownGrid		oGridPage4	= new ownGrid(iSpalten4);
		
//		// 2013-11-19 Kostenerfassung für die Incoterms
//		ownGrid		oGridPage5   = new ownGrid(2);
//		oGridPage5.setSize(2);
		

//		oGridPage1.setSize(2);
//		oGridPage2.setSize(2);          //NEU_09
//		oGridPage3.setSize(4);
		


		Insets oIN = new Insets(5,2,2,2);

		E2_MaskFiller oFiller = new E2_MaskFiller(mapAdress,mapLiefAdr,null);

		
		
		
		//hilfsgrid fuer die schalter
		RB_gld ldTitle = new RB_gld()._center_top()._col_back_d()._ins(0,7,5,2);
		RB_gld ldInhalt = new RB_gld()._center_mid()._ins(0,2,5,2);
		E2_Grid gridSchalter = new E2_Grid()._setSize(120,120,120,120,120)._bo_d();
		gridSchalter	._a(new RB_lab(S.ms("Sichere Anh.-7 Quelle"))._i()._lw(), 		ldTitle)
						._a(new RB_lab(S.ms("Sicheres Anh.-7 Ziel"))._i()._lw(),			ldTitle)
						._a(new RB_lab(S.ms("Registerführung"))._i()._lw(),					ldTitle)
						._a(new RB_lab(S.ms("In Formularen Hauptadr. drucken"))._i()._lw(),ldTitle)
						._a(new RB_lab(S.ms("Std. Lieferadr."))._i()._lw(),				ldTitle)
						._a(mapAdress.get__CompEcho(ADRESSE.ah7_quelle_sicher),				ldInhalt)
						._a(mapAdress.get__CompEcho(ADRESSE.ah7_ziel_sicher),				ldInhalt)
						._a(mapLiefAdr.get__CompEcho(LIEFERADRESSE.registerfuehrung),		ldInhalt)
						._a(mapLiefAdr.get__CompEcho(LIEFERADRESSE.hauptadresse_ersetzt_lager),	ldInhalt)
						._a(mapLiefAdr.get__CompEcho(LIEFERADRESSE.ist_standard),			ldInhalt)
						;
		
		
		//20180129: gps-block
		FS_MaskGridShowGpsComponents gpsGrid = new FS_MaskGridShowGpsComponents(	mapAdress.get_Comp(ADRESSE.longitude.fn())
																,mapAdress.get_Comp(ADRESSE.latitude.fn())
																, mapAdress.get_Comp(EN_FS_Fields.GPS_BUTTON_SEARCH.name())
																, mapAdress.get_Comp(EN_FS_Fields.GPS_BUTTON_VIEW_IN_MAP.name())
																, null
																);

		E2_Grid		e2Grid1	= new E2_Grid()._setSize(220,450,300);

		RB_gld     ld_s1 =  new RB_gld()._ins(5, 2, 2, 1);
		RB_gld     ld_s2 =  new RB_gld()._span(2)._ins(2,2,2,1);
		
		e2Grid1	._a(new RB_lab(S.ms("Aktiv"))._fo_s_plus(2)._b(), 		new RB_gld()._ins(5,10,5,10)._col_back_d())
				._a(new E2_Grid()._s(5)
									._a(mapAdress.get__CompEcho(ADRESSE.aktiv), new RB_gld()._ins(0,0,100,0))
									._a(new RB_lab()._tr("ID Lieferadresse:"), 	new RB_gld()._ins(0,0,10,0))
									._a(mapLiefAdr.get__CompEcho(LIEFERADRESSE.id_lieferadresse), new RB_gld()._ins(0,0,200,0))
									._a(new RB_lab()._tr("ID Adresse:"), new RB_gld()._ins(0,0,10,0))
									._a(mapAdress.get__CompEcho(ADRESSE.id_adresse))
																		, new RB_gld()._ins(5,10,5,10)._col_back_d()._span(2));
		
		e2Grid1	._a(new RB_lab()._tr("Name1"), 			ld_s1)
				._a(mapAdress.get__CompEcho(ADRESSE.name1), new RB_gld()._ins(2,2,2,2))
				._a(gpsGrid, new RB_gld()._span(1)._span_r(3)._ins(2,2,2,2))
				;
		
		e2Grid1	._a(new RB_lab()._tr("Name2"), 			ld_s1)				._a(mapAdress.get__CompEcho(ADRESSE.name2), 	ld_s2);
		e2Grid1	._a(new RB_lab()._tr("Name3"), 			ld_s1)				._a(mapAdress.get__CompEcho(ADRESSE.name3), 	ld_s2);
		e2Grid1	._a(new RB_lab()._tr("Strasse/HausNr."),ld_s1)		._a(
											new E2_Grid()._setSize(300,80)._a(mapAdress.get__CompEcho(ADRESSE.strasse))._a(mapAdress.get__CompEcho(ADRESSE.hausnummer), new RB_gld()._ins(5,0,0,0))
																											, 				ld_s2);
		
		e2Grid1	._a(new RB_lab()._tr("Land"),			ld_s1)				._a(mapAdress.get__CompEcho(ADRESSE.id_land), 	ld_s2);
		e2Grid1	._a(new RB_lab()._tr("PLZ"), 			ld_s1)				._a(mapAdress.get__CompEcho(ADRESSE.plz), 		ld_s2);
		e2Grid1	._a(new RB_lab()._tr("Ort"), 			ld_s1)				._a(mapAdress.get__CompEcho(ADRESSE.ort), 		ld_s2);
		e2Grid1._a(new RB_lab()._tr("Ortzusatz"),		ld_s1)				._a(mapAdress.get__CompEcho(ADRESSE.ortzusatz), ld_s2);
		e2Grid1._a(new RB_lab()._tr("Beschreibung"),	ld_s1)				._a(mapLiefAdr.get__CompEcho(LIEFERADRESSE.beschreibung), ld_s2);
		e2Grid1._a(new RB_lab()._tr("Öffnungszeiten"),	ld_s1)				._a(mapLiefAdr.get__CompEcho(LIEFERADRESSE.oeffnungszeiten), ld_s2);
		e2Grid1	._a(new RB_lab()._tr(""), 				ld_s1)				._a(gridSchalter, 								ld_s2);
		
		e2Grid1.addSeparator();
		
		e2Grid1._a(new RB_lab()._tr("LAGER: Sachbearbeiter"),ld_s1)			._a(mapAdress.get__CompEcho(ADRESSE.id_user_lager_sachbearb), 	ld_s2);
		e2Grid1	._a(new RB_lab()._tr("LAGER: zuständiger Händler"), ld_s1)	._a(mapAdress.get__CompEcho(ADRESSE.id_user_lager_haendler), 	ld_s2);
		e2Grid1	._a(new RB_lab()._tr("LAGER: zuständig vor Ort"), ld_s1)	._a(mapAdress.get__CompEcho(ADRESSE.lager_zustaendig_extern), 	ld_s2);
		e2Grid1	._a(new RB_lab()._tr("LAGER: Verwaltungsinfo"), 	ld_s1)	._a(mapAdress.get__CompEcho(ADRESSE.lager_kontrollinfo), 		ld_s2);
		
		e2Grid1.addSeparator();
		
		//diese zeile nur bei mandanten-lagern anzeigen 
		// eigentuemer-adresse
		e2Grid1	._a(mapLiefAdr.get__CompEcho(FSL__CONST.LABEL_ID_ADRESSE_FREMDWARE), 		ld_s1)
				._a(mapLiefAdr.get__CompEcho(LIEFERADRESSE.id_adresse_fremdware), 			ld_s2);

		
		//Besitzer-adresse und ggf ein lager des besitzers
		e2Grid1	._a(mapLiefAdr.get__CompEcho(FSL__CONST.LABEL_ID_ADRESSE_BESITZER_WARE), 	new RB_gld()._ins(5, 7, 2, 0))	
				._a(mapLiefAdr.get__CompEcho(LIEFERADRESSE.id_adresse_besitzer_ware), 		new RB_gld()._span(2)._ins(2,7,2,0));
		
		e2Grid1	._a(mapLiefAdr.get__CompEcho(FSL__CONST.LABEL_ID_ADRESSE_BESITZER_WARE_LAGER),new RB_gld()._ins(5,0, 2, 7))	
				._a(mapLiefAdr.get__CompEcho(LIEFERADRESSE.id_adresse_besitzer_ware_lager), new RB_gld()._span(2)._ins(2,0,2,7));
		
		
		
		e2Grid1	._a(mapLiefAdr.get__CompEcho(FSL__CONST.LABEL_ID_ADRESSE_BESITZ_LAGER), 	ld_s1)	
				._a(mapLiefAdr.get__CompEcho(LIEFERADRESSE.id_adresse_besitz_lager), 		ld_s2);
		

		
		//NEU_09
		oGridPage2.add(new MyE2_Label("Telefon- und Fax-Nummern"),2,oIN);
		oGridPage2.add(mapAdress.get_Comp(FS_CONST.MASK_FIELD_DAUGHTER_TELEFON_LIEF),2,oIN);
		
		oGridPage2.add(new Separator(),2,oIN);
		oGridPage2.add(new MyE2_Label("e-Mail-Adressen"),2,oIN);
		oGridPage2.add(mapAdress.get_Comp(FS_CONST.MASK_FIELD_DAUGHTER_EMAIL_LIEF),2,oIN);
		//NEU_09 - Ende
		
		
		oFiller.add_Line(oGridPage3,"#Lieferinfo TPA",1,"LIEFERINFO_TPA",3);
		oFiller.add_Line(oGridPage3,"#Fahrplan-Infos",1,"BEMERKUNG_FAHRPLAN",3);
		

		//2014-08-01:anpassen block wie in Hauptadresse
		oFiller.add_Trenner(oGridPage3, E2_INSETS.I_2_10_2_10);

		
		oFiller.add_Line(oGridPage3,"#Telefonfeld EU-Blatt:",1, "EU_BEIBLATT_TEL",1,"#Telefaxfeld EU-Blatt:",1,"EU_BEIBLATT_FAX",1);
		oFiller.add_Line(oGridPage3,"#Ansprechpa. EU-Blatt:",1, "EU_BEIBLATT_ANSPRECH",1,"#e-Mail EU-Blatt:",1,"EU_BEIBLATT_EMAIL",1);
		oFiller.add_Line(oGridPage3,"#EU-Vertrag bis Datum (EK):",1, "EU_BEIBLATT_EK_VERTRAG",1,"#EU-Vertrag bis Datum (VK):",1,"EU_BEIBLATT_VK_VERTRAG",1);
		//2014-07-10: neues Info-Feld zu EU-Vertraegen
		oFiller.add_Line(oGridPage3,"#Informationen zu EU-Verträgen",	1,RECORD_ADRESSE.FIELD__EU_BEIBLATT_INFOFELD,3);

		
		
		
		//2013-06-03: tabreiter erlaubte Avv-Codes fuer die jeweilige Adresse (anlieferungen)
		oGridPage4.add(new MyE2_Label("Zu dieser Adresse dürfen nur die folgenden AVV-Codes geliefert werden (falls leer, keine Beschränkung)",true),1,oIN);
		oGridPage4.add(mapAdress.get_Comp(FSL__CONST.MASK_FIELD_DAUGHTER_AVV_CODES_ZERTIFIZIERT_EXPORT_LA),1,oIN);
		oGridPage4.add(mapAdress.get_Comp(FSL__CONST.MASK_FIELD_DAUGHTER_AVV_CODES_ZERTIFIZIERT_IMPORT_LA),1,oIN);
		oGridPage4.add(mapAdress.get_Comp(FSL__CONST.MASK_FIELD_DAUGHTER_AVV_CODES_ZERTIFIZIERT_LA),3,oIN);
		
		
		MyE2_TabbedPane oTabbed = new MyE2_TabbedPane(null);
		oTabbed.set_bAutoHeight(true);

		oTabbed.add_Tabb(new MyE2_String("Lieferadresse"),e2Grid1);
		oTabbed.add_Tabb(new MyE2_String("Telefon/Mail"),oGridPage2);     //NEU_09
		oTabbed.add_Tabb(new MyE2_String("Lieferinfo/EU-Beiblatt"),oGridPage3);
		oTabbed.add_Tabb(new MyE2_String("AVV-Codes"),oGridPage4);
		
		this.add(oTabbed);
		
		this.vMaskGrids.add(e2Grid1);
		this.vMaskGrids.add(oGridPage2);
		this.vMaskGrids.add(oGridPage3);
		
	}

	@Override
	public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException
	{
		return this.vMaskGrids;
	}
	
	
	private class ownGrid extends MyE2_Grid
	{

		public ownGrid(int iNumCols) 
		{
			super(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			
			this.setSize(iNumCols);
			this.setWidth(new Extent(100,Extent.PERCENT));
			
			this.add(new MyE2_Label(" "),iNumCols, E2_INSETS.I_2_10_2_0);   //luft vom oberen rand
		}

		public ownGrid(int[] iSpalten)
		{
			super(iSpalten,0);
			//this.setWidth(new Extent(100,Extent.PERCENT));
			this.add(new MyE2_Label(" "),iSpalten.length, E2_INSETS.I_2_10_2_0);   //luft vom oberen rand
		}
		
		
//		public void add_sep()
//		{
//			this.NewLine();
//			this.add(new Separator(),this.getSize(),E2_INSETS.I_2_0_2_0);
//		}
		
		
	}
	
}
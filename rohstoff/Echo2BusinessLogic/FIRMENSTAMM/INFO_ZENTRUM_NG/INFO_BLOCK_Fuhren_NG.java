package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG;

import java.math.BigDecimal;
import java.util.Vector;

import echopointng.Separator;
import echopointng.able.Scrollable;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.UserSettings.XXX_UserSetting;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.DownLoader;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VKOPF_RG_DRUCK;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG_DRUCK;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE.DATUMSKORREKTUR.E2_Date_Selection_Von_Bis_TF;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG.FSI_Const_and_help.SORT_BY;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG.INFO_BLOCK_FUHRE_NG_SQL_ABFRAGE.ABFRAGE_FELD;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG.INFO_BLOCK_FUHRE_NG_SQL_ABFRAGE.Abfrage_EK;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG.INFO_BLOCK_FUHRE_NG_SQL_ABFRAGE.Abfrage_Ort_EK;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG.INFO_BLOCK_FUHRE_NG_SQL_ABFRAGE.Abfrage_Ort_VK;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG.INFO_BLOCK_FUHRE_NG_SQL_ABFRAGE.Abfrage_VK;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_MANDANT_ext;



public class INFO_BLOCK_Fuhren_NG extends MyE2_Grid 
{


	private cbStatusFuhre  cb_0_Storniert = null;
	private cbStatusFuhre  cb_1_Alt =	 null;
	private cbStatusFuhre  cb_2_Unvollstaendig = null;
	private cbStatusFuhre  cb_3_OhneBuchPos =	 null;
	private cbStatusFuhre  cb_4_Ungebucht = null;
	private cbStatusFuhre  cb_5_Teilgebucht = null;
	private cbStatusFuhre  cb_6_Ganzgebucht = null;

	private FSI_Navigation_Bar_NG comp_navBar= null;

	private cbAuswahlWasZeigt cb_6_EK = null;
	private cbAuswahlWasZeigt cb_7_VK = null;

	private MyE2_TextField    oTF_Suche = 	new MyE2_TextField("",60,30);
	private MyE2_Button       oBT_Refresh = new MyE2_Button(E2_ResourceIcon.get_RI("reload.png")); 

	private boolean hasEK = false;
	private boolean hasVK = false; 

	private MyE2_ContainerEx  oContainerEx = new MyE2_ContainerEx();

	//variable, die die sortierung definiert
	private static      String            SORTKEY_DATUM=				"SORTKEY_DATUM";
	private static      String            SORTKEY_MENGE=				"SORTKEY_MENGE";
	private static      String            SORTKEY_E_PREIS=			"SORTKEY_PREIS_BRUTTO";
	private static      String            SORTKEY_PREIS=			"SORTKEY_PREIS_NETTO";

	private static      String            SORTKEY_ANR1_2=				"SORTKEY_ANR1_2";
	private static      String            SORTKEY_FIRMA=				"SORTKEY_FIRMA";

	private SORT_BY 						sorting	= SORT_BY.DEFAULT;

	private             String            cSORTKEY = INFO_BLOCK_Fuhren_NG.SORTKEY_DATUM;

	private int[] spaltenbreite = new int[]{35,80,80,80,40,80,80,60,180,60,35,180,100,25};

	/**
	 * @return s Buchungsstatus einer Fuhre aufgrund der Dateninhalte in den positionssaetzen
	 * @throws myException
	 * 	public static int     	STATUS_FUHRE__IST_STORNIERT = 					-2;
	 *  public static int     	STATUS_FUHRE__IST_ALT_WID_NICHT_BEGUCHT = 		-1;
	 *  public static int     	STATUS_FUHRE__EINGABE_IST_NOCH_NICHT_FERTIG = 	1;
	 *  public static int     	STATUS_FUHRE__BESITZT_KEINE_BUCHUNGSPOS = 		2;
	 *  public static int     	STATUS_FUHRE__UNGEBUCHT= 						3;
	 *  public static int     	STATUS_FUHRE__TEILSGEBUCHT= 					4;
	 *  public static int     	STATUS_FUHRE__GANZGEBUCHT = 					5;
	 */
	private Vector<Component> vSelcomponents = new Vector<Component>();

	private Vector<Component[]>  vComponentZeilen = new Vector<Component[]>();

	private FS__Adress_Info_Zentrum_NG   oZentrum = null;

	private E2_Date_Selection_Von_Bis_TF datumRangeSelektor;

	private BigDecimal 	BD0 = new BigDecimal(0);

	private String fuhreNgQuery = new String();

	private MyE2_ContainerEx subContainerEx;

	private int firstVkIndex = 0;

	private boolean leerListe = false;
	//	private String query;

	public INFO_BLOCK_Fuhren_NG(FS__Adress_Info_Zentrum_NG oInfoZentrum, boolean bAufbau) throws myException 
	{
		super(1, MyE2_Grid.STYLE_GRID_DDARK_BORDER_W100());

		this.oContainerEx.setWidth(new Extent(100, Extent.PERCENT));
		this.oContainerEx.setHeight(new Extent(350));   //anfangshoehe
		this.oContainerEx.setBorder(new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		this.oContainerEx.setScrollBarPolicy(Scrollable.NEVER);
		this.oZentrum = oInfoZentrum;

		//ausblenden (wie in fuhrenzentrale)
		this.cb_0_Storniert=	   	new cbStatusFuhre("Storno",						new MyE2_String("Blendet stornierte Fuhren aus") ,     					false, "-2");
		this.cb_1_Alt=				new cbStatusFuhre("Alt",						new MyE2_String("Blendet alte (nicht buchbare) Fuhren aus") ,     		false, "-1");
		this.cb_2_Unvollstaendig=	new cbStatusFuhre("Unvoll.",					new MyE2_String("Blendet unvollständige Fuhren aus") ,     				false,  "1");
		this.cb_3_OhneBuchPos=		new cbStatusFuhre("Fremd",						new MyE2_String("Blendet Fuhren aus, die keine Warenpositionen haben") ,true,   "2");
		this.cb_4_Ungebucht=		new cbStatusFuhre("Ungeb.",						new MyE2_String("Blendet noch nicht gebuchte Fuhren aus") , 			false,  "3");
		this.cb_5_Teilgebucht=		new cbStatusFuhre("Teil.",						new MyE2_String("Blendet teils gebuchte Fuhren aus") , 					false,  "4");
		this.cb_6_Ganzgebucht=		new cbStatusFuhre("Kompl.",						new MyE2_String("Blendet fertig gebuchte Fuhren aus") ,	 				true,   "5");
		this.cb_6_EK=				new cbAuswahlWasZeigt("(*) ist Ausgangpunkt",	new MyE2_String("Zeigt Fuhren an, wo die Firma Lieferant ist") ,	 	true, false);
		this.cb_7_VK=				new cbAuswahlWasZeigt("(*) ist Zielpunkt",		new MyE2_String("Zeigt Fuhren an, wo die Firma Abnehmer ist") ,			true, false);

		this.datumRangeSelektor = 	new datumRangeCustomComponent()._fsa(-2)._i();

		this.comp_navBar = new FSI_Navigation_Bar_NG(this, 50);
		this.comp_navBar.addActionAgent_onSeiteLaengeComboBox(new actionSaveStatus());

		this.cb_6_EK.add_oActionAgent(				new actionNeubauFuhrenGrid());
		this.cb_7_VK.add_oActionAgent(				new actionNeubauFuhrenGrid());
		this.oBT_Refresh.add_oActionAgent(			new actionCheckHighlight());


		this.cb_6_EK.add_oActionAgent(				new actionSaveStatus());
		this.cb_7_VK.add_oActionAgent(				new actionSaveStatus());
		this.oBT_Refresh.add_oActionAgent(			new actionSaveStatus());

		vSelcomponents.add(new MyE2_Label(new MyE2_String("AUSBLENDEN:   "), new E2_FontItalic(-2)));

		vSelcomponents.add(this.cb_0_Storniert);
		vSelcomponents.add(this.cb_1_Alt);
		vSelcomponents.add(this.cb_2_Unvollstaendig);
		vSelcomponents.add(this.cb_3_OhneBuchPos);
		vSelcomponents.add(this.cb_4_Ungebucht);
		vSelcomponents.add(this.cb_5_Teilgebucht);
		vSelcomponents.add(this.cb_6_Ganzgebucht);

		vSelcomponents.add(datumRangeSelektor);

		vSelcomponents.add(this.cb_6_EK);
		vSelcomponents.add(this.cb_7_VK);

		vSelcomponents.add(this.oTF_Suche);
		vSelcomponents.add(this.oBT_Refresh);

		this.subContainerEx = new MyE2_ContainerEx();
		this.subContainerEx.setHeight(new Extent(550));

		this.restore_status_der_selektionen();

		if (bAufbau)
		{
			fuhren_dateien_laden_v3();
		}


	}

	public MyE2_ContainerEx get_oContainerEx() 
	{
		return oContainerEx;
	}

	public int getFirstVkIndex() {
		return firstVkIndex;
	}

	public boolean isEkChecked(){
		return cb_6_EK.isSelected();
	}

	public boolean isVkChecked(){
		return cb_7_VK.isSelected();
	}


	public void fuhren_dateien_laden_v3() throws myException{

		String id_adresse = this.oZentrum.get_recADRESSE().get_ID_ADRESSE_cUF_NN("");

		String UNION = new String(" UNION ");

		String cWhereAnteilCB = get_anteil_cb_as_string();

		String cWhereAnteilSelDatumLieferant = get_lieferant_datum();

		String cWhereAnteilSelDatumAbnehmer = get_abnehmer_datum();

		String cWhereAnteilSelDatumOrt = get_ort_datum();

		fuhreNgQuery = new String();

		String subQuery1 = new String();
		String subQuery2 = new String();
		String subQuery3 = new String();
		String subQuery4 = new String();

		//EK subQuery 
		subQuery1 = new Abfrage_EK(id_adresse).s()
				.concat(cWhereAnteilCB.equals("0")?"":"AND NVL(FU.STATUS_BUCHUNG,0) NOT IN ("+cWhereAnteilCB+")")
				.concat(cWhereAnteilSelDatumLieferant);

		subQuery2 = new Abfrage_VK(id_adresse).s()
				.concat(cWhereAnteilCB.equals("0")?"":"AND NVL(FU.STATUS_BUCHUNG,0) NOT IN ("+cWhereAnteilCB+")")
				.concat(cWhereAnteilSelDatumAbnehmer);


		subQuery3 = new Abfrage_Ort_EK(id_adresse).s()
				.concat(cWhereAnteilCB.equals("0")?"":"AND NVL(FU.STATUS_BUCHUNG,0) NOT IN ("+cWhereAnteilCB+")")
				.concat(cWhereAnteilSelDatumOrt);

		subQuery4 = new Abfrage_Ort_VK(id_adresse).s()
				.concat(cWhereAnteilCB.equals("0")?"":"AND NVL(FU.STATUS_BUCHUNG,0) NOT IN ("+cWhereAnteilCB+")")
				.concat(cWhereAnteilSelDatumOrt);


		if(cb_6_EK.isSelected() && cb_7_VK.isSelected()){
			fuhreNgQuery = subQuery1 + UNION + subQuery2 + UNION + subQuery3 + UNION + subQuery4;
		}else if(cb_6_EK.isSelected() && !cb_7_VK.isSelected()){
			fuhreNgQuery = subQuery1 + UNION + subQuery3 ;
		}else if(! cb_6_EK.isSelected() && cb_7_VK.isSelected()){
			fuhreNgQuery = subQuery2 + UNION + subQuery4;
		}else fuhreNgQuery = "";

		//select id und 'ek' oder 'vk'
		String select = "SELECT " + 
				ABFRAGE_FELD.ID_VPOS_TPA_FUHRE.db_val() + ", " 
				+ ABFRAGE_FELD.ID_VPOS_TPA_FUHRE_ORT.db_val() +", " 
				+ ABFRAGE_FELD.DEF_QUELLE_ZIEL;

		select += " FROM (";
		// sorten
		String orderBy ="";

		if (this.cSORTKEY.equals(INFO_BLOCK_Fuhren_NG.SORTKEY_ANR1_2)){
			orderBy = ABFRAGE_FELD.ANR1_2.db_val()+" "+sorting.getSortingWay();
		}else if(this.cSORTKEY.equals(INFO_BLOCK_Fuhren_NG.SORTKEY_DATUM)){
			orderBy = ABFRAGE_FELD.DATUM.db_val()+" "+sorting.getSortingWay();
		}else if(this.cSORTKEY.equals(INFO_BLOCK_Fuhren_NG.SORTKEY_MENGE)){
			orderBy = ABFRAGE_FELD.ABZUG_MENGE+" "+sorting.getSortingWay();
		}else if(this.cSORTKEY.equals(INFO_BLOCK_Fuhren_NG.SORTKEY_E_PREIS)){
			orderBy = ABFRAGE_FELD.EPREIS_RESULT+" "+sorting.getSortingWay();
		}else if(this.cSORTKEY.equals(INFO_BLOCK_Fuhren_NG.SORTKEY_PREIS)){
			orderBy = ABFRAGE_FELD.EPREIS+" "+sorting.getSortingWay();
		}else if(this.cSORTKEY.equals(INFO_BLOCK_Fuhren_NG.SORTKEY_FIRMA)){
			orderBy = ABFRAGE_FELD.ID_ADRESSE_STATION+" "+sorting.getSortingWay();
		}

		String orderBy_ = ") ORDER BY " 
				+ ABFRAGE_FELD.DEF_QUELLE_ZIEL.db_val() + " ASC, " 
				+ orderBy + ", " + ABFRAGE_FELD.ID_VPOS_TPA_FUHRE +" ASC";

		String finalQuery= select + fuhreNgQuery + orderBy_;

		String[][] abfrageErgebnis = null;

		if(S.isFull(fuhreNgQuery)){
			abfrageErgebnis = bibDB.EinzelAbfrageInArrayFormatiert(finalQuery);
		}

		if(!(abfrageErgebnis == null) && abfrageErgebnis.length>0 ){		
			this.leerListe = false;
			this.comp_navBar.init_navigationBar_with_idsList(abfrageErgebnis);
			this.comp_navBar.selectRecordFromTo_ids(0, this.comp_navBar.getDisplayedItemsPerPage());
			this.comp_navBar.setVisible(true);
			this.comp_navBar.set_bEnabled_For_Edit(true);
		}else{
			this.leerListe = true;
			this.comp_navBar.setVisible(false);
			this.subContainerEx.removeAll();
			this.comp_navBar.setVisible(false);
			_grid_aufbau(null, null);
			this.comp_navBar.set_bEnabled_For_Edit(false);
		}
	}

	private String get_ort_datum() {
		String cAusdruckDatumOrt = 	"FO.DATUM_LADE_ABLADE";
		String cWhereAnteilSelDatumOrt = this.datumRangeSelektor.get_von_SQLFORMAT().equals("")?"":" AND NVL(TO_CHAR("+cAusdruckDatumOrt+",'YYYY-MM-DD'),'9999-12-31')>="+bibALL.MakeSql(this.datumRangeSelektor.get_von_SQLFORMAT());
		cWhereAnteilSelDatumOrt += this.datumRangeSelektor.get_bis_SQLFORMAT().equals("")?"":" AND NVL(TO_CHAR("+cAusdruckDatumOrt+",'YYYY-MM-DD'),'9999-12-31')<="+bibALL.MakeSql(this.datumRangeSelektor.get_bis_SQLFORMAT());
		return cWhereAnteilSelDatumOrt;
	}

	private String get_abnehmer_datum() {
		String cAusdruckDatumVK	=	"NVL(FU.DATUM_ABLADEN, FU.DATUM_ANLIEFERUNG)";
		String cWhereAnteilSelDatumAbnehmer = 	this.datumRangeSelektor.get_von_SQLFORMAT().equals("")?"":" AND NVL(TO_CHAR("+cAusdruckDatumVK+",'YYYY-MM-DD'),'9999-12-31')>="+bibALL.MakeSql(this.datumRangeSelektor.get_von_SQLFORMAT());
		cWhereAnteilSelDatumAbnehmer  += 		this.datumRangeSelektor.get_bis_SQLFORMAT().equals("")?"":" AND NVL(TO_CHAR("+cAusdruckDatumVK+",'YYYY-MM-DD'),'9999-12-31')<="+bibALL.MakeSql(this.datumRangeSelektor.get_bis_SQLFORMAT());
		return cWhereAnteilSelDatumAbnehmer;
	}

	private String get_lieferant_datum() {
		String cAusdruckDatumEK	=	"NVL(FU.DATUM_AUFLADEN, FU.DATUM_ABHOLUNG)";
		String cWhereAnteilSelDatumLieferant = 	this.datumRangeSelektor.get_von_SQLFORMAT().equals("")?"":" AND NVL(TO_CHAR("+cAusdruckDatumEK+",'YYYY-MM-DD'),'9999-12-31')>="+bibALL.MakeSql(this.datumRangeSelektor.get_von_SQLFORMAT());
		cWhereAnteilSelDatumLieferant += 		this.datumRangeSelektor.get_bis_SQLFORMAT().equals("")?"":" AND NVL(TO_CHAR("+cAusdruckDatumEK+",'YYYY-MM-DD'),'9999-12-31')<="+bibALL.MakeSql(this.datumRangeSelektor.get_bis_SQLFORMAT());
		return cWhereAnteilSelDatumLieferant;
	}

	private String get_anteil_cb_as_string() throws myException {
		Vector<String> vStatus = new Vector<String>();
		for (Component oComp: this.vSelcomponents)
		{
			if (oComp instanceof cbStatusFuhre)
			{
				if (((cbStatusFuhre)oComp).isSelected())
				{
					vStatus.add(((cbStatusFuhre)oComp).EXT().get_C_MERKMAL());
				}
			}
		}
		String cWhereAnteilCB = bibALL.Concatenate(vStatus, ",", "0");
		return cWhereAnteilCB;
	}


	

	public void _grid_aufbau(Vector<FSI_storageObject> vDisplayedId, Vector<FSI_storageObject> IdsAnnexData) 
			throws myException {

		this.subContainerEx.removeAll();

		if(!(vDisplayedId==null)){
			hasEK = selectedRecFuhreHasEK(vDisplayedId);

			hasVK = selectedRecFuhreHasVK(vDisplayedId);
		}


		VectorSingle ekListFuerSprunge = new VectorSingle();
		VectorSingle vkListFuerSprunge = new VectorSingle();

		if(! (IdsAnnexData == null)){
			for( FSI_storageObject id: IdsAnnexData){
				if(id.isEk()){
					if(id.isOrt()){
						ekListFuerSprunge.add(new RECORD_VPOS_TPA_FUHRE_ORT(id.getIdOrt()).get_ID_VPOS_TPA_FUHRE_cUF_NN(""));
					}else
						ekListFuerSprunge.add(id.getId());
				}else{
					if(id.isOrt()){
						vkListFuerSprunge.add(new RECORD_VPOS_TPA_FUHRE_ORT(id.getIdOrt()).get_ID_VPOS_TPA_FUHRE_cUF_NN(""));
					}else
						vkListFuerSprunge.add(id.getId());
				}
			}
		}

		if ((hasEK && this.cb_6_EK.isSelected())|| leerListe)
		{

			E2_Grid grd1 = new E2_Grid()._bc(new E2_ColorDDark());//._w(new Extent(100, Extent.PERCENT));

			grd1
			._bo(new Border(1, new E2_ColorDDark(), Border.STYLE_SOLID))
			._ins(E2_INSETS.I(1,1,1,1))
			._s(14)
			._setSize(spaltenbreite)
			._a_lm(new RB_lab(""))
			._a_lm(new sortButton(new MyE2_String("Lad.Dat."),INFO_BLOCK_Fuhren_NG.SORTKEY_DATUM,true))
			._a_rm(new RB_lab("Plan")._fsa(-2))
			._a_rm(new sortButton(new MyE2_String("LadeM."),INFO_BLOCK_Fuhren_NG.SORTKEY_MENGE,true))
			._a_rm(new RB_lab("Abzug Mge.")._fsa(-2)._lw())
			._a_rm(new sortButton(new MyE2_String("Preis"),INFO_BLOCK_Fuhren_NG.SORTKEY_PREIS,true))
			._a_rm(new sortButton(new MyE2_String("Preis/Nettomge."),INFO_BLOCK_Fuhren_NG.SORTKEY_E_PREIS,true))
			._a_lm(new sortButton(new MyE2_String("ANR1-2"),INFO_BLOCK_Fuhren_NG.SORTKEY_ANR1_2,true))
			._a_lm(new RB_lab("Artbez1")._fsa(-2)._lw())
			._a_lm(new RB_lab("Buch-Nr.")._fsa(-2)._lw())
			._a_lm(new RB_lab("DL")._fsa(-2)._lw())
			._a_lm(new sortButton(new MyE2_String("Abnehmer"),INFO_BLOCK_Fuhren_NG.SORTKEY_FIRMA,true))
			._a_lm(new RB_lab("Zielort")._fsa(-2)._lw())
			._a_lm(new FS__Adress_Info_Zentrum_JUMP_ADRESSE_NAVI_POPUP_TO_FUHRE_NG(
					ekListFuerSprunge, 
					this.oZentrum.get_oContainerToCloseAfterJump(),	
					leerListe? false:this.oZentrum.is_jump_is_active())
					);


			if(!leerListe){
				buildRows(grd1, vDisplayedId, true);
				/*for (int i=0;i<vFuhrenUndOrte.size();i++)
				{
					String extQuery = "SELECT * FROM (" + fuhreNgQuery + ") WHERE ";

					if(vFuhrenUndOrte.get(i).isOrt){
						extQuery += ABFRAGE_FELD.ID_VPOS_TPA_FUHRE_ORT.db_val()+"="+vFuhrenUndOrte.get(i).recORT.get_ID_VPOS_TPA_FUHRE_ORT_cUF_NN("");
					}else{
						extQuery += ABFRAGE_FELD.ID_VPOS_TPA_FUHRE.db_val()+"="+vFuhrenUndOrte.get(i).get_ID_VPOS_TPA_FUHRE_cUF_NN("") + " AND "
								+ABFRAGE_FELD.ID_VPOS_TPA_FUHRE_ORT.db_val()+"=0";
					}

					DEBUG.System_println(extQuery);

					MyRECORD ownRecord = new MyRECORD(extQuery);

					FSI_RECORD_VPOS_TPA_FUHRE_own  recFuhre = vFuhrenUndOrte.get(i);

					String cADDonText = recFuhre.recORT!=null?" <ORT>":"";

					if (recFuhre.bEK)
					{

						//daten (vpos_rg) wenn vorhanden

						String abzugMenge ="-" + ownRecord.get_UnFormatedValue(ABFRAGE_FELD.ABZUG_MENGE.db_val());

						if(abzugMenge.equals("-0")){
							abzugMenge = "";
						}else
							abzugMenge = bibALL.convertID2FormattedID(abzugMenge);


						Component oCompHelp = null;

						if (! (recFuhre.get_record_vkopf_rg()== null))
						{
							oCompHelp=new ownDownButton(recFuhre.get_record_vkopf_rg(),recFuhre);
						}
						else 
							oCompHelp=new RB_lab(" ")._fsa(-2)._lw();	

						Component[] test = {
								this.generateStatusFuhre(recFuhre.get_STATUS_BUCHUNG_cUF_NN("0")),
								new RB_lab(ownRecord.get_FormatedValue(ABFRAGE_FELD.DATUM.db_val()))._fsa(-2)._lw(),
								new RB_lab(ownRecord.get_bdValue(ABFRAGE_FELD.PLAN.db_val(),BD0,2).toPlainString())._fsa(-2)._lw(),
								new RB_lab(ownRecord.get_bdValue(ABFRAGE_FELD.MENGE_BRUTTO.db_val(),BD0,2).toPlainString())._fsa(-2)._lw(),
								S.isEmpty(abzugMenge)?new RB_lab(abzugMenge):new RB_lab(abzugMenge)._col_fore(highlightColor)._fsa(-2),
										new RB_lab(ownRecord.get_bdValue(ABFRAGE_FELD.EPREIS.db_val(),BD0,2).toPlainString())._fsa(-2)._lw(),
										new RB_lab(ownRecord.get_bdValue(ABFRAGE_FELD.EPREIS_RESULT.db_val(),BD0,2).toPlainString())._fsa(-2)._lw(),
										new RB_lab(ownRecord.get_FormatedValue(ABFRAGE_FELD.ANR1_2.db_val(),"") )._fsa(-2)._lw(),
										new RB_lab(ownRecord.get_FormatedValue(ABFRAGE_FELD.ART_BEZ1.db_val(),""))._fsa(-2)._lw(),
										new RB_lab(ownRecord.get_FormatedValue(ABFRAGE_FELD.BUCHUNGSNUMMER.db_val(),""))._fsa(-2)._lw(),
										oCompHelp,
										new RB_lab(recFuhre.get_A_NAME1_cF_NN("<Abnehmer>"+cADDonText))._fsa(-2)._lw(),
										new RB_lab(recFuhre.get_A_ORT_cF_NN("<Abladeort>")+cADDonText)._fsa(-2)._lw(),
										new FS__Adress_Info_Zentrum_JUMP_ADRESSE_NAVI_POPUP_TO_FUHRE_NG(
												bibALL.get_Vector(ownRecord.get_UnFormatedValue(ABFRAGE_FELD.ID_VPOS_TPA_FUHRE.db_val())), 
												this.oZentrum.get_oContainerToCloseAfterJump(),
												this.oZentrum.is_jump_is_active()
												)
						};

						grd1
						._gld(new RB_gld()._ins(2, 0, 2, 0))
						._a_cm(test[0])._a_lm(test[1])
						._a_rm(test[2])._a_rm(test[3])
						._a_rm(test[4])._a_rm(test[5])._a_rm(test[6])._a_rm(test[7])._a_lm(test[8])
						._a_lm(test[9])._a_lm(test[10])._a_lm(test[11])._a_lm(test[12])._a_lm(test[13])
						._setSize(spaltenbreite);
						//						._setSize(new int[]{35,80,80,80,40,80,80,60,250,60,35,100,100,25})
						;

						new FSI_HighLighter_NG(this.oTF_Suche, test);
						this.vComponentZeilen.add(test);
					}
				}
				 */
			}else{
				grd1
				._gld(new RB_gld()._span(14)._col(new E2_ColorBase())._ins(10, 20, 10, 20))
				._a_cm(new RB_lab("KEINE DATEN")._fsa(15)._fo_bold()._col_fore_lgrey());


			}

			this.subContainerEx.add(grd1);

		}

		if(!leerListe){
			if (hasVK && this.cb_7_VK.isSelected())
			{
				if (hasEK && this.cb_6_EK.isSelected())
				{
					this.subContainerEx.add(new Separator());
				}
				//			
				E2_Grid grd2 = new E2_Grid()._bo(new Border(1, new E2_ColorDDark(), Border.STYLE_SOLID));

				grd2
				._s(14)
				._setSize(spaltenbreite)
				._ins(E2_INSETS.I(2,1,2,1))
				._bc(new E2_ColorDDark())
				._a_rm(new RB_lab(" ")._fsa(-2)._lw())
				._a_lm(new sortButton(new MyE2_String("Abl.Dat."),INFO_BLOCK_Fuhren_NG.SORTKEY_DATUM,true))
				._a_rm(new RB_lab("Plan")._fsa(-2)._lw())
				._a_rm(new sortButton(new MyE2_String("AbladeM."),INFO_BLOCK_Fuhren_NG.SORTKEY_MENGE,true))
				._a_rm(new RB_lab("Abzug Mge.")._fsa(-2)._lw())
				._a_rm(new sortButton(new MyE2_String("Preis"),INFO_BLOCK_Fuhren_NG.SORTKEY_PREIS,true))
				._a_rm(new sortButton(new MyE2_String("Preis/Nettomge."),INFO_BLOCK_Fuhren_NG.SORTKEY_E_PREIS,true))
				._a_lm(new sortButton(new MyE2_String("ANR1-2"),INFO_BLOCK_Fuhren_NG.SORTKEY_ANR1_2,true))
				._a_lm(new RB_lab("Artbez1")._fsa(-2)._lw())
				._a_lm(new RB_lab("Buch-Nr.")._fsa(-2)._lw())
				._a_lm(new RB_lab("DL")._fsa(-2)._lw())
				._a_lm(new sortButton(new MyE2_String("Lieferant"),INFO_BLOCK_Fuhren_NG.SORTKEY_FIRMA,true))
				._a_lm(new RB_lab("Herkunft Ort")._fsa(-2)._lw())
				._a_lm(new FS__Adress_Info_Zentrum_JUMP_ADRESSE_NAVI_POPUP_TO_FUHRE_NG(
						vkListFuerSprunge, 
						this.oZentrum.get_oContainerToCloseAfterJump(),
						this.oZentrum.is_jump_is_active()));

				buildRows(grd2, vDisplayedId, false);

				/*for (int i=0;i<vFuhrenUndOrte.size();i++)
				{
					FSI_RECORD_VPOS_TPA_FUHRE_own  recFuhre = vFuhrenUndOrte.get(i);
					String cADDonText = recFuhre.recORT!=null?" <ORT>":"";



					String extQuery = "SELECT * FROM (" + fuhreNgQuery + ") WHERE ";

					if(vFuhrenUndOrte.get(i).isOrt){
						extQuery += ABFRAGE_FELD.ID_VPOS_TPA_FUHRE_ORT.db_val()+"="+vFuhrenUndOrte.get(i).recORT.get_ID_VPOS_TPA_FUHRE_ORT_cUF_NN("");
					}else{
						extQuery += ABFRAGE_FELD.ID_VPOS_TPA_FUHRE.db_val()+"="+vFuhrenUndOrte.get(i).get_ID_VPOS_TPA_FUHRE_cUF_NN("") + " AND "
								+ABFRAGE_FELD.ID_VPOS_TPA_FUHRE_ORT.db_val()+"=0";
					}

					MyRECORD ownRecord = new MyRECORD(extQuery);

					if (!recFuhre.bEK)
					{
						//daten (vpos_rg) wenn vorhanden

						String abzugMenge ="-" + ownRecord.get_UnFormatedValue(ABFRAGE_FELD.ABZUG_MENGE.db_val());

						Component oCompHelp;

						if (! (recFuhre.get_record_vkopf_rg()== null))
						{
							oCompHelp=new ownDownButton(recFuhre.get_record_vkopf_rg(),recFuhre);
						}
						else
						{
							oCompHelp=new RB_lab(" ")._fsa(-2)._lw();	
						}	

						Component[] test = {
								this.generateStatusFuhre(recFuhre.get_STATUS_BUCHUNG_cUF_NN("0")),
								new RB_lab(ownRecord.get_FormatedValue(ABFRAGE_FELD.DATUM.db_val()))._fsa(-2)._lw(),
								new RB_lab(ownRecord.get_bdValue(ABFRAGE_FELD.PLAN.db_val(),BD0,2).toPlainString())._fsa(-2)._lw(),
								new RB_lab(ownRecord.get_bdValue(ABFRAGE_FELD.MENGE_BRUTTO.db_val(),BD0,2).toPlainString())._fsa(-2)._lw(),
								S.isEmpty(abzugMenge)?new RB_lab(abzugMenge):new RB_lab(abzugMenge)._col_fore(highlightColor)._fsa(-2),
										new RB_lab(ownRecord.get_bdValue(ABFRAGE_FELD.EPREIS.db_val(),BD0,2).toPlainString())._fsa(-2)._lw(),
										new RB_lab(ownRecord.get_bdValue(ABFRAGE_FELD.EPREIS_RESULT.db_val(),BD0,2).toPlainString())._fsa(-2)._lw(),
										new RB_lab(ownRecord.get_FormatedValue(ABFRAGE_FELD.ANR1_2.db_val(),"") )._fsa(-2)._lw(),
										new RB_lab(ownRecord.get_FormatedValue(ABFRAGE_FELD.ART_BEZ1.db_val(),""))._fsa(-2)._lw(),
										new RB_lab(ownRecord.get_FormatedValue(ABFRAGE_FELD.BUCHUNGSNUMMER.db_val(),""))._fsa(-2)._lw(),
										oCompHelp,
										new RB_lab(recFuhre.get_A_NAME1_cF_NN("<Abnehmer>"+cADDonText))._fsa(-2)._lw(),
										new RB_lab(recFuhre.get_A_ORT_cF_NN("<Abladeort>")+cADDonText)._fsa(-2)._lw(),
										new FS__Adress_Info_Zentrum_JUMP_ADRESSE_NAVI_POPUP_TO_FUHRE_NG(
												bibALL.get_Vector(ownRecord.get_UnFormatedValue(ABFRAGE_FELD.ID_VPOS_TPA_FUHRE.db_val())), 
												this.oZentrum.get_oContainerToCloseAfterJump(),
												this.oZentrum.is_jump_is_active()
												)
						};

						grd2._gld(new RB_gld()._ins(2, 0, 2, 0))
						._a_cm(test[0])._a_lm(test[1])
						._a_rm(test[2])._a_rm(test[3])
						._a_rm(test[4])._a_rm(test[5])._a_rm(test[6])._a_rm(test[7])._a_lm(test[8])
						._a_lm(test[9])._a_lm(test[10])._a_lm(test[11])._a_lm(test[12])._a_lm(test[13])
						._setSize(spaltenbreite)
						;

						new FSI_HighLighter_NG(this.oTF_Suche, test);
						this.vComponentZeilen.add(test);
					}
				}*/
				this.subContainerEx.add(grd2);
			}
		}
	}

	private boolean selectedRecFuhreHasEK(Vector <FSI_storageObject> vFuhrenUndOrte) throws myException{
		boolean ekInList = false;

		for(FSI_storageObject rec : vFuhrenUndOrte){
			if(rec.isEk()){
				ekInList=true;
				break;
			}
		}
		return ekInList;
	}

	private boolean selectedRecFuhreHasVK(Vector <FSI_storageObject> vFuhrenUndOrte) throws myException{
		boolean vkInList = false;
		for(FSI_storageObject rec : vFuhrenUndOrte){
			if(! rec.isEk()){
				vkInList=true;
				break;
			}
		}
		return vkInList;
	}


	private RB_lab generateStatusFuhre(String cBuchungsstatus) throws myException
	{
		E2_ResourceIcon labelIkon =  null;
		String toolTipLabel = "";


		if (cBuchungsstatus.equals(""+myCONST.STATUS_FUHRE__IST_STORNIERT))
		{
			labelIkon = E2_ResourceIcon.get_RI("buchung_fuhre_status_storno.png");
			toolTipLabel = "Die Fuhre wurde storniert und kann nicht gebucht werden !";
		}
		else if (cBuchungsstatus.equals(""+myCONST.STATUS_FUHRE__IST_ALT_WID_NICHT_BEGUCHT))
		{
			labelIkon = E2_ResourceIcon.get_RI("buchung_vollstaendig_weil_alt.png");
			toolTipLabel = "Die Fuhre ist aus dem Archivbestand und kann nicht gebucht werden !";
		}
		else if (cBuchungsstatus.equals(""+myCONST.STATUS_FUHRE__EINGABE_IST_NOCH_NICHT_FERTIG))
		{
			labelIkon = E2_ResourceIcon.get_RI("buchung_fuhre_ist_unvollstaendig.png");
			toolTipLabel ="Die Fuhre ist noch nicht komplett ausgefüllt ";
		}
		else if (cBuchungsstatus.equals(""+myCONST.STATUS_FUHRE__UNGEBUCHT))
		{	
			labelIkon = E2_ResourceIcon.get_RI("buchung_fuhre_ungebucht.png");
			toolTipLabel ="Die Fuhre kann in die freien Positionen überführt werden!";
		}
		else if (cBuchungsstatus.equals(""+myCONST.STATUS_FUHRE__BESITZT_KEINE_BUCHUNGSPOS))
		{
			labelIkon = E2_ResourceIcon.get_RI("buchung_hat_keine_buchungspositionen.png");
			toolTipLabel ="Die Fuhre hat keine Buchungs-Positionen: kann nicht gebucht werden !";
		}
		else if (cBuchungsstatus.equals(""+myCONST.STATUS_FUHRE__GANZGEBUCHT))
		{
			labelIkon = E2_ResourceIcon.get_RI("buchung_vollstaendig.png");
			toolTipLabel ="Die Fuhre ist vollständig verbucht !";
		}
		else if (cBuchungsstatus.equals(""+myCONST.STATUS_FUHRE__TEILSGEBUCHT))
		{
			labelIkon = E2_ResourceIcon.get_RI("buchung_teilweise.png");
			toolTipLabel ="Die Fuhre ist teilweise verbucht!";
		}
		else
		{
			labelIkon = E2_ResourceIcon.get_RI("listlabel_empty.png");
			toolTipLabel = "Buchungsstatus der Fuhre ist unbestimmt";	
		}

		return new RB_lab()._ttt(new MyE2_String(toolTipLabel))._ri(labelIkon);
	}


	public String get_Status_der_Selektoren() throws myException
	{

		StringBuffer cRueck = new StringBuffer("");
		cRueck.append(this.cb_0_Storniert.isSelected()?"Y":"N");
		cRueck.append("|");
		cRueck.append(this.cb_1_Alt.isSelected()?"Y":"N");
		cRueck.append("|");
		cRueck.append(this.cb_2_Unvollstaendig.isSelected()?"Y":"N");
		cRueck.append("|");
		cRueck.append(this.cb_3_OhneBuchPos.isSelected()?"Y":"N");
		cRueck.append("|");
		cRueck.append(this.cb_4_Ungebucht.isSelected()?"Y":"N");
		cRueck.append("|");
		cRueck.append(this.cb_5_Teilgebucht.isSelected()?"Y":"N");
		cRueck.append("|");
		cRueck.append(this.cb_6_Ganzgebucht.isSelected()?"Y":"N");
		cRueck.append("|");
		cRueck.append(S.NN(this.datumRangeSelektor.get_von_SQLFORMAT()));
		cRueck.append("|");
		cRueck.append(this.cb_6_EK.isSelected()?"Y":"N");
		cRueck.append("|");
		cRueck.append(this.cb_7_VK.isSelected()?"Y":"N");
		cRueck.append("|");
		cRueck.append(this.oTF_Suche.getText());
		cRueck.append("|");
		cRueck.append(this.cSORTKEY);
		cRueck.append("|");
		cRueck.append(S.NN(this.datumRangeSelektor.get_bis_SQLFORMAT()));
		cRueck.append("|");
		cRueck.append(S.NN(this.sorting.getSortingWay()));
		cRueck.append("|");
		cRueck.append(S.NN(this.comp_navBar.getSeiteLaenge()));
		//		DEBUG.System_println("Gefundene Seitenlänge: "+ S.NN(this.comp_navBar.getSeiteLaenge()));
		return cRueck.toString();
	}



	private void restore_status_der_selektionen() throws myException
	{
		//hier wird die einstellung gleich in dieser methode gemacht, rueckgabe ist unnoetig
		String cDatabaseSetting = (String)new E2_UserSettings_Checkbox_und_Selektoren().get_Settings(FS__Adress_Info_Zentrum_NG.STORE_MODUL_KENNER);

		if (cDatabaseSetting!=null)
		{
			Vector<String> vWerte= bibALL.TrenneZeile(cDatabaseSetting, "|");

			String datumVonBis[] ={"",""};

			if (vWerte.size()>0) cb_0_Storniert.setSelected(vWerte.get(0).equals("Y"));
			if (vWerte.size()>1) cb_1_Alt.setSelected(vWerte.get(1).equals("Y"));
			if (vWerte.size()>2) cb_2_Unvollstaendig.setSelected(vWerte.get(2).equals("Y"));
			if (vWerte.size()>3) cb_3_OhneBuchPos.setSelected(vWerte.get(3).equals("Y"));
			if (vWerte.size()>4) cb_4_Ungebucht.setSelected(vWerte.get(4).equals("Y"));
			if (vWerte.size()>5) cb_5_Teilgebucht.setSelected(vWerte.get(5).equals("Y"));
			if (vWerte.size()>6) cb_6_Ganzgebucht.setSelected(vWerte.get(6).equals("Y"));
			if (vWerte.size()>7) datumVonBis[0]=vWerte.get(7);
			if (vWerte.size()>8) cb_6_EK.setSelected(vWerte.get(8).equals("Y"));
			if (vWerte.size()>9) cb_7_VK.setSelected(vWerte.get(9).equals("Y"));
			if (vWerte.size()>10) this.oTF_Suche.setText(vWerte.get(10));
			if (vWerte.size()>11) this.cSORTKEY=vWerte.get(11);
			//steht hinten, weil am schluss angefuegt
			if (vWerte.size()>12) datumVonBis[1]=vWerte.get(12);

			if (vWerte.size()>13){
				if(S.isEmpty(vWerte.get(13))){
					this.sorting = SORT_BY.DEFAULT;
				}else{
					this.sorting = SORT_BY.valueOf(vWerte.get(13));
				}

			}

			if(vWerte.size()>14) this.comp_navBar.setSeiteLaenge(vWerte.get(14));

			if(S.isEmpty(datumVonBis[0]) && S.isEmpty(datumVonBis[1])){
				datumVonBis[0]=myDateHelper.get_cCalendarActual(-30);
				datumVonBis[1]=bibALL.get_cDateNOW();
				this.datumRangeSelektor.set_datum_range(datumVonBis[0], datumVonBis[1]);
			}else{
				if(datumVonBis[0].equals(datumVonBis[1])){
					datumVonBis[0]= datumVonBis[0]+"-01";
					datumVonBis[1]=datumVonBis[1]+"-28";
				}
				if((datumVonBis[0].split("-").length!=3) || (datumVonBis[0].split("-").length!=3)){
					datumVonBis[0]=datumVonBis[0]+"-01";
					datumVonBis[1]=datumVonBis[1]+"-28";
				}
				this.datumRangeSelektor.set_datum_range_SQLFORMAT(datumVonBis[0], datumVonBis[1]);
			}
		}
	}


	private E2_ComponentGroupHorizontal get_BedienzeileFuerFuhrenliste() 
	{
		return new E2_ComponentGroupHorizontal(0,vSelcomponents,new Insets(2,0,5,0));
	}


	//gibt ein zweizeiliges grid zurueck mit bedienzeile und eigentlicher liste in containerEx
	public MyE2_Grid get_ContainerGridMitBedienZeile() throws myException
	{
		MyE2_Grid gridRueck = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());

		this.oContainerEx.removeAll();
		this.oContainerEx.add(this);
		gridRueck.add(this.get_BedienzeileFuerFuhrenliste());
		gridRueck.add(new Separator());
		gridRueck.add(comp_navBar);
		gridRueck.add(subContainerEx);

		return gridRueck;
	}

	public boolean isLeerListe() {
		return leerListe;
	}

	//ein user-setting-objekt
	private class E2_UserSettings_Checkbox_und_Selektoren extends XXX_UserSetting 
	{

		private String cSessionHash = "SESSION_HASH_FIRMENSTAMM_JUMPBOX_SPEICHERE_SELECTOR_FUHREN";

		public E2_UserSettings_Checkbox_und_Selektoren() 
		{
			super();
		}

		@Override
		public String get_SessionHash() 
		{
			return this.cSessionHash;
		}

		@Override
		protected String get_OBJECT_TO_STRING(Object oSetting) throws myException 
		{
			return (String)oSetting;
		}

		@Override
		protected Object get_STRING_TO_OBJECT(String cDatabaseSetting)	throws myException 
		{
			return cDatabaseSetting;
		}


	}


	private class datumRangeCustomComponent extends E2_Date_Selection_Von_Bis_TF{

		public datumRangeCustomComponent() throws myException {
			super(75);
		}

		@Override
		public void saveDatumRange() {
			try {
				fuhren_dateien_laden_v3();


				new E2_UserSettings_Checkbox_und_Selektoren().STORE(FS__Adress_Info_Zentrum_NG.STORE_MODUL_KENNER, INFO_BLOCK_Fuhren_NG.this.get_Status_der_Selektoren());

			} catch (myException e) {
				e.printStackTrace();
			}
		}

	}


	private class actionSaveStatus extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			new E2_UserSettings_Checkbox_und_Selektoren().STORE(FS__Adress_Info_Zentrum_NG.STORE_MODUL_KENNER, INFO_BLOCK_Fuhren_NG.this.get_Status_der_Selektoren());
		}
	}


	private class cbStatusFuhre extends MyE2_CheckBox
	{
		public cbStatusFuhre(Object cText, MyE2_String cToolTipText,boolean bIsSelected, String cKennummer) 
		{
			super(cText, cToolTipText, bIsSelected, false);
			this.EXT().set_C_MERKMAL(cKennummer);
			this.setFont(new E2_FontItalic(-2));
			this.add_oActionAgent(	new actionNeubauFuhrenGrid());
			this.add_oActionAgent(	new actionSaveStatus());
		}
	}


	private class cbAuswahlWasZeigt extends MyE2_CheckBox
	{
		public cbAuswahlWasZeigt(Object cText, MyE2_String cToolTipText,boolean bIsSelected, boolean bSetDisabledFromBasic) 
		{
			super(cText, cToolTipText, bIsSelected, bSetDisabledFromBasic);
			this.setFont(new E2_FontPlain(-2));
		}
	}


	private class ownDownButton extends MyE2_Button 
	{
		private RECORD_VKOPF_RG          recVKOPF_RG = null;

		public ownDownButton(RECORD_VKOPF_RG rec_VKOPF_RG) 
		{
			super(E2_ResourceIcon.get_RI("down.png"), true);
			this.recVKOPF_RG = rec_VKOPF_RG;
			this.setLayoutData(MyE2_Grid.LAYOUT_CENTER_TOP(E2_INSETS.I_4_2_4_0));
			this.add_GlobalAUTHValidator_PROGRAMM_WIDE("DOWNLOAD_RECHNUNG_AUS_FIRMENINFO");
			this.add_oActionAgent(new ownActionAgent());
		}

		private class ownActionAgent extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				RECORD_VKOPF_RG  recVkopf =ownDownButton.this.recVKOPF_RG;

				RECLIST_VKOPF_RG_DRUCK  reclistDruck = recVkopf.get_DOWN_RECORD_LIST_VKOPF_RG_DRUCK_id_vkopf_rg("","ID_VKOPF_RG_DRUCK",true);

				if (reclistDruck.get_vKeyValues().size()>0)
				{
					RECORD_VKOPF_RG_DRUCK  recDruck = reclistDruck.get(reclistDruck.get_vKeyValues().size()-1);   //den letzten druck rausziehen

					//kann eingesetzt werden fuer: JT_VKOPF_RG_DRUCK/JT_VKOPF_STD_DRUCK/JT_VKOPF_TPA_DRUCK/JT_VKOPF_KON_DRUCK/JT_VPOS_TPA_FUHRE_DRUCK
					String cTableREF_ARCH = "VKOPF_RG_DRUCK";
					String cTableREF_ID   = recDruck.get_ID_VKOPF_RG_DRUCK_cUF();


					RECLIST_ARCHIVMEDIEN  recArch = new RECLIST_ARCHIVMEDIEN("SELECT * FROM "+bibE2.cTO()+".JT_ARCHIVMEDIEN WHERE JT_ARCHIVMEDIEN.TABLENAME="+bibALL.MakeSql(cTableREF_ARCH)+" AND "+
							"JT_ARCHIVMEDIEN.ID_TABLE="+cTableREF_ID);


					//jetzt 3 moeglichkeiten:  kein eintrag, genau ein eintrag oder mehrere
					if (recArch.get_vKeyValues().size()==0)
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es existiert zu diesem Eintrag keine Archivdatei !"));
					}
					else if (recArch.get_vKeyValues().size()>=1)       //download startet
					{
						new DownLoader(recArch.get(recArch.get_vKeyValues().size()-1));   //den letzten holen
					}
				}
			}
		}
	}

	private class sortButton extends MyE2_Grid{

		private MyE2_Button bt;
		private RB_lab sortUpLbl = new RB_lab(SORT_BY.ASC.getIkon());
		private RB_lab sortDoLbl = new RB_lab(SORT_BY.DESC.getIkon());
		private RB_lab sortNoLbl = new RB_lab(SORT_BY.DEFAULT.getIkon());
		private RB_lab sortStatus = null;
		private String cSortKey;

		public sortButton(MyE2_String cText, String SortKey, boolean bLeft) throws myException {
			super(2);

			this.cSortKey=SortKey;

			GridLayoutData Gl = MyE2_Grid.LAYOUT_RIGHT_TOP(E2_INSETS.I_1_1_1_1);
			if (bLeft)
			{
				Gl = MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I_1_1_1_1);
			}
			Gl.setBackground(new E2_ColorDDark());

			bt = new MyE2_Button(cText);
			bt.setBackground(new E2_ColorDDark());
			bt.setFont(new E2_FontPlain(-2));
			bt.setLayoutData(Gl);
			bt.add_oActionAgent(new XX_ActionAgent() {	
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					if(!leerListe){
						INFO_BLOCK_Fuhren_NG.this.cSORTKEY=sortButton.this.cSortKey;

						INFO_BLOCK_Fuhren_NG.this.sorting = nextSort();

						new E2_UserSettings_Checkbox_und_Selektoren().STORE(FS__Adress_Info_Zentrum_NG.STORE_MODUL_KENNER, INFO_BLOCK_Fuhren_NG.this.get_Status_der_Selektoren());

						INFO_BLOCK_Fuhren_NG.this.fuhren_dateien_laden_v3();
					}
				}
			});

			//			if(leerListe){
			////				this.set_bEnabled_For_Edit(false);
			//			}
			updateSortIkon();

			if (!bLeft)
			{
				this.add(sortStatus);
			}

			this.add(bt);

			if( bLeft){
				this.add(sortStatus);
			}
		}

		private void updateSortIkon(){
			if(this.cSortKey.equals(INFO_BLOCK_Fuhren_NG.this.cSORTKEY)){
				if(sorting == SORT_BY.ASC){
					sorting= SORT_BY.ASC;
					sortStatus = sortUpLbl;
				}else if(sorting == SORT_BY.DESC){
					sorting = SORT_BY.DESC;
					sortStatus = sortDoLbl;
				}else if(sorting == SORT_BY.DEFAULT){
					sorting = SORT_BY.DEFAULT;
					sortStatus= sortNoLbl;
				}
			}else sortStatus = sortNoLbl;

		}

		private SORT_BY nextSort(){
			SORT_BY returnedValue = null ;
			switch(sorting){
			case ASC:
				returnedValue = SORT_BY.DESC;
				break;
			case DEFAULT:
				returnedValue = SORT_BY.ASC;
				break;
			case DESC:
				returnedValue = SORT_BY.DEFAULT;
				break;
			default:
				break;
			}
			return returnedValue;
		}
	}

	public class actionNeubauFuhrenGrid extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			INFO_BLOCK_Fuhren_NG.this.fuhren_dateien_laden_v3();
		}
	}

	public class actionCheckHighlight extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			INFO_BLOCK_Fuhren_NG oThis = INFO_BLOCK_Fuhren_NG.this;

			for (int i=0;i<oThis.vComponentZeilen.size();i++)
			{
				new FSI_HighLighter_NG(oThis.oTF_Suche, oThis.vComponentZeilen.get(i));
			}

		}
	}

	private void buildRows(E2_Grid grid_to_fill, Vector<FSI_storageObject> vDateien, boolean showEk) throws myException{

		for(FSI_storageObject obj: vDateien){

			String subQuery = "";
			String rowQuery = "";
			String addWhereStatement = ") WHERE ";
			String cADDonText ="";

			rowQuery = "SELECT " + ABFRAGE_FELD.getAllFeldIn_1_String() + " FROM (" ;

			if(obj.isOrt()){
				addWhereStatement += ABFRAGE_FELD.ID_VPOS_TPA_FUHRE_ORT.db_val()+"="+obj.getIdOrt();


			}else{
				addWhereStatement += ABFRAGE_FELD.ID_VPOS_TPA_FUHRE.db_val()+"="+obj.getId() + " AND "
						+ABFRAGE_FELD.ID_VPOS_TPA_FUHRE_ORT.db_val()+"=0";
			}

			String cWhereAnteilCB = get_anteil_cb_as_string();
			if(obj.isEk() && showEk){
				if(!obj.getIdOrt().equals("0")){
					subQuery = new Abfrage_Ort_EK(this.oZentrum.get_recADRESSE().get_ID_ADRESSE_cUF_NN("")).s();
					subQuery+= cWhereAnteilCB.equals("0")?"":"AND NVL(FU.STATUS_BUCHUNG,0) NOT IN ("+cWhereAnteilCB+")";
					subQuery+= get_ort_datum();
					cADDonText=" <ORT>";
				}else{
					subQuery = new Abfrage_EK(this.oZentrum.get_recADRESSE().get_ID_ADRESSE_cUF_NN("")).s();
					subQuery += get_lieferant_datum();
					subQuery+= cWhereAnteilCB.equals("0")?"":"AND NVL(FU.STATUS_BUCHUNG,0) NOT IN ("+cWhereAnteilCB+")";
				}
			}

			if(! obj.isEk() && !showEk){
				if(!obj.getIdOrt().equals("0")){
					subQuery = new Abfrage_Ort_VK(this.oZentrum.get_recADRESSE().get_ID_ADRESSE_cUF_NN("")).s();
					subQuery+= cWhereAnteilCB.equals("0")?"":"AND NVL(FU.STATUS_BUCHUNG,0) NOT IN ("+cWhereAnteilCB+")";
					subQuery += get_ort_datum();
					cADDonText=" <ORT>";
				}else{
					subQuery = new Abfrage_VK(this.oZentrum.get_recADRESSE().get_ID_ADRESSE_cUF_NN("")).s();
					subQuery+= cWhereAnteilCB.equals("0")?"":"AND NVL(FU.STATUS_BUCHUNG,0) NOT IN ("+cWhereAnteilCB+")";
					subQuery += get_abnehmer_datum();
				}
			}

			if(S.isFull(subQuery)){
				rowQuery += (subQuery + " "+addWhereStatement);

				MyRECORD ownRecord = new MyRECORD(rowQuery);

				String abzugMenge="";
				if(! ownRecord.get_UnFormatedValue(ABFRAGE_FELD.ABZUG_MENGE.db_val()).equals("0")){
					abzugMenge="-" + ownRecord.get_bdValue(ABFRAGE_FELD.ABZUG_MENGE.db_val(), BD0, 2).toPlainString();
				}

				Color highlightColor = new RECORD_MANDANT_ext(bibALL.get_ID_MANDANT()).get_COLOR_MASK_HIGHLIGHT();

				Component oCompHelp = null;

				if (S.isFull(ownRecord.get_UnFormatedValue(ABFRAGE_FELD.ID_VKOPF_RG.db_val())))
				{
					oCompHelp = new ownDownButton(new RECORD_VKOPF_RG( ownRecord.get_UnFormatedValue(ABFRAGE_FELD.ID_VKOPF_RG.db_val()) ));
				}
				else 
					oCompHelp = new RB_lab(" ")._fsa(-2)._lw();	

				String epreis 			= ownRecord.get_bdValue(ABFRAGE_FELD.EPREIS.db_val()		,BD0,2).toPlainString();
				String epreis_result 	= ownRecord.get_bdValue(ABFRAGE_FELD.EPREIS_RESULT.db_val()	,BD0,2).toPlainString();

				if(epreis.equals("0.00")){
					epreis = "";
				}

				if(epreis_result.equals("0.00")){
					epreis_result = "";
				}

				Component[] test = {
						this.generateStatusFuhre(ownRecord.get_FormatedValue(ABFRAGE_FELD.BUCHUNG_STATUS.db_val())),
						new RB_lab(ownRecord.get_FormatedValue(ABFRAGE_FELD.DATUM.db_val()))._fsa(-2)._lw(),
						new RB_lab(ownRecord.get_bdValue(ABFRAGE_FELD.PLAN.db_val(),BD0,2).toPlainString())._fsa(-2)._lw(),
						new RB_lab(ownRecord.get_bdValue(ABFRAGE_FELD.MENGE_BRUTTO.db_val(),BD0,2).toPlainString())._fsa(-2)._lw(),
						S.isEmpty(abzugMenge)?new RB_lab(abzugMenge):new RB_lab(abzugMenge)._col_fore(highlightColor)._fsa(-2),
								new RB_lab(epreis)._fsa(-2)._lw(),
								new RB_lab(epreis_result)._fsa(-2)._lw(),
								new RB_lab(ownRecord.get_FormatedValue(ABFRAGE_FELD.ANR1_2.db_val(),"") )._fsa(-2)._lw(),
								new RB_lab(ownRecord.get_FormatedValue(ABFRAGE_FELD.ART_BEZ1.db_val(),""))._fsa(-2)._lw(),
								new RB_lab(ownRecord.get_FormatedValue(ABFRAGE_FELD.BUCHUNGSNUMMER.db_val(),""))._fsa(-2)._lw(),
								oCompHelp, 
								new RB_lab(ownRecord.get_FormatedValue(ABFRAGE_FELD.A_FIRMA.db_val()))._fsa(-2)._lw(),
								new RB_lab(ownRecord.get_FormatedValue(ABFRAGE_FELD.A_ORT.db_val())+cADDonText)._fsa(-2)._lw(),
								new FS__Adress_Info_Zentrum_JUMP_ADRESSE_NAVI_POPUP_TO_FUHRE_NG(
										bibALL.get_Vector(ownRecord.get_UnFormatedValue(ABFRAGE_FELD.ID_VPOS_TPA_FUHRE.db_val())), 
										this.oZentrum.get_oContainerToCloseAfterJump(),
										this.oZentrum.is_jump_is_active()
										)
				};

				grid_to_fill._gld(new RB_gld()._ins(2, 0, 2, 0))
				._a_cm(test[0])._a_lm(test[1])
				._a_rm(test[2])._a_rm(test[3])
				._a_rm(test[4])._a_rm(test[5])._a_rm(test[6])._a_rm(test[7])._a_lm(test[8])
				._a_lm(test[9])._a_lm(test[10])._a_lm(test[11])._a_lm(test[12])._a_lm(test[13])
				._setSize(spaltenbreite)
				;

				new FSI_HighLighter_NG(this.oTF_Suche, test);
				this.vComponentZeilen.add(test);
			}
		}



	}


}

package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import echopointng.Separator;
import nextapp.echo2.app.Border;
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
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.UserSettings.XXX_UserSetting;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.DownLoader;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField_month_year;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VKOPF_RG_DRUCK;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG_DRUCK;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class INFO_BLOCK_Fuhren extends MyE2_Grid 
{


	private cbStatusFuhre  cb_0_Storniert = null;
	private cbStatusFuhre  cb_1_Alt =	 null;
	private cbStatusFuhre  cb_2_Unvollstaendig = null;
	private cbStatusFuhre  cb_3_OhneBuchPos =	 null;
	private cbStatusFuhre  cb_4_Ungebucht = null;
	private cbStatusFuhre  cb_5_Teilgebucht = null;
	private cbStatusFuhre  cb_6_Ganzgebucht = null;
	
	private MyE2_SelectField_month_year  o_SelField_ab = null;
	private MyE2_SelectField_month_year  o_SelField_bis = null;
	
	private cbAuswahlWasZeigt cb_6_EK = null;
	private cbAuswahlWasZeigt cb_7_VK = null;

	private MyE2_TextField    oTF_Suche = 	new MyE2_TextField("",60,30);
	private MyE2_Button       oBT_Refresh = new MyE2_Button(E2_ResourceIcon.get_RI("reload.png")); 

	
	private BigDecimal 	 		BD0 = new BigDecimal(0);

	private MyE2_ContainerEx  oContainerEx = new MyE2_ContainerEx();
	
	//variable, die die sortierung definiert
	private static      String            SORTKEY_DATUM=				"SORTKEY_DATUM";
	private static      String            SORTKEY_MENGE=				"SORTKEY_MENGE";
	private static      String            SORTKEY_PREIS=				"SORTKEY_PREIS";
	private static      String            SORTKEY_ANR1_2=				"SORTKEY_ANR1_2";
	private static      String            SORTKEY_FIRMA=				"SORTKEY_FIRMA";
	
	private             String            cSORTKEY = INFO_BLOCK_Fuhren.SORTKEY_DATUM;
	



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

	private FS__Adress_Info_Zentrum   oZentrum = null;
	
	public INFO_BLOCK_Fuhren(FS__Adress_Info_Zentrum oInfoZentrum, boolean bAufbau) throws myException 
	{
		super(12, MyE2_Grid.STYLE_GRID_DDARK_BORDER_W100());
		

		
		this.oContainerEx.setWidth(new Extent(100, Extent.PERCENT));
		this.oContainerEx.setHeight(new Extent(350));   //anfangshoehe
		this.oContainerEx.setBorder(new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));


		oZentrum = oInfoZentrum;
		
		//ausblenden (wie in fuhrenzentrale)
		this.cb_0_Storniert=	   	new cbStatusFuhre("Storno",				new MyE2_String("Blendet stornierte Fuhren aus") ,     					false, "-2");
		this.cb_1_Alt=				new cbStatusFuhre("Alt",				new MyE2_String("Blendet alte (nicht buchbare) Fuhren aus") ,     		false, "-1");
		this.cb_2_Unvollstaendig=	new cbStatusFuhre("Unvoll.",			new MyE2_String("Blendet unvollständige Fuhren aus") ,     				false,  "1");
		this.cb_3_OhneBuchPos=		new cbStatusFuhre("Fremd",				new MyE2_String("Blendet Fuhren aus, die keine Warenpositionen haben") ,true,   "2");
		this.cb_4_Ungebucht=		new cbStatusFuhre("Ungeb.",				new MyE2_String("Blendet noch nicht gebuchte Fuhren aus") , 			false,  "3");
		this.cb_5_Teilgebucht=		new cbStatusFuhre("Teil.",				new MyE2_String("Blendet teils gebuchte Fuhren aus") , 					false,  "4");
		this.cb_6_Ganzgebucht=		new cbStatusFuhre("Kompl.",				new MyE2_String("Blendet fertig gebuchte Fuhren aus") ,	 				true,   "5");
		this.o_SelField_ab =        new MyE2_SelectField_month_year(36,2,false);
		this.cb_6_EK=				new cbAuswahlWasZeigt("EK",				new MyE2_String("Zeigt Fuhren an, wo die Firma Lieferant ist") ,	 	true, false);
		this.cb_7_VK=				new cbAuswahlWasZeigt("VK",				new MyE2_String("Zeigt Fuhren an, wo die Firma Abnehmer ist") ,			true, false);
		this.o_SelField_bis =       new MyE2_SelectField_month_year(36,2,false);
		
		//kontroll-feld fuer die von-bi-dropdowns
		this.o_SelField_ab.add_oActionAgent(	 	new actionAgentkontrolliereDatumsFelder(this.o_SelField_ab,this.o_SelField_bis));
		this.o_SelField_bis.add_oActionAgent(	 	new actionAgentkontrolliereDatumsFelder(this.o_SelField_ab,this.o_SelField_bis));
		
		this.o_SelField_ab.add_oActionAgent(	 	new actionNeubauFuhrenGrid());
		this.o_SelField_bis.add_oActionAgent(		new actionNeubauFuhrenGrid());
		
		this.cb_6_EK.add_oActionAgent(				new actionNeubauFuhrenGrid());
		this.cb_7_VK.add_oActionAgent(				new actionNeubauFuhrenGrid());
		this.oBT_Refresh.add_oActionAgent(			new actionCheckHighlight());

		
		//dann status speichern
		this.o_SelField_ab.add_oActionAgent(		new actionSaveStatus());
		this.o_SelField_bis.add_oActionAgent(		new actionSaveStatus());
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
		
		vSelcomponents.add(new MyE2_Label(new MyE2_String("Zeit:"), new E2_FontItalic(-2)));
		vSelcomponents.add(this.o_SelField_ab);
		vSelcomponents.add(new MyE2_Label(new MyE2_String("bis"), new E2_FontItalic(-2)));
		vSelcomponents.add(this.o_SelField_bis);
		
		vSelcomponents.add(this.cb_6_EK);
		vSelcomponents.add(this.cb_7_VK);
		
		vSelcomponents.add(this.oTF_Suche);
		vSelcomponents.add(this.oBT_Refresh);
		
		this.restore_status_der_selektionen();
		
		if (bAufbau)
		{
			this.__aufbau();
		}
		
	}
	
	public MyE2_ContainerEx get_oContainerEx() 
	{
		return oContainerEx;
	}

	
	public void __aufbau() throws myException
	{
		this.removeAll();
		
		this.vComponentZeilen.removeAllElements();
		
		RECORD_ADRESSE recADRESSE = this.oZentrum.get_recADRESSE();
		

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
		
		String cAusdruckDatumEK	=	"NVL(JT_VPOS_TPA_FUHRE.DATUM_AUFLADEN,JT_VPOS_TPA_FUHRE.DATUM_ABHOLUNG)";
		String cAusdruckDatumVK	=	"NVL(JT_VPOS_TPA_FUHRE.DATUM_ABLADEN,JT_VPOS_TPA_FUHRE.DATUM_ANLIEFERUNG)";

		String cWhereAnteilSelDatumLieferant = 	this.o_SelField_ab.get_ActualWert().trim().equals("")?"":" AND NVL(TO_CHAR("+cAusdruckDatumEK+",'YYYY-MM'),'9999-99')>="+bibALL.MakeSql(this.o_SelField_ab.get_ActualWert());
		String cWhereAnteilSelDatumAbnehmer = 	this.o_SelField_ab.get_ActualWert().trim().equals("")?"":" AND NVL(TO_CHAR("+cAusdruckDatumVK+",'YYYY-MM'),'9999-99')>="+bibALL.MakeSql(this.o_SelField_ab.get_ActualWert());

		cWhereAnteilSelDatumLieferant += 		this.o_SelField_bis.get_ActualWert().trim().equals("")?"":" AND NVL(TO_CHAR("+cAusdruckDatumEK+",'YYYY-MM'),'9999-99')<="+bibALL.MakeSql(this.o_SelField_bis.get_ActualWert());
		cWhereAnteilSelDatumAbnehmer  += 		this.o_SelField_bis.get_ActualWert().trim().equals("")?"":" AND NVL(TO_CHAR("+cAusdruckDatumVK+",'YYYY-MM'),'9999-99')<="+bibALL.MakeSql(this.o_SelField_bis.get_ActualWert());
		
		RECLIST_VPOS_TPA_FUHRE  reclistFuhrenAlsLieferant = 
			recADRESSE.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_adresse_start(
					"NVL(DELETED,'N')='N' AND NVL(IST_STORNIERT,'N')='N' AND NVL(STATUS_BUCHUNG,0) NOT IN ("+ cWhereAnteilCB +") "+cWhereAnteilSelDatumLieferant,"TO_CHAR("+cAusdruckDatumEK+",'YYYY-MM-DD')",true);
		
		RECLIST_VPOS_TPA_FUHRE  reclistFuhrenAlsAbnehmer = 
			recADRESSE.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_adresse_ziel(
					"NVL(DELETED,'N')='N' AND NVL(IST_STORNIERT,'N')='N'  AND NVL(STATUS_BUCHUNG,0) NOT IN ("+ cWhereAnteilCB +") "+cWhereAnteilSelDatumAbnehmer,"TO_CHAR("+cAusdruckDatumVK+",'YYYY-MM-DD')",true);
		
		
		
//		DEBUG.System_println("SELECT * FROM JT_VPOS_TPA_FUHRE WHERE NVL(DELETED,'N')='N' AND NVL(IST_STORNIERT,'N')='N' AND NVL(STATUS_BUCHUNG,0) NOT IN ("+ cWhereAnteilCB +") "+cWhereAnteilSelDatumLieferant);
//		DEBUG.System_println("SELECT * FROM JT_VPOS_TPA_FUHRE WHERE NVL(DELETED,'N')='N' AND NVL(IST_STORNIERT,'N')='N' AND NVL(STATUS_BUCHUNG,0) NOT IN ("+ cWhereAnteilCB +") "+cWhereAnteilSelDatumAbnehmer);
		
		DEBUG.System_println(reclistFuhrenAlsLieferant.get_cQueryString());
		DEBUG.System_println(reclistFuhrenAlsAbnehmer.get_cQueryString());
		
		
		
		//die ergebnismenge wird in einen vector geschrieben (EK und VK und dann sortiert)
		Vector<RECORD_VPOS_TPA_FUHRE_own> vFuhrenUndOrte = new Vector<RECORD_VPOS_TPA_FUHRE_own>();
		
		boolean bHasEK = false;
		boolean bHasVK = false;
		
		for (int i=0;i<reclistFuhrenAlsLieferant.get_vKeyValues().size();i++)
		{
			vFuhrenUndOrte.add(new RECORD_VPOS_TPA_FUHRE_own(reclistFuhrenAlsLieferant.get(i), reclistFuhrenAlsLieferant.get(i).get_ANR1_EK_cUF_NN("")+"-"+reclistFuhrenAlsLieferant.get(i).get_ANR2_EK_cUF_NN(""), true));
			bHasEK = true;
		}
		for (int i=0;i<reclistFuhrenAlsAbnehmer.get_vKeyValues().size();i++)
		{
			vFuhrenUndOrte.add(new RECORD_VPOS_TPA_FUHRE_own(reclistFuhrenAlsAbnehmer.get(i), reclistFuhrenAlsAbnehmer.get(i).get_ANR1_VK_cUF_NN("")+"-"+reclistFuhrenAlsAbnehmer.get(i).get_ANR2_VK_cUF_NN(""), false));
			bHasVK = true;
		}
		
		
		//fuhrenorte:
		String cQueryOrteEK = "SELECT JT_VPOS_TPA_FUHRE_ORT.* FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT LEFT OUTER JOIN "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE ON (JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_TPA_FUHRE=JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE)" +
									"  WHERE " +
									" NVL(JT_VPOS_TPA_FUHRE_ORT.DELETED,'N')='N' AND " +
									" NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='N' AND " +
									" NVL(JT_VPOS_TPA_FUHRE.IST_STORNIERT,'N')='N' AND " +
									" NVL(JT_VPOS_TPA_FUHRE_ORT.DEF_QUELLE_ZIEL,'-')='EK' AND "+
									" NVL(JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG,0) NOT IN ("+ cWhereAnteilCB +") "+
									" AND JT_VPOS_TPA_FUHRE_ORT.ID_ADRESSE="+recADRESSE.get_ID_ADRESSE_cUF_NN("-1")+" "+
									cWhereAnteilSelDatumLieferant;
		
		//fuhrenorte:
		String cQueryOrteVK = "SELECT JT_VPOS_TPA_FUHRE_ORT.* FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT LEFT OUTER JOIN "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE ON (JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_TPA_FUHRE=JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE)" +
									"  WHERE " +
									" NVL(JT_VPOS_TPA_FUHRE_ORT.DELETED,'N')='N' AND " +
									" NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='N' AND " +
									" NVL(JT_VPOS_TPA_FUHRE.IST_STORNIERT,'N')='N' AND " +
									" NVL(JT_VPOS_TPA_FUHRE_ORT.DEF_QUELLE_ZIEL,'-')='VK' AND "+
									" NVL(JT_VPOS_TPA_FUHRE.STATUS_BUCHUNG,0) NOT IN ("+ cWhereAnteilCB +") "+
									" AND JT_VPOS_TPA_FUHRE_ORT.ID_ADRESSE="+recADRESSE.get_ID_ADRESSE_cUF_NN("-1")+" "+
									cWhereAnteilSelDatumAbnehmer;

		
//		DEBUG.System_println(cQueryOrteEK);
//		DEBUG.System_println(cQueryOrteVK);
		
		
		RECLIST_VPOS_TPA_FUHRE_ORT   reclistFuhrenOrteEK = new RECLIST_VPOS_TPA_FUHRE_ORT(cQueryOrteEK);
		RECLIST_VPOS_TPA_FUHRE_ORT   reclistFuhrenOrteVK = new RECLIST_VPOS_TPA_FUHRE_ORT(cQueryOrteVK);
		
		
		DEBUG.System_println(reclistFuhrenOrteEK.get_cQueryString());
		DEBUG.System_println(reclistFuhrenOrteVK.get_cQueryString());
		
		
		for (int i=0;i<reclistFuhrenOrteEK.get_vKeyValues().size();i++)
		{
			vFuhrenUndOrte.add(new RECORD_VPOS_TPA_FUHRE_own(reclistFuhrenOrteEK.get(i),reclistFuhrenOrteEK.get(i).get_ANR1_cUF_NN("")+"-"+reclistFuhrenOrteEK.get(i).get_ANR2_cUF_NN(""),true));
			bHasEK = true;
		}

		for (int i=0;i<reclistFuhrenOrteVK.get_vKeyValues().size();i++)
		{
			vFuhrenUndOrte.add(new RECORD_VPOS_TPA_FUHRE_own(reclistFuhrenOrteVK.get(i),reclistFuhrenOrteVK.get(i).get_ANR1_cUF_NN("")+"-"+reclistFuhrenOrteVK.get(i).get_ANR2_cUF_NN(""),false));
			bHasVK = true;
		}


		//jetzt den Vector vFuhrenUndOrte sortieren
		
		if (this.cSORTKEY.equals(INFO_BLOCK_Fuhren.SORTKEY_ANR1_2))
		{
			Collections.sort(vFuhrenUndOrte,new Comparator<RECORD_VPOS_TPA_FUHRE_own>() 
			{
				@Override
				public int compare(RECORD_VPOS_TPA_FUHRE_own o1,RECORD_VPOS_TPA_FUHRE_own o2) 
				{
					return (o1.ANR1_2+o1.get__DATUM_STRING_FUER_SORT()).compareTo((o2.ANR1_2+o2.get__DATUM_STRING_FUER_SORT()));
				}
			});
		}
		else if (this.cSORTKEY.equals(INFO_BLOCK_Fuhren.SORTKEY_DATUM))
		{
			Collections.sort(vFuhrenUndOrte,new Comparator<RECORD_VPOS_TPA_FUHRE_own>() 
			{
				@Override
				public int compare(RECORD_VPOS_TPA_FUHRE_own o1,RECORD_VPOS_TPA_FUHRE_own o2) 
				{
					return o1.get__DATUM_STRING_FUER_SORT().compareTo(o2.get__DATUM_STRING_FUER_SORT());
				}
			});
		} 
		else if (this.cSORTKEY.equals(INFO_BLOCK_Fuhren.SORTKEY_MENGE))
		{
			Collections.sort(vFuhrenUndOrte,new Comparator<RECORD_VPOS_TPA_FUHRE_own>() 
			{
				@Override
				public int compare(RECORD_VPOS_TPA_FUHRE_own o1,RECORD_VPOS_TPA_FUHRE_own o2) 
				{
					return o1.get__MENGE_FUER_SORT().compareTo(o2.get__MENGE_FUER_SORT());
				}
			});
		}
		else if (this.cSORTKEY.equals(INFO_BLOCK_Fuhren.SORTKEY_PREIS))
		{
			Collections.sort(vFuhrenUndOrte,new Comparator<RECORD_VPOS_TPA_FUHRE_own>() 
			{
				@Override
				public int compare(RECORD_VPOS_TPA_FUHRE_own o1,RECORD_VPOS_TPA_FUHRE_own o2) 
				{
					return o1.get__PREIS_FUER_SORT().compareTo(o2.get__PREIS_FUER_SORT());
				}
			});
		}
		else if (this.cSORTKEY.equals(INFO_BLOCK_Fuhren.SORTKEY_FIRMA))
		{
			Collections.sort(vFuhrenUndOrte,new Comparator<RECORD_VPOS_TPA_FUHRE_own>() 
			{
				@Override
				public int compare(RECORD_VPOS_TPA_FUHRE_own o1,RECORD_VPOS_TPA_FUHRE_own o2) 
				{
					return o1.get__FIRMA_GEGENSEITE_FUER_SORT().compareTo(o2.get__FIRMA_GEGENSEITE_FUER_SORT());
				}
			});
		}
		
		
		if (bHasEK && this.cb_6_EK.isSelected())
		{
			
			
			//ueberschrift
			this.add(new ownLabelRightTitel(" "));
			this.add(new ownSortButton(new MyE2_String("Lad.Dat."),INFO_BLOCK_Fuhren.SORTKEY_DATUM,true));
			this.add(new ownLabelRightTitel("Plan"));
			this.add(new ownSortButton(new MyE2_String("LadeM."),INFO_BLOCK_Fuhren.SORTKEY_MENGE,false));
			this.add(new ownSortButton(new MyE2_String("Preis"),INFO_BLOCK_Fuhren.SORTKEY_PREIS,false));    
			this.add(new ownSortButton(new MyE2_String("ANR1-2"),INFO_BLOCK_Fuhren.SORTKEY_ANR1_2,true));    
			this.add(new ownLabelLeftTitel("Artbez1"));
			this.add(new ownLabelRightTitel("Buch-Nr."));   															// NEU
			this.add(new ownLabelLeftTitel("DL"));          															// NEU
			this.add(new ownSortButton(new MyE2_String("Abnehmer"),INFO_BLOCK_Fuhren.SORTKEY_FIRMA,true));  
			this.add(new ownLabelLeftTitel("Zielort"));
			this.add(new FS__Adress_Info_Zentrum_JUMP_ADRESSE_NAVI_POPUP_TO_FUHRE(
										reclistFuhrenAlsLieferant.get_vKeyValues(), 
										this.oZentrum.get_oContainerToCloseAfterJump(),
										this.oZentrum.is_jump_is_active()));
			
			for (int i=0;i<vFuhrenUndOrte.size();i++)
			{
				RECORD_VPOS_TPA_FUHRE_own  recFuhre = vFuhrenUndOrte.get(i);
				String cADDonText = recFuhre.recORT!=null?" <ORT>":"";
			
				
				
				if (recFuhre.bEK)
				{

					//daten (vpos_rg) wenn vorhanden
					String cPreisAusRechnung = 	"";
					String cBuchungsNummer=	"";
					
					RECORD_VPOS_RG recVPos = recFuhre.recVPOS_RG_UNGELOESCHT_UNSTORNIERT(true);
					if (recVPos != null)
					{
						cPreisAusRechnung = recVPos.get_EINZELPREIS_FW_cF_NN("");
						//cLadeAbladeDat = recVPos.get_AUSFUEHRUNGSDATUM_cF_NN("");
						if (recVPos.get_UP_RECORD_VKOPF_RG_id_vkopf_rg()!=null)
						{
							cBuchungsNummer=recVPos.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().get_BUCHUNGSNUMMER_cF_NN("");
						}
					}
					
					Component[] oCompHelp = new Component[12];
					
					oCompHelp[0]=this.generateStatusFuhre(recFuhre.get_STATUS_BUCHUNG_cUF_NN("0"));
					//oCompHelp[]=new ownLabelLeft(cLadeAbladeDat,recFuhre);
					oCompHelp[1]=new ownLabelLeft(recFuhre.get__DATUM_STRING_FUER_LISTE(),recFuhre);
					oCompHelp[2]=new ownLabelRight(MyNumberFormater.formatDez( recFuhre.get_ANTEIL_PLANMENGE_LIEF_bdValue(BD0),0,true),recFuhre);
					oCompHelp[3]=new ownLabelRight(MyNumberFormater.formatDez( recFuhre.get_ANTEIL_LADEMENGE_LIEF_bdValue(BD0),0,true),recFuhre);
					oCompHelp[4]=new ownLabelRight(cPreisAusRechnung,recFuhre);
					oCompHelp[5]=new ownLabelLeft(recFuhre.ANR1_2,recFuhre);
					oCompHelp[6]=new ownLabelLeft(recFuhre.get_ARTBEZ1_EK_cF_NN("<Artbez1>"),recFuhre);
					oCompHelp[7]=new ownLabelRight(cBuchungsNummer,recFuhre);
					if (recVPos!=null && recVPos.get_UP_RECORD_VKOPF_RG_id_vkopf_rg()!=null && recVPos.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().is_ABGESCHLOSSEN_YES())
					{
						oCompHelp[8]=new ownDownButton(recVPos.get_UP_RECORD_VKOPF_RG_id_vkopf_rg(),recFuhre);
					}
					else
					{
						oCompHelp[8]=new ownLabelLeft(" ",recFuhre);	
					}
					oCompHelp[9]=new ownLabelLeft(recFuhre.get_A_NAME1_cF_NN("<Abnehmer>"+cADDonText),recFuhre);
					oCompHelp[10]=new ownLabelLeft(recFuhre.get_A_ORT_cF_NN("<Abladeort>")+cADDonText,recFuhre);
					oCompHelp[11]=new FS__Adress_Info_Zentrum_JUMP_ADRESSE_NAVI_POPUP_TO_FUHRE(
									bibALL.get_Vector(recFuhre.get_ID_VPOS_TPA_FUHRE_cUF()), 
									this.oZentrum.get_oContainerToCloseAfterJump(),
									this.oZentrum.is_jump_is_active());
					
					new FSI_HighLighter(this.oTF_Suche, oCompHelp);
					this.vComponentZeilen.add(oCompHelp);
					
					for (int k=0;k<oCompHelp.length;k++)
					{
						this.add(oCompHelp[k]);
					}
					
				}
			}
			
		}

		
		if (bHasVK && this.cb_7_VK.isSelected())
		{
			if (bHasEK && this.cb_6_EK.isSelected())
			{
				//trenner einbauen
				this.add(new Separator(),this.getSize(),E2_INSETS.I_1_1_1_1);
			}
			
			//ueberschrift
			this.add(new ownLabelRightTitel(" "));
			this.add(new ownSortButton(new MyE2_String("Abl.Dat."),INFO_BLOCK_Fuhren.SORTKEY_DATUM,true));
			this.add(new ownLabelRightTitel("Plan"));
			this.add(new ownSortButton(new MyE2_String("AbladeM."),INFO_BLOCK_Fuhren.SORTKEY_MENGE,false));
			this.add(new ownSortButton(new MyE2_String("Preis"),INFO_BLOCK_Fuhren.SORTKEY_PREIS,false));    
			this.add(new ownSortButton(new MyE2_String("ANR1-2"),INFO_BLOCK_Fuhren.SORTKEY_ANR1_2,true));    
			this.add(new ownLabelLeftTitel("Artbez1"));
			this.add(new ownLabelRightTitel("Buch-Nr."));       // NEU
			this.add(new ownLabelLeftTitel("DL"));               // NEU
			this.add(new ownSortButton(new MyE2_String("Lieferant"),INFO_BLOCK_Fuhren.SORTKEY_FIRMA,true));  
			this.add(new ownLabelLeftTitel("Herkunft Ort"));
			this.add(new FS__Adress_Info_Zentrum_JUMP_ADRESSE_NAVI_POPUP_TO_FUHRE(
										reclistFuhrenAlsAbnehmer.get_vKeyValues(), 
										this.oZentrum.get_oContainerToCloseAfterJump(),
										this.oZentrum.is_jump_is_active()));

			for (int i=0;i<vFuhrenUndOrte.size();i++)
			{
				RECORD_VPOS_TPA_FUHRE_own  recFuhre = vFuhrenUndOrte.get(i);
				String cADDonText = recFuhre.recORT!=null?" <ORT>":"";
				
				if (!recFuhre.bEK)
				{
					//daten (vpos_rg) wenn vorhanden
					String cPreisAusRechnung = 	"";
					String cLadeAbladeDat = 	recFuhre.get_DATUM_ABLADEN_cF_NN("");
					String cBuchungsNummer=	"";
					
					RECORD_VPOS_RG recVPos = recFuhre.recVPOS_RG_UNGELOESCHT_UNSTORNIERT(false);
					if (recVPos != null)
					{
						cPreisAusRechnung = recVPos.get_EINZELPREIS_FW_cF_NN("");
						//cLadeAbladeDat = recVPos.get_AUSFUEHRUNGSDATUM_cF_NN("");
						if (recVPos.get_UP_RECORD_VKOPF_RG_id_vkopf_rg()!=null)
						{
							cBuchungsNummer=recVPos.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().get_BUCHUNGSNUMMER_cF_NN("");
						}
					}
					
					Component[] oCompHelp = new Component[12];

					oCompHelp[0]=this.generateStatusFuhre(recFuhre.get_STATUS_BUCHUNG_cUF_NN("0"));
					//oCompHelp[]=new ownLabelLeft(cLadeAbladeDat,recFuhre);
					oCompHelp[1]=new ownLabelLeft(recFuhre.get__DATUM_STRING_FUER_LISTE(),recFuhre);
					oCompHelp[2]=new ownLabelRight(MyNumberFormater.formatDez( recFuhre.get_ANTEIL_PLANMENGE_ABN_bdValue(BD0),0,true),recFuhre);
					oCompHelp[3]=new ownLabelRight(MyNumberFormater.formatDez( recFuhre.get_ANTEIL_ABLADEMENGE_ABN_bdValue(BD0),0,true),recFuhre);
					oCompHelp[4]=new ownLabelRight(cPreisAusRechnung,recFuhre);
					oCompHelp[5]=new ownLabelLeft(recFuhre.ANR1_2,recFuhre);
					oCompHelp[6]=new ownLabelLeft(recFuhre.get_ARTBEZ1_VK_cF_NN("<Artbez1>"),recFuhre);
					oCompHelp[7]=new ownLabelRight(cBuchungsNummer,recFuhre);
					if (recVPos!=null && recVPos.get_UP_RECORD_VKOPF_RG_id_vkopf_rg()!=null && recVPos.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().is_ABGESCHLOSSEN_YES())
					{
						oCompHelp[8]=new ownDownButton(recVPos.get_UP_RECORD_VKOPF_RG_id_vkopf_rg(),recFuhre);
					}
					else
					{
						oCompHelp[8]=new ownLabelLeft(" ",recFuhre);	
					}
					oCompHelp[9]=new ownLabelLeft(recFuhre.get_L_NAME1_cF_NN("<Lieferant>"+cADDonText),recFuhre);
					oCompHelp[10]=new ownLabelLeft(recFuhre.get_L_ORT_cF_NN("<Ladeort>")+cADDonText,recFuhre);
					oCompHelp[11]=new FS__Adress_Info_Zentrum_JUMP_ADRESSE_NAVI_POPUP_TO_FUHRE(
										bibALL.get_Vector(recFuhre.get_ID_VPOS_TPA_FUHRE_cUF()), 
										this.oZentrum.get_oContainerToCloseAfterJump(),
										this.oZentrum.is_jump_is_active());
					
					new FSI_HighLighter(this.oTF_Suche, oCompHelp);
					
					for (int k=0;k<oCompHelp.length;k++)
					{
						this.add(oCompHelp[k]);
					}
					this.vComponentZeilen.add(oCompHelp);


				}
			}
		}
	}
	

	
	
	private MyE2_Label generateStatusFuhre(String cBuchungsstatus)
	{
		
		MyE2_Label labelHelp = null;
			
			
		if (cBuchungsstatus.equals(""+myCONST.STATUS_FUHRE__IST_STORNIERT))
		{
			labelHelp = new MyE2_Label(E2_ResourceIcon.get_RI("buchung_fuhre_status_storno.png"));
			labelHelp.setToolTipText(new MyE2_String("Die Fuhre wurde storniert und kann nicht gebucht werden !").CTrans());
		}
		else if (cBuchungsstatus.equals(""+myCONST.STATUS_FUHRE__IST_ALT_WID_NICHT_BEGUCHT))
		{
			labelHelp = new MyE2_Label(E2_ResourceIcon.get_RI("buchung_vollstaendig_weil_alt.png"));
			labelHelp.setToolTipText(new MyE2_String("Die Fuhre ist aus dem Archivbestand und kann nicht gebucht werden !").CTrans());
		}
		else if (cBuchungsstatus.equals(""+myCONST.STATUS_FUHRE__EINGABE_IST_NOCH_NICHT_FERTIG))
		{
			labelHelp = new MyE2_Label(E2_ResourceIcon.get_RI("buchung_fuhre_ist_unvollstaendig.png"));
			labelHelp.setToolTipText(new MyE2_String("Die Fuhre ist noch nicht komplett ausgefüllt ").CTrans());
		}
		else if (cBuchungsstatus.equals(""+myCONST.STATUS_FUHRE__UNGEBUCHT))
		{	
			labelHelp = new MyE2_Label(E2_ResourceIcon.get_RI("buchung_fuhre_ungebucht.png"));
			labelHelp.setToolTipText(new MyE2_String("Die Fuhre kann in die freien Positionen überführt werden!").CTrans());
		}
		else if (cBuchungsstatus.equals(""+myCONST.STATUS_FUHRE__BESITZT_KEINE_BUCHUNGSPOS))
		{
			labelHelp = new MyE2_Label(E2_ResourceIcon.get_RI("buchung_hat_keine_buchungspositionen.png"));
			labelHelp.setToolTipText(new MyE2_String("Die Fuhre hat keine Buchungs-Positionen: kann nicht gebucht werden !").CTrans());
		}
		else if (cBuchungsstatus.equals(""+myCONST.STATUS_FUHRE__GANZGEBUCHT))
		{
			labelHelp = new MyE2_Label(E2_ResourceIcon.get_RI("buchung_vollstaendig.png"));
			labelHelp.setToolTipText(new MyE2_String("Die Fuhre ist vollständig verbucht !").CTrans());
		}
		else if (cBuchungsstatus.equals(""+myCONST.STATUS_FUHRE__TEILSGEBUCHT))
		{
			labelHelp = new MyE2_Label(E2_ResourceIcon.get_RI("buchung_teilweise.png"));
			labelHelp.setToolTipText(new MyE2_String("Die Fuhre ist teilweise verbucht!").CTrans());
		}
		else
		{
			labelHelp = new MyE2_Label(E2_ResourceIcon.get_RI("listlabel_empty.png"));
			labelHelp.setToolTipText(new MyE2_String("Buchungsstatus der Fuhre ist unbestimmt").CTrans());
		}


		return labelHelp;
	}

	
	public String get_Status_der_Selektoren() throws myException
	{
		
		String cRueck = "";
		cRueck += (this.cb_0_Storniert.isSelected()?"Y":"N");
		cRueck += "|";
		cRueck += (this.cb_1_Alt.isSelected()?"Y":"N");
		cRueck += "|";
		cRueck += (this.cb_2_Unvollstaendig.isSelected()?"Y":"N");
		cRueck += "|";
		cRueck += (this.cb_3_OhneBuchPos.isSelected()?"Y":"N");
		cRueck += "|";
		cRueck += (this.cb_4_Ungebucht.isSelected()?"Y":"N");
		cRueck += "|";
		cRueck += (this.cb_5_Teilgebucht.isSelected()?"Y":"N");
		cRueck += "|";
		cRueck += (this.cb_6_Ganzgebucht.isSelected()?"Y":"N");
		cRueck += "|";
		cRueck += S.NN(this.o_SelField_ab.get_ActualWert());
		cRueck += "|";
		cRueck += (this.cb_6_EK.isSelected()?"Y":"N");
		cRueck += "|";
		cRueck += (this.cb_7_VK.isSelected()?"Y":"N");
		cRueck += "|";
		cRueck += (this.oTF_Suche.getText());
		cRueck += "|";
		cRueck += this.cSORTKEY;
		cRueck += "|";
		cRueck += S.NN(this.o_SelField_bis.get_ActualWert());
		cRueck += "|";
		
		return cRueck;
	}

	
	
	private void restore_status_der_selektionen() throws myException
	{
		//hier wird die einstellung gleich in dieser methode gemacht, rueckgabe ist unnoetig
		String cDatabaseSetting = (String)new E2_UserSettings_Checkbox_und_Selektoren().get_Settings(FS__Adress_Info_Zentrum.STORE_MODUL_KENNER);
		
		if (cDatabaseSetting!=null)
		{
			Vector<String> vWerte= bibALL.TrenneZeile(cDatabaseSetting, "|");
			
			if (vWerte.size()>0) cb_0_Storniert.setSelected(vWerte.get(0).equals("Y"));
			if (vWerte.size()>1) cb_1_Alt.setSelected(vWerte.get(1).equals("Y"));
			if (vWerte.size()>2) cb_2_Unvollstaendig.setSelected(vWerte.get(2).equals("Y"));
			if (vWerte.size()>3) cb_3_OhneBuchPos.setSelected(vWerte.get(3).equals("Y"));
			if (vWerte.size()>4) cb_4_Ungebucht.setSelected(vWerte.get(4).equals("Y"));
			if (vWerte.size()>5) cb_5_Teilgebucht.setSelected(vWerte.get(5).equals("Y"));
			if (vWerte.size()>6) cb_6_Ganzgebucht.setSelected(vWerte.get(6).equals("Y"));
			if (vWerte.size()>7) o_SelField_ab.set_ActiveValue_OR_FirstValue(vWerte.get(7));
			if (vWerte.size()>8) cb_6_EK.setSelected(vWerte.get(8).equals("Y"));
			if (vWerte.size()>9) cb_7_VK.setSelected(vWerte.get(9).equals("Y"));
			if (vWerte.size()>10) this.oTF_Suche.setText(vWerte.get(10));
			if (vWerte.size()>11) this.cSORTKEY=vWerte.get(11);
			
			//steht hinten, weil am schluss angefuegt
			if (vWerte.size()>12) o_SelField_bis.set_ActiveValue_OR_FirstValue(vWerte.get(12));
			
		}
	}


	
	private class actionAgentkontrolliereDatumsFelder extends XX_ActionAgent
	{
		private MyE2_SelectField_month_year  o_SelField_ab = null;
		private MyE2_SelectField_month_year  o_SelField_bis = null;
		
		public actionAgentkontrolliereDatumsFelder( 	MyE2_SelectField_month_year SelField_ab,
														MyE2_SelectField_month_year SelField_bis) 
		{
			super();
			this.o_SelField_ab = 	SelField_ab;
			this.o_SelField_bis = 	SelField_bis;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			MyE2_SelectField_month_year  oActionField = (MyE2_SelectField_month_year)oExecInfo.get_MyActionEvent().getSource();
			
			if (oActionField==this.o_SelField_ab)
			{
				if (this.o_SelField_bis.get_ActualWert().compareTo(this.o_SelField_ab.get_ActualWert())<0)
				{
					this.o_SelField_bis.set_ActiveValue_OR_FirstValue(this.o_SelField_ab.get_ActualWert());
				}
			}
			else
			{
				if (this.o_SelField_bis.get_ActualWert().compareTo(this.o_SelField_ab.get_ActualWert())<0)
				{
					this.o_SelField_ab.set_ActiveValue_OR_FirstValue(this.o_SelField_bis.get_ActualWert());
				}
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
		gridRueck.add(oContainerEx);
		
		return gridRueck;
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
	
	private class actionSaveStatus extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			new E2_UserSettings_Checkbox_und_Selektoren().STORE(FS__Adress_Info_Zentrum.STORE_MODUL_KENNER, INFO_BLOCK_Fuhren.this.get_Status_der_Selektoren());
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

	
	
	private class ownLabelLeft extends FSI_Label 
	{
		public RECORD_VPOS_TPA_FUHRE_own recFuhre = null;
		
		public ownLabelLeft(String cWert,RECORD_VPOS_TPA_FUHRE_own  rec_Fuhre) 
		{
			super(S.NN(cWert), 0);
			this.recFuhre=rec_Fuhre;
		}
	}
	
	private class ownLabelRight extends FSI_Label 
	{
		public RECORD_VPOS_TPA_FUHRE_own recFuhre = null;

		public ownLabelRight(String cWert,RECORD_VPOS_TPA_FUHRE_own  rec_Fuhre) 
		{
			super(S.NN(cWert), 2);
			this.recFuhre=rec_Fuhre;
		}
	}

	private class ownDownButton extends MyE2_Button 
	{
		public RECORD_VPOS_TPA_FUHRE_own recFuhre = null;
		private RECORD_VKOPF_RG          recVKOPF_RG = null;

		public ownDownButton(RECORD_VKOPF_RG rec_VKOPF_RG,RECORD_VPOS_TPA_FUHRE_own  rec_Fuhre) 
		{
			super(E2_ResourceIcon.get_RI("down.png"), true);
			this.recFuhre=rec_Fuhre;
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

	

	private class ownLabelLeftTitel extends MyE2_Label 
	{

		public ownLabelLeftTitel(String cWert) 
		{
			super(S.NN(cWert), MyE2_Label.STYLE_SMALL_PLAIN());
			GridLayoutData Gl = MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I_4_2_4_0);
			Gl.setBackground(new E2_ColorDDark());
			this.setLayoutData(Gl);
		}
		
	}
	
	private class ownLabelRightTitel extends MyE2_Label 
	{

		public ownLabelRightTitel(String cWert) 
		{
			super(S.NN(cWert), MyE2_Label.STYLE_SMALL_PLAIN());
			GridLayoutData Gl = MyE2_Grid.LAYOUT_RIGHT_TOP(E2_INSETS.I_4_2_4_0);
			Gl.setBackground(new E2_ColorDDark());
			this.setLayoutData(Gl);
		}
		
	}

	
	private class ownSortButton extends MyE2_Button
	{
		private String cSortKey = null;
		public ownSortButton(MyE2_String cText, String SortKey, boolean bLeft)
		{
			super(cText);
			this.cSortKey=SortKey;
			
			this.setStyle(MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder(new E2_FontPlain(-2)));
			GridLayoutData Gl = MyE2_Grid.LAYOUT_RIGHT_TOP(E2_INSETS.I_4_2_4_0);
			if (bLeft)
			{
				Gl = MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I_4_2_4_0);
			}
			Gl.setBackground(new E2_ColorDDark());
			this.setLayoutData(Gl);
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException
				{
					INFO_BLOCK_Fuhren.this.cSORTKEY=ownSortButton.this.cSortKey;
					new E2_UserSettings_Checkbox_und_Selektoren().STORE(FS__Adress_Info_Zentrum.STORE_MODUL_KENNER, INFO_BLOCK_Fuhren.this.get_Status_der_Selektoren());
					INFO_BLOCK_Fuhren.this.__aufbau();
				}
			});
		}
	}

	
	
	public class actionNeubauFuhrenGrid extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			INFO_BLOCK_Fuhren.this.__aufbau();
		}
	}


	
	private class RECORD_VPOS_TPA_FUHRE_own extends RECORD_VPOS_TPA_FUHRE
	{
		public RECORD_VPOS_TPA_FUHRE_ORT    recORT = null;
		public String   					ANR1_2 = "";
		public boolean   					bEK = false;

		public RECORD_VPOS_TPA_FUHRE_own(RECORD_VPOS_TPA_FUHRE_ORT rec_ORT, String _ANR1_2, boolean EK) throws myException 
		{
			super(rec_ORT.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre());
			this.recORT = rec_ORT;
			this.ANR1_2 = _ANR1_2;
			this.bEK = EK;
			
		}
		
		public RECORD_VPOS_TPA_FUHRE_own(RECORD_VPOS_TPA_FUHRE rec_Fuhre, String _ANR1_2, boolean EK) throws myException 
		{
			super(rec_Fuhre);
			this.ANR1_2 = _ANR1_2;
			this.bEK = EK;
		}
		
		
		public String get_ARTBEZ1_EK_cF_NN(String cNotNullValue) throws myException
		{
			if (this.recORT==null)
			{
				return super.get_ARTBEZ1_EK_cF_NN(cNotNullValue);
			}
			else
			{
				return this.recORT.get_ARTBEZ1_cF_NN(cNotNullValue);
			}
		}


		
		public String get_ARTBEZ1_VK_cF_NN(String cNotNullValue) throws myException
		{
			if (this.recORT==null)
			{
				return super.get_ARTBEZ1_VK_cF_NN(cNotNullValue);
			}
			else
			{
				return this.recORT.get_ARTBEZ1_cF_NN(cNotNullValue);
			}
		}




		public BigDecimal get_ANTEIL_PLANMENGE_LIEF_bdValue(BigDecimal bdValueWhenNULL) throws myException
		{
			if (this.recORT==null)
			{
				return super.get_ANTEIL_PLANMENGE_LIEF_bdValue(bdValueWhenNULL);
			}
			else
			{
				return this.recORT.get_ANTEIL_PLANMENGE_bdValue(bdValueWhenNULL);
			}
		}

		
		public BigDecimal get_ANTEIL_PLANMENGE_ABN_bdValue(BigDecimal bdValueWhenNULL) throws myException
		{
			if (this.recORT==null)
			{
				return super.get_ANTEIL_PLANMENGE_ABN_bdValue(bdValueWhenNULL);
			}
			else
			{
				return this.recORT.get_ANTEIL_PLANMENGE_bdValue(bdValueWhenNULL);
			}
		}

		
		public BigDecimal get__MENGE_FUER_SORT()
		{
			BigDecimal bdRueck = BD0; 
			try
			{
				if (this.bEK)
				{
					bdRueck = this.get_ANTEIL_LADEMENGE_LIEF_bdValue(BD0);
				}
				else
				{
					bdRueck = this.get_ANTEIL_ABLADEMENGE_ABN_bdValue(BD0);
				}
			}
			catch (myException e)
			{
				e.printStackTrace();
			}
			return bdRueck;
		}

		
		public BigDecimal get__PREIS_FUER_SORT()
		{
			BigDecimal bdRueck = BD0; 
			try
			{
				if (this.bEK)
				{
					if (this.recORT==null)
					{
						bdRueck = this.get_EINZELPREIS_EK_bdValue(BD0);
					}
					else
					{
						bdRueck = this.recORT.get_EINZELPREIS_bdValue(BD0);
					}
				}
				else
				{
					if (this.recORT==null)
					{
						bdRueck = this.get_EINZELPREIS_VK_bdValue(BD0);
					}
					else
					{
						bdRueck = this.recORT.get_EINZELPREIS_bdValue(BD0);
					}
				}
			}
			catch (myException e)
			{
				e.printStackTrace();
			}
			return bdRueck;
		}

		
		
		public String get__FIRMA_GEGENSEITE_FUER_SORT()
		{
			String cRueck = ""; 
			try
			{
				if (this.bEK)
				{
					cRueck = this.get_A_NAME1_cUF_NN("");   //bei ek-fuhren wird abnehmer angezeigt
				}
				else
				{
					cRueck = this.get_L_NAME1_cUF_NN("");   //bei vk-fuhren wird lieferant angezeigt
				}
			}
			catch (myException e)
			{
				e.printStackTrace();
			}
			return cRueck;
		}

		
		
		
		public BigDecimal get_ANTEIL_LADEMENGE_LIEF_bdValue(BigDecimal bdValueWhenNULL) throws myException
		{
			if (this.recORT==null)
			{
				return super.get_ANTEIL_LADEMENGE_LIEF_bdValue(bdValueWhenNULL);
			}
			else
			{
				return this.recORT.get_ANTEIL_LADEMENGE_bdValue(bdValueWhenNULL);
			}
		}

		
		public BigDecimal get_ANTEIL_ABLADEMENGE_ABN_bdValue(BigDecimal bdValueWhenNULL) throws myException
		{
			if (this.recORT==null)
			{
				return super.get_ANTEIL_ABLADEMENGE_ABN_bdValue(bdValueWhenNULL);
			}
			else
			{
				return this.recORT.get_ANTEIL_ABLADEMENGE_bdValue(bdValueWhenNULL);
			}
		}

		
		
		public RECORD_VPOS_RG  recVPOS_RG_UNGELOESCHT_UNSTORNIERT(boolean bEK) throws myException
		{
			RECORD_VPOS_RG  rgRueck = null;
			
			String cWhereZusatz = " AND LAGER_VORZEICHEN=1 ";
			
			if (!bEK)
			{
				cWhereZusatz = " AND LAGER_VORZEICHEN=-1 ";
			}
			
			if (this.recORT==null)
			{
				RECLIST_VPOS_RG recListRG = this.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord("NVL(JT_VPOS_RG.DELETED,'N')='N' AND JT_VPOS_RG.ID_VPOS_TPA_FUHRE_ORT_ZUGEORD IS NULL"+cWhereZusatz, "JT_VPOS_RG.ID_VPOS_RG", true);
				
				//den letzten raussuchen
				if (recListRG.get_vKeyValues().size()>0)
				{
					rgRueck = recListRG.get(recListRG.get_vKeyValues().size()-1);
					
					if (S.isFull(rgRueck.get_ID_VPOS_RG_STORNO_VORGAENGER_cUF_NN("")) || 
						S.isFull(rgRueck.get_ID_VPOS_RG_STORNO_NACHFOLGER_cUF_NN("")))
					{
						rgRueck=null;   //nur den letzten zurueckgeben, wenn er keine storno-merkmale hat
					}
				}
				
			}
			else
			{
				RECLIST_VPOS_RG recListRG = this.recORT.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_ort_zugeord("NVL(JT_VPOS_RG.DELETED,'N')='N'"+cWhereZusatz, "JT_VPOS_RG.ID_VPOS_RG", true);
				
				//den letzten raussuchen
				if (recListRG.get_vKeyValues().size()>0)
				{
					rgRueck = recListRG.get(recListRG.get_vKeyValues().size()-1);
					
					if (S.isFull(rgRueck.get_ID_VPOS_RG_STORNO_VORGAENGER_cUF_NN("")) || 
						S.isFull(rgRueck.get_ID_VPOS_RG_STORNO_NACHFOLGER_cUF_NN("")))
					{
						rgRueck=null;   //nur den letzten zurueckgeben, wenn er keine storno-merkmale hat
					}
				}
			}
			
			return rgRueck;
		}
		
		
		
		public String get_DATUM_AUFLADEN_cF_NN(String cNotNullValue) throws myException
		{
			if (this.recORT==null)
			{
				return super.get_DATUM_AUFLADEN_cF_NN(cNotNullValue);
			}
			else
			{
				return this.recORT.get_DATUM_LADE_ABLADE_cF_NN(cNotNullValue);
			}
		}

		public String get_DATUM_ABLADEN_cF_NN(String cNotNullValue) throws myException
		{
			if (this.recORT==null)
			{
				return super.get_DATUM_ABLADEN_cF_NN(cNotNullValue);
			}
			else
			{
				return this.recORT.get_DATUM_LADE_ABLADE_cF_NN(cNotNullValue);
			}
		}
		
		public String get__DATUM_STRING_FUER_LISTE() throws myException
		{
			String cRueck = "";
			
			if (this.bEK)
			{
				if (this.recORT==null)
				{
					cRueck = super.get_DATUM_AUFLADEN_cF_NN(super.get_DATUM_ABHOLUNG_cF_NN("--")+" *");
				}
				else
				{
					cRueck = this.recORT.get_DATUM_LADE_ABLADE_cF_NN(super.get_DATUM_ABHOLUNG_cF_NN("--")+" *");
				}
			}
			else
			{
				if (this.recORT==null)
				{
					cRueck = super.get_DATUM_ABLADEN_cF_NN(super.get_DATUM_ANLIEFERUNG_cF_NN("--")+" *");
				}
				else
				{
					cRueck = this.recORT.get_DATUM_LADE_ABLADE_cF_NN(super.get_DATUM_ANLIEFERUNG_cF_NN("--")+" *");
				}
			}
			if (cRueck.equals("-- *"))
			{
				cRueck = "-";
			}
			return cRueck;
		}
		

		public String get__DATUM_STRING_FUER_SORT()
		{
			String cRueck = "";
			
			try
			{
				if (this.bEK)
				{
					if (this.recORT==null)
					{
						cRueck = super.get_DATUM_AUFLADEN_VALUE_FOR_SQLSTATEMENT();
						if (cRueck.trim().equals("NULL"))
						{
							cRueck = super.get_DATUM_ABHOLUNG_VALUE_FOR_SQLSTATEMENT();
						}
					}
					else
					{
						cRueck = this.recORT.get_DATUM_LADE_ABLADE_VALUE_FOR_SQLSTATEMENT();
						if (cRueck.trim().equals("NULL"))
						{
							cRueck = super.get_DATUM_ABHOLUNG_VALUE_FOR_SQLSTATEMENT();
						}
					}
				}
				else
				{
					if (this.recORT==null)
					{
						cRueck = super.get_DATUM_ABLADEN_VALUE_FOR_SQLSTATEMENT();
						if (cRueck.trim().equals("NULL"))
						{
							cRueck = super.get_DATUM_ANLIEFERUNG_VALUE_FOR_SQLSTATEMENT();
						}
					}
					else
					{
						cRueck = this.recORT.get_DATUM_LADE_ABLADE_VALUE_FOR_SQLSTATEMENT();
						if (cRueck.trim().equals("NULL"))
						{
							cRueck = super.get_DATUM_ANLIEFERUNG_VALUE_FOR_SQLSTATEMENT();
						}
					}
				}
			}
			catch (myException e)
			{
				e.printStackTrace();
			}
			if (cRueck.trim().equals("NULL"))   //ganz nach hinten
			{
				cRueck = "'9999-99-99'";
			}
			return cRueck;
		}

		
		
	}
	
	
	
	public class actionCheckHighlight extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			INFO_BLOCK_Fuhren oThis = INFO_BLOCK_Fuhren.this;
			
			for (int i=0;i<oThis.vComponentZeilen.size();i++)
			{
				new FSI_HighLighter(oThis.oTF_Suche, oThis.vComponentZeilen.get(i));
			}
			
		}
	}

	

}

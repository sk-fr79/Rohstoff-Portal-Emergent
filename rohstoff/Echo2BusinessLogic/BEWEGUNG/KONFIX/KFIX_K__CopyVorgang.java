package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_RG;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_RG;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF_HashMap;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_VKOPF_KON;
import rohstoff.utils.VorgangTableNames;

public class KFIX_K__CopyVorgang
{
	private String 				cID_VORGANGSKOPF = null;
	
	private MyDBToolBox			oDB = null;
	
	private VorgangTableNames 	oTableQuelle = null;
	private VorgangTableNames 	oTableZiel=null;
	private Vector<String>		vSQL_STACK_ForCopy = new Vector<String>();
	
	private HashMap<String, String>  hmKopfErsatzVonOben = null;
	private HashMap<String, String>  hmPosErsatzVonOben = null;
	private HashMap<String, String>  hmZusatzErsatzVonOben = null;
	
	private boolean 				is_fixierungskontrakte = false;
	
	protected void finalize()
	{
		bibALL.destroy_myDBToolBox(this.oDB);
	}
	
	/**
	 * 
	 * @param cid_vorgangskopf
	 * @param TableQuelle
	 * @param TableZiel
	 * @param hm_KopfErsatzVonOben
	 * @param hm_PosErsatzVonOben
	 * @param hm_ZusatzErsatzVonOben
	 * @throws myException
	 */
	public KFIX_K__CopyVorgang(	String 				cid_vorgangskopf, 
							VorgangTableNames 	TableQuelle, 
							VorgangTableNames 	TableZiel, 
							HashMap<String, String> hm_KopfErsatzVonOben, 
							HashMap<String, String> hm_PosErsatzVonOben, 
							HashMap<String, String> hm_ZusatzErsatzVonOben) throws myException
	{
		super();
		cID_VORGANGSKOPF = cid_vorgangskopf;
		
		this.oTableQuelle = TableQuelle;
		this.oTableZiel = TableZiel;
		
		if (this.oTableZiel == null)
			this.oTableZiel = this.oTableQuelle;
			
		this.hmKopfErsatzVonOben = hm_KopfErsatzVonOben;
		this.hmPosErsatzVonOben = hm_PosErsatzVonOben;
		this.hmZusatzErsatzVonOben = hm_ZusatzErsatzVonOben;
		
		this.oDB = bibALL.get_myDBToolBox();
		
		is_fixierungskontrakte = new Rec20_VKOPF_KON(new Rec20(_TAB.vkopf_kon)._fill_id(cID_VORGANGSKOPF)).is_fixierungsKontrakte();
		
		// feststellen welche positionen der kopf hat
		String cSQL_QueryPos = "SELECT "+TableQuelle.get_cVPOS_PK()+" FROM "+bibE2.cTO()+"."+TableQuelle.get_cVPOS_TAB()+" WHERE "+
								TableQuelle.get_cVKOPF_PK()+"="+cid_vorgangskopf+" AND   NVL(DELETED,'N')='N'";
		
		
		String[][] cID_POS = this.oDB.EinzelAbfrageInArray(cSQL_QueryPos);
		
		if (cID_POS == null)
			throw new myException("BS_CopyVorgang:Error Query Positions: "+cSQL_QueryPos);
		
		String[] cID_POS_ZUSATZ = new String[cID_POS.length];
		for (int i=0;i<cID_POS_ZUSATZ.length;i++)
			cID_POS_ZUSATZ[i]=null;

		
		if (this.oTableQuelle==this.oTableZiel)
			if (! bibALL.isEmpty(TableQuelle.get_cVPOS_ZUSATZ_TAB()))
			{
				String cQueryPosZusatz = "SELECT "+this.oTableQuelle.get_cVPOS_ZUSATZ_TAB_PK()+" FROM "+
													bibE2.cTO()+"."+this.oTableQuelle.get_cVPOS_ZUSATZ_TAB()+" WHERE "+
													this.oTableQuelle.get_cVPOS_PK()+"=";
				
				for (int i=0;i<cID_POS.length;i++)
				{
					String ccID_POS_ZUSATZ = this.oDB.EinzelAbfrage(cQueryPosZusatz+cID_POS[i][0]);
					if (!bibALL.isInteger(ccID_POS_ZUSATZ))
						throw new myException("BS_CopyVorgang:Error Query Positions: "+cQueryPosZusatz+cID_POS[i][0]);
					
					cID_POS_ZUSATZ[i]=ccID_POS_ZUSATZ;
				}
			}
		
		HashMap<String,String> hmErsatzKopf = new HashMap<String,String>();
		HashMap<String,String> hmErsatzPOS = new HashMap<String,String>();
		HashMap<String,String> hmErsatzZusatz = null;
		

		if (TableQuelle.get_cVORGANG_TYP().equals(myCONST.VORGANGSART_EK_KONTRAKT) || TableQuelle.get_cVORGANG_TYP().equals(myCONST.VORGANGSART_VK_KONTRAKT))
		{
			KFIX_K__CopyVorgang._WRITE_ERSATZFIELDS_IN_KOPF_KON(hmErsatzKopf,this.oTableZiel);   //kleine unterschiede / user wird auch kopiert
		}
		else
		{
			KFIX_K__CopyVorgang._WRITE_ERSATZFIELDS_IN_KOPF(hmErsatzKopf,this.oTableZiel);
		}
		
		KFIX_K__CopyVorgang._WRITE_ERSATZFIELDS_IN_POS(hmErsatzPOS,true,this.oTableZiel);
		
		// zusatztabelle gibts bei kontrakt, transportauftrag und angebot
		if (this.oTableQuelle==this.oTableZiel)
		{
			if (this.oTableQuelle.get_cVORGANG_TYP().equals(myCONST.VORGANGSART_EK_KONTRAKT) ||
				this.oTableQuelle.get_cVORGANG_TYP().equals(myCONST.VORGANGSART_VK_KONTRAKT))
			{
				hmErsatzZusatz = new HashMap<String,String>();
				KFIX_K__CopyVorgang._WRITE_ERSATZFIELDS_IN_POS_KON(hmErsatzZusatz,this.oTableQuelle);
			}
//			else if (this.oTableQuelle.get_cVORGANG_TYP().equals(myCONST.VORGANGSART_TRANSPORT))
//			{
//				hmErsatzZusatz = new HashMap<String,String>();
//				BS_CopyVorgang._WRITE_ERSATZFIELDS_IN_POS_TPA_FUHRE(hmErsatzZusatz,this.oTableQuelle);
//			}
			if (this.oTableQuelle.get_cVORGANG_TYP().equals(myCONST.VORGANGSART_ABNAHMEANGEBOT) ||
				this.oTableQuelle.get_cVORGANG_TYP().equals(myCONST.VORGANGSART_ANGEBOT))
			{
				hmErsatzZusatz = new HashMap<String,String>();
				KFIX_K__CopyVorgang._WRITE_ERSATZFIELDS_IN_POS_STD_ANGEBOT(hmErsatzZusatz,this.oTableQuelle);
			}
		}
		
		
		
		
		try 
		{
			HashMap<String,String> hmBlock_VKOPF_ZIEL = 		new MyMetaFieldDEF_HashMap(this.oTableZiel.get_cVKOPF_TAB()).get_hmFields();
			HashMap<String,String> hmBlock_VPOS_ZIEL = 			new MyMetaFieldDEF_HashMap(this.oTableZiel.get_cVPOS_TAB()).get_hmFields();
			HashMap<String,String> hmBlock_VPOS_ZIEL_ZUSATZ = 	null;

			hmBlock_VKOPF_ZIEL.putAll(hmErsatzKopf);
			hmBlock_VPOS_ZIEL.putAll(hmErsatzPOS);
			
			if (this.hmKopfErsatzVonOben != null) hmBlock_VKOPF_ZIEL.putAll(this.hmKopfErsatzVonOben );
			if (this.hmPosErsatzVonOben != null) hmBlock_VPOS_ZIEL.putAll(this.hmPosErsatzVonOben );
			
			Vector<String> vFieldBlock = bibALL.get_vBuildKeyVectorFromHashmap(hmBlock_VKOPF_ZIEL);
			Vector<String> vValueBlock = bibALL.get_vBuildValueVectorFromHashmap(hmBlock_VKOPF_ZIEL);

			Vector<String> vFieldBlockPos = bibALL.get_vBuildKeyVectorFromHashmap(hmBlock_VPOS_ZIEL);
			Vector<String> vValueBlockPos = bibALL.get_vBuildValueVectorFromHashmap(hmBlock_VPOS_ZIEL);

			Vector<String> vFieldBlockPosZusatz = null;
			Vector<String> vValueBlockPosZusatz = null;
			
			if (hmErsatzZusatz != null) //fuer alle, ausser TPA, dort wird das in der pos-schleife gemacht
			{
				hmBlock_VPOS_ZIEL_ZUSATZ = 	new MyMetaFieldDEF_HashMap(this.oTableZiel.get_cVPOS_ZUSATZ_TAB()).get_hmFields();
				hmBlock_VPOS_ZIEL_ZUSATZ.putAll(hmErsatzZusatz);
				if (this.hmZusatzErsatzVonOben != null) hmBlock_VPOS_ZIEL_ZUSATZ.putAll(this.hmZusatzErsatzVonOben );
				
				vFieldBlockPosZusatz = bibALL.get_vBuildKeyVectorFromHashmap(hmBlock_VPOS_ZIEL_ZUSATZ);
				vValueBlockPosZusatz = bibALL.get_vBuildValueVectorFromHashmap(hmBlock_VPOS_ZIEL_ZUSATZ);
			}

			
			String 	cSQLKopf = "INSERT INTO "+bibE2.cTO()+"."+this.oTableZiel.get_cVKOPF_TAB()+"("+bibALL.Concatenate(vFieldBlock,",", null)+")" +
								" SELECT "+bibALL.Concatenate(vValueBlock,",", null)+" FROM "+bibE2.cTO()+"."+this.oTableQuelle.get_cVKOPF_TAB()+" WHERE "+
								this.oTableQuelle.get_cVKOPF_PK()+"="+this.cID_VORGANGSKOPF;
			
			vSQL_STACK_ForCopy = new Vector<String>();
			vSQL_STACK_ForCopy.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+cSQLKopf);
			if(! is_fixierungskontrakte){
				// jetzt die positionen
				for (int i=0;i<cID_POS.length;i++)
				{
					//2011-01-06: sonderbehandlung fuer tpas/fuhren, die sonderfelder werden individuell gesetzt
					if (this.oTableQuelle.get_cVORGANG_TYP().equals(myCONST.VORGANGSART_TRANSPORT))
					{
						hmErsatzZusatz = new HashMap<String,String>();
						KFIX_K__CopyVorgang._WRITE_ERSATZFIELDS_IN_POS_TPA_FUHRE(hmErsatzZusatz,this.oTableQuelle, new RECORD_VPOS_TPA(cID_POS[i][0]).get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0));
						
						hmBlock_VPOS_ZIEL_ZUSATZ = 	new MyMetaFieldDEF_HashMap(this.oTableZiel.get_cVPOS_ZUSATZ_TAB()).get_hmFields();
						hmBlock_VPOS_ZIEL_ZUSATZ.putAll(hmErsatzZusatz);
						if (this.hmZusatzErsatzVonOben != null) hmBlock_VPOS_ZIEL_ZUSATZ.putAll(this.hmZusatzErsatzVonOben );
						
						vFieldBlockPosZusatz = bibALL.get_vBuildKeyVectorFromHashmap(hmBlock_VPOS_ZIEL_ZUSATZ);
						vValueBlockPosZusatz = bibALL.get_vBuildValueVectorFromHashmap(hmBlock_VPOS_ZIEL_ZUSATZ);
					}
	
					
					String 	cSQLPos = "INSERT INTO "+bibE2.cTO()+"."+this.oTableZiel.get_cVPOS_TAB()+"("+bibALL.Concatenate(vFieldBlockPos,",", null)+")"+
									" SELECT "+bibALL.Concatenate(vValueBlockPos,",", null)+" FROM "+bibE2.cTO()+"."+this.oTableQuelle.get_cVPOS_TAB()+" WHERE NVL(DELETED,'N')='N' AND "+
									this.oTableQuelle.get_cVPOS_PK()+"="+cID_POS[i][0]+ "  ";
					
					vSQL_STACK_ForCopy.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+cSQLPos);
					// falls es eine zusatztabelle gibt, dann jetzt einfuehren
					if (hmErsatzZusatz!=null)
					{
						String 	cSQLPosZusatz = "INSERT INTO "+bibE2.cTO()+"."+this.oTableZiel.get_cVPOS_ZUSATZ_TAB()+"("+bibALL.Concatenate(vFieldBlockPosZusatz,",", null)+")"+
													" SELECT "+bibALL.Concatenate(vValueBlockPosZusatz,",", null)+" FROM "+bibE2.cTO()+"."+this.oTableQuelle.get_cVPOS_ZUSATZ_TAB()+" WHERE "+
													this.oTableQuelle.get_cVPOS_ZUSATZ_TAB_PK()+"="+cID_POS_ZUSATZ[i];
						
						vSQL_STACK_ForCopy.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+cSQLPosZusatz);
					}
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new myException(this,"SQLException: "+e.getLocalizedMessage());
		}
	}

	
	public Vector<String> get_vSQL_STACK_ForCopy()
	{
		return vSQL_STACK_ForCopy;
	}
	
	
	
	// statische methoden fuer ersetzung der felder, kann auch aus anderen modulen benutzt werden
	public static void _WRITE_ERSATZFIELDS_IN_KOPF(HashMap<String,String> hmErsatzKopf, VorgangTableNames oTN)
	{
		// HashMap mit Ersatz fuer die <Quellfelder> 
		hmErsatzKopf.put(oTN.get_cVKOPF_PK(),oTN.get_cVKOPF_SEQ()+".NEXTVAL");
		hmErsatzKopf.put("ID_MANDANT",bibALL.get_ID_MANDANT());
		hmErsatzKopf.put("GEAENDERT_VON",bibALL.MakeSql(bibALL.get_KUERZEL()));
		hmErsatzKopf.put("LETZTE_AENDERUNG",DB_META.get_tStampString(bibE2.get_DB_KENNUNG()));
		hmErsatzKopf.put("ABGESCHLOSSEN","'N'");
		hmErsatzKopf.put("DELETED","'N'");
		hmErsatzKopf.put("VORGANG_TYP",bibALL.MakeSql(oTN.get_cVORGANG_TYP()));
		hmErsatzKopf.put("ERSTELLUNGSDATUM",bibALL.MakeSql(bibALL.get_cDateNOWInverse("-")));
		//2014-05-05: druckdatum immer null in kopien
		//hmErsatzKopf.put("DRUCKDATUM",bibALL.MakeSql(bibALL.get_cDateNOWInverse("-")));
		hmErsatzKopf.put("DRUCKDATUM","NULL");
		hmErsatzKopf.put("VORGANGSGRUPPE","NULL");
		hmErsatzKopf.put("BUCHUNGSNUMMER","NULL");
		hmErsatzKopf.put("LETZTER_DRUCK","NULL");
		hmErsatzKopf.put("ZAEHLER_ENTSPERRUNG","NULL");
	
		//2010-12-22: Druckzaehler muss immer 0 sein in der kopie
		hmErsatzKopf.put("DRUCKZAEHLER","0");
		
		
		//2014-05-04: Stornokennzeichen aus RE-kopf raus
		if (oTN.get_cVORGANG_TYP().equals(myCONST.VORGANGSART_RECHNUNG) || oTN.get_cVORGANG_TYP().equals(myCONST.VORGANGSART_GUTSCHRIFT))
		{
			//2013-07-24: weitere felder nullen: stornos wegnehmen
			hmErsatzKopf.put(_DB.VKOPF_RG$ID_VKOPF_RG_STORNO_VORGAENGER,	"NULL");
			hmErsatzKopf.put(_DB.VKOPF_RG$ID_VKOPF_RG_STORNO_NACHFOLGER,	"NULL");
			
			//2015-08-20: bearbeiter wird geaendert in der kopie
			hmErsatzKopf.put(VKOPF_RG.formulartext_anfang.fn(),	"NULL");
		}
		
		//2015-08-19: bearbeiter wird geaendert in der kopie (nicht nur die id wie bisher)
//		hmErsatzKopf.put("ID_USER",bibALL.get_ID_USER());
		//trotz der nutzung in allen Belegen werden die feldbezeichner des kontraktes genutzt (feldnamen ueberall gleich) 
		try {
			hmErsatzKopf.put(VKOPF_KON.id_user.fn(),bibALL.get_RECORD_USER().get_ID_USER_cUF());
			hmErsatzKopf.put(VKOPF_KON.name_bearbeiter_intern.fn(),"'"+bibALL.get_RECORD_USER().get___KETTE(bibALL.get_Vector("VORNAME", "NAME1"), "", "", "", " ")+"'");
			hmErsatzKopf.put(VKOPF_KON.tel_bearbeiter_intern.fn(),bibALL.get_RECORD_USER().get_TELEFON_VALUE_FOR_SQLSTATEMENT());
			hmErsatzKopf.put(VKOPF_KON.fax_bearbeiter_intern.fn(),bibALL.get_RECORD_USER().get_TELEFAX_VALUE_FOR_SQLSTATEMENT());
		} catch (myException e) {
			e.printStackTrace();
		}

		
		
	}
	

	// statische methoden fuer ersetzung der felder, kann auch aus anderen modulen benutzt werden
	public static void _WRITE_ERSATZFIELDS_IN_KOPF_KON(HashMap<String,String> hmErsatzKopf, VorgangTableNames oTN)
	{
		// HashMap mit Ersatz fuer die <Quellfelder> 
		hmErsatzKopf.put(oTN.get_cVKOPF_PK(),oTN.get_cVKOPF_SEQ()+".NEXTVAL");
		hmErsatzKopf.put("ID_MANDANT",bibALL.get_ID_MANDANT());
		hmErsatzKopf.put("GEAENDERT_VON",bibALL.MakeSql(bibALL.get_KUERZEL()));
		hmErsatzKopf.put("LETZTE_AENDERUNG",DB_META.get_tStampString(bibE2.get_DB_KENNUNG()));
		hmErsatzKopf.put("ABGESCHLOSSEN","'N'");
		hmErsatzKopf.put("DELETED","'N'");
		hmErsatzKopf.put("VORGANG_TYP",bibALL.MakeSql(oTN.get_cVORGANG_TYP()));
		hmErsatzKopf.put("ERSTELLUNGSDATUM",bibALL.MakeSql(bibALL.get_cDateNOWInverse("-")));
		hmErsatzKopf.put("DRUCKDATUM",bibALL.MakeSql(bibALL.get_cDateNOWInverse("-")));
		hmErsatzKopf.put("VORGANGSGRUPPE","NULL");
		hmErsatzKopf.put("BUCHUNGSNUMMER","NULL");
		hmErsatzKopf.put("LETZTER_DRUCK","NULL");
		hmErsatzKopf.put("ZAEHLER_ENTSPERRUNG","NULL");
		
		//2010-12-22: Druckzaehler muss immer 0 sein in der kopie
		hmErsatzKopf.put("DRUCKZAEHLER","0");

//		//2013-07-24: weitere nullfelder in storno-rechnungen
//		if (oTN.get_cVORGANG_TYP().equals(myCONST.VORGANGSART_RECHNUNG) || oTN.get_cVORGANG_TYP().equals(myCONST.VORGANGSART_GUTSCHRIFT))
//		{
//			//2013-07-24: weitere felder nullen: stornos wegnehmen
//			hmErsatzKopf.put(_DB.VKOPF_RG$ID_VKOPF_RG_STORNO_VORGAENGER,	"NULL");
//			hmErsatzKopf.put(_DB.VKOPF_RG$ID_VKOPF_RG_STORNO_NACHFOLGER,	"NULL");
//		}
		
		//2015-08-19: bearbeiter wird geaendert in der kopie
		try {
			hmErsatzKopf.put(VKOPF_KON.id_user.fn(),bibALL.get_RECORD_USER().get_ID_USER_cUF());
			hmErsatzKopf.put(VKOPF_KON.name_bearbeiter_intern.fn(),"'"+bibALL.get_RECORD_USER().get___KETTE(bibALL.get_Vector("VORNAME", "NAME1"), "", "", "", " ")+"'");
			hmErsatzKopf.put(VKOPF_KON.tel_bearbeiter_intern.fn(),bibALL.get_RECORD_USER().get_TELEFON_VALUE_FOR_SQLSTATEMENT());
			hmErsatzKopf.put(VKOPF_KON.fax_bearbeiter_intern.fn(),bibALL.get_RECORD_USER().get_TELEFAX_VALUE_FOR_SQLSTATEMENT());
		} catch (myException e) {
			e.printStackTrace();
		}
		
	}

	
	
	public static void _WRITE_ERSATZFIELDS_IN_POS(HashMap<String,String> hmErsatzPOS,boolean bReplaceKOPF_ID, VorgangTableNames oTN)
	{
		hmErsatzPOS.put(oTN.get_cVPOS_PK(),oTN.get_cVPOS_SEQ()+".NEXTVAL");
		if (bReplaceKOPF_ID) hmErsatzPOS.put(oTN.get_cVKOPF_PK(),oTN.get_cVKOPF_SEQ()+".CURRVAL");
		hmErsatzPOS.put("ID_MANDANT",bibALL.get_ID_MANDANT());
		hmErsatzPOS.put("GEAENDERT_VON",bibALL.MakeSql(bibALL.get_KUERZEL()));
		hmErsatzPOS.put("LETZTE_AENDERUNG",DB_META.get_tStampString(bibE2.get_DB_KENNUNG()));
		hmErsatzPOS.put("ANZAHL_ABZUG","NULL");
		hmErsatzPOS.put("DELETED","'N'");
		hmErsatzPOS.put("GESAMTPREIS_ABZUG","NULL");
		hmErsatzPOS.put("EINZELPREIS_ABZUG","NULL");
		hmErsatzPOS.put("EINZELPREIS_RESULT","EINZELPREIS");

		hmErsatzPOS.put("GESAMTPREIS_ABZUG_FW","NULL");
		hmErsatzPOS.put("EINZELPREIS_ABZUG_FW","NULL");
		hmErsatzPOS.put("EINZELPREIS_RESULT_FW","EINZELPREIS_FW");

		hmErsatzPOS.put("GESAMTPREIS","ROUND(ANZAHL*EINZELPREIS,2)");
		hmErsatzPOS.put("GESAMTPREIS_FW","ROUND(ANZAHL*EINZELPREIS_FW,2)");
		
		hmErsatzPOS.put("AUSFUEHRUNGSDATUM","NULL");
		
		if (oTN.get_cVORGANG_TYP().equals(myCONST.VORGANGSART_RECHNUNG) || oTN.get_cVORGANG_TYP().equals(myCONST.VORGANGSART_GUTSCHRIFT))
		{
			hmErsatzPOS.put("AUSFUEHRUNGSDATUM",DB_META.get_tStampString(bibE2.get_DB_KENNUNG()));      //in der rechnung darf datum nicht null sein
			hmErsatzPOS.put("ID_VPOS_KON_ZUGEORD","NULL");
			hmErsatzPOS.put("ID_VPOS_TPA_FUHRE_ORT_ZUGEORD","NULL");
			hmErsatzPOS.put("ID_VPOS_TPA_FUHRE_ZUGEORD","NULL");
			
			//2011-04-18: weitere abzugsfelder
			hmErsatzPOS.put("ANZAHL_ABZUG_LAGER",			"NULL");
			hmErsatzPOS.put("GPREIS_ABZ_MGE",				"NULL");
			hmErsatzPOS.put("GPREIS_ABZ_MGE_FW",			"NULL");
			hmErsatzPOS.put("GPREIS_ABZ_AUF_NETTOMGE",		"NULL");
			hmErsatzPOS.put("GPREIS_ABZ_AUF_NETTOMGE_FW",	"NULL");
			hmErsatzPOS.put("GPREIS_ABZ_VORAUSZAHLUNG",		"NULL");
			hmErsatzPOS.put("GPREIS_ABZ_VORAUSZAHLUNG_FW",	"NULL");
			hmErsatzPOS.put("EPREIS_RESULT_NETTO_MGE",		"EINZELPREIS");
			hmErsatzPOS.put("EPREIS_RESULT_NETTO_MGE_FW",	"EINZELPREIS_FW");
			
			//2013-07-24: weitere felder nullen: stornos wegnehmen
			hmErsatzPOS.put(_DB.VPOS_RG$ID_VPOS_RG_STORNO_VORGAENGER,	"NULL");
			hmErsatzPOS.put(_DB.VPOS_RG$ID_VPOS_RG_STORNO_NACHFOLGER,	"NULL");
			
			//2015-08-20: preise in positionen von RG-Kopien leermachen
			hmErsatzPOS.put(VPOS_RG.einzelpreis.fn(),			"NULL");
			hmErsatzPOS.put(VPOS_RG.einzelpreis_fw.fn(),		"NULL");
			hmErsatzPOS.put(VPOS_RG.gesamtpreis.fn(),			"NULL");
			hmErsatzPOS.put(VPOS_RG.gesamtpreis_fw.fn(),		"NULL");
			hmErsatzPOS.put(VPOS_RG.gesamtpreis_abzug.fn(),		"NULL");
			hmErsatzPOS.put(VPOS_RG.gesamtpreis_abzug_fw.fn(),	"NULL");
			hmErsatzPOS.put(VPOS_RG.ausfuehrungsdatum.fn(),			"SYSDATE");
			hmErsatzPOS.put(VPOS_RG.zahlungsbed_calc_datum.fn(),	"NULL");
			
		}
		
		//2014-05-05: Bei Angeboten werden die Einzelpreise in den kopien leergemacht
		if (oTN.get_cVORGANG_TYP().equals(myCONST.VORGANGSART_ANGEBOT) || oTN.get_cVORGANG_TYP().equals(myCONST.VORGANGSART_ABNAHMEANGEBOT))
		{
			hmErsatzPOS.put(_DB.VPOS_STD$EINZELPREIS,		"NULL");
			hmErsatzPOS.put(_DB.VPOS_STD$EINZELPREIS_FW,	"NULL");
		}
		
	}

	
	public static void _WRITE_ERSATZFIELDS_IN_POS_KON(HashMap<String,String> hmErsatzPOS_KON, VorgangTableNames oTN)
	{
		hmErsatzPOS_KON.put(oTN.get_cVPOS_ZUSATZ_TAB_PK(),oTN.get_cVPOS_ZUSATZ_TAB_SEQ()+".NEXTVAL");
		hmErsatzPOS_KON.put(oTN.get_cVPOS_PK(),oTN.get_cVPOS_SEQ()+".CURRVAL");
		hmErsatzPOS_KON.put("ID_MANDANT",bibALL.get_ID_MANDANT());
		hmErsatzPOS_KON.put("GEAENDERT_VON",bibALL.MakeSql(bibALL.get_KUERZEL()));
		hmErsatzPOS_KON.put("LETZTE_AENDERUNG",DB_META.get_tStampString(bibE2.get_DB_KENNUNG()));
		hmErsatzPOS_KON.put("ABGESCHLOSSEN","'N'");
		hmErsatzPOS_KON.put("DELETED","'N'");

	}

	
	public static void _WRITE_ERSATZFIELDS_IN_POS_STD_ANGEBOT(HashMap<String,String> hmErsatzPOS_STD_ANGEBOT, VorgangTableNames oTN)
	{
		hmErsatzPOS_STD_ANGEBOT.put(oTN.get_cVPOS_ZUSATZ_TAB_PK(),oTN.get_cVPOS_ZUSATZ_TAB_SEQ()+".NEXTVAL");
		hmErsatzPOS_STD_ANGEBOT.put(oTN.get_cVPOS_PK(),oTN.get_cVPOS_SEQ()+".CURRVAL");
		hmErsatzPOS_STD_ANGEBOT.put("ID_MANDANT",bibALL.get_ID_MANDANT());
		hmErsatzPOS_STD_ANGEBOT.put("GEAENDERT_VON",bibALL.MakeSql(bibALL.get_KUERZEL()));
		hmErsatzPOS_STD_ANGEBOT.put("LETZTE_AENDERUNG",DB_META.get_tStampString(bibE2.get_DB_KENNUNG()));
		hmErsatzPOS_STD_ANGEBOT.put("DELETED","'N'");

	}

	
	
	
	
	
	/*
	 * wird als Kopie der ganze Transportauftrag kopiert, dann werden auch 
	 * die Felder zu den Kontrakten und die Planmenge geloescht
	 */
	public static void _WRITE_ERSATZFIELDS_IN_POS_TPA_FUHRE(HashMap<String,String> hmErsatz, VorgangTableNames oTN, RECORD_VPOS_TPA_FUHRE recFuhre) throws myException
	{
		
		//aenderung am 20100324 wegen nutzung in einfacher fuhrenkopie
		if (oTN != null)
		{
			hmErsatz.put(oTN.get_cVPOS_ZUSATZ_TAB_PK(),oTN.get_cVPOS_ZUSATZ_TAB_SEQ()+".NEXTVAL");
			hmErsatz.put(oTN.get_cVPOS_PK(),oTN.get_cVPOS_SEQ()+".CURRVAL");
		}
		else
		{
			hmErsatz.put("ID_VPOS_TPA_FUHRE","NULL");
			hmErsatz.put("ID_VPOS_TPA","NULL");            //muss in der rufenden einheit gesetzt werden
		}

		
		hmErsatz.put("ID_MANDANT",bibALL.get_ID_MANDANT());
		hmErsatz.put("GEAENDERT_VON",bibALL.MakeSql(bibALL.get_KUERZEL()));
		hmErsatz.put("LETZTE_AENDERUNG",DB_META.get_tStampString(bibE2.get_DB_KENNUNG()));
		hmErsatz.put("ABGESCHLOSSEN","'N'");
		
		hmErsatz.put("MENGE_VORGABE_KO","NULL");
		hmErsatz.put("MENGE_AUFLADEN_KO","NULL");
		hmErsatz.put("MENGE_ABLADEN_KO","NULL");

		//hmErsatzPOS_TPA.put("ANTEIL_PLANMENGE_LIEF","NULL");
		hmErsatz.put("ANTEIL_LADEMENGE_LIEF","NULL");
		hmErsatz.put("ANTEIL_ABLADEMENGE_LIEF","NULL");
		//hmErsatzPOS_TPA.put("ANTEIL_PLANMENGE_ABN","NULL");
		hmErsatz.put("ANTEIL_LADEMENGE_ABN","NULL");
		hmErsatz.put("ANTEIL_ABLADEMENGE_ABN","NULL");

		hmErsatz.put("BUCHUNGSNR_FUHRE","NULL");
		
		
		hmErsatz.put("DATUM_ABLADEN","NULL");
		hmErsatz.put("DATUM_AUFLADEN","NULL");
		hmErsatz.put("AUFLADEN_BRUTTO","NULL");
		hmErsatz.put("AUFLADEN_TARA","NULL");
		hmErsatz.put("ABLADEN_BRUTTO","NULL");
		hmErsatz.put("ABLADEN_TARA","NULL");
		hmErsatz.put("FUHRENGRUPPE","NULL");
		hmErsatz.put("WIEGEKARTENKENNER_LADEN","NULL");
		hmErsatz.put("WIEGEKARTENKENNER_ABLADEN","NULL");
		hmErsatz.put("ID_WIEGEKARTE_LIEF","NULL");
		hmErsatz.put("ID_WIEGEKARTE_ABN","NULL");
		
		
		/*
		 * geaendert am: 24.03.2010 von: martin
		 */
		hmErsatz.put("AVV_AUSSTELLUNG_DATUM","NULL");
		
		
		/*
		 * geaendert am: 24.03.2010 von: martin
		 */
		//preisfelder muessen bei kopien auch leer werden
		hmErsatz.put("ABZUG_LADEMENGE_LIEF","NULL");
		hmErsatz.put("ABZUG_ABLADEMENGE_ABN","NULL");
		hmErsatz.put("ID_VPOS_STD_EK","NULL");
		hmErsatz.put("ID_VPOS_STD_VK","NULL");
		hmErsatz.put("MANUELL_PREIS_EK","NULL");
		hmErsatz.put("EINZELPREIS_EK","NULL");
		hmErsatz.put("STEUERSATZ_EK","NULL");
		hmErsatz.put("MANUELL_PREIS_VK","NULL");
		hmErsatz.put("EINZELPREIS_VK","NULL");
		hmErsatz.put("STEUERSATZ_VK","NULL");
		hmErsatz.put("EU_STEUER_VERMERK_EK","NULL");
		hmErsatz.put("EU_STEUER_VERMERK_VK","NULL");
		hmErsatz.put("SPEICHERN_TROTZ_INKONSISTENZ","NULL");
		hmErsatz.put("PRUEFUNG_LADEMENGE","NULL");
		hmErsatz.put("PRUEFUNG_LADEMENGE_VON","NULL");
		hmErsatz.put("PRUEFUNG_LADEMENGE_AM","NULL");
		hmErsatz.put("PRUEFUNG_ABLADEMENGE","NULL");
		hmErsatz.put("PRUEFUNG_ABLADEMENGE_VON","NULL");
		hmErsatz.put("PRUEFUNG_ABLADEMENGE_AM","NULL");
		
		//2013-03-21: die weiteren prueffelder auch leermachen
		hmErsatz.put(_DB.VPOS_TPA_FUHRE$PRUEFUNG_EK_PREIS,"NULL");
		hmErsatz.put(_DB.VPOS_TPA_FUHRE$PRUEFUNG_EK_PREIS_VON,"NULL");
		hmErsatz.put(_DB.VPOS_TPA_FUHRE$PRUEFUNG_EK_PREIS_AM,"NULL");
		hmErsatz.put(_DB.VPOS_TPA_FUHRE$PRUEFUNG_VK_PREIS,"NULL");
		hmErsatz.put(_DB.VPOS_TPA_FUHRE$PRUEFUNG_VK_PREIS_VON,"NULL");
		hmErsatz.put(_DB.VPOS_TPA_FUHRE$PRUEFUNG_VK_PREIS_AM,"NULL");
		hmErsatz.put(_DB.VPOS_TPA_FUHRE$SCHLIESSE_FUHRE,"NULL");
		hmErsatz.put(_DB.VPOS_TPA_FUHRE$SCHLIESSE_FUHRE_VON,"NULL");
		hmErsatz.put(_DB.VPOS_TPA_FUHRE$SCHLIESSE_FUHRE_AM,"NULL");

		hmErsatz.put(_DB.VPOS_TPA_FUHRE$ID_TAX_EK,"NULL");
		hmErsatz.put(_DB.VPOS_TPA_FUHRE$ID_TAX_VK,"NULL");
		hmErsatz.put(_DB.VPOS_TPA_FUHRE$INTRASTAT_MELD_IN,"NULL");
		hmErsatz.put(_DB.VPOS_TPA_FUHRE$INTRASTAT_MELD_OUT,"NULL");
		hmErsatz.put(_DB.VPOS_TPA_FUHRE$TRANSIT_EK,"NULL");
		hmErsatz.put(_DB.VPOS_TPA_FUHRE$TRANSIT_VK,"NULL");
		
		//2013-10-01: die id der handelsdefinition darf auch nicht kopiert werden
		hmErsatz.put(_DB.VPOS_TPA_FUHRE$ID_HANDELSDEF,"NULL");
		
		//aenderung 2010-12-22: FUHRE_KOMPLETT ist bei kopierten Fuhren immer N sein,
		//weitere Felder resettet beim kopieren einer fuhre
		//damit eine kopie einer gefaehrlichen sorte durch den validierer geht (noti ist null)
		hmErsatz.put("NOTIFIKATION_NR","NULL");
		
		hmErsatz.put("NOTIFIKATION_NR_EK","NULL");    //2012-10-18: ek-noti muss ebenfalls auf leer gesetzt werden
		
		hmErsatz.put("STATUS_BUCHUNG","NULL");
		hmErsatz.put("IST_STORNIERT","'N'");
		hmErsatz.put("IST_GEPLANT_FP","'N'");
		hmErsatz.put("STORNO_GRUND","NULL");
		hmErsatz.put("STORNO_KUERZEL","NULL");
		hmErsatz.put("WIEGEKARTENKENNER_LADEN","NULL");
		hmErsatz.put("WIEGEKARTENKENNER_ABLADEN","NULL");
		hmErsatz.put("KENNER_ALTE_SAETZE_FP","NULL");
		
		//falls in der kopierten fuhre eine noti vorhanden ist, muss der bediener nochmals ueber die Fuhrenmaske gehen
		//2012-10-18: auch die notifikation-ek in diese pruefung miteinbeziehen
		if (S.isFull(recFuhre.get_NOTIFIKATION_NR_cUF_NN("")) || S.isFull(recFuhre.get_NOTIFIKATION_NR_EK_cUF_NN("")))
		{
			hmErsatz.put("FUHRE_KOMPLETT","'N'");
		}

		//2012-01-04: UM-Kontrakt aus der kopie raushalten
		hmErsatz.put("ID_UMA_KONTRAKT","NULL");

		//2012-01-23: postennummer aus kopie raushalten
		hmErsatz.put(RECORD_VPOS_TPA_FUHRE.FIELD__POSTENNUMMER_EK,"NULL");
		hmErsatz.put(RECORD_VPOS_TPA_FUHRE.FIELD__POSTENNUMMER_VK,"NULL");

//		//2013-07-16: proforma-rechnung-buchungsnummer loeschen
//		hmErsatz.put(RECORD_VPOS_TPA_FUHRE.FIELD__BELEGNR_PROFORMA,"NULL");

		//2015-08-19: bestellnummer-EK nicht kopieren (Anweisung MV)
		hmErsatz.put(VPOS_TPA_FUHRE.bestellnummer_ek.fn(), "NULL");
		
		
		
	}
	
	
}

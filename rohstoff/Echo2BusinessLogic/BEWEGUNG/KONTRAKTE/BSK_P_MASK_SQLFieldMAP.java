package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__CompMap_FieldMAP_Gemeinsamkeiten;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class BSK_P_MASK_SQLFieldMAP extends Project_SQLFieldMAP {

	public BSK_P_MASK_SQLFieldMAP(BS__SETTING oSetting) throws myException 
	{
		super("JT_VPOS_KON", ":ID_VKOPF_KON:"+VPOS_KON.typ_25_to.fn()+":"+VPOS_KON.typ_ladung.fn()+":", false);
		
		
		//zusatz-abfragefelder fuer die information, zu welchem kopf die vertragsposition gehoert
//		String cQueryFirmaKopf = "(NVL((SELECT NVL("+_DB.VKOPF_KON$NAME1+",'<name1>')||' '||NVL("+_DB.VKOPF_KON$PLZ+",'<plz>')||' '||NVL("+_DB.VKOPF_KON$ORT+",'<ort>') " +
//									" FROM "+bibE2.cTO()+"."+_DB.VKOPF_KON+" WHERE "+_DB.VKOPF_KON$ID_VKOPF_KON+
//									"=NVL("+_DB.VPOS_KON+"."+_DB.VPOS_KON$ID_VKOPF_KON+",-1)),'-'))";
//
//		String cQueryBuchungsnummer = "(NVL((SELECT NVL("+_DB.VKOPF_KON$BUCHUNGSNUMMER+",'<buchnungsnummer>')" +
//								" FROM "+bibE2.cTO()+"."+_DB.VKOPF_KON+" WHERE "+_DB.VKOPF_KON$ID_VKOPF_KON+
//								"=NVL("+_DB.VPOS_KON+"."+_DB.VPOS_KON$ID_VKOPF_KON+",-1)).'-'))";
//
		
		String cQueryFirmaKopf = "NVL((SELECT NVL("+_DB.VKOPF_KON$NAME1+",'<name1>')||' '||NVL("+_DB.VKOPF_KON$PLZ+",'<plz>')||' '||NVL("+_DB.VKOPF_KON$ORT+",'<ort>') " +
				 							" FROM "+bibE2.cTO()+"."+_DB.VKOPF_KON+" " +
				 							" WHERE "+_DB.VKOPF_KON$ID_VKOPF_KON+"=NVL("+_DB.VPOS_KON+"."+_DB.VPOS_KON$ID_VKOPF_KON+",-1)),'-')";
		
		String cQueryBuchungsnummer= "NVL((SELECT NVL("+_DB.VKOPF_KON$BUCHUNGSNUMMER+",'<buchnungsnummer>') " +
											" FROM "+bibE2.cTO()+"."+_DB.VKOPF_KON+" " +
											" WHERE "+_DB.VKOPF_KON$ID_VKOPF_KON+"=NVL("+_DB.VPOS_KON+"."+_DB.VPOS_KON$ID_VKOPF_KON+",-1)),'-')";
		
		
		//2013-09-25: kopfinfos in positionsmaske
		this.add_SQLField(new SQLField(cQueryFirmaKopf, 		BSK__CONST.HASHKEY_VK_INFO_FIRMA, 			new MyE2_String("Firma"), bibE2.get_CurrSession()), false);
		this.add_SQLField(new SQLField(cQueryBuchungsnummer, 	BSK__CONST.HASHKEY_VK_INFO_BUCHUNGNR, new MyE2_String("Buchungsnummer Kopf"), bibE2.get_CurrSession()), false);
		this.add_SQLField(new SQLField("(TO_CHAR(NVL("+_DB.VPOS_KON+"."+_DB.VPOS_KON$POSITIONSNUMMER+",0)))", 
																BSK__CONST.HASHKEY_VK_INFO_BUCHUNGPOSNR, new MyE2_String("Positionsnummer"), bibE2.get_CurrSession()), false);
		
		this.add_SQLField(new SQLField("(TO_CHAR(NVL("+_DB.VPOS_KON+"."+_DB.VPOS_KON$ANZAHL+",0),'fm9g999g999g990d0','NLS_NUMERIC_CHARACTERS = '',.''')||' '||" +
																	"NVL("+_DB.VPOS_KON+"."+_DB.VPOS_KON$EINHEITKURZ+",'<eh>'))", 
																BSK__CONST.HASHKEY_VK_INFO_MENGE, new MyE2_String("Menge"), bibE2.get_CurrSession()), false);
		
		this.add_SQLField(new SQLField("(TO_CHAR(NVL("+_DB.VPOS_KON+"."+_DB.VPOS_KON$EINZELPREIS+",0),'fm9g999g999g990d00','NLS_NUMERIC_CHARACTERS = '',.''')||' "+bibALL.get_BASIS_WAEHRUNG_KURZ()+"')", 
																BSK__CONST.HASHKEY_VK_INFO_PREIS, new MyE2_String("Preis"), bibE2.get_CurrSession()), false);
		this.add_SQLField(new SQLField("(NVL("+_DB.VPOS_KON+"."+_DB.VPOS_KON$ANR1+",'<anr1>')||' '||NVL("+_DB.VPOS_KON$ARTBEZ1+",'<artbez1>'))", 
																BSK__CONST.HASHKEY_VK_INFO_SORTE, new MyE2_String("Sorte"), bibE2.get_CurrSession()), false);
		
		
		/*
		 * beschraenkung fuer das Feld VORGANG_TYP
		 */
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_VPOS_KON","ID_VKOPF_KON","ID_VKOPF_KON",new MyE2_String("ID-VKOPF"),"NULL",bibE2.get_CurrSession()), false);

		/*
		 * defaultwerte setzen
		 */
		BS__CompMap_FieldMAP_Gemeinsamkeiten.set_BasicSQLFieldSettings_for_Positions(this,"0");
		
		if (oSetting.get_cBELEGTYP().equals(myCONST.VORGANGSART_EK_KONTRAKT))
		{
			this.get_("MENGEN_TOLERANZ_PROZENT").set_cDefaultValueFormated(bibALL.get_RECORD_MANDANT().get_MG_TOLERANZ_EK_KONTRAKT_POS_cF_NN("0"));
		}
		else
		{
			this.get_("MENGEN_TOLERANZ_PROZENT").set_cDefaultValueFormated(bibALL.get_RECORD_MANDANT().get_MG_TOLERANZ_VK_KONTRAKT_POS_cF_NN("0"));
		}

		
		this.initFields();
	}

}

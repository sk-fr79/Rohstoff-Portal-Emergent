package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyFieldTimeFieldValidator;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.BST_P_MASK_SQLFieldMAP;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.FP_SQL_Insert_Agent;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.FP_SQL_Update_Agent;
import rohstoff.Echo2BusinessLogic._TAX.TAX_CONST;

public class FU_MASK_SQLFieldMAP extends SQLFieldMAP
{
	/*
	 * falls die sql-fieldmap im standalone-module fuhrenzentrale benutzt wird,
	 * ist der parameter oSQLFieldMap_VPOS_TPA null
	 */
	
	private BST_P_MASK_SQLFieldMAP oSQLFieldMap_VPOS_TPA = null;
	
	public FU_MASK_SQLFieldMAP(BST_P_MASK_SQLFieldMAP SQLFieldMAP_VPOS_TPA, boolean bFuerFahrplan) throws myException
	{
		super("JT_VPOS_TPA_FUHRE", bibE2.get_CurrSession());

		this.oSQLFieldMap_VPOS_TPA = SQLFieldMAP_VPOS_TPA;
		
		/*
		 * zuerst den primary-key
		 */
		this.add_SQLField(new SQLFieldForPrimaryKey(		"JT_VPOS_TPA_FUHRE",
															"ID_VPOS_TPA_FUHRE",
															"ID_VPOS_TPA_FUHRE",
															new MyString("ID-Fuhre"),
															bibE2.get_CurrSession(),
															"SELECT "+bibE2.cTO()+".SEQ_VPOS_TPA_FUHRE.NEXTVAL FROM DUAL",true), false);

		/*
		 * dann die tabellenfelder selber dranhaengen (ausser ID_VPOS_TPA, die bleibt in diesem Fall NULL,
		 * das es sich um freie fuhren handelt, bei Neueingabe=null, bei update unveraendert
		 */
		this.addCompleteTable_FIELDLIST("JT_VPOS_TPA_FUHRE",
									":ID_VPOS_TPA_FUHRE:ID_VPOS_TPA:LETZTE_AENDERUNG:GEAENDERT_VON:ID_MANDANT:",false,true, "");
	
		/*
		 * default-wert setzen
		 */
		this.get_("ABGESCHLOSSEN").set_cDefaultValueFormated("N");
		this.get_("EK_VK_SORTE_LOCK").set_cDefaultValueFormated("Y");   // normalerweise muessen die EK gleich der VK-Sorte sein
		
		this.get_("PRINT_EU_AMTSBLATT").set_cDefaultValueFormated("Y");   // normalerweise amtsblatt immer drucken
		this.get_("OHNE_AVV_VERTRAG_CHECK").set_cDefaultValueFormated("N");   // pruefung des AVV-Vertrages mit lieferanten/Abnehmer verifizieren

		/*
		 * falls tochter-map, dann eine verknuepfung
		 */
		if (this.oSQLFieldMap_VPOS_TPA != null)
		{
			// das connection-field
			this.add_SQLField(new SQLFieldJoinOutside("JT_VPOS_TPA_FUHRE","ID_VPOS_TPA","ID_VPOS_TPA",new MyE2_String("ID-TP-Pos."),false,bibE2.get_CurrSession(),oSQLFieldMap_VPOS_TPA.get_("ID_VPOS_TPA")), false);
		}
		else
		{
			this.add_SQLField(new SQLField("JT_VPOS_TPA_FUHRE","ID_VPOS_TPA","ID_VPOS_TPA",new MyE2_String("ID-TP-Pos"),true,bibE2.get_CurrSession()), false);
		}
		/*
		 * einige MUST-Fields
 		 */
		
		this.get_("ID_ADRESSE_START").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_ADRESSE_ZIEL").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_ADRESSE_LAGER_START").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_ADRESSE_LAGER_ZIEL").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		this.get_("ID_ARTIKEL").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("DATUM_ABHOLUNG").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("DATUM_ANLIEFERUNG").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("TRANSPORTMITTEL").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		if (S.NN(bibALL.get_TEXTVAL_FROM_RECLIST_MANDANTEN_ZUSATZ("JT_VPOS_TPA_FUHRE_KENNZEICHEN_IST_MUSSFELD")).equals("Y"))
		{
			this.get_("TRANSPORTKENNZEICHEN").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		}
		
		
		this.get_("ID_ARTIKEL_BEZ_EK").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_ARTIKEL_BEZ_VK").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		this.get_("ID_ADRESSE_START").set_cFieldLabelForUser(new MyE2_String("Abholadresse"));
		this.get_("ID_ADRESSE_ZIEL").set_cFieldLabelForUser(new MyE2_String("Zieladresse"));
		this.get_("ID_ARTIKEL").set_cFieldLabelForUser(new MyE2_String("Sorte"));
		this.get_("DATUM_ABHOLUNG").set_cFieldLabelForUser(new MyE2_String("Abholdatum"));
		this.get_("DATUM_ANLIEFERUNG").set_cFieldLabelForUser(new MyE2_String("Lieferdatum"));
		this.get_("TRANSPORTMITTEL").set_cFieldLabelForUser(new MyE2_String("Transportmittel"));
		
		this.get_("DELETED").set_cDefaultValueFormated("N");
		this.get_("DELETED").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		this.get_("LADEMENGE_GUTSCHRIFT").set_cDefaultValueFormated("Y");
		this.get_("ABLADEMENGE_RECHNUNG").set_cDefaultValueFormated("Y");
		this.get_("STATUS_BUCHUNG").set_cDefaultValueFormated("0");
		
		this.get_("ERFASSER_FP").set_cDefaultValueFormated(bibALL.get_RECORD_USER().get_KUERZEL_cF_NN("-"));
		this.get_("FAHRT_ANFANG_FP").get_vValidInput().add(new MyFieldTimeFieldValidator(this.get_("FAHRT_ANFANG_FP")));
		this.get_("FAHRT_ENDE_FP").get_vValidInput().add(new MyFieldTimeFieldValidator(this.get_("FAHRT_ENDE_FP")));
		
		this.get_("ANZAHL_CONTAINER_FP").set_cDefaultValueFormated("1");
		this.get_("ANZAHL_CONTAINER_FP").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		this.get_("ABZUG_LADEMENGE_LIEF").set_cDefaultValueFormated("0");
		this.get_("ABZUG_ABLADEMENGE_ABN").set_cDefaultValueFormated("0");
		
		this.get_("FUHRE_AUS_FAHRPLAN").set_cDefaultValueFormated("N");
		
		if (bFuerFahrplan)
		{
			this.get_("FUHRE_AUS_FAHRPLAN").set_cDefaultValueFormated("Y");

			//im unterschied zur normalen maske wird hier vormerkzeitraum zum mussfeld
			this.get_("DAT_VORGEMERKT_FP").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
			this.get_("DAT_VORGEMERKT_ENDE_FP").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		}
		
		this.get_("EK_VK_ZUORD_ZWANG").set_cDefaultValueFormated("Y");
		
		//2011-02-10: aenderung fuhrenmaske
		if (S.isFull(bibALL.get_TEXTVAL_FROM_RECLIST_MANDANTEN_ZUSATZ("JT_VPOS_TPA_FUHRE_VORGABE_TRANSPORTMITTEL")))
		{
			this.get_("TRANSPORTMITTEL").set_cDefaultValueFormated(bibALL.get_TEXTVAL_FROM_RECLIST_MANDANTEN_ZUSATZ("JT_VPOS_TPA_FUHRE_VORGABE_TRANSPORTMITTEL"));
		}

		
		//2013-01-07: den status des TP-veranwortlichen auf mandant setzen (standard)
		this.get_(RECORD_VPOS_TPA_FUHRE.FIELD__TP_VERANTWORTUNG).set_cDefaultValueFormated(TAX_CONST.TP_VERANTWORTUNG_MANDANT);
//		this.get_(RECORD_VPOS_TPA_FUHRE.FIELD__INTRASTAT_MELD_IN).set_cDefaultValueFormated(TAX_CONST.INTRASTAT_MELDEN_UDEF);
//		this.get_(RECORD_VPOS_TPA_FUHRE.FIELD__INTRASTAT_MELD_OUT).set_cDefaultValueFormated(TAX_CONST.INTRASTAT_MELDEN_UDEF);
//		this.get_(RECORD_VPOS_TPA_FUHRE.FIELD__ART_GESCHAEFT).set_cDefaultValueFormated(TAX_CONST.ART_HANDEL_NICHT_KLASSIFIZIERT);
		
//		this.get_(RECORD_VPOS_TPA_FUHRE.FIELD__INTRASTAT_MELD_IN).set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_(RECORD_VPOS_TPA_FUHRE.FIELD__INTRASTAT_MELD_OUT).set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);

		
		
		
		//falls es fuhren fuer die fahrplanmodule sind, muessen noch sqlagenten eingefuegt werden
		this.set_oInsertAgent(new FP_SQL_Insert_Agent());
		this.set_oUpdateAgent(new FP_SQL_Update_Agent());
		
	}

}

package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__CompMap_FieldMAP_Gemeinsamkeiten;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

/*
 * benutzung durch: Rechnung-Gutschrift-Modul, 	oBS_Setting != null, cID_VPOS_TPA_FUHRE==null
 * freie positionen:							oBS_Setting == null, cID_VPOS_TPA_FUHRE == null = Freie positionen, cID_VPOS_TPA_FUHRE != null Fuhren-Buchungsmodul 
 */
public class BSRG_P_MASK_SQLFieldMAP extends Project_SQLFieldMAP {

	public BSRG_P_MASK_SQLFieldMAP(BS__SETTING oBS_Setting, String cID_VPOS_TPA_FUHRE) throws myException 
	{
		super("JT_VPOS_RG", ":ID_VKOPF_RG:ID_VPOS_TPA_FUHRE_ZUGEORD:", false);
		
		
		if (oBS_Setting == null)           // freie positionen
		{
			this.add_ConnectedLookUpTable("JT_ADRESSE","NAME1:NAME2:ORT","A_"," JT_VPOS_RG.ID_ADRESSE=JT_ADRESSE.ID_ADRESSE (+)");
			this.add_SQLField(new SQLField("  NVL(JT_ADRESSE.NAME1,'-')||'-'||  NVL(JT_ADRESSE.NAME2,'-')||'-'||  NVL(JT_ADRESSE.ORT,'-') ||' ('||TO_CHAR(  NVL(JT_ADRESSE.ID_ADRESSE,0))||')' ","ADRESSE",
					new MyE2_String("Adresse"),bibE2.get_CurrSession()), false);
			
			if (cID_VPOS_TPA_FUHRE == null)   // bedeutet standalonebetrieb
			{
				this.add_SQLField(new SQLField(	"JT_VPOS_RG","ID_VPOS_TPA_FUHRE_ZUGEORD","ID_VPOS_TPA_FUHRE_ZUGEORD",new MyE2_String("ID_VPOS_TPA_FUHRE_ZUGEORD"),true,bibE2.get_CurrSession()), false);
				this.add_BEDINGUNG_STATIC("JT_VPOS_RG.ID_VKOPF_RG IS NULL");
			}
			else							 // benutzung unter fuhre-buchungsmodul
			{
				this.add_SQLField(new SQLFieldForRestrictTableRange("JT_VPOS_RG","ID_VPOS_TPA_FUHRE_ZUGEORD","ID_VPOS_TPA_FUHRE_ZUGEORD",new MyE2_String("ID_VPOS_TPA_FUHRE_ZUGEORD"),cID_VPOS_TPA_FUHRE,bibE2.get_CurrSession()), false);
			}

			BS__CompMap_FieldMAP_Gemeinsamkeiten.set_BasicSQLFieldSettings_for_Positions(this, "0");

			// bei freien positionen wird das feld id_vkopf_rg nur als lesbares feld proforma angezeigt. ist immer leer
			SQLField oFieldVKOPF_RG = new SQLField(	"JT_VPOS_RG","ID_VKOPF_RG","ID_VKOPF_RG",new MyE2_String("ID_VKOPF_RG"),true,bibE2.get_CurrSession());
			oFieldVKOPF_RG.set_bFieldCanBeWrittenInMask(false);
			this.add_SQLField(oFieldVKOPF_RG, false);

		}
		else								// gebundene positionen
		{
			this.add_SQLField(new SQLField(	"JT_VPOS_RG","ID_VPOS_TPA_FUHRE_ZUGEORD","ID_VPOS_TPA_FUHRE_ZUGEORD",new MyE2_String("ID_VPOS_TPA_FUHRE_ZUGEORD"),true,bibE2.get_CurrSession()), false);

			/*
			 * beschraenkung fuer das Feld Vorgangskopf-feld
			 */
			this.add_SQLField(new SQLFieldForRestrictTableRange("JT_VPOS_RG","ID_VKOPF_RG","ID_VKOPF_RG",new MyE2_String("ID-VKOPF"),"NULL",bibE2.get_CurrSession()), false);
			
			
			if (oBS_Setting.get_cBELEGTYP().equals(myCONST.VORGANGSART_RECHNUNG))
				BS__CompMap_FieldMAP_Gemeinsamkeiten.set_BasicSQLFieldSettings_for_Positions(this, "-1");
			else
				BS__CompMap_FieldMAP_Gemeinsamkeiten.set_BasicSQLFieldSettings_for_Positions(this, "1");

		}


		this.get_("WAEHRUNGSKURS").set_cDefaultValueFormated("1");

		
		/*
		 * defaultwerte setzen
		 */
		this.get_("POSITIONSNUMMER").set_cDefaultValueFormated("0");
		this.get_("AUSFUEHRUNGSDATUM").set_cDefaultValueFormated(bibALL.get_cDateNOW());

		this.get_("IST_SONDERPOSITION").set_cDefaultValueFormated("N");

		/*
		 * must-values
		 */
		this.get_("ANZAHL").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("EINZELPREIS").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("EINHEITKURZ").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("EINHEIT_PREIS_KURZ").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("STEUERSATZ").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("AUSFUEHRUNGSDATUM").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		
		this.initFields();
	}

}

package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import java.util.Vector;

import nextapp.echo2.app.Component;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_DBC_DaughterTable;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_STD;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_TPA;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG_VL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_STD;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WAEHRUNG;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.BSRG__CONST;

public class BS_MASK_POS_WAEHRUNGSSYMBOL_SETTER
{

	private String  			cTableKennung = null;
	private E2_ComponentMAP 	oMap = null;
	
	/**
	 * @param TableKennung (STD,RG,RG_VL,KON oder TPA)
	 * @param map
	 * @throws myException
	 */
	public BS_MASK_POS_WAEHRUNGSSYMBOL_SETTER(String TableKennung, E2_ComponentMAP map) throws myException
	{
		super();
		this.cTableKennung = 	TableKennung;
		this.oMap = 			map;
		
		boolean  bNeueingabe = (oMap.get_oInternalSQLResultMAP()==null);
		
		if (!(this.cTableKennung.equals("STD") || this.cTableKennung.equals("RG") || this.cTableKennung.equals("RG_VL") || this.cTableKennung.equals("KON") || this.cTableKennung.equals("TPA")))
			throw new myException(this,"False Tablekennung: "+bibALL.null2leer(this.cTableKennung)+" is not allowed !");
		
		// bei neueingabe pruefen, ob ein restricting-wert gesetzt wurde
		// positionsnummern forsetzen
		String cID_VKOPF = null;

		if (!TableKennung.equals("RG_VL"))   //bei vorlage-positionen sinnlos
		{
			SQLField oField_ID_VKOPF = oMap.get_oSQLFieldMAP().get_("ID_VKOPF_"+this.cTableKennung);
			
			if (oField_ID_VKOPF instanceof SQLFieldForRestrictTableRange)
			{
				cID_VKOPF = ((SQLFieldForRestrictTableRange)oMap.get_oSQLFieldMAP().get_("ID_VKOPF_"+this.cTableKennung)).get_cRestictionValue_IN_DB_FORMAT();
			}
		}
		boolean  bMitKopfsatz = bibALL.isLong(cID_VKOPF); 
		
		
		// waehrungs-symbole laden
		String cID_WAEHRUNG_FREMD_UF = null; 

		if (!bNeueingabe)
		{
			String cID_VPOS = oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			
			if (this.cTableKennung.equals("STD"))
			{
				cID_WAEHRUNG_FREMD_UF = new RECORD_VPOS_STD(cID_VPOS).get_ID_WAEHRUNG_FREMD_cUF_NN("");
			}
			if (this.cTableKennung.equals("RG"))
			{
				cID_WAEHRUNG_FREMD_UF = new RECORD_VPOS_RG(cID_VPOS).get_ID_WAEHRUNG_FREMD_cUF_NN("");
			}
			if (this.cTableKennung.equals("RG_VL"))
			{
				cID_WAEHRUNG_FREMD_UF = new RECORD_VPOS_RG_VL(cID_VPOS).get_ID_WAEHRUNG_FREMD_cUF_NN("");
			}
			if (this.cTableKennung.equals("KON"))
			{
				cID_WAEHRUNG_FREMD_UF = new RECORD_VPOS_KON(cID_VPOS).get_ID_WAEHRUNG_FREMD_cUF_NN("");
			}
			if (this.cTableKennung.equals("TPA"))
			{
				cID_WAEHRUNG_FREMD_UF = new RECORD_VPOS_TPA(cID_VPOS).get_ID_WAEHRUNG_FREMD_cUF_NN("");
			}
		}
		else
		{
			// bei Neueingabe ...
			
			if (bMitKopfsatz)
			{
				// dann wird die waehrung des kopfes geladen und dargestellt
				String cID_WAEHRUNG_FREMD_F = null;
				if (this.cTableKennung.equals("STD"))
				{
					RECORD_VKOPF_STD oMAP = new RECORD_VKOPF_STD(cID_VKOPF);
					cID_WAEHRUNG_FREMD_UF = oMAP.get_ID_WAEHRUNG_FREMD_cUF_NN("");	
					cID_WAEHRUNG_FREMD_F =  oMAP.get_ID_WAEHRUNG_FREMD_cF_NN("");	
				}
				if (this.cTableKennung.equals("RG"))
				{
					RECORD_VKOPF_RG oMAP = new RECORD_VKOPF_RG(cID_VKOPF);
					cID_WAEHRUNG_FREMD_UF = oMAP.get_ID_WAEHRUNG_FREMD_cUF_NN("");
					cID_WAEHRUNG_FREMD_F =  oMAP.get_ID_WAEHRUNG_FREMD_cF_NN("");	
				}
				if (this.cTableKennung.equals("KON"))
				{
					RECORD_VKOPF_KON oMAP = new RECORD_VKOPF_KON(cID_VKOPF);
					cID_WAEHRUNG_FREMD_UF = oMAP.get_ID_WAEHRUNG_FREMD_cUF_NN("");	
					cID_WAEHRUNG_FREMD_F =  oMAP.get_ID_WAEHRUNG_FREMD_cF_NN("");	
				}
				if (this.cTableKennung.equals("TPA"))
				{
					RECORD_VKOPF_TPA oMAP = new RECORD_VKOPF_TPA(cID_VKOPF);
					cID_WAEHRUNG_FREMD_UF = oMAP.get_ID_WAEHRUNG_FREMD_cUF_NN("");	
					cID_WAEHRUNG_FREMD_F =  oMAP.get_ID_WAEHRUNG_FREMD_cF_NN("");	
				}
				
				//dann wird die waehrungsid in der maske definiert und die komponente gesperrt
				((MyE2_DB_SelectField)this.oMap.get__Comp("ID_WAEHRUNG_FREMD")).set_ActiveValue(cID_WAEHRUNG_FREMD_F);
			}
			else
			{
				// basis-waehrung laden
				cID_WAEHRUNG_FREMD_UF = bibALL.get_ID_BASISWAEHRUNG();
				((MyE2_DB_SelectField)this.oMap.get__Comp("ID_WAEHRUNG_FREMD")).set_ActiveValue(bibALL.get_ID_BASISWAEHRUNG_FORMATED()); 
			}
		}

		String cWaehrungssymbol = "-";
		if (!bibALL.isEmpty(cID_WAEHRUNG_FREMD_UF))
		{
			cWaehrungssymbol = new RECORD_WAEHRUNG(cID_WAEHRUNG_FREMD_UF).get_WAEHRUNGSSYMBOL_cUF_NN("-");
		}
		((BS_ComponentMAP)oMap).set_FremdWaehrungsSymbol(cWaehrungssymbol);


		//jetzt bei eingabe mit kopfsatz die waehrungsauswahl sperren
		if (bMitKopfsatz)
		{
			((MyE2_DB_SelectField)this.oMap.get__Comp("ID_WAEHRUNG_FREMD")).EXT().set_bDisabledFromInteractive(true);
		}
		
		
		// wenn es eine RG-Position ist, dann muss auch in der abzugsliste gesetzt werden
		//jetzt nachsehen, ob es eine Abzugsliste gibt, wenn ja, dort auch die fremdwaehrungssymbole ersetzen
		if (((BS_ComponentMAP)oMap).containsKey(BSRG__CONST.HASH_KEY_DAUGHTER_ABZUEGE_IN_POS))
		{
//			MyE2_DBC_DaughterTable oAbzuege = 
//				(MyE2_DBC_DaughterTable)((BS_ComponentMAP)oMap).get__Comp(BSRG__CONST.HASH_KEY_DAUGHTER_ABZUEGE_IN_POS);
			
			Vector<Component>  vLabs = ((MyE2_DBC_DaughterTable)oMap.get__Comp(BSRG__CONST.HASH_KEY_DAUGHTER_ABZUEGE_IN_POS)).get_vComponents(bibALL.get_Vector(MyE2_Label.class.getName()),null);
			
			// dann alle komponenten vom Typ MyE2_Label rausziehen
			for (Component oLab: vLabs)
			{
				if (oLab instanceof MyE2_Label)
				{
					((MyE2_Label)oLab).set_ReplaceText("#FW#", cWaehrungssymbol);
				}
			}
		}
		
	}
	
	
	
}

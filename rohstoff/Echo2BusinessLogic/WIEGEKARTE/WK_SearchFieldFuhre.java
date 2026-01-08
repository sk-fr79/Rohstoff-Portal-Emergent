package rohstoff.Echo2BusinessLogic.WIEGEKARTE;

import java.util.Vector;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.MaskSearchField.MyE2_MaskSearchField;
import panter.gmbh.Echo2.components.MaskSearchField.XX_FormaterForFoundButtonsNonDB;
import panter.gmbh.Echo2.components.MaskSearchField.XX_MaskActionAfterFoundNonDB;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.XX_Button4SearchResultList;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class WK_SearchFieldFuhre extends MyE2_MaskSearchField
{
	private XX_ActionAgent ActionAfterThisActionFound = null;
	private XX_ActionAgent ActionAfterThisActionDeleted = null;	
	
	private MyE2_TextField  oTF_Fuhre = null;
	private MyE2_TextField  oTF_Fuhre_ort = null;

	

	public WK_SearchFieldFuhre(
			MyE2_TextField  TF_Fuhre,
			MyE2_TextField  TF_Fuhre_ort, 
			String idLager, 
			XX_ActionAgent action_after_found,
			XX_ActionAgent action_after_deleted) throws myException {
		
//		String 			cSQLSelectBlock,
//		String 			cSQLFromBlock,
//		String 			cSQLOrderBlock,
//		String 			cSQLWhereBlockJoiningTables,
//		String 			cSQLWhereBlockForSelecting,
//		String 			cSQLqueryForUniqueSearch, 
//		String 			charForUniqueSearch, 
//		String 			cCOMPLETE_SQL_FOR_label, 
//		Insets 			INSETS_For_Components, 
//		boolean 		ShowEraser,
//		int             iMaxresults
		super( 	
				//SELECT-BLOCK
				  " TO_CHAR(ID_VPOS_TPA_FUHRE)||'-'||NVL(TO_CHAR(ID_VPOS_TPA_FUHRE_ORT),''),"+
				  // WE/WA
				  " CASE ID_ADRESSE_LAGER_ZIEL WHEN " + idLager + " THEN 'WE' ELSE CASE ID_ADRESSE_LAGER_START WHEN " + idLager + " then 'WA' ELSE null END END ,"+
				  // KENNZEICHEN
				  " CASE NVL(TRANSPORTKENNZEICHEN, to_nchar('N')) WHEN to_nchar('N') THEN to_nchar('k.A.') ELSE ( TRANSPORTKENNZEICHEN || ' ') END || " + 
				  " CASE NVL(ANHAENGERKENNZEICHEN, to_nchar('N')) WHEN to_nchar('N') THEN null ELSE (' / ' || ANHAENGERKENNZEICHEN ) END , " +
				  // LIEFERORT
				  " CASE ID_ADRESSE_LAGER_START when " + idLager + "  THEN A_NAME1  ||  ' ' || A_NAME2 ||  ' ' || A_NAME3 ELSE L_NAME1  ||  ' ' || L_NAME2 ||  ' ' || L_NAME3 end , " +
				  " CASE ID_ADRESSE_LAGER_START when " + idLager + " THEN A_PLZ ||  ' ' ||   A_ORT || ', ' || A_STRASSE ||  ' ' || A_HAUSNUMMER  ELSE L_PLZ  ||  ' ' ||  L_ORT || ', ' ||  L_STRASSE ||  ' ' ||  L_HAUSNUMMER   end ,"+
				  // FIRMENORT
				  " CASE WHEN ID_ADRESSE_LAGER_START = " + idLager + " AND ID_ADRESSE_LAGER_ZIEL != ID_ADRESSE_ZIEL " +  
				  	" THEN  (SELECT  A.NAME1 || ' ' || NVL2( A.NAME2, ' ' ||  A.NAME2,'') || NVL2( A.NAME3, ' ' || A.NAME3, '') ||  ', ' ||   A.PLZ||  ' ' ||  A.ORT || ', ' || NVL( A.STRASSE,'') || NVL2( A.HAUSNUMMER,' ' ||  A.HAUSNUMMER,'')  FROM  "+bibE2.cTO()+".JT_ADRESSE A WHERE A.ID_ADRESSE = ID_ADRESSE_ZIEL )" +
				  	" ELSE CASE WHEN ID_ADRESSE_LAGER_ZIEL = " + idLager + " AND ID_ADRESSE_LAGER_START != ID_ADRESSE_START  " +
				  		" THEN (SELECT  A.NAME1 || ' ' || NVL2( A.NAME2, ' ' ||  A.NAME2,'') || NVL2( A.NAME3, ' ' || A.NAME3, '') ||  ', ' || A.PLZ ||  ' ' ||  A.ORT || ', ' || NVL( A.STRASSE,'') || NVL2( A.HAUSNUMMER,' ' ||  A.HAUSNUMMER,'') FROM  "+bibE2.cTO()+".JT_ADRESSE A WHERE A.ID_ADRESSE = ID_ADRESSE_START ) " +
				  				"		ELSE to_nchar('-') END " +
				  " END , " +
				  // SORTE
				  " CASE ID_ADRESSE_LAGER_ZIEL WHEN " + idLager + " THEN ANR1_EK || '-' || ANR2_EK || ' ' || ARTBEZ1_EK || ' (' || to_nchar(b1.ID_ARTIKEL_BEZ) || ')'  ELSE  CASE ID_ADRESSE_LAGER_START WHEN " + idLager + 
				  	" THEN  ANR1_VK || '-' || ANR2_VK || ' ' || ARTBEZ1_VK || ' (' || to_nchar(b2.ID_ARTIKEL_BEZ) || ')'  ELSE null END END " ,
				  // FROM-BLOCK
				  " " + bibE2.cTO()+".V"+bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF()+"_FUHREN"+
				  " LEFT OUTER JOIN "+bibE2.cTO()+".JT_ARTIKEL_BEZ b1 on b1.ID_ARTIKEL_BEZ = " + bibE2.cTO()+".V"+bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF()+"_FUHREN.ID_ARTIKEL_BEZ_EK"+
				  " LEFT OUTER JOIN "+bibE2.cTO()+".JT_ARTIKEL_BEZ b2 on b2.ID_ARTIKEL_BEZ = " + bibE2.cTO()+".V"+bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF()+"_FUHREN.ID_ARTIKEL_BEZ_VK",
				  // cSQLOrderBlock
				  null,
				  // -cSQLWhereBlockJoiningTables
				  " NVL(ALT_WIRD_NICHT_GEBUCHT,'N')='N'"+
				  " AND NVL(IST_STORNIERT,'N') = 'N'"+
				  " AND NVL(DELETED,'N') = 'N'" +
//				  " AND  ( (ID_ADRESSE_LAGER_START = " + idLager + " AND ID_WIEGEKARTE_LIEF IS NULL ) " +
//				  		"OR ( ID_ADRESSE_LAGER_ZIEL = " + idLager + " AND ID_WIEGEKARTE_ABN IS NULL ) ) " +
				  
// laut Fr. Meier müssen auch Streckenfuhren gefunden werden ( Streckenschein)				  
//				  " AND  ( ID_ADRESSE_LAGER_START = " + idLager    +
//			  	  "        OR  ID_ADRESSE_LAGER_ZIEL = " + idLager   +
//			  	  "      )" +
				  " " +
				  " AND STATUS_BUCHUNG IN (1,2,3) " +
				  " AND (" +
				  "			CASE " + idLager + " WHEN ID_ADRESSE_LAGER_ZIEL THEN DATUM_ABLADEN ELSE DATUM_AUFLADEN END  IS NULL " +
				  " 		OR " +
				  "			CASE " + idLager + " WHEN ID_ADRESSE_LAGER_ZIEL THEN NVL(PRUEFUNG_ABLADEMENGE,'N') ELSE NVL(PRUEFUNG_LADEMENGE,'N') END  = 'N' " +
				  " )" +
				  " " 
				  ,
				  // -cSQLWhereBlockForSelecting
				  " 'FU-' || TO_CHAR(id_vpos_tpa_fuhre) || '-' || to_CHAR(id_vpos_tpa_fuhre_ort) like '%#WERT#%' OR "+
				  " TO_CHAR(id_vpos_tpa_fuhre) || '-' || to_CHAR(id_vpos_tpa_fuhre_ort) like '%#WERT#%' OR "+
				  " 'BF-' || BUCHUNGSNR_FUHRE  like '%#WERT#%' OR "+
				  " UPPER(A_NAME1) LIKE UPPER('%#WERT#%') OR "+
				  " UPPER(L_NAME1) LIKE UPPER('%#WERT#%') OR "+
				  " UPPER(A_ORT) LIKE UPPER('%#WERT#%') OR " +
				  " UPPER(L_ORT) LIKE UPPER('%#WERT#%')" +
				  " OR UPPER(TRANSPORTKENNZEICHEN) LIKE UPPER('%#WERT#%') " +
				  " OR UPPER(A_PLZ) LIKE UPPER('%#WERT#%')  " +
				  " OR UPPER(L_PLZ) LIKE UPPER('%#WERT#%')  " 
//				  " OR CASE " + idLager + " WHEN ID_ADRESSE_LAGER_ZIEL THEN L_PLZ ELSE A_PLZ END = '#WERT#'" 
				  ,
				  //cSQLqueryForUniqueSearch
				  null,
				  //charForUniqueSearch
				  null,
				  // cCOMPLETE_SQL_FOR_label
				  " SELECT TO_CHAR(ID_VPOS_TPA_FUHRE)||'-'||NVL(TO_CHAR(ID_VPOS_TPA_FUHRE_ORT),'') || ' / ' ||" +
				  " case ID_ADRESSE_LAGER_START when " + idLager + " then A_NAME1 else L_NAME1 end ||' ' ||"+
				  " case ID_ADRESSE_LAGER_START when " + idLager + " then A_NAME2 else L_NAME2 end ||' ' "+		
				  " FROM "+bibE2.cTO()+".V"+
			  		bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF()+"_FUHREN WHERE TO_CHAR(id_vpos_tpa_fuhre) ||'-'|| to_CHAR(id_vpos_tpa_fuhre_ort) = '#WERT#'",
			  	  // INSETS_For_Components
			  	  E2_INSETS.I_0_5_10_5, 
			  	  // ShowEraser
			  	  true,
			  	  // iMaxresults
			  	  0);
		
		
			// verketteter ActionAgent anhängen
			this.ActionAfterThisActionFound = action_after_found;
			this.ActionAfterThisActionDeleted = action_after_deleted;
			
		
			this.oTF_Fuhre = TF_Fuhre;
			this.oTF_Fuhre_ort = TF_Fuhre_ort;
			
			this.set_oMaskActionAfterMaskValueIsFound(new ownActionAfterFound());
			this.get_oSeachBlock().set_bAllowEmptySearchField(true);
			
			this.get_oButtonErase().add_oActionAgent(new actionAfterErase());
			this.set_FormatterForButtons(new ownButtonFormatter());
	}
	
	
	
	private class ownActionAfterFound extends XX_MaskActionAfterFoundNonDB
	{
		@Override
		public void doMaskSettingsAfterValueWrittenInMaskField(String maskValue, MyE2_MaskSearchField searchField, boolean afterAction) throws myException
		{
			
			Vector<String> vIDs = bibALL.TrenneZeile(maskValue, "-");

			String cID_VPOS_TPA_FUHRE = 	vIDs.get(0);
			String cID_VPOS_TPA_FUHRE_ORT = "";
			if (vIDs.size()==2)
			{
				cID_VPOS_TPA_FUHRE_ORT=vIDs.get(1);
			}
			
			WK_SearchFieldFuhre oThis = WK_SearchFieldFuhre.this;
			
			oThis.oTF_Fuhre.setText(cID_VPOS_TPA_FUHRE);
			oThis.oTF_Fuhre_ort.setText(cID_VPOS_TPA_FUHRE_ORT);
			if (oThis.ActionAfterThisActionFound != null){
				ActionAfterThisActionFound.executeAgentCode(null);
			}
//			oThis.oButtonAfterFound.doAction();
		}
	}
	
	
	private class actionAfterErase extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			WK_SearchFieldFuhre oThis = WK_SearchFieldFuhre.this;
			
			oThis.oTF_Fuhre.setText("");
			oThis.oTF_Fuhre_ort.setText("");
			if (oThis.ActionAfterThisActionDeleted != null){
				ActionAfterThisActionDeleted.executeAgentCode(null);
			}
		}
		
	}
	
	
	private class ownButtonFormatter extends XX_FormaterForFoundButtonsNonDB
	{

		@Override
		public void DO_Format(XX_Button4SearchResultList button)
		{
			String maskValue = button.EXT().get_C_MERKMAL();

			Vector<String> vIDs = bibALL.TrenneZeile(maskValue, "-");

			String cID_VPOS_TPA_FUHRE = 	vIDs.get(0);
			String cID_VPOS_TPA_FUHRE_ORT = "";
		}

		@Override
		public void RESET()
		{
			
		}

//		@Override
//		public Component DO_Transform(MyE2_Button oButton) throws myException
//		{
//			return null;
//		}

		@Override
		public void RESET_ROW(XX_Button4SearchResultList[] buttonsInRow) throws myException
		{
		}
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = -5378358855407093924L;


	
	@Override
	public E2_BasicModuleContainer get_ownContainer() throws myException
	{
		return new ownE2_Container();
	}

	private class ownE2_Container extends E2_BasicModuleContainer
	{
		public ownE2_Container()
		{
			super();
		}
	}
	

}

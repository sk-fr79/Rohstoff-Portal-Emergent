package rohstoff.Echo2BusinessLogic.BAM_IMPORT;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.MaskSearchField.MyE2_MaskSearchField;
import panter.gmbh.Echo2.components.MaskSearchField.XX_FormaterForFoundButtonsNonDB;
import panter.gmbh.Echo2.components.MaskSearchField.XX_MaskActionAfterFoundNonDB;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.XX_Button4SearchResultList;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class BAM_IMPORT_SearchFieldFuhre extends MyE2_MaskSearchField
{
	private XX_ActionAgent ActionAfterThisActionFound 	= null;
	private XX_ActionAgent ActionAfterThisActionDeleted = null;	

	
	// internen Felder für die IDs
	private String 			m_sID_Fuhre 		= null;
	private String 			m_sID_FuhreOrt 		= null;
	

	private static final String sSELECTLAGER = ""
			+ "( SELECT A.ID_ADRESSE   from JT_ADRESSE A WHERE A.ID_ADRESSE = " + bibALL.get_ID_ADRESS_MANDANT() 
			+ " UNION ALL "
			+ " SELECT  A.ID_ADRESSE  FROM JT_ADRESSE A "
			+ " INNER JOIN JT_LIEFERADRESSE L ON A.ID_MANDANT = L.ID_MANDANT AND A.ID_ADRESSE = L.ID_ADRESSE_LIEFER "
			+ " WHERE A.ID_MANDANT = " + bibALL.get_ID_MANDANT()  
			+ " AND   L.ID_ADRESSE_BASIS = " + bibALL.get_ID_ADRESS_MANDANT() + " AND     A.SONDERLAGER is null )";
	
	

	public BAM_IMPORT_SearchFieldFuhre(
			XX_ActionAgent 	action_after_found,
			XX_ActionAgent 	action_after_deleted,
			boolean 		bShowEraser ) throws myException {
		
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
				  " CASE WHEN ID_ADRESSE_LAGER_ZIEL in " + sSELECTLAGER + " THEN 'WE' ELSE CASE WHEN ID_ADRESSE_LAGER_START in " + sSELECTLAGER + " then 'WA' ELSE null END END ,"+
				  // KENNZEICHEN
				  " CASE NVL(TRANSPORTKENNZEICHEN, to_nchar('N')) WHEN to_nchar('N') THEN to_nchar('k.A.') ELSE ( TRANSPORTKENNZEICHEN || ' ') END || " + 
				  " CASE NVL(ANHAENGERKENNZEICHEN, to_nchar('N')) WHEN to_nchar('N') THEN null ELSE (' / ' || ANHAENGERKENNZEICHEN ) END , " +
				  // LIEFERORT
				  " CASE WHEN ID_ADRESSE_LAGER_START in " + sSELECTLAGER + "  THEN A_NAME1  ||  ' ' || A_NAME2 ||  ' ' || A_NAME3 ELSE L_NAME1  ||  ' ' || L_NAME2 ||  ' ' || L_NAME3 end , " +
				  " CASE WHEN ID_ADRESSE_LAGER_START in " + sSELECTLAGER + " THEN A_PLZ ||  ' ' ||   A_ORT || ', ' || A_STRASSE ||  ' ' || A_HAUSNUMMER  ELSE L_PLZ  ||  ' ' ||  L_ORT || ', ' ||  L_STRASSE ||  ' ' ||  L_HAUSNUMMER   end ,"+
				  // FIRMENORT
				  " CASE WHEN ID_ADRESSE_LAGER_START IN " + sSELECTLAGER + " AND ID_ADRESSE_LAGER_ZIEL != ID_ADRESSE_ZIEL " +  
				  	" THEN  (SELECT  A.NAME1 || ' ' || NVL2( A.NAME2, ' ' ||  A.NAME2,'') || NVL2( A.NAME3, ' ' || A.NAME3, '') ||  ', ' ||   A.PLZ||  ' ' ||  A.ORT || ', ' || NVL( A.STRASSE,'') || NVL2( A.HAUSNUMMER,' ' ||  A.HAUSNUMMER,'')  FROM  "+bibE2.cTO()+".JT_ADRESSE A WHERE A.ID_ADRESSE = ID_ADRESSE_ZIEL )" +
				  	" ELSE CASE WHEN ID_ADRESSE_LAGER_ZIEL in " + sSELECTLAGER + " AND ID_ADRESSE_LAGER_START != ID_ADRESSE_START  " +
				  		" THEN (SELECT  A.NAME1 || ' ' || NVL2( A.NAME2, ' ' ||  A.NAME2,'') || NVL2( A.NAME3, ' ' || A.NAME3, '') ||  ', ' || A.PLZ ||  ' ' ||  A.ORT || ', ' || NVL( A.STRASSE,'') || NVL2( A.HAUSNUMMER,' ' ||  A.HAUSNUMMER,'') FROM  "+bibE2.cTO()+".JT_ADRESSE A WHERE A.ID_ADRESSE = ID_ADRESSE_START ) " +
				  				"		ELSE to_nchar('-') END " +
				  " END , " +
				  // SORTE
				  " CASE WHEN ID_ADRESSE_LAGER_ZIEL in " + sSELECTLAGER + " THEN ANR1_EK || '-' || ANR2_EK || ' ' || ARTBEZ1_EK || ' (' || to_nchar(b1.ID_ARTIKEL_BEZ) || ')'  ELSE  CASE WHEN ID_ADRESSE_LAGER_START in " + sSELECTLAGER + 
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
				  " AND NVL(DELETED,'N') = 'N'" 
				  ,
				  // -cSQLWhereBlockForSelecting
				  " 'FU-' || TO_CHAR(id_vpos_tpa_fuhre) || '-' || to_CHAR(id_vpos_tpa_fuhre_ort) like '%#WERT#%' OR "+
				  " TO_CHAR(id_vpos_tpa_fuhre) || '-' || to_CHAR(id_vpos_tpa_fuhre_ort) like '%#WERT#%' OR "+
				  " 'BF-' || BUCHUNGSNR_FUHRE  like '%#WERT#%' OR "+
				  " TO_CHAR(id_vpos_tpa_fuhre) like '%#WERT#%' OR "+
				  " TO_CHAR(id_vpos_tpa_fuhre_ort) like '%#WERT#%' OR "+
				  " UPPER(A_NAME1) LIKE UPPER('%#WERT#%') OR "+
				  " UPPER(L_NAME1) LIKE UPPER('%#WERT#%') OR "+
				  " UPPER(A_ORT) LIKE UPPER('%#WERT#%') OR " +
				  " UPPER(L_ORT) LIKE UPPER('%#WERT#%')" +
				  " OR UPPER(TRANSPORTKENNZEICHEN) LIKE UPPER('%#WERT#%') " +
				  " OR UPPER(A_PLZ) LIKE UPPER('%#WERT#%')  " +
				  " OR UPPER(L_PLZ) LIKE UPPER('%#WERT#%')  " 

				  ,
				  //cSQLqueryForUniqueSearch
				  null,
				  //charForUniqueSearch
				  null,
				  // cCOMPLETE_SQL_FOR_label
				  " SELECT TO_CHAR(ID_VPOS_TPA_FUHRE)||'-'||NVL(TO_CHAR(ID_VPOS_TPA_FUHRE_ORT),'') || ' / ' ||" +
				  " case WHEN ID_ADRESSE_LAGER_START in " + sSELECTLAGER + " then A_NAME1 else L_NAME1 end ||' ' ||"+
				  " case WHEN ID_ADRESSE_LAGER_START in " + sSELECTLAGER + " then A_NAME2 else L_NAME2 end ||' ' "+		
				  " FROM "+bibE2.cTO()+".V"+
			  		bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF()+"_FUHREN WHERE TO_CHAR(id_vpos_tpa_fuhre) ||'-'|| to_CHAR(id_vpos_tpa_fuhre_ort) = '#WERT#'",
			  	  // INSETS_For_Components
			  	  E2_INSETS.I_0_0_10_0, 
			  	  // ShowErasertrue
			  	  bShowEraser,
			  	  // iMaxresults
			  	  200);
		
		
			// verketteter ActionAgent anhängen
			this.ActionAfterThisActionFound = action_after_found;
			this.ActionAfterThisActionDeleted = action_after_deleted;
			
			this.set_oMaskActionAfterMaskValueIsFound(new ownActionAfterFound());
			this.get_oSeachBlock().set_bAllowEmptySearchField(true);
			
			this.get_oButtonErase().add_oActionAgent(new actionAfterErase());
			this.set_FormatterForButtons(new ownButtonFormatter());
			

			
	}
	
	
	
	/**
	 * Initialisiert die Fuhrensuche mit den vorgegebenen IDs 
	 */
	public void set_Fuhre (String idFuhre, String idFuhrenOrt){
		m_sID_Fuhre 		= idFuhre;
		m_sID_FuhreOrt 	= idFuhrenOrt;
		
		String sFuhre = "";
		if (!bibALL.isEmpty(m_sID_Fuhre)){
			sFuhre = m_sID_Fuhre + "-" + bibALL.null2leer(m_sID_FuhreOrt);
			get_oTextFieldForSearchInput().setText(sFuhre);
			try {
				FillLabelWithDBQuery(sFuhre);
			} catch (myException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * gibt die aktuell gefundene Fuhrenid zurück
	 * @return
	 */
	public String get_IDFuhre (){
		return m_sID_Fuhre;
	}
	
	/**
	 * gibt die aktuell gefundene Fuhrenortid zurück
	 * @return
	 */
	public String get_IDFuhreOrt(){
		return m_sID_FuhreOrt;
	}
	
	
	
	private class ownActionAfterFound extends XX_MaskActionAfterFoundNonDB
	{
		@Override
		public void doMaskSettingsAfterValueWrittenInMaskField(String maskValue, MyE2_MaskSearchField searchField, boolean afterAction) throws myException
		{
			
			Vector<String> vIDs = bibALL.TrenneZeile(maskValue, "-");

			m_sID_Fuhre = 	vIDs.get(0);
			m_sID_FuhreOrt = "";
			if (vIDs.size()==2)
			{
				m_sID_FuhreOrt=vIDs.get(1);
			}
			
			
			
			BAM_IMPORT_SearchFieldFuhre oThis = BAM_IMPORT_SearchFieldFuhre.this;
			
			if (oThis.ActionAfterThisActionFound != null){
				ActionAfterThisActionFound.executeAgentCode(null);
			}

		}
	}
	
	
	private class actionAfterErase extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			BAM_IMPORT_SearchFieldFuhre oThis = BAM_IMPORT_SearchFieldFuhre.this;
			
			m_sID_Fuhre 		= "";
			m_sID_FuhreOrt 		= "";
			
//			oThis.oTF_Fuhre.setText("");
//			oThis.oTF_Fuhre_ort.setText("");
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

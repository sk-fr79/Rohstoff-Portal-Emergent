package rohstoff.Echo2BusinessLogic.WIEGEKARTE;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.COMP.SearchDialog.SearchDialog_Base;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_WIEGEKARTE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;


/**
 * 
 * @author manfred
 * @date 06.10.2017
 *
 */
public class WK_LIST_BT_CONNECT_FUHRE_to_WK extends MyE2_ButtonInLIST
{

	private String  _sID_Fuhre = null;
	private String  _sID_Fuhre_Ort = null;
	private E2_NavigationList _oNaviList;
	private String 	_tableName;
	
	public WK_LIST_BT_CONNECT_FUHRE_to_WK(String TableName)
	{
		super(E2_ResourceIcon.get_RI("suche_wk.png"),E2_ResourceIcon.get_RI("leer.png"));
		this.setToolTipText(new MyE2_String("Zugehörige Wiegekarte suchen und Verknüpfen...").CTrans());
		this._tableName = TableName;
		this.add_oActionAgent(new ownActionAgent());
	}

	
	
	private class actionSearchResult extends XX_ActionAgent{
		/**
		 * @author manfred
		 * @date 14.08.2017
		 *
		 */
		private SearchDialog_Base oDialog = null;
		public actionSearchResult(SearchDialog_Base oDialogCalling) {
			oDialog = oDialogCalling;
		}
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			String sResult = oDialog.getResultValue();
			String sID_WK = null;
			
			if (!bibALL.isEmpty(sResult)){
				sID_WK = sResult;
			}
			
			WK_WiegekartenHandler wkHandler = new WK_WiegekartenHandler( sID_WK);
			wkHandler.setFuhreInWiegekarte(_sID_Fuhre, _sID_Fuhre_Ort);
			
			_oNaviList._REBUILD_ACTUAL_SITE("");
		}
	}

	
	
	private class ownActionAgent extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			WK_LIST_BT_CONNECT_FUHRE_to_WK oButton = WK_LIST_BT_CONNECT_FUHRE_to_WK.this;
			
			E2_ComponentMAP _oComponentMap = oButton.EXT().get_oComponentMAP();
			_oComponentMap.set_Marker(true);

			_oNaviList = _oComponentMap.get_oNavigationList_This_Belongs_to();
			
			String wewa				=null; 
			String idAdresseKunde 	=null;
			String kennzeichen  	=null;
			String idArtikelBez 	=null;
			String datum			=null;
			
			if (_tableName.equals(VPOS_TPA_FUHRE._tab().fullTableName())){
				_sID_Fuhre 		= oButton.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_UnFormatedValue(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre.fn());
				_sID_Fuhre_Ort 	= null;
				
				if (S.isFull(_sID_Fuhre))
				{

					RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA_FUHRE(_sID_Fuhre);
					String idAdrStart				= recFuhre.get_ID_ADRESSE_START_cUF();
					
					boolean bWA 	= (bibALL.get_ID_ADRESS_MANDANT().equals(idAdrStart)) ;
					
					wewa 			= bWA ? "WA" : "WE";
					idArtikelBez 	= bWA ? recFuhre.get_ID_ARTIKEL_BEZ_VK_cUF():recFuhre.get_ID_ARTIKEL_BEZ_EK_cUF();
					datum			= bWA ? recFuhre.get_DATUM_AUFLADEN_cUF() : recFuhre.get_DATUM_ABLADEN_cUF();
					idAdresseKunde  = bWA ? recFuhre.get_ID_ADRESSE_LAGER_ZIEL_cUF() : recFuhre.get_ID_ADRESSE_LAGER_START_cUF();
					kennzeichen     = bibALL.isEmpty(recFuhre.get_ANHAENGERKENNZEICHEN_cUF()) ? recFuhre.get_TRANSPORTKENNZEICHEN_cUF() : recFuhre.get_TRANSPORTKENNZEICHEN_cUF() + " / " + recFuhre.get_ANHAENGERKENNZEICHEN_cUF_NN("-");  
				
				}	
			} else if (_tableName.equals(VPOS_TPA_FUHRE_ORT._tab().fullTableName())) {
				_sID_Fuhre 		= oButton.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_UnFormatedValue(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre.fn());
				_sID_Fuhre_Ort 	= oButton.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_UnFormatedValue(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre_ort.fn());
				
				RECORD_VPOS_TPA_FUHRE_ORT  recFuhreORT 	= new RECORD_VPOS_TPA_FUHRE_ORT(_sID_Fuhre_Ort);
				RECORD_VPOS_TPA_FUHRE 	   recFuhre		= recFuhreORT.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre();	
				
				String def_quelle_ziel			= recFuhreORT.get_DEF_QUELLE_ZIEL_cUF();
				
				boolean bWA 	= def_quelle_ziel.equals("VK") ;
				wewa 			= bWA ? "WE" : "WA";
				idArtikelBez 	= recFuhreORT.get_ID_ARTIKEL_BEZ_cUF();
				datum			= recFuhreORT.get_DATUM_LADE_ABLADE_cUF();
				idAdresseKunde  = "";
				kennzeichen     = bibALL.isEmpty(recFuhre.get_ANHAENGERKENNZEICHEN_cUF()) ? recFuhre.get_TRANSPORTKENNZEICHEN_cUF() : recFuhre.get_TRANSPORTKENNZEICHEN_cUF() + " / " + recFuhre.get_ANHAENGERKENNZEICHEN_cUF_NN("-");  
					
			}
			

			// den eigentlichen Suchdialog aufrufen	
			WK_SearchDialogWiegekarte search = new WK_SearchDialogWiegekarte();
			search.set_iMaxResults(500);

			search.getSelections_list().getSelectorEntry(WK_SearchDialogWiegekarte.ENUM_SELEKTORLIST.KUNDE.name()).set_Value(idAdresseKunde).set_Active(!bibALL.isEmpty(idAdresseKunde));
			search.getSelections_list().getSelectorEntry(WK_SearchDialogWiegekarte.ENUM_SELEKTORLIST.WEWA.name()).set_Value(wewa).set_Active(!bibALL.isEmpty(wewa));
			search.getSelections_list().getSelectorEntry(WK_SearchDialogWiegekarte.ENUM_SELEKTORLIST.KENNZEICHEN.name()).set_Value(kennzeichen).set_Active(!bibALL.isEmpty(kennzeichen));
			search.getSelections_list().getSelectorEntry(WK_SearchDialogWiegekarte.ENUM_SELEKTORLIST.SORTE.name()).set_Value(idArtikelBez).set_Active(!bibALL.isEmpty(idArtikelBez));
			search.getSelections_list().getSelectorEntry(WK_SearchDialogWiegekarte.ENUM_SELEKTORLIST.DATUM_AB.name()).set_Value(datum).set_Active(!bibALL.isEmpty(datum));
			search.getSelections_list().getSelectorEntry(WK_SearchDialogWiegekarte.ENUM_SELEKTORLIST.DATUM_BIS.name()).set_Value(datum).set_Active(!bibALL.isEmpty(datum));
			
			search.setAgentAfterFound(new actionSearchResult(search));
			
			
			// Anzeige für die bestehenden Daten der Fuhre im Suchdialog
			E2_Grid _gridDesc ;
			_gridDesc = new E2_Grid();
//					._s(2)
//					._ins(E2_INSETS.I_10_20_10_5)
//					._a(new MyE2_Label("Gewicht der Wiegekarte: "), new RB_gld()._ins(E2_INSETS.I_10_20_10_5))
//					._a(new MyE2_Label(sGewicht), new RB_gld()._ins(E2_INSETS.I_10_20_10_5)._al(Alignment.ALIGN_RIGHT))
//				;
			search.set_extra_headerArea(_gridDesc);

			// Anzeigen des Dialogs
			search.show();
			
		}
	}

	

	
	/**
	 * Ermittelt die FuhrenID aus der ComponentMap
	 * @author manfred
	 * @date 10.10.2017
	 *
	 * @return
	 */
	private String getIDFuhre(){
		if (_sID_Fuhre == null ) {
			WK_LIST_BT_CONNECT_FUHRE_to_WK oButton = WK_LIST_BT_CONNECT_FUHRE_to_WK.this;
			
			SQLResultMAP oResultMap     = oButton.EXT().get_oComponentMAP().get_oInternalSQLResultMAP();
			
			if (oResultMap != null && oResultMap.get_oSQLFieldMAP().containsKey(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre.fn())){
				try {
					_sID_Fuhre = oResultMap.get_UnFormatedValue(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre.fn(), "");
				} catch (myException e) {
					_sID_Fuhre = null;
				}
			}
		}
		
		return _sID_Fuhre;
	}
	
	
	
	/**
	 * Ermittelt die FuhrenortID aus der Component-Map
	 * @author manfred
	 * @date 10.10.2017
	 *
	 * @return
	 */
	private String getIDFuhreOrt(){
		if (_sID_Fuhre_Ort == null ) {
			WK_LIST_BT_CONNECT_FUHRE_to_WK oButton = WK_LIST_BT_CONNECT_FUHRE_to_WK.this;

			SQLResultMAP oResultMap     = oButton.EXT().get_oComponentMAP().get_oInternalSQLResultMAP();
			
			if (oResultMap != null && oResultMap.get_oSQLFieldMAP().containsKey(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre_ort.fn())){
				try {
					_sID_Fuhre_Ort = oResultMap.get_UnFormatedValue(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre_ort.fn(), "");
				} catch (myException e) {
					_sID_Fuhre_Ort = null;
				}
			}
		}
		return _sID_Fuhre_Ort;
	}
	
	
	
	/**
	 * ermittelt, ob eine Wiegekarte zugeordnet ist über einen Select auf die Wiegekaten
	 * @author manfred
	 * @date 10.10.2017
	 *
	 * @return
	 */
	private boolean bHasWiegekarte() {
		boolean bRet = true;
		
		SEL sel;
		String sSQL = "";
		String sIDFuhre = getIDFuhre();
		String sIDFuhreOrt = getIDFuhreOrt();
		
		
		if (!bibALL.isEmpty(sIDFuhreOrt)){
			// fuhrenort
			try {
				sel= new SEL()
						.FROM(WIEGEKARTE._tab())
						.WHERE( new And(WIEGEKARTE.id_vpos_tpa_fuhre_ort,COMP.EQ , "#WERT#") )
						;
				sSQL = sel.s();
				sSQL = sSQL.replace("#WERT#", sIDFuhreOrt );
			
			} catch (Exception e) {
				return bRet;
			}
			
		} else {
			// fuhre...
			try {
				sel= new SEL()
						.FROM(WIEGEKARTE._tab())
						.WHERE( new And(WIEGEKARTE.id_vpos_tpa_fuhre,COMP.EQ , "#WERT#") )
						;
				sSQL = sel.s();
				sSQL = sSQL.replace("#WERT#", sIDFuhre );
				
			} catch (Exception e) {
				return bRet;
			}

		}
	
		
		RECLIST_WIEGEKARTE rl;
		try {
			rl = new RECLIST_WIEGEKARTE(sSQL);
			int rows = rl.size(); 
			bRet = rows > 0;
			
		} catch (myException e) {
			e.printStackTrace();
		}
		return bRet;
	}
	

	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		
		WK_LIST_BT_CONNECT_FUHRE_to_WK oButton = new WK_LIST_BT_CONNECT_FUHRE_to_WK(_tableName);
		oButton.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oButton));
		return oButton;
	}

	
	/*
	 * Enabled nur, wenn man die Verbindung setzen muss,
	 * Falls die Verbindung zur Fuhre schon gemacht ist, muss man in die Maske um sie zu trennen.
	 */
	public void set_bEnabled_For_Edit(boolean _enabled) throws myException
	{
		WK_LIST_BT_CONNECT_FUHRE_to_WK oButton = WK_LIST_BT_CONNECT_FUHRE_to_WK.this;
		

		boolean bIstLagerfuhre = false;
		
		
		String idAdresseLagerStart = "";
		String idAdresseLagerZiel = "";
		
		
		try {
			SQLResultMAP oResultMap = oButton.EXT().get_oComponentMAP().get_oInternalSQLResultMAP();
		
			if (_tableName.equals(VPOS_TPA_FUHRE._tab().fullTableName())){
				idAdresseLagerStart 	= oResultMap.get_UnFormatedValue(VPOS_TPA_FUHRE.id_adresse_lager_start.fn(), "");
				idAdresseLagerZiel 	= oResultMap.get_UnFormatedValue(VPOS_TPA_FUHRE.id_adresse_lager_ziel.fn(), "");
			} else {
				idAdresseLagerStart = oResultMap.get_UnFormatedValue(VPOS_TPA_FUHRE_ORT.id_adresse_lager.fn());
			}
			
			
			// prüfen, ob die Lageradressen in den Adressen für die Waagen vorkommen.
			if (bibSES.get__EIGENE_WAAGE_LAGER_ADRESSEN(false).contains(idAdresseLagerZiel) || bibSES.get__EIGENE_WAAGE_LAGER_ADRESSEN(false).contains(idAdresseLagerStart) ){
				bIstLagerfuhre = true;
			}
			
		} catch (Exception e) {
			// wenn keine Fuhre gefunden wurde, dann auch keine Wiegekarte suchen
			bIstLagerfuhre = false;
		}

		boolean enabled = !this.bHasWiegekarte() && bIstLagerfuhre;
		
		this.setEnabled(enabled);
		this.setVisible(enabled);
	}
	
	
}

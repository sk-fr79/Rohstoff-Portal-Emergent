package rohstoff.Echo2BusinessLogic.WIEGEKARTE;

import java.util.StringTokenizer;

import org.apache.batik.css.engine.value.svg.AlignmentBaselineManager;
import org.apache.lucene.queryparser.flexible.standard.processors.AllowLeadingWildcardProcessor;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.ALIGN_HORIZONTAL;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.ALIGN_VERTICAL;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.SearchDialog.SearchDialog_Base;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.ZUSATZFELDER.MZ_BUTTON_TO_DEF_ZUSATZFELDER.ownComponentMAP_ZusatzFelder;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS.WAEGUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_WAEGUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WAEGUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WIEGEKARTE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;


/**
 * 
 * @author manfred
 * @date 04.10.2017
 *
 */
public class WK_LIST_BT_CONNECT_to_FUHRE extends MyE2_ButtonInLIST
{

	private boolean _bConnected = true;
	private String  _sID_WIEGEKARTE = null;
	private E2_NavigationList _oNaviList;
	
	public WK_LIST_BT_CONNECT_to_FUHRE()
	{
		super(E2_ResourceIcon.get_RI("suche_fuhre.png"),E2_ResourceIcon.get_RI("leer.png"));
		this.setToolTipText(new MyE2_String("Zugehörige Fuhre suchen und Verknüpfen...").CTrans());
		
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
			String sIDFuhre = null;
			String sIDFuhreOrt = null;
			
			if (!bibALL.isEmpty(sResult)){
				int i = 0;
				StringTokenizer st = new StringTokenizer(sResult,"#");
				while (st.hasMoreTokens()){
					String s  = st.nextToken();
					switch (i) {
					case 0:
						sIDFuhre = s;
						break;
					case 1:
						sIDFuhreOrt = s;

					default:
						break;
					}
					i++;
				}
			}
			
			WK_WiegekartenHandler wkHandler = new WK_WiegekartenHandler(_sID_WIEGEKARTE);
			wkHandler.setFuhreInWiegekarte(sIDFuhre, sIDFuhreOrt);
			
			_oNaviList._REBUILD_ACTUAL_SITE("");
			
//				bibMSG.add_MESSAGE(new MyE2_Info_Message(oDialog.getResultValue()));
				//setText(oDialog.getResultValue());
		}
	}

	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			WK_LIST_BT_CONNECT_to_FUHRE oWK = WK_LIST_BT_CONNECT_to_FUHRE.this;
			
			E2_ComponentMAP _oComponentMap = oWK.EXT().get_oComponentMAP();
			_oComponentMap.set_Marker(true);

			_oNaviList = _oComponentMap.get_oNavigationList_This_Belongs_to();
			
			_sID_WIEGEKARTE = oWK.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			
			if (S.isFull(_sID_WIEGEKARTE))
			{
				RECORD_WIEGEKARTE  recWK = new RECORD_WIEGEKARTE(_sID_WIEGEKARTE);
				String idLager 			= recWK.get_ID_ADRESSE_LAGER_cUF();
				String idAdresse		= recWK.get_ID_ADRESSE_LIEFERANT_cUF();
				String idArtikelBez 	= recWK.get_ID_ARTIKEL_BEZ_cUF();
				String wewa				= (recWK.get_IST_LIEFERANT_cUF_NN("N").equals("Y") ? "WE" : "WA");
				String kennzeichen  	= recWK.get_KENNZEICHEN_cUF();
				RECLIST_WAEGUNG rl 		= recWK.get_DOWN_RECORD_LIST_WAEGUNG_id_wiegekarte("nvl(" + WAEGUNG.storno.fn() + ",'N') = 'N'", WAEGUNG.waegung_pos.fn(), false);
				RECORD_WAEGUNG  rec		= rl.get(0);
				String datum			= (rec != null ? rec.get_DATUM_cUF() : "")  ;
				String sGewicht 		= recWK.get_GEWICHT_NACH_ABZUG_FUHRE_cF_NN("N/A");
				
				WK_SearchDialogFuhre search = new WK_SearchDialogFuhre(idLager);
				search.set_iMaxResults(500);

				search.getSelections_list().getSelectorEntry(WK_SearchDialogFuhre.ENUM_SELEKTORLIST.ID_ADRESSE.name()).set_Value(idAdresse).set_Active(!bibALL.isEmpty(idAdresse));
				search.getSelections_list().getSelectorEntry(WK_SearchDialogFuhre.ENUM_SELEKTORLIST.WEWA.name()).set_Value(wewa).set_Active(!bibALL.isEmpty(wewa));
				search.getSelections_list().getSelectorEntry(WK_SearchDialogFuhre.ENUM_SELEKTORLIST.KENNZEICHEN.name()).set_Value(kennzeichen).set_Active(!bibALL.isEmpty(kennzeichen));
				search.getSelections_list().getSelectorEntry(WK_SearchDialogFuhre.ENUM_SELEKTORLIST.SORTE.name()).set_Value(idArtikelBez).set_Active(!bibALL.isEmpty(idArtikelBez));
				search.getSelections_list().getSelectorEntry(WK_SearchDialogFuhre.ENUM_SELEKTORLIST.DATUM_AB.name()).set_Value(datum).set_Active(!bibALL.isEmpty(datum));
				search.getSelections_list().getSelectorEntry(WK_SearchDialogFuhre.ENUM_SELEKTORLIST.DATUM_BIS.name()).set_Value(datum).set_Active(!bibALL.isEmpty(datum));
				
				search.setAgentAfterFound(new actionSearchResult(search));
				
				
				// Anzeige für die bestehenden Daten der Wiegekarte im Suchdialog
				E2_Grid _gridDesc ;
				_gridDesc = new E2_Grid()
					._s(2)
					._ins(E2_INSETS.I_10_20_10_5)
					._a(new MyE2_Label("Gewicht der Wiegekarte: "), new RB_gld()._ins(E2_INSETS.I_10_20_10_5))
					._a(new MyE2_Label(sGewicht), new RB_gld()._ins(E2_INSETS.I_10_20_10_5)._al(Alignment.ALIGN_RIGHT))
				;
				

				search.set_extra_headerArea(_gridDesc);
				// Anzeigen des Dialogs
				search.show();
			}
		}
	}

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		WK_LIST_BT_CONNECT_to_FUHRE oButton = new WK_LIST_BT_CONNECT_to_FUHRE();
		oButton.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oButton));
		return oButton;
	}
	
	
	/*
	 * Enabled nur, wenn man die Verbindung setzen muss,
	 * Falls die Verbindung zur Fuhre schon gemacht ist, muss man in die Maske um sie zu trennen.
	 */
	public void set_bEnabled_For_Edit(boolean _enabled) throws myException
	{
		
		boolean enabled = !this._bConnected;
		
		this.setEnabled(enabled);
		if (this.get_oImgDisabled() != null && this.get_oImgEnabled() != null)
		{
			if (enabled)
				this.setIcon(this.get_oImgEnabled());
			else
				this.setIcon(this.get_oImgDisabled());
		}
			
	}
	
	


	/**
	 * Setzt den Schalter zur Anzeige der Suche nach der Fuhre 
	 * @author manfred
	 * @date 29.09.2017
	 *
	 * @param bConnected
	 */
	public void set_Connected(boolean bConnected){
		this._bConnected = bConnected;
		
		if (_bConnected){
			this.__setImages(E2_ResourceIcon.get_RI("buchungen_trennen.png"),E2_ResourceIcon.get_RI("leer.png"));
		} else {
			this.__setImages(E2_ResourceIcon.get_RI("suche_fuhre.png"),E2_ResourceIcon.get_RI("leer.png"));
		}
	}

	
	

	
	
}

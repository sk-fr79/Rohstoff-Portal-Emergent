/**
 * rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN
 * @author martin
 * @date 17.06.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_V2;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.dataTools.TERM.VglNotNull;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH.KV_Const;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VposTpaFuhre;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VposTpaFuhreOrt;

/**
 * @author martin
 * @date 17.06.2019
 *
 */
public class FU__ListAnzeigeAblaufFakturierungsFrist extends E2_UniversalListComponent {


	public FU__ListAnzeigeAblaufFakturierungsFrist() {
		super();
		this._setSize(KV_Const.breiteWarnGrid,KV_Const.breiteWarnGrid);
		this.EXT().set_bLineWrapListHeader(true);
		this.EXT().setLongString4ColumnSelection(S.ms("Anzeige Ablauf Fakturierungsfrist"));
		this.EXT().set_oColExtent(new Extent(2*KV_Const.breiteWarnGrid));
		this.EXT().set_oLayout_ListTitelElement(new RB_gld()._center_top()._col_back_d());
	}		
		


	@Override
	public String key() throws myException {
		return "FU__ListAnzeigeAblaufFakturierungsFrist<9224c562-90dc-11e9-bc42-526af7764f64>";
	}

	@Override
	public String userText() throws myException {
		return "Fakt. Frist";
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.components.DB.MyE2IF_DB_SimpleComponent#prepare_ContentForNew(boolean)
	 */
	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		_clear();
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.components.DB.E2_UniversalListComponent#populate(panter.gmbh.indep.dataTools.SQLResultMAP)
	 */
	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		this._clear();
		
		
		try {
			
			//zuerst die vorhandenen infos nuetzen
			Long    countOrte = 		resultMap.get_LActualDBValue("AA_ANZAHL_ZUSATZORTE", false);
			String  idFremdAuftrag = 	resultMap.get_UnFormatedValue("ID_ADRESSE_FREMDAUFTRAG");
			boolean ohneAbrechnung = 	resultMap.get_booleanActualValue("OHNE_ABRECHNUNG");
			
			if (ohneAbrechnung || (!S.NN(idFremdAuftrag,"0").trim().equals("0"))) {
				this._a(new RB_lab()._t("-")._ttt("Fremdauftrag/Ohne Abrechnung"), new RB_gld()._center_mid());
				this._a(new RB_lab()._t("-")._ttt("Fremdauftrag/Ohne Abrechnung"), new RB_gld()._center_mid());
			} else if (		resultMap.getUfs(VPOS_TPA_FUHRE.id_adresse_start).trim().equals(bibALL.get_ID_ADRESS_MANDANT())
						&& 	resultMap.getUfs(VPOS_TPA_FUHRE.id_adresse_ziel).trim().equals(bibALL.get_ID_ADRESS_MANDANT())
						&& 	countOrte.intValue()==0) {
				
				this._a(new RB_lab()._t("-")._ttt("Lager-Lager-Fuhre"), new RB_gld()._center_mid());
				this._a(new RB_lab()._t("-")._ttt("Lager-Lager-Fuhre"), new RB_gld()._center_mid());
			} else {
						
				E2_ComponentMAP_V2 			map = (E2_ComponentMAP_V2)this.EXT().get_oComponentMAP();
				Rec21_VposTpaFuhre 			rec = new Rec21_VposTpaFuhre( map.getRec21());    //rec21-fuhre
				
	//			VEK<KV_Einstufung>     		vEinstufungen = new VEK<>();
				
				this._a(rec.getStatusFakturierungsFristPruefungGutschrift(), new RB_gld()._ins(0, 2, 0, 0))
					._a(rec.getStatusFakturierungsFristPruefungRechnung(), new RB_gld()._ins(0, 2, 0, 0))
					;
				
				if (countOrte.intValue()>0) {
					SEL sel = new SEL(_TAB.vpos_tpa_fuhre_ort).FROM(_TAB.vpos_tpa_fuhre_ort)
							.WHERE(new vglParam(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre))
							.AND(new vglParam(VPOS_TPA_FUHRE_ORT.deleted))
							.AND(new VglNotNull(VPOS_TPA_FUHRE_ORT.datum_lade_ablade))
							;
					SqlStringExtended  sqlExt = new SqlStringExtended(sel.s());
					sqlExt 				._addParameter(new Param_Long(rec.getIdLong()))
										._addParameter(new Param_String("1","N"))
										;
		
					RecList21 	rlFuo = new RecList21(_TAB.vpos_tpa_fuhre_ort)._fill(sqlExt);
		
					E2_Grid     g_left = new E2_Grid()._setSize(KV_Const.breiteWarnGrid);
					E2_Grid     g_right = new E2_Grid()._setSize(KV_Const.breiteWarnGrid);
		
					if (rlFuo.size()>0) {
						for (Rec21 r: rlFuo) {
							Rec21_VposTpaFuhreOrt rfo = new Rec21_VposTpaFuhreOrt(r);
							if (rfo.isEK()) {
								g_left._a(rfo.getStatusFakturierungsFrist());
							} else {
								g_right._a(rfo.getStatusFakturierungsFrist());
							}
						}
						this._a(g_left)
							._a(g_right);
					}
				}
			}	
		} catch (Exception e) {
			
			this._clear();
			this._a(new RB_lab()._t("System-Error")._ttt(e.getLocalizedMessage()),new RB_gld()._col_back_alarm());
			
			e.printStackTrace();
		}
		
		
		
	}

	
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		return new FU__ListAnzeigeAblaufFakturierungsFrist();
	}

	
	
	
}

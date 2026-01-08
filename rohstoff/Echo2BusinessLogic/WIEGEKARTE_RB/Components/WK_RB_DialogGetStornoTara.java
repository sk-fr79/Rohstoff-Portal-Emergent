package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_MessageBoxGetValue_Complex;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS.WAEGUNG;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.RecList22;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorE2String;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWaegung;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarte;

public class WK_RB_DialogGetStornoTara extends E2_MessageBoxGetValue_Complex {

	private String _id_wiegekarte = null;
	private RB_TransportHashMap _pHashMap; 
	
	private int _waegung_pos = 0;
	private Rec22 _waegung = null;
	
	
	
	public WK_RB_DialogGetStornoTara( int Waegung_pos, RB_TransportHashMap pHashMap ) throws myException {
		this("Auswahl der Storno-Gewichte","Übernehmen","Abbrechen",true,true,new VectorE2String(),null,500,300);
		_pHashMap = pHashMap;
		_waegung_pos = Waegung_pos;
		
	}

	public WK_RB_DialogGetStornoTara(
			String TextTitelZeile, 
			String TextOk, 
			String TextCancel, 
			boolean bShowOK,
			boolean bShowCancel, 
			VectorE2String Infos, 
			XX_ActionAgent ActionAgentStart, 
			int iWidth, 
			int iHeight)
			throws myException {
		super(TextTitelZeile, TextOk, TextCancel, bShowOK, bShowCancel, Infos, ActionAgentStart, iWidth, iHeight);
			
	}

	
	private E2_Grid getGrid4Data() {
		E2_Grid grid = new E2_Grid();
		
		RecDOWiegekarte doWK = (RecDOWiegekarte) _pHashMap.getMaskDataObjectsCollector().get(RecDOWiegekarte.key);

		String sHeading = "Keine Waegung";
		String sWeight  = "-";

		String sql = 
				   " SELECT * FROM " + bibE2.cTO() + "." + _TAB.waegung.fullTableName() 
				+  " WHERE " + WAEGUNG.id_wiegekarte.fieldName() + " = ? "
				+  " and "   + WAEGUNG.waegung_pos.fieldName() + " = ? " 
				;
				
				
				
		
		
		
		try {
			// stornierte WK holen
			Long lIDWKold = doWK.getLongDbValue(WIEGEKARTE.id_wiegekarte_storno);
			if (lIDWKold != null) {
			
				SqlStringExtended sqlex = new SqlStringExtended(sql)
						._addParameter(new Param_Long(lIDWKold))
						._addParameter(new Param_Long(_waegung_pos));
				
				RecList22 rlWaegung = new RecList22(_TAB.waegung)._fill(sqlex);
				if (rlWaegung!= null && rlWaegung.size() > 0) {
					_waegung = rlWaegung.get(0);
					sHeading = new MyE2_String("Wägung x").CTrans();

				}
			}
		} catch (myException e1) {
			e1.printStackTrace();
		}

		
		if (_waegung != null ) {
			try {
				sWeight = _waegung.getFs(WAEGUNG.gewicht);
			} catch (myException e) {
				sWeight = "";
			}
		}
		grid	._s(2)
				._bo_green()
				._a(new MyE2_Label(sHeading,MyE2_Label.STYLE_NORMAL_BOLD()),new RB_gld()._center_mid()._span(2))
				._a(new MyE2_Label("Gewicht:"),new RB_gld()._center_mid())
				._a(new MyE2_Label(sWeight),new RB_gld()._span(2)._center_mid());
		
		
		return grid;
	}
	
	
	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.components.E2_MessageBoxGetValue_Complex#initGui()
	 */
	@Override
	public void initGui() {
		this._setComponentExtra(getGrid4Data());
	}
	
	
	
	@Override
	public MyE2_MessageVector validate_input() {
		
		return bibMSG.getNewMV();
	}

	/**
	 * gibt die Waegung zurück.
	 */
	@Override
	public Object getValue() {
		
		return _waegung;
	}

	
	

}

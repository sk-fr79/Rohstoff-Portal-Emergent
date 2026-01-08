
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.KOSTEN_NG;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Vector;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V1;
import panter.gmbh.Echo2.RB.DATA.RB_StatementBuilderCollector;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_KOSTEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_VPOS_TPA_FUHRE_KOSTEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_KOSTEN;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.exceptions.myException;


public class FK_MASK_DataObjectCollector extends RB_DataobjectsCollector_V1 {

	private FK_MASK_bt_MULTI_FUHREN_KOSTEN maskButton;

	public FK_MASK_DataObjectCollector(FK_MASK_bt_MULTI_FUHREN_KOSTEN oMaskButton) throws myException {
		super();
		this.registerComponent(new RB_KM(_TAB.vpos_tpa_fuhre_kosten), new FK_MASK_DataObject(new RECORDNEW_VPOS_TPA_FUHRE_KOSTEN()));
		this.maskButton = oMaskButton;
	}

	public FK_MASK_DataObjectCollector(String id_vpos_tpa_fuhre_kosten, MASK_STATUS status) throws myException {
		super();
		this.registerComponent(new RB_KM(_TAB.vpos_tpa_fuhre_kosten), new FK_MASK_DataObject(new RECORD_VPOS_TPA_FUHRE_KOSTEN(id_vpos_tpa_fuhre_kosten), status));
	}

	@Override
	public void database_to_dataobject(Object id_vpos_tpa_fuhre_kosten) throws myException {

		// wird bei verbundenen dataObjects benoetigt, wo bei der basis-id
		// gestartet, alle aufgebaut wird
	}

	/*
	select * from LEBER_TEST4.JT_VPOS_TPA_FUHRE_KOSTEN where JT_VPOS_TPA_FUHRE_KOSTEN.ID_VPOS_TPA_FUHRE in (284197, 284198,284199) and JT_VPOS_TPA_FUHRE_KOSTEN.INFO_KOSTEN = 'test'
	 */
	
	@Override
	public void dataobject_to_database_connect_RB_MASK_DATA(RB_StatementBuilderCollector Statemenbuilder_Collector) throws myException {
		Vector<String> selectedIds = this.maskButton.getSelectedFuhreId();
		
		String firstSelectedId = this.maskButton.getSmallestSelectedFuhreId();
		BigDecimal totalSelectedId = new BigDecimal(this.maskButton.totalSelectedFuhreId());

		MySqlStatementBuilder sqlStatement = Statemenbuilder_Collector.get(new RB_KM(_TAB.vpos_tpa_fuhre_kosten)).get(0);
		sqlStatement.put(VPOS_TPA_FUHRE_KOSTEN.id_vpos_tpa_fuhre.fieldName(), firstSelectedId);
		
		BigDecimal betrag = new BigDecimal(sqlStatement.get(VPOS_TPA_FUHRE_KOSTEN.betrag_kosten.fieldName())).setScale(2);

		BigDecimal doFuhreKost = betrag.divide(totalSelectedId, RoundingMode.DOWN).setScale(2);

		BigDecimal intermediateSum = betrag.subtract(doFuhreKost.multiply(totalSelectedId.subtract(new BigDecimal("1"))));
		
		sqlStatement.put(VPOS_TPA_FUHRE_KOSTEN.betrag_kosten.fieldName(), intermediateSum.toString());
		
		if(totalSelectedId.intValue()>1){
			Vector<String>finalSelectedIds = new Vector<String>(selectedIds);
			finalSelectedIds.remove(firstSelectedId);
			for(String finalId: finalSelectedIds){
		
				RECORDNEW_VPOS_TPA_FUHRE_KOSTEN neuKosteRecords = new RECORDNEW_VPOS_TPA_FUHRE_KOSTEN();
				MySqlStatementBuilder stmt = new MySqlStatementBuilder(_TAB.vpos_tpa_fuhre_kosten.n());
				stmt.set_RecordCorrelated(neuKosteRecords);
				
				stmt.putAll(sqlStatement);
				stmt.put(VPOS_TPA_FUHRE_KOSTEN.id_vpos_tpa_fuhre.fieldName(), finalId);
				stmt.put(VPOS_TPA_FUHRE_KOSTEN.betrag_kosten.fieldName(), doFuhreKost.toString());

				Statemenbuilder_Collector.add(new RB_KM(_TAB.vpos_tpa_fuhre_kosten), stmt);
			}
		}
	}
}

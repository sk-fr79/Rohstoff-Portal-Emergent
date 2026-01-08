package rohstoff.Echo2BusinessLogic.FIBU;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS.REP__POPUP_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIBU.ANLAGEN.FIBU_LIST_BT_Anlagen;
import rohstoff.Echo2BusinessLogic.FIBU.MAHNUNGEN2.FIBU_LIST_BT_Mahnungen_NG;
import rohstoff.Echo2BusinessLogic.FIBU.MAHNUNGEN3.FIBU_MAHNUNG_Button;


public class FIBU_LIST_BedienPanel extends MyE2_Column 
{

	private FIBU_LIST_Selector    oFIBU_LIST_Selector = null;
	private FIBU_AUSWERTE_BLOCK   oZahlenBlock = null;

	public FIBU_LIST_BedienPanel(E2_NavigationList oNaviList) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER());


		MyE2_Row oRowForComponents = new MyE2_Row(MyE2_Row.STYLE_THIN_BORDER());

		this.oFIBU_LIST_Selector = new FIBU_LIST_Selector(oNaviList, E2_MODULNAMES.NAME_MODUL_FIBU_LIST);
		this.oZahlenBlock = 		new FIBU_AUSWERTE_BLOCK(oNaviList,this.oFIBU_LIST_Selector);

		oNaviList.add_actionAfterListCompleted(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				FIBU_LIST_BedienPanel.this.oZahlenBlock.fill_Block();
			}
		});

		Insets oInsets = new Insets(0,0,0,5);

		this.add(oFIBU_LIST_Selector, oInsets);
		if (bibALL.get_RECORD_USER().is_IST_SUPERVISOR_YES() || bibALL.get_RECORD_USER().is_SONDERRECH_ZEIGE_OPLISTE_SALDO_YES())
		{
			this.add(oZahlenBlock, oInsets);
		}
		this.add(oRowForComponents, oInsets);

		FIBU_LIST_DATASEARCH searcher = new FIBU_LIST_DATASEARCH(oNaviList);


		oRowForComponents.add(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList),  new Insets(2,2,20,2));
		oRowForComponents.add(new FIBU_LIST_BT_AlleBelegeVerbuchen(oNaviList), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new FIBU_LIST_BT_BUCHUNG_ZUSAMMENFASSEN(oNaviList), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new FIBU_LIST_BT_BUCHUNG_TRENNEN(oNaviList), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new FIBU_LIST_BT_BUCHUNG(oNaviList), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new FIBU_LIST_BT_SCHECK(oNaviList), E2_INSETS.I_2_2_2_2);
		oRowForComponents.add(new FIBU_LIST_BT_STORNO(oNaviList), E2_INSETS.I_2_2_2_2);
		//oRowForComponents.add(new FIBU_LIST_BT_Mahnungen(oNaviList), E2_INSETS.I_2_2_2_2);


		//2016.12.09:Mahnung für mv gottenheim
		if(ENUM_MANDANT_DECISION.USE_MAHNUNG_BUTTON_NEU.is_YES()){
			oRowForComponents.add(new FIBU_MAHNUNG_Button(oNaviList), E2_INSETS.I_2_2_2_2);
		}
		if(ENUM_MANDANT_DECISION.USE_MAHNUNG_BUTTON_ALT.is_YES()){
			oRowForComponents.add(new FIBU_LIST_BT_Mahnungen_NG(oNaviList), E2_INSETS.I_2_2_2_2);
		}

		//2016-01-14: anlagen hinzufuegen wegen den eMail-Druck-Aktionen mit archivierung
		oRowForComponents.add(new FIBU_LIST_BT_Anlagen(oNaviList),E2_INSETS.I_2_2_2_2);


		oRowForComponents.add(new FIBU_LIST_popup_export(oNaviList), E2_INSETS.I_2_2_2_2);

		oRowForComponents.add(new FIBU_LIST_POPUP_DRUCKE_MAIL(oNaviList,this.oFIBU_LIST_Selector), E2_INSETS.I_2_2_2_2);

		oRowForComponents.add(new REP__POPUP_Button(E2_MODULNAMES.NAME_MODUL_FIBU_LIST,oNaviList), E2_INSETS.I_2_2_2_2);

		//2015-12-04: umgebungssuche ...
		oRowForComponents.add(new FIBU_LIST_BT_sucheUmgebung(	oNaviList,
				this.oFIBU_LIST_Selector.get_filterSelektor(),
				searcher.get_oButtonClearFieldsAndReload()), E2_INSETS.I_2_2_10_2);


		oRowForComponents.add(searcher, new Insets(20,2,2,2));
	}

	public FIBU_LIST_Selector get_oFIBU_LIST_Selector() 
	{
		return oFIBU_LIST_Selector;
	}


}

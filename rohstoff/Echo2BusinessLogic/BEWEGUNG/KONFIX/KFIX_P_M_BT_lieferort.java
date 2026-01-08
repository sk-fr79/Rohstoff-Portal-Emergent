package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_TRAKT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LIEFERADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_KON;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_P_M_BT_lieferort extends MyE2_Button implements IF_RB_Component {
	
	private RB_KF key;
	
	public KFIX_P_M_BT_lieferort(){
		super(E2_ResourceIcon.get_RI("popup.png"), true);
		this.add_oActionAgent(new ownActionAgent());
		this.setToolTipText(new MyE2_String("Lieferorte/Lagerorte des Kunden/Lieferanten").CTrans());
		this.setLineWrap(true);
	}
	
	private class ownActionAgent extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			KFIX_P_M_BT_lieferort oParent = (KFIX_P_M_BT_lieferort)oExecInfo.get_MyActionEvent().getSource();
			
			KFIX_P_M__ComponentMapAddon oParentMap = (KFIX_P_M__ComponentMapAddon)oParent.EXT().get_oComponentMAP();
			
			String idVkOpfKon = oParentMap.getRecVkopf().get_fs_dbVal(VKOPF_KON.id_vkopf_kon);
			
			if (S.isFull(idVkOpfKon))
			{
				Rec20 recVKopfKon = new Rec20(_TAB.vkopf_kon)._fill_id(idVkOpfKon);
				new ownBasicContainer(recVKopfKon.gen_record(false));
			}
			
		}
		
	}
	
	private class ownBasicContainer extends E2_BasicModuleContainer
	{

		public ownBasicContainer(MyRECORD record) throws myException
		{
			super();
			
			RECORD_VKOPF_KON recVKOPF_KON = new RECORD_VKOPF_KON(record);
			
			Vector<String> vLieferorte = new Vector<String>();
			
			String cFeldListeAusMandantenZusatz = bibALL.get_TEXTVAL_FROM_RECLIST_MANDANTEN_ZUSATZ("FELDLISTE_KONTRAKT_LIEFERORT");
			
			if (S.isEmpty(cFeldListeAusMandantenZusatz))
			{
				cFeldListeAusMandantenZusatz="STRASSE|PLZ|ORT";
			}
			
			Vector<String> vNamenFelder = bibALL.TrenneZeile(cFeldListeAusMandantenZusatz, "|");

			vLieferorte.add(recVKOPF_KON.get_UP_RECORD_ADRESSE_id_adresse().get___KETTE(vNamenFelder, "", "", "", " "));
			
			RECLIST_LIEFERADRESSE reclistLAGER = recVKOPF_KON.get_UP_RECORD_ADRESSE_id_adresse().get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_basis();
			
			if (reclistLAGER.get_vKeyValues().size()>0)
			{
				for (int i=0;i<reclistLAGER.get_vKeyValues().size();i++)
				{
					vLieferorte.add(reclistLAGER.get(i).get_UP_RECORD_ADRESSE_id_adresse_liefer().get___KETTE(vNamenFelder, "", "", "", " "));
				}
			}
			
			MyE2_Grid oGrid  = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			
			
			this.add(oGrid, E2_INSETS.I_5_5_5_5);
			
			if (vLieferorte.size()==1)  //dann gleich besetzen
			{
				this.setLieferort(vLieferorte.get(0));
			}
			else
			{
				for (int i=0;i<vLieferorte.size();i++)
				{
					MyE2_Button  oButtonSetOrt = new MyE2_Button(vLieferorte.get(i));
					oButtonSetOrt.add_oActionAgent(new actionToSetLieferort());
					oGrid.add_raw(oButtonSetOrt, LayoutDataFactory.get_GridLayoutGridLeftMID(E2_INSETS.I_5_2_5_2));
				}
				
				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(400), new Extent(300), new MyE2_String("Bitte wählen Sie einen Lieferort..."));
			}
		}
		
		private class actionToSetLieferort extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				MyE2_Button   ownButton = (MyE2_Button)oExecInfo.get_MyActionEvent().getSource();
				ownBasicContainer.this.setLieferort(ownButton.getText());
				ownBasicContainer.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			}
			
		}
		
		private void setLieferort(String cOrt) throws myException
		{
			KFIX_P_M_BT_lieferort oThis = KFIX_P_M_BT_lieferort.this;
			
			
			KFIX_P_M__ComponentMapAddon  oMap = (KFIX_P_M__ComponentMapAddon)oThis.EXT().get_oComponentMAP();

			oMap._setValue(VPOS_KON_TRAKT.lieferort, cOrt);//rb_set_cActualMaskValue(cOrt);

		}

	}
	
	@Override
	public void mark_Neutral() throws myException {/**/}

	@Override
	public void mark_MustField() throws myException {/**/}

	@Override
	public void mark_Disabled() throws myException {
		this.setEnabled(false);
	}

	@Override
	public void mark_FalseInput() throws myException {/**/}

	@Override
	public void set_Alignment(Alignment align) throws myException {/**/}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {

	}

	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {/**/}

	@Override
	public RB_KF rb_KF() throws myException {
		return key;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.key = key;
	}

	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() throws myException {
		return new Vector<RB_Validator_Component>();
	}

}

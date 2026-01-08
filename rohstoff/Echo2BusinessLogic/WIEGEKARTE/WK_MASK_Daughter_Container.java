package rohstoff.Echo2BusinessLogic.WIEGEKARTE;

import java.math.BigDecimal;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_SelectFieldWithParameters;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectFieldWithParameter;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.ActionAgent_DaughterTable_NEW;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_ButtonMarkForDelete;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_DBC_DaughterTable;
import panter.gmbh.basics4project.DB_ENUMS.CONTAINER;
import panter.gmbh.basics4project.DB_ENUMS.CONTAINERTYP;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_CONTAINER;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_WIEGEKARTE_CONTAINER;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class WK_MASK_Daughter_Container extends MyE2_DBC_DaughterTable {
	
	private RECLIST_WIEGEKARTE_CONTAINER m_listContainer = null;
	
	private E2_ComponentMAP 					m_oMapContainer 		= null;
	private	WK_SelectfieldParam_ContainerTyp 	_oSelectTypParam 		= null;
	private WK_SelectfieldParam_Container 		_oSelectContainerParam 	= null;
	
	
	
	private static String _fieldMapName = "JT_WIEGEKARTE_CONTAINER";
	
	public WK_MASK_Daughter_Container(SQLFieldMAP fieldMAPMotherTable, E2_ComponentMAP ocomponentMAP_from_Mother) throws myException 
	{
	
		super();
		
		SQLFieldMAP oSQLFieldMapContainer = new SQLFieldMAP(_fieldMapName,	bibE2.get_CurrSession());
		
		oSQLFieldMapContainer.addCompleteTable_FIELDLIST( _fieldMapName,
							":ID_WIEGEKARTE_CONTAINER:ID_WIEGEKARTE:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:"
							,false, true, "");
		
		oSQLFieldMapContainer.add_SQLField(new SQLFieldForPrimaryKey( WIEGEKARTE_CONTAINER.fullTabName(), WIEGEKARTE_CONTAINER.id_wiegekarte_container.fn(), WIEGEKARTE_CONTAINER.id_wiegekarte_container.fn(),
									new MyE2_String("ID-Wiegekarte-Container"), bibE2.get_CurrSession(),
									
									"SELECT " + bibALL.get_TABLEOWNER()	+ ".SEQ_" + WIEGEKARTE_CONTAINER.baseTabName() + ".NEXTVAL FROM DUAL", true), false); 
		
		
		oSQLFieldMapContainer.add_SQLField(new SQLFieldJoinOutside(	WIEGEKARTE_CONTAINER.fullTabName(), WIEGEKARTE_CONTAINER.id_wiegekarte.fieldName(), WIEGEKARTE_CONTAINER.id_wiegekarte.fieldName(),
				new MyE2_String("ID-Wiegekarte"), false, bibE2.get_CurrSession(),
				fieldMAPMotherTable.get_SQLField(WIEGEKARTE.id_wiegekarte.fieldName())), false);

		// Mussfelder 
		oSQLFieldMapContainer.initFields();

		
		m_oMapContainer = new E2_ComponentMAP( oSQLFieldMapContainer );

		MyE2_DB_TextArea   		oTF_Beschreibung = new MyE2_DB_TextArea( oSQLFieldMapContainer.get_SQLField(WIEGEKARTE_CONTAINER.beschreibung.fn() ),200,3,false,new E2_FontPlain(-2));
		MyE2_DB_TextField 		oTF_Tara = new MyE2_DB_TextField(	oSQLFieldMapContainer.get_SQLField( WIEGEKARTE_CONTAINER.tara.fn() ),true,60);
		MyE2_DB_TextField 		oTF_ContainerNr = new MyE2_DB_TextField(	oSQLFieldMapContainer.get_SQLField( WIEGEKARTE_CONTAINER.container_nr.fn() ),true,80);
	

		_oSelectTypParam = new WK_SelectfieldParam_ContainerTyp(oSQLFieldMapContainer.get_(WIEGEKARTE_CONTAINER.id_containertyp.fn()));
		_oSelectTypParam.SetParameter("#ID_ADRESSE#", "-1");
		_oSelectTypParam.RefreshComboboxFromSQL();
		_oSelectTypParam._aaa(new actionSelectContainerTyp());
		
		_oSelectContainerParam = new WK_SelectfieldParam_Container(oSQLFieldMapContainer.get_(WIEGEKARTE_CONTAINER.id_container.fn()));
		_oSelectContainerParam.SetParameter("#ID_CONTAINERTYP#", "-1");
		_oSelectContainerParam.RefreshComboboxFromSQL();
		_oSelectContainerParam._aaa(new actionSelectContainer());
		

		
		this.m_oMapContainer.set_allDBComponents_Sortable(false);

		MyE2_ButtonMarkForDelete oButtonForDel = new MyE2_ButtonMarkForDelete();
		
		m_oMapContainer.add_Component("DELETE_SELECTOR",  oButtonForDel, new MyE2_String("?"));
		m_oMapContainer.add_Component(_oSelectTypParam, new MyE2_String("Containertyp..."));
		m_oMapContainer.add_Component(_oSelectContainerParam, new MyE2_String("Container..."));
		m_oMapContainer.add_Component(oTF_ContainerNr,new MyE2_String("ContainerNr"));
		m_oMapContainer.add_Component(oTF_Beschreibung, new MyE2_String("Beschreibung"));
		m_oMapContainer.add_Component(oTF_Tara, new MyE2_String("Tara"));

		MyE2_Button oButtonNEW = new MyE2_Button(E2_ResourceIcon.get_RI("new.png"), true);

		oButtonNEW.add_oActionAgent(new ownActionRefresh_before_new());
		oButtonNEW.add_oActionAgent(new ActionAgent_DaughterTable_NEW(this,	true));
		oButtonForDel.EXT().set_oCompTitleInList(oButtonNEW);

		this.get_vComponentForDifferentTasks().add(oButtonNEW);

		this.set_to_100_percent();
		this.set_oContainerExScrollHeight(new Extent(40, Extent.PERCENT));
		
		this.INIT_DAUGHTER(fieldMAPMotherTable.get_oSQLFieldPKMainTable(),	ocomponentMAP_from_Mother, m_oMapContainer, null);
	}
	
	

	private class ownActionRefresh_before_new extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
//			WK_MASK_Daughter_Container.this.refresh_list();
		}
	}
	

	

	private class actionSelectContainerTyp extends XX_ActionAgent{
		public actionSelectContainerTyp() {
			super();
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			WK_MASK_Daughter_Container oThis = WK_MASK_Daughter_Container.this;
			
			// Selectfield des Container-Typs 
			MyE2_DB_SelectFieldWithParameter oSel = (MyE2_DB_SelectFieldWithParameter)bibE2.get_LAST_ACTIONEVENT().getSource();
			String id = oSel.get_cActualDBValueFormated().replace(".", "");
			
			
			// component-Map holen um das zugehörige Selectfield für die Container zu holen...
			E2_ComponentMAP oMap = oSel.EXT().get_oComponentMAP();
			
			// selectfield für container holen
			MyE2_DB_SelectFieldWithParameter oSelContainer = (MyE2_DB_SelectFieldWithParameter) oMap.get(WIEGEKARTE_CONTAINER.id_container.fn());
			oSelContainer.SetParameter("#ID_CONTAINERTYP#", id);
			oSelContainer.set_ActiveInhalt_or_FirstInhalt("");
			oSelContainer.RefreshComboboxFromSQL();
		}
		
	}
	
	
	
	
	private class actionSelectContainer extends XX_ActionAgent{
		public actionSelectContainer() {
			super();
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			WK_MASK_Daughter_Container oThis = WK_MASK_Daughter_Container.this;
			
			// Selectfield des Container-Typs 
			MyE2_DB_SelectFieldWithParameter oSel = (MyE2_DB_SelectFieldWithParameter)bibE2.get_LAST_ACTIONEVENT().getSource();
			String id = oSel.get_cActualDBValueFormated().replace(".", "");

			if (bibALL.isEmpty(id)){
				return;
			}
			
			
			// component-Map holen um das zugehörige Selectfield für die Container zu holen...
			E2_ComponentMAP oMap = oSel.EXT().get_oComponentMAP();
			
			// Textfelder holen
			MyE2_DB_TextField oFieldTara 	= (MyE2_DB_TextField) oMap.get(WIEGEKARTE_CONTAINER.tara.fn());
			MyE2_DB_TextField oFieldNr 		= (MyE2_DB_TextField) oMap.get(WIEGEKARTE_CONTAINER.container_nr.fn());
			

			// lesen des Records...
			Rec20 rec = new Rec20(CONTAINER._tab())._fill_id(id);
			
			String idTyp		= rec.get_fs_dbVal(CONTAINER.id_containertyp);
			MyBigDecimal tara 	= rec.get_myBigDecimal_dbVal(CONTAINER.tara);
			String nr			= rec.get_ufs_dbVal(CONTAINER.container_nr);

			
			
			// prüfen, ob im Typ schon was drin steht, und wenn ja, ob es das richtige ist.
			MyE2_DB_SelectFieldWithParameter typ = (MyE2_DB_SelectFieldWithParameter) oMap.get(WIEGEKARTE_CONTAINER.id_containertyp.fn());
			
			String activetyp = typ.get_ActualWert();
			if (!typ.get_cActualMaskValue().equals(idTyp)){
				
				typ.set_ActiveValue_OR_FirstValue(idTyp);
			}
			
			
			oFieldNr.setText(nr);
			oFieldTara.setText(tara.get_FormatedRoundedNumber(3));
			
		}
		
	}
}

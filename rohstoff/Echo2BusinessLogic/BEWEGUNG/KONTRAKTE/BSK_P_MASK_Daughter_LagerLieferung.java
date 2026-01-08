package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.ActionAgent_DaughterTable_NEW;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_ButtonMarkForDelete;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_DBC_DaughterTable;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_SelectFieldOwnLAGER;



public class BSK_P_MASK_Daughter_LagerLieferung extends MyE2_DBC_DaughterTable
{
	
	public BSK_P_MASK_Daughter_LagerLieferung(	SQLFieldMAP 			fieldMAP, 
										  	E2_ComponentMAP			ocomponentMAP_from_Mother) throws myException
	{
		super();
		
		
		/*
		 * tochtertabelle fuer das druckprotokoll
		 */
		Project_SQLFieldMAP oFieldMapLager = new Project_SQLFieldMAP("JT_VPOS_KON_LAGER",":ID_VPOS_KON:",false);
		oFieldMapLager.add_SQLField(
				new SQLFieldJoinOutside("JT_VPOS_KON_LAGER","ID_VPOS_KON","ID_VPOS_KON",
									new MyE2_String("ID-VPOS-Kon"),false,bibE2.get_CurrSession(),
									fieldMAP.get_SQLField("ID_VPOS_KON")), false
									);
		
		oFieldMapLager.get_("ID_ADRESSE_LAGER").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		oFieldMapLager.initFields();
		
		
		E2_ComponentMAP oMapLager = new E2_ComponentMAP(oFieldMapLager);
		MyE2_ButtonMarkForDelete 	oButtonForDel = 	new MyE2_ButtonMarkForDelete();
		MyE2_DB_TextField			oTF_Lagermenge = 	new MyE2_DB_TextField(oFieldMapLager.get_SQLField("LAGERMENGE"));
		BS_SelectFieldOwnLAGER		oSelectOwnLager = 	new BS_SelectFieldOwnLAGER(oFieldMapLager.get_SQLField("ID_ADRESSE_LAGER"));
		MyE2_DB_Label				oLabelID = 			new MyE2_DB_Label(oFieldMapLager.get_SQLField("ID_VPOS_KON_LAGER"));

		oMapLager.add_Component(BSK__CONST.HASH_KEY_DEL_BUTTON_DAUGHTERTABLE_LAGERLIEFERUNG,oButtonForDel,new MyE2_String("?"));
		oMapLager.add_Component(oTF_Lagermenge,new MyE2_String("Menge"));
		oMapLager.add_Component(oSelectOwnLager,new MyE2_String("Lager"));
		oMapLager.add_Component(oLabelID,new MyE2_String("ID"));
		
		/*
		 * neueingabe-button definieren
		 */
		MyE2_Button		oButtonNEW = new MyE2_Button(E2_ResourceIcon.get_RI("new.png"),true);
		oButtonNEW.add_oActionAgent(new ActionAgent_DaughterTable_NEW(this, false));
		oButtonForDel.EXT().set_oCompTitleInList(oButtonNEW);
		this.get_vComponentForDifferentTasks().add(oButtonNEW);
		
		this.set_oContainerExScrollHeight(new Extent(150));
		
		this.INIT_DAUGHTER(	fieldMAP.get_oSQLFieldPKMainTable(),
											ocomponentMAP_from_Mother,
											oMapLager,
											null);
	}

	
	
	/*
	 * die summe aller lagereintraege berechnen
	 */
	public double get_dSummeAllerLagereintraege() throws myException
	{
		double dRueck = 0;
		Vector<E2_ComponentMAP> vComponentMaps = this.get_vE2_ComponentMAPs_NewAndEdit_WithoutDeleteMarker();
		
		for (int i=0;i<vComponentMaps.size();i++)
		{
			dRueck += ((E2_ComponentMAP)vComponentMaps.get(i)).get_DActualDBValue("LAGERMENGE",true, false, new Double(0)).doubleValue();
		}
		
		return dRueck;
	}
	
	
	
}

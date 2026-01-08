package rohstoff.Echo2BusinessLogic.BEWEGUNG.UMA;

import java.math.BigDecimal;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.XX_FieldSetter_AND_Validator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.ActionAgent_DaughterTable_NEW;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_ButtonMarkForDelete;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_DBC_DaughterTable;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;

public class UMA_MASK_Component_DAUGHTER_LIEFERSORTE extends MyE2_DBC_DaughterTable
{

	public UMA_MASK_Component_DAUGHTER_LIEFERSORTE(	SQLFieldMAP 		fieldMAPMotherTable, 
													E2_ComponentMAP		ocomponentMAP_from_Mother,
													boolean 			bIstLiefersorte) throws myException
	{
		super();
		
		String cTableName = bIstLiefersorte?"JT_UMA_KON_ARTB_LIEF":"JT_UMA_KON_ARTB_RUECKLIEF";
		String cID_Name   = bIstLiefersorte?"ID_UMA_KON_ARTB_LIEF":"ID_UMA_KON_ARTB_RUECKLIEF";
		String cSEQ_Name  = bIstLiefersorte?"SEQ_UMA_KON_ARTB_LIEF":"SEQ_UMA_KON_ARTB_RUECKLIEF";
		String cTitel     = bIstLiefersorte?"Liefersorte":"Rückliefersorte";
		
		SQLFieldMAP 			oSQLFieldMapUMA_Sorte = new SQLFieldMAP(cTableName,bibE2.get_CurrSession());

		oSQLFieldMapUMA_Sorte.addCompleteTable_FIELDLIST(cTableName,cID_Name+":ID_UMA_KONTRAKT:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true,"");

		oSQLFieldMapUMA_Sorte.add_SQLField(new SQLFieldForPrimaryKey(cTableName,cID_Name,cID_Name,new MyE2_String("ID"),bibE2.get_CurrSession(),
									"SELECT "+bibALL.get_TABLEOWNER()+"."+cSEQ_Name+".NEXTVAL FROM DUAL",true), false);

		oSQLFieldMapUMA_Sorte.add_SQLField(new SQLFieldJoinOutside(cTableName,"ID_UMA_KONTRAKT","ID_UMA_KONTRAKT",
											new MyE2_String("ID-UMA-Kontrakt"),false,bibE2.get_CurrSession(),fieldMAPMotherTable.get_SQLField("ID_UMA_KONTRAKT")), false);

		oSQLFieldMapUMA_Sorte.get_("ID_ARTIKEL_BEZ").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		oSQLFieldMapUMA_Sorte.get_("NUTZBAR_PROZENT").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		oSQLFieldMapUMA_Sorte.get_("NUTZBAR_PROZENT").set_cDefaultValueFormated("100");
		
		oSQLFieldMapUMA_Sorte.initFields();
		

		MyE2_ButtonMarkForDelete 		oButtonForDel = 		new MyE2_ButtonMarkForDelete();
		MyE2_Button						oButtonNEW = 			new MyE2_Button(E2_ResourceIcon.get_RI("new.png"),true);
		oButtonNEW.add_oActionAgent(new ActionAgent_DaughterTable_NEW(this, false));
		
		E2_ComponentMAP 				oComponentMapUMS_Sorte = 			new E2_ComponentMAP(oSQLFieldMapUMA_Sorte);

		UMA_MASK_DB_SEARCH_SORTE_BEZ 	oSearchSortenBez = 	new UMA_MASK_DB_SEARCH_SORTE_BEZ(oSQLFieldMapUMA_Sorte.get_SQLField("ID_ARTIKEL_BEZ"),bIstLiefersorte);
		MyE2_DB_TextField				oTF_Prozent = 		new MyE2_DB_TextField(oSQLFieldMapUMA_Sorte.get_SQLField("NUTZBAR_PROZENT"),true,50,0,false);
		MyE2_DB_Label				    oLabelID = 			new MyE2_DB_Label(oSQLFieldMapUMA_Sorte.get_SQLField(cID_Name));
		
		oSearchSortenBez.EXT().set_oColExtent(	new Extent(370));
		oTF_Prozent.EXT().set_oColExtent(		new Extent(70));
		oLabelID.EXT().set_oColExtent(			new Extent(70));
		
		oComponentMapUMS_Sorte.add_Component("DELETE_SELECTOR",oButtonForDel,new MyE2_String("?"));
		oComponentMapUMS_Sorte.add_Component(oSearchSortenBez,new MyE2_String(cTitel));
		oComponentMapUMS_Sorte.add_Component(oTF_Prozent,new MyE2_String("Nutzanteil"));
		oComponentMapUMS_Sorte.add_Component(oLabelID,new MyE2_String("ID"));
	
		oButtonForDel.EXT().set_oCompTitleInList(oButtonNEW);
		this.get_vComponentForDifferentTasks().add(oButtonNEW);

		//this.set_oContainerExScrollHeight(new Extent(300));
		this.set_to_100_percent();
		this.set_oContainerExScrollHeight(new Extent(40,Extent.PERCENT));

		this.EXT().add_FieldSetters_AND_Validator__AfterReadInputMAP(new ownValidator());

		
		this.INIT_DAUGHTER(fieldMAPMotherTable.get_oSQLFieldPKMainTable(),
							ocomponentMAP_from_Mother,
							oComponentMapUMS_Sorte,
							null);
		
	}

	
	private class ownValidator extends XX_FieldSetter_AND_Validator
	{

		@Override
		public MyE2_MessageVector isValid(String cSTATUS_MAP, MyE2EXT__Component EXT_own) throws myException
		{
			UMA_MASK_Component_DAUGHTER_LIEFERSORTE 	oThis = UMA_MASK_Component_DAUGHTER_LIEFERSORTE.this;
			E2_NavigationList 							oList = oThis.get_oNavigationList();
			
			MyE2_MessageVector  oMV = new MyE2_MessageVector();
			Vector<E2_ComponentMAP> 	vE2_ComponentMAPs = 		oList.get_vComponentMAPS();
			Vector<E2_ComponentMAP> 	vE2_ComponentMAPs_NEW = 	oList.get_vComponentMAPS_NEW();

			//zuerst muss geprueft werden, ob ueberhaupt was da ist
			Vector<Long>  			vIDs_Sorten = new Vector<Long>();
			Vector<BigDecimal>		vProzent    = new Vector<BigDecimal>();
			
			for (int i=0;i<vE2_ComponentMAPs.size();i++)
			{
				if (! oThis.get_bMapIsMarkedToDelete((E2_ComponentMAP)vE2_ComponentMAPs.get(i)))
				{
					Long lID_Sorte = vE2_ComponentMAPs.get(i).get_LActualDBValue("ID_ARTIKEL_BEZ", new Long(0), new Long(0));
					if (lID_Sorte.longValue()>0)
					{
						vIDs_Sorten.add(lID_Sorte);
						vProzent.add(vE2_ComponentMAPs.get(i).get_bdActualDBValue("NUTZBAR_PROZENT", new BigDecimal(-1), new BigDecimal(-1)));
					}
				}
			}
			for (int i=0;i<vE2_ComponentMAPs_NEW.size();i++)
			{
				if (! oThis.get_bMapIsMarkedToDelete((E2_ComponentMAP)vE2_ComponentMAPs_NEW.get(i)))
				{
					Long lID_Sorte = vE2_ComponentMAPs_NEW.get(i).get_LActualDBValue("ID_ARTIKEL_BEZ", new Long(0), new Long(0));
					if (lID_Sorte.longValue()>0)
					{
						vIDs_Sorten.add(lID_Sorte);
						vProzent.add(vE2_ComponentMAPs_NEW.get(i).get_bdActualDBValue("NUTZBAR_PROZENT", new BigDecimal(-1), new BigDecimal(-1)));
					}
				}
			}

			
			// 1. pruefung: ist ueberhaupt etwas da ?
			if (vIDs_Sorten.size()>0)
			{
				//2. pruefung: sind alle sorten in der liste mit der gleichen einheit definiert
				VectorSingle vEinheiten = new VectorSingle();
				for (int i=0;i<vIDs_Sorten.size();i++)
				{
					RECORD_ARTIKEL_BEZ  recArtBez = new RECORD_ARTIKEL_BEZ(vIDs_Sorten.get(i));
					if (recArtBez.get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_EINHEIT_id_einheit()!=null)
					{
						vEinheiten.add(recArtBez.get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_EINHEIT_id_einheit().get_EINHEITKURZ_cUF_NN("<eh>"));
					}
				}
				if (vEinheiten.size()!=1)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Sortenblöcke Liefersorten und Rückliefersorten MÜSSEN die gleiche Einheit besitzen !")));
				}
				else
				{
					//3. pruefung: alle prozente zwischen 0 und 100
					for (int i=0;i<vProzent.size();i++)
					{
						if (vProzent.get(i).compareTo(new BigDecimal(0))<0 ||vProzent.get(i).compareTo(new BigDecimal(100))>0)
						{
							oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Alle Prozent-Angaben müssen zwischen 0 und 100 liegen !!")));
						}
					}
				}
			}
			else
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte geben Sie mindestens eine Liefer- und eine Rueckliefersorte an !")));
			}
			return oMV;
		}
		
	}
	
}

package rohstoff.Echo2BusinessLogic.BEWEGUNG.ABNAHMEANGEBOTELISTE;


import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ButtonActionAgentMULTIDELETE;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_DELETER;

public class BSAAL_ButtonDelete extends MyE2_Button 
{
	private BSAAL__ModulContainerLIST	oModulContainerList = null;
	private MyDBToolBox				oDB = bibALL.get_myDBToolBox();
	
	
	
	protected void finalize()
	{
		bibALL.destroy_myDBToolBox(this.oDB);
	}
	
	public BSAAL_ButtonDelete(BSAAL__ModulContainerLIST	oModulContainer)
	{

		super(E2_ResourceIcon.get_RI("delete.png"), true);
		
		this.oModulContainerList = oModulContainer;

		this.add_oActionAgent(new ownActionAgent());
		this.setToolTipText(new MyE2_String("Löschen einer Position für die Abnahme-Angebote ...").CTrans());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(oModulContainer.get_MODUL_IDENTIFIER(),BSAAL__CONST.BUTTON_DELETE_POSITION));
		this.add_IDValidator(new BSAAL_Validator_Kopf_IS_NOT_CLOSED());
		this.add_IDValidator(new E2_Validator_ID_DBQuery("JT_VPOS_STD","  NVL(DELETED,'N')",bibALL.get_Array("N"),true, new MyE2_String("Die Position wurde bereits gelöscht !")));

	}
	
	
	private class ownActionAgent extends BS_ButtonActionAgentMULTIDELETE
	{

		public ownActionAgent() 
		{
			super(BSAAL_ButtonDelete.this.oModulContainerList.get_oNaviList(), "JT_VPOS_STD", false);
		}
		
		
		
		/*
	     * feststellen, welche koepfe nach dem loeschen leer sind, und diese auch loeschen
	     */
		public void PruefeWeiterLoeschungen(Vector<String> vIDS_to_Delete, Vector<String> vSQL_Stack, String cLoeschInfoText) throws myException
		{
			
			if (vIDS_to_Delete.size()==0)
				return;
			
			
			// jetzt den vector mit den koepfen fuellen, die ganz weg fallen, da alle ihre positionen geloescht werden
			String cSQL_KOPF = "SELECT DISTINCT ID_VKOPF_STD FROM "+bibE2.cTO()+".JT_VPOS_STD WHERE ID_VPOS_STD IN ("+bibALL.ConcatenateWithoutException(vIDS_to_Delete,",", null)+")";
			
			String[][] cID_Koepfe = bibDB.EinzelAbfrageInArray(cSQL_KOPF);
			
			
			// fehler darf hier nicht vorkommen
			if (cID_Koepfe == null || cID_Koepfe.length==0)
				throw new myException("BSAAL_ButtonDelete:Error: Querying Headers is not working !!!");

			
			for (int i=0;i<cID_Koepfe.length;i++)
			{
				
				// fehler darf hier nicht vorkommen
				// alle positionen dieses kopfs abfragen und gegen den delete-vector pruefen
				String[][] cID_Pos = BSAAL_ButtonDelete.this.oDB.EinzelAbfrageInArray("SELECT ID_VPOS_STD FROM "+
									bibE2.cTO()+".JT_VPOS_STD WHERE   NVL(DELETED,'N')='N' AND ID_VKOPF_STD="+cID_Koepfe[i][0]);
				if (cID_Pos == null || cID_Pos.length==0)
					throw new myException("BSAAL_ButtonDelete:Error: No Positions Querying Headers is not working !!!");

				boolean bAllePositionenGeloescht = true; 
				for (int k=0;k<cID_Pos.length;k++)
				{
					if (!vIDS_to_Delete.contains(cID_Pos[k][0]))
						bAllePositionenGeloescht = false;
				}
				
				// damit stehen die leere koepfe bereit und werden separat geloescht
				if (bAllePositionenGeloescht)
					vSQL_Stack.add(new BS_DELETER("JT_VKOPF_STD",cID_Koepfe[i][0],cLoeschInfoText).get_MAIN_DELETE_SQL());
				
			}
			
		}

		public MyE2_MessageVector CheckIdToDelete(Vector<String> vID_UnformatedToDelete) {	return null;}

	}
	
	
}

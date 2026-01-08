package rohstoff.Echo2BusinessLogic.BEWEGUNG.UMA;

import java.util.Map;
import java.util.Vector;

import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_UMA_KON_ARTB_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_UMA_KON_ARTB_RUECKLIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_UMA_KON_ARTB_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_UMA_KON_ARTB_RUECKLIEF;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;



public class UMA_LIST_GridSortenAnzeige extends MyE2_Grid
{
	private boolean bLiefersorte = true;
	private static int[]   iBreiten = {50,20,180,70};
	
	public UMA_LIST_GridSortenAnzeige(boolean Liefersorte)
	{
		super(UMA_LIST_GridSortenAnzeige.iBreiten, MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS());
		
		this.bLiefersorte = Liefersorte;
	}
	
	
	//diese komponente ist immer enbabled
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
	}

	
	//2011-02-03: grid-copy aktiviert
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		return new UMA_LIST_GridSortenAnzeige(this.bLiefersorte);
	}


	public void populate_Sorten() throws myException
	{
		this.removeAll();
		
		GridLayoutData  oGL = MyE2_Grid.LAYOUT_LEFT_TOP(new Insets(0, 0, 5, 2));
		GridLayoutData  oGLR = MyE2_Grid.LAYOUT_RIGHT_TOP(new Insets(0, 0, 5, 2));
		
		if (this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP()!=null)
		{
			if (this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_LActualDBValue("ID_UMA_KONTRAKT",true).longValue()>0)
			{
			
				String cID_UMA_UF = ""+this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_LActualDBValue("ID_UMA_KONTRAKT",true).longValue();
					
				if (this.bLiefersorte)
				{
					RECLIST_UMA_KON_ARTB_LIEF  recListArtLief = new RECLIST_UMA_KON_ARTB_LIEF("SELECT * FROM "+bibE2.cTO()+".JT_UMA_KON_ARTB_LIEF WHERE ID_UMA_KONTRAKT="+cID_UMA_UF);
					
					for (int i=0;i<recListArtLief.get_vKeyValues().size();i++)
					{
						RECORD_UMA_KON_ARTB_LIEF  recArtbez = recListArtLief.get(i);
						
						String cANR1 = 		recArtbez.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cF_NN("<anr1>");
						String cANR2 = 		recArtbez.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_ANR2_cF_NN("<anr2>");
						String cARTBEZ1 = 	recArtbez.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_ARTBEZ1_cUF_NN("<artbez1>");
						String cProz = 		recArtbez.get_NUTZBAR_PROZENT_cF_NN("<%>")+" %";
						
						this.add(new ownButton(cANR1,false), 		oGL);
						this.add(new ownButton(cANR2,false), 		oGL);
						this.add(new ownButton(cARTBEZ1,true), 		oGL);
						this.add(new ownButton(cProz,false), 		oGLR);
					}
				}
				else
				{
					RECLIST_UMA_KON_ARTB_RUECKLIEF  recListArtRueckLief = new RECLIST_UMA_KON_ARTB_RUECKLIEF("SELECT * FROM "+bibE2.cTO()+".JT_UMA_KON_ARTB_RUECKLIEF WHERE ID_UMA_KONTRAKT="+cID_UMA_UF);
					
					for (int i=0;i<recListArtRueckLief.get_vKeyValues().size();i++)
					{
						RECORD_UMA_KON_ARTB_RUECKLIEF  recArtbez = recListArtRueckLief.get(i);
						
						String cANR1 = 		recArtbez.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cF_NN("<anr1>");
						String cANR2 = 		recArtbez.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_ANR2_cF_NN("<anr2>");
						String cARTBEZ1 = 	recArtbez.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_ARTBEZ1_cUF_NN("<artbez1>");
						String cProz = 		recArtbez.get_NUTZBAR_PROZENT_cF_NN("<%>")+" %";
						
						this.add(new ownButton(cANR1,false), 		oGL);
						this.add(new ownButton(cANR2,false), 		oGL);
						this.add(new ownButton(cARTBEZ1,true),	 	oGL);
						this.add(new ownButton(cProz,false), 		oGLR);
					}
					
				}
			}
		}
	}
	

	
	private class ownButton extends MyE2_ButtonInLIST
	{
		public ownButton(String cText, boolean bLineWrap)
		{
			super(cText, MyE2_Button.StyleTextButton_LOOK_like_LABEL(bLineWrap));
			this.add_oActionAgent(new ownButtonSelectActualLine());
		}
	}

	
	private class ownButtonSelectActualLine extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			UMA_LIST_GridSortenAnzeige oThis = UMA_LIST_GridSortenAnzeige.this;
			
			E2_ComponentMAP oMap = oThis.EXT().get_oComponentMAP();
			Vector<E2_ComponentMAP> vVectorComponentMapThisBelongsTo = null;
			
			//zuerst nachsehen, ob die komponente in einer componentMap ist
			if 	(oMap != null)
			{
				vVectorComponentMapThisBelongsTo = oMap.get_VectorComponentMAP_thisBelongsTo();
				
				//dann nachsehen, ob sich das ganze in einer NaviList abspielt
				if (vVectorComponentMapThisBelongsTo!=null)
				{
					//dann nachsehen, ob die eingen ComponentMAP eine CheckBox hat
					for (Map.Entry<String,MyE2IF__Component> oEntry: oMap.entrySet())
					{
						if (oEntry.getValue() instanceof E2_CheckBoxForList)
						{
							E2_CheckBoxForList oCB_For_List = (E2_CheckBoxForList)oEntry.getValue();
							
							if (oCB_For_List.isEnabled())
							{
								for (E2_ComponentMAP oMapSchleife: vVectorComponentMapThisBelongsTo)
								{
									oMapSchleife.setChecked_CheckBoxForList(false);   //alle aus 
								}
								oCB_For_List.setSelected(true);   //ausser ich
							}
						}
					}
				}
			}
		}
		
	}
	
}

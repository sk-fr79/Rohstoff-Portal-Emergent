package rohstoff.Echo2BusinessLogic._4_ALL;

import java.util.Vector;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.components.E2_Selection_Row_With_Multi_Cols;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.indep.exceptions.myException;

public abstract class KontraktSelector extends E2_Selection_Row_With_Multi_Cols
{
	private String cKenner_EK_VK = null;
	
	/*
	 * ein vector, wenn die finalen auswahl-selektionsbuttons aus einer bestimmten liste kommen muessen
	 */
	private Vector<String>   vIDs_zugelassene_kontrakt_Positionen = null;
	private boolean    	     bDisableNotAllwedIDs = false;                 //wenn true, dann werden die erlaubten Fettgedruckt, die nicht erlaubten aber nicht gesperrt
	
	public KontraktSelector(String EK_VK_Kenner) throws myException
	{
		super(MyE2_Row.STYLE_THIN_BORDER(),200,true);
		
		this.cKenner_EK_VK=EK_VK_Kenner;
		
		
		

		
		this.add_QueryStringForSteps("SELECT DISTINCT SUBSTR(ANR1,1,2) FROM "+bibE2.cTO()+".JT_VKOPF_KON,"+bibE2.cTO()+".JT_VPOS_KON, "+bibE2.cTO()+".JT_VPOS_KON_TRAKT  WHERE" +
									" JT_VKOPF_KON.ID_VKOPF_KON=JT_VPOS_KON.ID_VKOPF_KON  AND" +
									" JT_VPOS_KON.ID_VPOS_KON=JT_VPOS_KON_TRAKT.ID_VPOS_KON  AND" +
									" NVL(JT_VPOS_KON.DELETED,'N')='N' AND " +
									" NVL(JT_VPOS_KON_TRAKT.ABGESCHLOSSEN,'N')='N' AND " +
									" JT_VKOPF_KON.VORGANG_TYP='"+this.cKenner_EK_VK+"_KONTRAKT'" +
									" ORDER BY SUBSTR(ANR1,1,2)" , new MyE2_String("Stoffgruppe"),100,1,new ownButtonBuilder());

		this.add_QueryStringForSteps("SELECT DISTINCT ANR1 FROM "+bibE2.cTO()+".JT_VKOPF_KON,"+bibE2.cTO()+".JT_VPOS_KON, "+bibE2.cTO()+".JT_VPOS_KON_TRAKT  WHERE" +
									" JT_VKOPF_KON.ID_VKOPF_KON=JT_VPOS_KON.ID_VKOPF_KON  AND" +
									" JT_VPOS_KON.ID_VPOS_KON=JT_VPOS_KON_TRAKT.ID_VPOS_KON  AND" +
									" NVL(JT_VPOS_KON.DELETED,'N')='N' AND " +
									" NVL(JT_VPOS_KON_TRAKT.ABGESCHLOSSEN,'N')='N' AND " +
									" SUBSTR(JT_VPOS_KON.ANR1,1,2)='#WERT1#' AND " +
									" JT_VKOPF_KON.VORGANG_TYP='"+this.cKenner_EK_VK+"_KONTRAKT'" +
									" ORDER BY SUBSTR(ANR1,1,2)" , new MyE2_String("ANR1"),60,1,new ownButtonBuilder());

		
		
		this.add_QueryStringForSteps("SELECT "+
								"   JT_VPOS_KON.ID_VPOS_KON,"+
								"    NVL(JT_VKOPF_KON.BUCHUNGSNUMMER,TO_CHAR(JT_VKOPF_KON.ID_VKOPF_KON))||'-'||NVL(TO_CHAR(JT_VPOS_KON.POSITIONSNUMMER),'?'),"+
								"    NVL(JT_VKOPF_KON.NAME1,'')||' '||NVL(JT_VKOPF_KON.ORT,''),"+
								"    'Mge:'||NVL(JT_VPOS_KON.ANZAHL,0), "+
								"    NVL(JT_VPOS_KON.ANR1,'')||'-'||NVL(JT_VPOS_KON.ANR2,'')||' ('||NVL(JT_VPOS_KON.ARTBEZ1,'')||')', "+
								"    NVL(TO_CHAR(JT_VPOS_KON_TRAKT.GUELTIG_VON,'dd.mm.yyyy'),'-'),"+
								"    NVL(TO_CHAR(JT_VPOS_KON_TRAKT.GUELTIG_BIS,'dd.mm.yyyy'),'-'),"+
								"   JT_VPOS_KON.ID_VPOS_KON"+
								" FROM "+
								"    JT_VKOPF_KON, "+
								"    JT_VPOS_KON, "+
								"    JT_VPOS_KON_TRAKT "+
								" WHERE "+
								"    JT_VKOPF_KON.ID_VKOPF_KON   = JT_VPOS_KON.ID_VKOPF_KON AND "+
								"    JT_VPOS_KON.ID_VPOS_KON = JT_VPOS_KON_TRAKT.ID_VPOS_KON AND " +
								"    NVL(JT_VPOS_KON.DELETED,'N')='N' AND " +
								"    NVL(JT_VPOS_KON_TRAKT.ABGESCHLOSSEN,'N')='N' AND " +
								"    JT_VKOPF_KON.VORGANG_TYP='"+this.cKenner_EK_VK+"_KONTRAKT' AND " +
								"    JT_VPOS_KON.ANR1='#WERT2#'"+
								" ORDER BY "+
								" 	 NVL(JT_VKOPF_KON.NAME1,'')||' '||NVL(JT_VKOPF_KON.NAME2,'')||' '||NVL(JT_VKOPF_KON.ORT,'')",
								new MyE2_String("Land"),
								900,7,new ownButtonBuilder2());

	}
	

	
	public Vector<String> get_vIDs_zugelassene_kontrakt_Positionen()
	{
		return this.vIDs_zugelassene_kontrakt_Positionen;
	}

	public void set_vIDs_zugelassene_kontrakt_Positionen(Vector<String> ds_zugelassene_kontrakt_Positionen)
	{
		this.vIDs_zugelassene_kontrakt_Positionen = ds_zugelassene_kontrakt_Positionen;
	}

	public boolean get_bDisableNotAllwedIDs()
	{
		return bDisableNotAllwedIDs;
	}



	public void set_bDisableNotAllwedIDs(boolean DisableNotAllwedIDs)
	{
		bDisableNotAllwedIDs = DisableNotAllwedIDs;
	}

	
	
	private class ownButtonBuilder extends XX_ButtonBuilder
	{
		private 	Color 	oColorButtonUnselected = new E2_ColorBase();

		@Override
		public Vector<MyE2_Button> build_Buttons_In_Grid(E2_Selection_Row_With_Multi_Cols oMultiSel, String[] ergebnisZeile, SelectBlockGrid ownSelectGrid)	throws myException
		{
			String cNextValue = ergebnisZeile[0];
				
			MyE2_Button oBut = new MyE2_Button(new MyE2_String(cNextValue,false));
			oBut.setBackground(oColorButtonUnselected);
			oBut.EXT().set_C_MERKMAL(cNextValue);
			oBut.EXT().set_O_PLACE_FOR_EVERYTHING(ownSelectGrid);
			oBut.add_oActionAgent(new ActionToSelectNextColumn(oMultiSel));
			oBut.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo) throws myException
				{
					KontraktSelector.this.actionForGotoSecondCol();
				}
				
			});
			ownSelectGrid.add(oBut, E2_INSETS.I_1_1_1_1);
			
			Vector<MyE2_Button> vRueck = new Vector<MyE2_Button>();
			vRueck.add(oBut);
			
			
			return vRueck;
		}
	}

	
	private class ownButtonBuilder2 extends XX_ButtonBuilder
	{
		private 	Color 	oColorButtonUnselected = new E2_ColorBase();

		@Override
		public Vector<MyE2_Button> build_Buttons_In_Grid(E2_Selection_Row_With_Multi_Cols oMultiSel, String[] ergebnisZeile, SelectBlockGrid ownSelectGrid)	throws myException
		{
			KontraktSelector oThis = KontraktSelector.this;
			
			String cNextValue = ergebnisZeile[0];
			
			Vector<MyE2_Button>  vRueck = new Vector<MyE2_Button>();
			
			
			for (int i=1;i<ergebnisZeile.length;i++)
			{
				String cButtonText = ergebnisZeile[i];
				
				MyE2_Button oBut = new MyE2_Button(new MyE2_String(cButtonText,false));
				oBut.setBackground(oColorButtonUnselected);
				oBut.EXT().set_C_MERKMAL(cNextValue);
				oBut.EXT().set_O_PLACE_FOR_EVERYTHING(ownSelectGrid);
				oBut.add_oActionAgent(new ActionToSelectNextColumn(oMultiSel));
				oBut.add_oActionAgent(new XX_ActionAgent()
				{
					@Override
					public void executeAgentCode(ExecINFO execInfo) throws myException
					{
						KontraktSelector.this.actionForExit();
					}
				});

				if (oThis.vIDs_zugelassene_kontrakt_Positionen!=null && oThis.vIDs_zugelassene_kontrakt_Positionen.size()>0)
				{
					if (!oThis.vIDs_zugelassene_kontrakt_Positionen.contains(oBut.EXT().get_C_MERKMAL()))
					{
						if (oThis.bDisableNotAllwedIDs)
						{
							oBut.set_bEnabled_For_Edit(false);
						}
						oBut.setFont(new E2_FontItalic(-2));
						oBut.setForeground(Color.DARKGRAY);
					}
				}
				
				ownSelectGrid.add(oBut, E2_INSETS.I_1_1_1_1);
				
				vRueck.add(oBut);
				
			}
			
			return vRueck;
		}
	}
	
	public abstract void actionForGotoSecondCol() throws myException;
	
	public abstract void actionForExit() throws myException;




	
}

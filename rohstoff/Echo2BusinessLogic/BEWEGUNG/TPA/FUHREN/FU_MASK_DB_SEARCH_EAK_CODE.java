package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_FormaterFor_oTextForAnzeige;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class FU_MASK_DB_SEARCH_EAK_CODE extends MyE2_DB_MaskSearchField
{

	// wird NUR ZUR anzeige benutzt, nicht zum suchen
	public FU_MASK_DB_SEARCH_EAK_CODE(SQLField osqlField) throws myException
	{
		super(	osqlField, 
				"JT_EAK_CODE.ID_EAK_CODE,JT_EAK_CODE.CODE", 
				bibE2.cTO()+".JT_EAK_CODE ", 
				"JT_EAK_CODE.CODE",
				"", 
				"UPPER(JT_EAK_CODE.CODE) LIKE UPPER('%#WERT#%')", 
				null, 
				 null, 
				 "SELECT  JT_EAK_BRANCHE.KEY_BRANCHE||'-'||JT_EAK_GRUPPE.KEY_GRUPPE||'-'||JT_EAK_CODE.KEY_CODE||' '||TRANSLATE(  NVL(JT_EAK_CODE.GEFAEHRLICH,'N'),'YN','* ')||" +
				 "' -> '||  NVL(JT_EAK_CODE.CODE,'-')"+
					" FROM " +
					bibE2.cTO()+".JT_EAK_BRANCHE,"+
					bibE2.cTO()+".JT_EAK_GRUPPE,"+
					bibE2.cTO()+".JT_EAK_CODE"+
					" WHERE " +
					" JT_EAK_BRANCHE.ID_EAK_BRANCHE = JT_EAK_GRUPPE.ID_EAK_BRANCHE  AND"+ 
					" JT_EAK_GRUPPE.ID_EAK_GRUPPE = JT_EAK_CODE.ID_EAK_GRUPPE  AND "+
					" JT_EAK_CODE.ID_EAK_CODE=#WERT#", null, false);
		
		this.get_buttonStartSearch().setVisible(false);
		this.get_oTextFieldForSearchInput().setVisible(false);
		this.get_oTextForAnzeige().setWidth(new Extent(400));
		this.set_bLabel4AnzeigeStattText4Anzeige(true);
		this.get_oLabel4Anzeige().setWidth(new Extent(400));
		
		
		this.set_oAnzeigeFormatierer(new ownAnzeigeUebersetzer());
	}

	
	private class ownAnzeigeUebersetzer extends XX_FormaterFor_oTextForAnzeige
	{

		public void format_Anzeige(MyE2_DB_MaskSearchField oIchSelber, String cID_FOUND_UNFORMATED) throws myException
		{
			 String cQuery = 
				 	"SELECT  JT_EAK_BRANCHE.KEY_BRANCHE||'-'||JT_EAK_GRUPPE.KEY_GRUPPE||'-'||JT_EAK_CODE.KEY_CODE||' '||TRANSLATE(  NVL(JT_EAK_CODE.GEFAEHRLICH,'N'),'YN','* ')||" +
				 	"' -> '||  NVL(JT_EAK_CODE.CODE,'-')"+
					" FROM " +
					bibE2.cTO()+".JT_EAK_BRANCHE,"+
					bibE2.cTO()+".JT_EAK_GRUPPE,"+
					bibE2.cTO()+".JT_EAK_CODE,"+
					bibE2.cTO()+".JT_EAK_CODE_SP"+
					" WHERE " +
					" JT_EAK_BRANCHE.ID_EAK_BRANCHE = JT_EAK_GRUPPE.ID_EAK_BRANCHE  AND"+ 
					" JT_EAK_GRUPPE.ID_EAK_GRUPPE = JT_EAK_CODE.ID_EAK_GRUPPE  AND "+
					" JT_EAK_CODE.ID_EAK_CODE="+cID_FOUND_UNFORMATED+" AND "+
					" JT_EAK_CODE_SP.ID_EAK_CODE=JT_EAK_CODE.ID_EAK_CODE AND "+
					" JT_EAK_CODE_SP.ID_SPRACHE="+bibALL.get_ID_SPRACHE_USER();
			 
			 String[][] cRueck = bibDB.EinzelAbfrageInArray(cQuery, "");
			 if (cRueck == null)
				 throw new myException(this,": Error reading Language - Code !");
			 
			 if (cRueck.length==1)   // nur da ist eine uebersetzung gefunden worden
			 {
				 oIchSelber.get_oTextForAnzeige().setText(cRueck[0][0]);
				 oIchSelber.get_oTextForAnzeige().setToolTipText(cRueck[0][0]);
			 }
					
			
			
		}
		
	}
	
	
	@Override
	public E2_BasicModuleContainer get_ownContainer() throws myException
	{
		return new ownE2_Container();
	}

	private class ownE2_Container extends E2_BasicModuleContainer
	{
		public ownE2_Container()
		{
			super();
		}
	}
	

}

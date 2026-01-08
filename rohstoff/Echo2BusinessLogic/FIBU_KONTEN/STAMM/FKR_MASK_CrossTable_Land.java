package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.STAMM;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.DB.CROSS_CONNECTOR.MyE2_DBC_CrossConnection;
import panter.gmbh.Echo2.components.DB.CROSS_CONNECTOR.MyE2_DBC_CrossConnection_NG;
import panter.gmbh.Echo2.components.DB.CROSS_CONNECTOR.XX_FormatCheckBox;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAND;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class FKR_MASK_CrossTable_Land extends MyE2_DBC_CrossConnection_NG {

	private String c_DEF_QUELLE_ZIEL = null;
	
	
	/**
	 * 
	 * @param osqlField
	 * @param DEF_QUELLE_ZIEL (EK oder VK)
	 * @throws myException
	 */
	public FKR_MASK_CrossTable_Land(SQLFieldForPrimaryKey osqlField, String DEF_QUELLE_ZIEL) throws myException {
		super(		osqlField, 		
					_DB.FIBU_KONTENREGEL_LAND, 
					_DB.LAND, 
					_DB.FIBU_KONTENREGEL_LAND$ID_FIBU_KONTENREGEL_LAND, 
					_DB.FIBU_KONTENREGEL_LAND$ID_FIBU_KONTENREGEL,
					_DB.FIBU_KONTENREGEL_LAND$ID_LAND,
					_DB.LAND$ID_LAND, 
					"NVL("+_DB.LAND+"."+_DB.LAND$LAENDERNAME+","+_DB.LAND+"."+_DB.LAND$LAENDERCODE+")", 
					null, 
					null, 
					"NVL("+_DB.LAND+"."+_DB.LAND$LAENDERNAME+","+_DB.LAND+"."+_DB.LAND$LAENDERCODE+")",  
					11, 
					new E2_FontPlain(-2), 
					new MyE2_String("Bitte mindestens sowohl ein QUELL- als auch ein ZIEL-Land ankreuzen !"), 
					MyE2_DBC_CrossConnection.CROSSTYP_ALLOW_ALL);
		
		this.c_DEF_QUELLE_ZIEL=DEF_QUELLE_ZIEL;
		
		
		this.set_oFormatCheckBoxes(new ownFormater());
		this.add_ActionAgentToCheckboxes(new ownActionAgentFormatAfterClick());
		
		this.add_AddOnComponentsInFront(new ownButtonAlleAus());
		this.add_AddOnComponentsInFront(new ownButtonEU());
		this.add_AddOnComponentsInFront(new ownButtonNichtEU());
		
	}

	@Override
	public Vector<String> build_InsertVector(String cHauptIDUnformated) throws myException {
	    Vector<String> vRueck = new Vector<String>();
	    
	    // jetzt das grid aufbauen, das als komponente dient
	    String cSEQUENCE = "SEQ_" + this.get_cCROSSFIELD_MittlerTabelle().substring(3);

	    vRueck.add("DELETE FROM " + bibE2.cTO() + "." + this.get_cCROSSFIELD_MittlerTabelle() + " WHERE " + 
	    					this.get_cCROSSFIELD_IndexMittlerTabelleZuHaupt() + "=" + cHauptIDUnformated +" AND "+
	    					_DB.FIBU_KONTENREGEL_LAND$DEF_QUELLE_ZIEL+"="+bibALL.MakeSql(this.c_DEF_QUELLE_ZIEL));

	    // für alle selektierten werte einen eintrag in den insert-sql-vektor machen
	   
	    for (int i = 0; i < this.get_vCheckBoxKreuzTab().size(); i++)
	    {
	    	MySqlStatementBuilder  stmtb = new MySqlStatementBuilder(_DB.FIBU_KONTENREGEL_LAND);
	    	stmtb.addSQL_Paar(_DB.FIBU_KONTENREGEL_LAND$ID_FIBU_KONTENREGEL_LAND, cSEQUENCE + ".NEXTVAL", false);
	    	stmtb.addSQL_Paar(_DB.FIBU_KONTENREGEL_LAND$ID_FIBU_KONTENREGEL, cHauptIDUnformated, false);
	    	stmtb.addSQL_Paar(_DB.FIBU_KONTENREGEL_LAND$ID_LAND, this.get_vCheckBoxKreuzTab().get(i).EXT().get_C_MERKMAL(), false);
	    	stmtb.addSQL_Paar(_DB.FIBU_KONTENREGEL_LAND$DEF_QUELLE_ZIEL, this.c_DEF_QUELLE_ZIEL, true);
	    	
	        if (((MyE2_CheckBox) this.get_vCheckBoxKreuzTab().get(i)).isSelected())  {
	            vRueck.add(stmtb.get_CompleteInsertString(_DB.FIBU_KONTENREGEL_LAND, bibE2.cTO()));
	         }
	    }

	    return vRueck;
	    
	}

	@Override
	public Vector<String> get_vSQL_WHEREBLOCKS_QUERYING_CROSSTABLE() {
		return null;
	}

	@Override
	public Vector<String> get_vSQL_WHEREBLOCKS_VALUES_TO_FILL() {
		return bibVECTOR.get_Vector(_DB.FIBU_KONTENREGEL_LAND+"."+_DB.FIBU_KONTENREGEL_LAND$DEF_QUELLE_ZIEL+"='"+this.c_DEF_QUELLE_ZIEL+"'");
	}

	
	private class ownFormater extends XX_FormatCheckBox {

		@Override
		public void doFormat(MyE2_CheckBox oCB) throws myException {
			if (oCB.isSelected()) {
				oCB.setBackground( new E2_ColorLLLight());
			} else {
				oCB.setBackground( new E2_ColorBase());
			}
		}
	}
	
	
	private class ownActionAgentFormatAfterClick extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			FKR_MASK_CrossTable_Land.this.MakeFormatting();
		}
		
	}
	
	
	private class ownButtonAlleAus extends MyE2_Button {

		public ownButtonAlleAus() {
			super(E2_ResourceIcon.get_RI("clear.png"), true);
			this.add_oActionAgent(new ownActionAgentAlleAus());
		}
		
		private class ownActionAgentAlleAus extends XX_ActionAgent {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				FKR_MASK_CrossTable_Land oThis = FKR_MASK_CrossTable_Land.this;
				
				Vector<MyE2_CheckBox> vCB = oThis.get_vCheckBoxKreuzTab();
				
				for (MyE2_CheckBox  cb: vCB) {
					if (cb.isSelected()) {
						cb.setSelected(false);
					}
				}
				oThis.MakeFormatting();
			}
			
		}
		
	}

	private class ownButtonEU extends MyE2_Button {

		public ownButtonEU() {
			super(new MyE2_String("EU"));
			this.add_oActionAgent(new ownActionAgentEU());
		}
		
		private class ownActionAgentEU extends XX_ActionAgent {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				FKR_MASK_CrossTable_Land oThis = FKR_MASK_CrossTable_Land.this;
				
				RECLIST_LAND  rlLAND = new RECLIST_LAND("SELECT * FROM "+bibE2.cTO()+".JD_LAND");
				
				Vector<MyE2_CheckBox> vCB = oThis.get_vCheckBoxKreuzTab();
				
				for (MyE2_CheckBox  cb: vCB) {
					if (rlLAND.get(cb.EXT().get_C_MERKMAL())!=null && rlLAND.get(cb.EXT().get_C_MERKMAL()).is_INTRASTAT_JN_YES()) {
						cb.setSelected(true);
					}
				}
				oThis.MakeFormatting();

				
				
			}
			
		}
	
	}

	private class ownButtonNichtEU extends MyE2_Button {

		public ownButtonNichtEU() {
			super(new MyE2_String("Welt"));
			this.add_oActionAgent(new ownActionAgentWelt());
		}
		
		private class ownActionAgentWelt extends XX_ActionAgent {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				FKR_MASK_CrossTable_Land oThis = FKR_MASK_CrossTable_Land.this;
				
				
				RECLIST_LAND  rlLAND = new RECLIST_LAND("SELECT * FROM "+bibE2.cTO()+".JD_LAND");
				
				Vector<MyE2_CheckBox> vCB = oThis.get_vCheckBoxKreuzTab();
				
				for (MyE2_CheckBox  cb: vCB) {
					if (rlLAND.get(cb.EXT().get_C_MERKMAL())!=null && rlLAND.get(cb.EXT().get_C_MERKMAL()).is_INTRASTAT_JN_NO()) {
						cb.setSelected(true);
					}
				}
				oThis.MakeFormatting();

			}
			
		}

		
	}
	
	
}

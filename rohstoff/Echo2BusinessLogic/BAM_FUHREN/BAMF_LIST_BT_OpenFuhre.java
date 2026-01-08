package rohstoff.Echo2BusinessLogic.BAM_FUHREN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.DB.E2_DB_BUTTON_OPEN_MASK_FromID;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_MASK_BT_PRINT_MAIL_BELEG;

public class BAMF_LIST_BT_OpenFuhre extends E2_DB_BUTTON_OPEN_MASK_FromID
{

		private BAMF_LIST_ModulContainer oBAMF_LIST_ModulContainer = null;
		
		/**
		 * 
		 * @param osqlField
		 * @param BAMF_list_ModulContainer
		 * @throws myException
		 */
		public BAMF_LIST_BT_OpenFuhre(SQLField	osqlField, 	BAMF_LIST_ModulContainer        BAMF_list_ModulContainer)   throws myException
		{
			super(	osqlField, 
					BAMF_list_ModulContainer.get_oFuhrenMaskContainer(),
					new MyE2_String("Fuhren"),
					BAMF_list_ModulContainer.get_MODUL_IDENTIFIER(),
					"OPEN_FUHRE",
					"ANZEIGE_FUHRE");

			this.oBAMF_LIST_ModulContainer = BAMF_list_ModulContainer;
		}

		
		public Object get_Copy(Object ob) throws myExceptionCopy
		{
			BAMF_LIST_BT_OpenFuhre oButtCopy = null;
			
			try
			{
				oButtCopy = new BAMF_LIST_BT_OpenFuhre(
												this.EXT_DB().get_oSQLField(),
												this.oBAMF_LIST_ModulContainer);
			}
			catch (myException ex)
			{
				throw new myExceptionCopy("FP_DB_BUTTON_OPEN_FUHRENMASK:get_Copy:Copy-Error!");
			}
			
			oButtCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oButtCopy));
			if (this.getIcon() != null)
				oButtCopy.setIcon(this.getIcon());
			
			if (this.get_oText() != null)
				oButtCopy.set_Text(this.get_oText());
			
			oButtCopy.set_EXT_DB((MyE2EXT__DB_Component)this.EXT_DB().get_Copy(oButtCopy));
			
			oButtCopy.setStyle(this.getStyle());
			oButtCopy.setInsets(this.getInsets());
			return oButtCopy;
		}

		
		
		// ueberschreiben, wenn noetig
		public void put_SpecialButtonsToRowForButtons_EDIT(MyE2_Row oRowForButtons)
		{
			MyE2_Button oButton_Print_und_Mail = new FU_MASK_BT_PRINT_MAIL_BELEG(this.oBAMF_LIST_ModulContainer.get_oFuhrenMaskContainer(),false);
			oRowForButtons.add(oButton_Print_und_Mail);
		}

		

}

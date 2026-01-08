package panter.gmbh.Echo2.__BASIC_COMPONENTS.MessageProvider;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_USER_DROPDOWN_NEW;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.ENUM_MESSAGE_PROVIDER;
import panter.gmbh.basics4project.DB_ENUMS.MESSAGE_PROVIDER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MESSAGE_PROVIDER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;



public class MES_LIST_ComponentMap extends E2_ComponentMAP  {
	
    public MES_LIST_ComponentMap() throws myException  {
        super(new MES_LIST_SqlFieldMAP());
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(MES_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,    new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(MES_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,    new E2_ButtonListMarker(),new MyE2_String("?"));
        //hier kommen die Felder  
		this.add_Component(new DB_Component_USER_DROPDOWN_NEW(oFM.get_("ID_USER"),true),					new MyE2_String("Empfänger"));
        this.add_Component(new ownLabel4eMailProvider(oFM.get_(MESSAGE_PROVIDER.messagekey)),     			new MyE2_String("Programmstelle "));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(MESSAGE_PROVIDER.send_email),true),     			new MyE2_String("eMail ?"));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(MESSAGE_PROVIDER.send_message),true),    		 	new MyE2_String("Meldung ?"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(MESSAGE_PROVIDER.id_message_provider),true),    new MyE2_String("ID "));
        
        this._setLineWrapListHeader(true 
                                  ,MESSAGE_PROVIDER.id_message_provider.fn()
                                  ,MESSAGE_PROVIDER.id_user.fn()
                                  ,MESSAGE_PROVIDER.messagekey.fn()
                                  ,MESSAGE_PROVIDER.send_email.fn()
                                  ,MESSAGE_PROVIDER.send_message.fn()
        );
        
        RB_gld gldElementLeft = 	new RB_gld()._left_top()._ins(2,4,2,2)._col(new E2_ColorBase());
       	this._setLayoutElements(gldElementLeft
                                 ,MESSAGE_PROVIDER.id_user.fn()
                                 ,MESSAGE_PROVIDER.messagekey.fn()
      	);
      	
       	
      	RB_gld gldTitelLeft = 	new RB_gld()._left_top()._ins(1,2,1,1)._col(new E2_ColorDark());
       	this._setLayoutTitles(gldTitelLeft
                                 ,MESSAGE_PROVIDER.id_user.fn()
                                 ,MESSAGE_PROVIDER.messagekey.fn()
      	);

        RB_gld gldElementCenter = 	new RB_gld()._center_top()._ins(2,4,2,2)._col(new E2_ColorBase());
       	this._setLayoutElements(gldElementCenter
                                 ,MESSAGE_PROVIDER.send_email.fn()
                                 ,MESSAGE_PROVIDER.send_message.fn()
                                 ,MESSAGE_PROVIDER.id_message_provider.fn()
      	);
      	
       	
      	RB_gld gldTitelCenter = 	new RB_gld()._center_top()._ins(1,2,1,1)._col(new E2_ColorDark());
       	this._setLayoutTitles(gldTitelCenter
                                 ,MESSAGE_PROVIDER.send_email.fn()
                                 ,MESSAGE_PROVIDER.send_message.fn()
                                 ,MESSAGE_PROVIDER.id_message_provider.fn()
      	);

       	
       	
       	
       	this._setColExtent(new Extent(200), MESSAGE_PROVIDER.id_user.fn(), MESSAGE_PROVIDER.messagekey.fn());
       	this._setColExtent(new Extent(100), MESSAGE_PROVIDER.send_email.fn(), MESSAGE_PROVIDER.send_message.fn(), MESSAGE_PROVIDER.id_message_provider.fn());
       	
       	
       	
        this.set_oSubQueryAgent(new MES_LIST_FORMATING_Agent());
        	
        this.set_Factory4Records(new factory4Records());
    }
    
    
    
    private class factory4Records extends E2_ComponentMAP_Factory4Records {
        @Override
        public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
            return new RECORD_MESSAGE_PROVIDER(cID_MAINTABLE);
        }
    }


    
    /**
     * label fuer die uebersetzung der enuma-eintraege in usertexte 
     * @author martin
     *
     */
    private class ownLabel4eMailProvider extends MyE2_DB_Label_INGRID {
		/**
		 * @param osqlField
		 * @throws myException
		 */
		public ownLabel4eMailProvider(SQLField osqlField) throws myException {
			super(osqlField);
		}
    	
		public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException {
			super.set_cActual_Formated_DBContent_To_Mask(cText, cMASK_STATUS, oResultMAP);
			String c = S.NN(ENUM_MESSAGE_PROVIDER.MESSAGE_QUALIFYING_AH7_STEUERSATZ.getHmKeyUserText().get(cText),"--");
			this.get_oErsatzButton().setText(c);
		}

		public Object get_Copy(Object ob) throws myExceptionCopy {
			ownLabel4eMailProvider oLabCopy = null;
			try
			{
				oLabCopy = new ownLabel4eMailProvider(this.EXT_DB().get_oSQLField());
			}
			catch (myException ex)
			{
				throw new myExceptionCopy("MyE2_DB_Label:get_Copy:copy-error!");
			}
			
			oLabCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oLabCopy));
			oLabCopy.setFont(this.getFont());
			return oLabCopy;
		}
		
    }
    
    
    
}
 

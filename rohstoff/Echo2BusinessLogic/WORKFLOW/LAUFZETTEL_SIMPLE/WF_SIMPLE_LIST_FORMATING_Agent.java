 
package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE;
   
import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_Font;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG;
import panter.gmbh.indep.dataTools.MyResultValue;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
  
public class WF_SIMPLE_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT  {
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public WF_SIMPLE_LIST_FORMATING_Agent(RB_TransportHashMap  p_tpHashMap) throws myException {
       this.m_tpHashMap = p_tpHashMap;
    }
    
    
    public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)    throws myException   {
    }
    
    
    
    public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException  {
    	RB_gld gld = new RB_gld()._al(Alignment.ALIGN_TOP)._col(new E2_ColorBase(1));
    	String lz_text_name =  "LZ_"+LAUFZETTEL.text.fn();
    	
    	// Aufgabe und Bericht rendern
    	MyE2_DB_TextArea txtLaufzettel = (MyE2_DB_TextArea) oMAP.get__Comp_From_RealComponents(lz_text_name);
    	MyE2_DB_TextArea txtAufgabe = (MyE2_DB_TextArea) oMAP.get__Comp_From_RealComponents(LAUFZETTEL_EINTRAG.aufgabe.fn());
    	MyE2_DB_TextArea txtBericht = (MyE2_DB_TextArea) oMAP.get__Comp_From_RealComponents(LAUFZETTEL_EINTRAG.bericht.fn());
    	
    	boolean bIsDeleted = oMAP.get_bActualDBValue(LAUFZETTEL_EINTRAG.geloescht.fn());

    	MyResultValue text = oUsedResultMAP.get(lz_text_name);
        	
    	MyResultValue aufgabe = oUsedResultMAP.get(LAUFZETTEL_EINTRAG.aufgabe.fn());
    	MyResultValue bericht = oUsedResultMAP.get(LAUFZETTEL_EINTRAG.bericht.fn());

    	formatTextArea(txtLaufzettel, text.get_FieldValueUnformated(), gld, false, bIsDeleted, 0, new E2_ColorBase(), false);

    	formatTextArea(txtAufgabe, aufgabe.get_FieldValueUnformated(), gld, false, bIsDeleted, 0, new E2_ColorBase(), false);
    	formatTextArea(txtBericht, bericht.get_FieldValueUnformated(), gld, false, bIsDeleted, 0, new E2_ColorBase(), false);
    	
    	BT_AttachmentToWFEntry bt = (BT_AttachmentToWFEntry) oMAP.get__Comp(WF_SIMPLE_CONST.WF_SIMPLE_NAMES.DIRECT_UPLOAD2.db_val());
    	bt.initButton(oUsedResultMAP.get_cUNFormatedROW_ID());
    	
    }

    

	
	private void formatTextArea(MyE2_DB_TextArea olblInGrid, String cTextComplete, GridLayoutData LayoutData, boolean bIsTitle, boolean bIsDeleted, int pSize, Color backgroundcol, boolean bIsAnswer)
	{
		
		int 	iRowToAdd = 0;
		

		E2_Font oFontAnswer = new E2_Font(Font.ITALIC, pSize);
		E2_Font oDelFont = new E2_Font(Font.ITALIC + Font.LINE_THROUGH, pSize);

		
		if (cTextComplete == null){
			cTextComplete = "-";
		}
		else{
			iRowToAdd = 1;
		}
		
		String[] rows = cTextComplete.split("\n");
		
		// vorrausgesetzt man geht von 90 Zeichen in der BOX aus...
		int r1= cTextComplete.length() / 90;
		int r = (int) Math.round((rows.length + iRowToAdd) * 1.2);
		r = Math.max(r1, r);
		
		if (r > 6) r = 6;
		
//		oTextRueck = new MyE2_TextArea(ccText,600,4000,r);

		
		olblInGrid.set_iRows(r);
		
		

		if (bIsDeleted){
			olblInGrid.setFont(oDelFont);
		}
		else if (bIsAnswer){
			olblInGrid.setFont(oFontAnswer);
		}
		else {
		  	olblInGrid.setFont(new E2_FontPlain(pSize));
		}


		if (backgroundcol != null)
		{
			olblInGrid.setBackground(backgroundcol);
		}
		
		if (cTextComplete != null)
		{
			olblInGrid.setToolTipText(cTextComplete);
		}

		try {
			olblInGrid.set_bEnabled_For_Edit(false);
			
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    
    
}
 
 

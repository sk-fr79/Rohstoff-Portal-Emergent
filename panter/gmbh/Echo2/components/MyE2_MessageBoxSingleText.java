package panter.gmbh.Echo2.components;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.WindowPane;
import nextapp.echo2.app.layout.ColumnLayoutData;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_ContentPane;
import panter.gmbh.indep.MyString;

public class MyE2_MessageBoxSingleText extends MyE2_WindowPane
{

	public MyE2_MessageBoxSingleText(MyString cTitle, MyString cMessage, MyString cButtonText)
	{
		super(cTitle, new Extent(400),new Extent(400),true);
		MyE2_Column oColumBasic = new MyE2_Column();
		super.setDefaultCloseOperation(WindowPane.DISPOSE_ON_CLOSE);
		
		MyE2_Button oButton = new MyE2_Button(cButtonText);
		MyE2_Label  oLabel = new MyE2_Label(cMessage);
		oLabel.setLineWrap(true);
		
		oButton.add_oActionAgent(new XX_ActionAgent()
				{
					public void executeAgentCode(ExecINFO oExecInfo)
					{
						MyE2_MessageBoxSingleText.this.userClose();
					}
				});
		
		oColumBasic.add(oLabel,new ownColLayout(280, new Insets(2,10,2,0)));
		oColumBasic.add(oButton,new ownColLayout(60, new Insets(2,10,2,0)));
		E2_ContentPane oPane = new E2_ContentPane(true);
		this.add(oPane);
		oPane.add(oColumBasic);
		
	}

	
	private class ownColLayout extends ColumnLayoutData
	{

		public ownColLayout(int iPixel, Insets oInset)
		{
			super();
			this.setInsets(oInset);
			this.setAlignment(new Alignment(Alignment.CENTER,Alignment.CENTER));
			this.setHeight(new Extent(iPixel, Extent.PX));
		}

	}

}

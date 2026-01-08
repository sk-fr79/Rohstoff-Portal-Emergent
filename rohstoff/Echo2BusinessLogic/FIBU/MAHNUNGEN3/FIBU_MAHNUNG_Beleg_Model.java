package rohstoff.Echo2BusinessLogic.FIBU.MAHNUNGEN3;

public class FIBU_MAHNUNG_Beleg_Model {
	
	private String fibuId;
	private Integer position;
	private String 	text;
	
	public FIBU_MAHNUNG_Beleg_Model() {
		this.fibuId ="";
		this.position = 0;
		this.text = "";
	}

	public FIBU_MAHNUNG_Beleg_Model(String fibuId) {
		super();
		this.fibuId = fibuId;
		this.position = 0;
		this.text = "";

	}
	
	public FIBU_MAHNUNG_Beleg_Model(String fibuId,Integer position, String text ) {
		this.fibuId = fibuId;
		this.position = position;
		this.text = text;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFibuId() {
		return fibuId;
	}

	public void setFibuId(String fibuId) {
		this.fibuId = fibuId;
	}

	
	
	
	
}

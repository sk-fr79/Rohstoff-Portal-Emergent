package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev.csv;

public abstract class DatevCSVType {
	String value; 
	
	public DatevCSVType() {
		// TODO Auto-generated constructor stub
	}
	
	public abstract void setValue(String s);
	public abstract void setValue(Object o);

	//TODO: Make this abstract and move it, with the checks for the right value/format into the subclasses
	public abstract String print();

	public void reset() {
		value = null;
	}
}

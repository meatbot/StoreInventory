
public class InventoryItem {
	
	private long itemNum;
	private String catNum, description, per;
	private double listPrice, oh, or, oo;
	//will contain all alternative barcodes
	private long[] altCodes;
	
	public long getItemNum() {
		return itemNum;
	}

	public String getCatNum() {
		return catNum;
	}

	public String getDescription() {
		return description;
	}

	public String getPer() {
		return per;
	}

	public double getListPrice() {
		return listPrice;
	}

	public double getOh() {
		return oh;
	}

	public double getOr() {
		return or;
	}

	public double getOo() {
		return oo;
	}

	public long[] getAltCodes() {
		return altCodes;
	}

	/**
	 * Basic constructor - parses item string into individual properties.
	 * 
	 * @param itemData string containing item data.  List of values separated by '~'
	 */
	public InventoryItem(String itemData) {
		String[] fields = itemData.split("~");
		this.itemNum = Integer.parseInt(fields[0]);
		this.catNum = fields[1];
		this.description = fields[2];
		this.listPrice = Double.parseDouble(fields[3]);
		this.per = fields[4];
		this.oh = Double.parseDouble(fields[5]);
		this.or = Double.parseDouble(fields[6]);
		this.oo = Double.parseDouble(fields[7]);
		this.altCodes = parseAltCodes(fields[8]);
	}
	
	/**
	 * Takes a string-list of alternative barcodes. 
	 * Likely 8 digit integers, seperated by spaces (" ")
	 * Method takes list and places it in class level long array altCodes
	 * 
	 * @param altCodes the string containing a list of alternative barcodes seperated by spaces
	 */
	public long[] parseAltCodes(String altCodes) {
		String[] stringCodes = altCodes.split(" ");
		
		long[] codes = new long[stringCodes.length];
		
		//parse out long from string array
		for (int i = 0; i < stringCodes.length; i++) {
			codes[i] = Long.parseLong(stringCodes[i]);
		}
		
		return codes;
	}
}

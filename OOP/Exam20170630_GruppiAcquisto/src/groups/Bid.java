package groups;

public class Bid {
	private String groupName;
	private String supplierName;
	private int price;

	public Bid(String groupName, String supplierName, int price) {
		this.groupName = groupName;
		this.supplierName = supplierName;
		this.price = price;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}

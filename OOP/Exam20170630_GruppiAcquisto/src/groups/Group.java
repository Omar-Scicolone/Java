package groups;

import java.util.List;

public class Group {
	private String name;
	private String productName;
	private List<String> customerNames;
	private List<String> supplierNames;

	
	
	public Group(String name, String productName, List<String> customerNames) {
		this.name = name;
		this.setProductName(productName);
		this.customerNames = customerNames;
	}



	public List<String> getCustomerNames() {
		return customerNames;
	}



	public void setCustomerNames(List<String> customerNames) {
		this.customerNames = customerNames;
	}



	public String getName() {
		return name;
	}



	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}



	public List<String> getSupplierNames() {
		return supplierNames;
	}



	public void setSupplierNames(List<String> supplierNames) {
		this.supplierNames = supplierNames;
	}

}

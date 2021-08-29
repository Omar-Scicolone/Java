package groups;

import java.util.List;
import java.util.ArrayList;

public class Product {
	private String productTypeName;
	private List<String> supplierNames = new ArrayList<>();

	public Product(String productTypeName, List<String> supplierNames) {
		this.productTypeName = productTypeName;
		this.supplierNames = supplierNames;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public List<String> getSupplierNames() {
		return supplierNames;
	}

	public void setSupplierNames(List<String> supplierNames) {
		this.supplierNames = supplierNames;
	}

}

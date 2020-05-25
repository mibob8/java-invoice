<<<<<<< HEAD
package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
	private Collection<Product> products = new ArrayList<>();

	public void addProduct(Product product) {
		addProduct(product, 1); 
	}

	public void addProduct(Product product, Integer quantity) {
		
		if( quantity <= 0)
			throw new IllegalArgumentException();
		
		for (int i = 1; i <= quantity; i++)
			products.add(product);
		
	}

	public BigDecimal getNetValue() {
		
		BigDecimal subtotal = new BigDecimal(0);
		 
		for(Product product : products) {
			subtotal = subtotal.add(product.getPrice());
		}
			 
		return subtotal;
	}

	public BigDecimal getTax() {
		
		BigDecimal total = new BigDecimal(0);
		 
		for(Product product : products) {
			total = total.add(product.getPriceWithTax().subtract(product.getPrice()));
		}
			 
		return total;
	}

	public BigDecimal getGrossValue() {
		
		BigDecimal total = new BigDecimal(0);
		 
		for(Product product : products) {
			total = total.add(product.getPriceWithTax());
		}
		 
		return total;
	}
}
=======
package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Map<Product, Integer> products = new HashMap<Product, Integer>();

    private static int invoiceNumber = 0;

    public Invoice()
    {
        invoiceNumber++;
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }

        if(products.containsKey(product))
        {
            AddDuplicatedProductQuantity(product, quantity);
        }
        else
        {
            products.put(product, quantity);
        }
    }

    public int getProductQuantity(Product product){
        return products.get(product);
    }

    private void AddDuplicatedProductQuantity(Product product, Integer quantity)
    {
            int currentQuantity = products.get(product);
            int newQuantity = currentQuantity + quantity;

            products.replace(product, newQuantity);
    }

    public void print(){

        System.out.println("invoice number: " + invoiceNumber);

        for (Map.Entry<Product, Integer> set : products.entrySet()){

            String productName = set.getKey().getName();
            BigDecimal productPrice = set.getKey().getPrice();
            int productQuantity = set.getValue();

            System.out.println( productName + " price: " + productPrice + " quantity: " + productQuantity);
        }

        System.out.println("invoice items count: " + products.size());
    }

    public int getNumber(){
        return invoiceNumber;
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }
}
>>>>>>> tdd

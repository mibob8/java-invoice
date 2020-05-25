package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class FuelCanister extends Product
{
    protected FuelCanister(String name, BigDecimal price)
    {
        super(name, price, new BigDecimal("0"));
    }

    @Override
    public BigDecimal getPriceWithTax()
    {
        return super.getPrice().add(new BigDecimal("5.56"));
    }
}

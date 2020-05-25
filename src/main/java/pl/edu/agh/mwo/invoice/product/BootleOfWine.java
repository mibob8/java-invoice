package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class BootleOfWine extends Product
{
    protected BootleOfWine(String name, BigDecimal price)
    {
        super(name, price, new BigDecimal("0.23"));
    }

    @Override
    public BigDecimal getPriceWithTax()
    {
        return super.getPriceWithTax().add(new BigDecimal("5.56"));
    }
}

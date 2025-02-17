package com.kleberrhuan.model.product;

import com.kleberrhuan.utils.IdGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@NoArgsConstructor
public class Product  {
    private Long id;
    private String name;
    private int quantity;
    private BigDecimal value;
    
    private Product(Long id, String name, int quantity, BigDecimal value) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.value = value.setScale(2, RoundingMode.HALF_UP);
    }

    public static Product create(@NonNull String name, 
                                 @NonNull int quantity,
                                 @NonNull final BigDecimal value) {
        Long newId = IdGenerator.getNextId();
        return new Product(newId, name, quantity, value);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return getQuantity() == product.getQuantity() && getId().equals(product.getId()) &&
                getName().equals(product.getName()) && getValue().equals(product.getValue());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getQuantity();
        result = 31 * result + getValue().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("""
                ID: %d
                Nome: %s
                Quantidade: %d
                Valor: %s
                --------------------------
                """,
                id,
                name,
                quantity,
                value
        );
    }
    
}

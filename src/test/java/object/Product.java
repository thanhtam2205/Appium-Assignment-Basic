package object;

public class Product {
    private String nameProduct;
    private String size;
    private String color;
    private String quantity;

    private String typeCategory;

    public Product(String nameProduct, String size, String color, String quantity , String typeCategory) {
        this.nameProduct = nameProduct;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
        this.typeCategory = typeCategory;
    }

    // Getters and setters
    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTypeCategory() {
        return typeCategory;
    }

    public void setTypeCategory(String typeCategory) {
        this.typeCategory = typeCategory;
    }
}

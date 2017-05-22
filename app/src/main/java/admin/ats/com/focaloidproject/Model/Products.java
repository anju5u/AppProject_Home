package admin.ats.com.focaloidproject.Model;

/**
 * Created by Anju on 25-04-2017.
 */

public class Products {
    private String pro_pk_id;
    private String pro_name;
    private Double pro_price;
    private String pro_desc;
    private String product_images;

    public String getPro_pk_id() {

        return pro_pk_id;
    }

    public void setPro_pk_id(String pro_pk_id) {
        this.pro_pk_id = pro_pk_id;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public Double getPro_price() {
        return pro_price;
    }

    public void setPro_price(Double pro_price) {
        this.pro_price = pro_price;
    }

    public String getPro_desc() {
        return pro_desc;
    }

    public void setPro_desc(String pro_desc) {
        this.pro_desc = pro_desc;
    }

    public String getProduct_images() {
        return product_images;
    }

    public void setProduct_images(String product_images) {
        this.product_images = product_images;
    }
}

package ma.beldifood.productcatalogservice.exception;

public class SubcategoryNotFoundException extends RuntimeException{
    SubcategoryNotFoundException(String error){

        super(error);
    }

}

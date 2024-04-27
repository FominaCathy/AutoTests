package ProjectSQL;

import FominaKat.ProjectSQL.CourierInfoEntity;

public class CourierInfoEntityBuilder {

    private short courierId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String deliveryType;

    public CourierInfoEntityBuilder(short courierId, String firstName, String lastName, String phoneNumber, String deliveryType){
        this.courierId = courierId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.deliveryType = deliveryType;
    }



    public CourierInfoEntity build (){

        CourierInfoEntity result = new CourierInfoEntity();
        result.setCourierId(courierId);
        result.setFirstName(firstName);
        result.setLastName(lastName);
        result.setPhoneNumber(phoneNumber);
        result.setDeliveryType(deliveryType);
        return result;
    }
}

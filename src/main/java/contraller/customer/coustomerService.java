package contraller.customer;

import javafx.collections.ObservableList;
import model.coustomer;
import model.item;

import java.util.List;

public interface coustomerService {
    boolean addCustomer(coustomer coustomer);



    boolean deleteCustomer(String id);

    boolean updateCustomer(coustomer coustomer);

    coustomer searchCoustomer(Integer id);

    ObservableList<coustomer> getCustomer();

    List<Integer> getAllCustomerId();
}

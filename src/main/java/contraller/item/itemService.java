package contraller.item;

import javafx.collections.ObservableList;
import model.item;
import model.orderDeatails;

import java.util.List;

public interface itemService {

    boolean addItem(item itm);

    boolean deleteItem(String id);

    boolean updateItem(item itm);

    item searchCoustomer(String id);

    ObservableList<item>  getAllItems();

    List<String> getAllitemIds();

    boolean updateStock(List<orderDeatails> orderDeatails);
}

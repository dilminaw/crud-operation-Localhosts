package contraller.item;

import model.item;

public interface itemService {

    boolean addItem(item itm);

    boolean deleteItem(String id);

    boolean updateItem(item itm);

    item searchCoustomer(String id);
}

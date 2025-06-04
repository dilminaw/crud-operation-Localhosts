package contraller.customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.coustomer;
import model.item;
import util.Crudutil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class coustomerContraller implements coustomerService{
    coustomerContraller(){

    }

    private static coustomerContraller Instance;
    public static coustomerContraller getInstance(){
        return Instance==null?new coustomerContraller():Instance;
    }



    @Override
    public boolean addCustomer(coustomer coustomer) {
        try {
            return Crudutil.execute("insert into customer values(?,?,?,?)",
                    coustomer.getId(),
                            coustomer.getName(),
                            coustomer.getAddress(),
                            coustomer.getSallary());


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    @Override
    public boolean deleteCustomer(String id) {
        try {
          return   Crudutil.execute("DELETE FROM customer WHERE id=?",id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public boolean updateCustomer(coustomer coustomer) {
        try {
            Crudutil.execute("UPDATE customer set name=?,address=?,sallary=? WHERE id=?",
                    coustomer.getName(),coustomer.getAddress(),coustomer.getSallary(),coustomer.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public coustomer searchCoustomer(Integer id) {

        try {
            ResultSet resultSet = Crudutil.execute("SELECT * FROM customer WHERE id=?", id);
            while (resultSet.next()) {
                return new coustomer(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ObservableList<coustomer> getCustomer() {
        ObservableList<coustomer> observableList = FXCollections.observableArrayList();

        try {
            ResultSet resultSet = Crudutil.execute("SELECT * FROM customer");
            while (resultSet.next()) {
                observableList.add( new coustomer( resultSet.getInt(1),
                       resultSet.getString(2),
                       resultSet.getString(3),
                       resultSet.getDouble(4),
                        resultSet.getString(5)));


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

return observableList;
    }

    @Override
    public List<Integer> getAllCustomerId() {
        ArrayList<Integer> CusIDS = new ArrayList<>();
        ObservableList<coustomer> allItems = getCustomer();
        allItems.forEach(obj->{
            CusIDS.add(obj.getId());

        });
        return CusIDS;
    }


}

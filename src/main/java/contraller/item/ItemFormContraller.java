package contraller.item;

import dbConnection.dbconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.item;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ItemFormContraller  implements Initializable {

    public TextField txtSuplier;
    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colLocation;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQuentity;

    @FXML
    private TableColumn<?, ?> colSupplier;

    @FXML
    private TableView<item> itemTable;

    @FXML
    private ComboBox<String> txtCategory;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtLocation;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQuentity;




    itemService service= new itemContraller();
    List<item> itemArrayList = new ArrayList<>();

    @FXML
    void btnAddItemOnAction(ActionEvent event) {

      item itm=  new item(txtID.getText(),txtName.getText(),txtCategory.getValue(),Double.parseDouble(txtPrice.getText()), Integer.parseInt(txtQuentity.getText()),txtSuplier.getText(),txtLocation.getText(),txtDate.getValue());

     if (service.addItem(itm)){

            new Alert(Alert.AlertType.CONFIRMATION,"added");
     }
dataReaload();
    }

    @FXML
    void btnDeleteItemOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchItemOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateItemOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

          colID.setCellValueFactory(new PropertyValueFactory<>("id"));
          colName.setCellValueFactory(new PropertyValueFactory<>("name"));
          colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
          colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
          colQuentity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
          colSupplier.setCellValueFactory(new PropertyValueFactory<>("suplier"));
          colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
          colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        ObservableList<String> categoryList= FXCollections.observableArrayList();

        categoryList.add("Electronics");
        categoryList.add("Stationery");
        txtCategory.setItems(categoryList);
        dataReaload();

        itemTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) ->{
            if (newVal!=null){
                insertToTextField( newVal);
            }
        });
        dataReaload();

    }

    private void insertToTextField(item itm) {
      txtID.setText(itm.getId());
      txtName.setText(itm.getName());
      txtCategory.setValue(itm.getCategory());
      txtPrice.setText(""+itm.getClass());
      txtQuentity.setText(""+itm.getQuantity());
      txtLocation.setText(itm.getLocation());
      txtDate.setValue(itm.getDate());
      txtSuplier.setText(itm.getSuplier());


    }

    private void dataReaload(){
        ObservableList<item> observableList= FXCollections.observableArrayList();
//item itm = null;
        try {
            Connection connection = dbconnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from items");

        ResultSet resultSet =    preparedStatement.executeQuery();

            while (resultSet.next()) {
               item  itm =new item(
                        resultSet.getString("ItemID"),
                resultSet.getString("ItemName"),
                resultSet.getString("Category"),
                resultSet.getDouble("Price"),
                resultSet.getInt("Quantity"),
                resultSet.getString("Supplier"),
                resultSet.getString("WarehouseLocation"),
                        resultSet.getDate("AddedDate").toLocalDate()
                );

                System.out.println(itm);
                observableList.add(itm);

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

itemTable.setItems(observableList);

    }

}

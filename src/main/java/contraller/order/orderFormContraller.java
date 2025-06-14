package contraller.order;

import contraller.customer.coustomerContraller;
import contraller.item.itemContraller;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.*;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class orderFormContraller implements Initializable {

    public TextField TxtProducts;
    public TextField UnitPrice;
    public TextField txtOrderId;
    @FXML
    private ComboBox<String> CmbitemCode;

    @FXML
    private TableColumn<?, ?> ColTotal;

    @FXML
    private ComboBox<Integer> cmbCustomerID;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderID;

    @FXML
    private Label lblOrderTime;

    @FXML
    private TableView<cartTM> tableOrder;

    @FXML
    private TextField txtCustomerAddress;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtStock;
    ObservableList<cartTM> carts = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setdateAndTIME();
        loadCusIDs();
        loadItemID();
        cmbCustomerID.getSelectionModel().selectedItemProperty().addListener((observableValue, stringSingleSelectionModel, t1) ->{
            searchcoustomer(t1);
        } );

        CmbitemCode.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            searchItem(t1);
        });
    }

    private void searchItem(String t1) {
        item item = itemContraller.getInstance().searchCoustomer(t1);
        txtStock.setText(String.valueOf(item.getQuantity()));

       // TxtProducts.setText(String.valueOf(item.));
       // UnitPrice.setText(String.valueOf(item.getPrice()));
        UnitPrice.setText(String.valueOf(item.getPrice()));

    }

    private void searchcoustomer(Integer id) {
        coustomer coustomer = coustomerContraller.getInstance().searchCoustomer(id);
        txtCustomerAddress.setText(coustomer.getAddress());
        txtDescription.setText(coustomer.getDiscription());
        txtCustomerName.setText(coustomer.getName());

    }

    @FXML
    void btnAddCardOnAction(ActionEvent event) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("numberOfProducts"));
        ColTotal.setCellValueFactory(new PropertyValueFactory<>("total"));




        String itemCode = CmbitemCode.getValue();
        String itemDescription = txtDescription.getText();
        Integer qty = Integer.valueOf(txtStock.getText());
        Double unitPrice = Double.valueOf(UnitPrice.getText());
        Integer numOfProduct=Integer.valueOf(TxtProducts.getText());
        Double total= numOfProduct*unitPrice;

        Integer itemStock=Integer.parseInt(txtStock.getText());
        if (itemStock<numOfProduct){
            new Alert(Alert.AlertType.CONFIRMATION,"Can't set").show();
        }
        else {
            carts.add( new cartTM(itemCode,itemDescription,qty,unitPrice,total,numOfProduct));
            tableOrder.setItems(carts);

        }


calculateTotal();


    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws SQLException {
        String orderIdText = txtOrderId.getText();
        LocalDate orderDateText =LocalDate.parse( lblOrderDate.getText());
        Integer CusIdText = cmbCustomerID.getValue();
        ArrayList<orderDeatails> orderDeatails = new ArrayList<>();
        carts.forEach(obj->{
            orderDeatails.add(new orderDeatails(
                   txtOrderId.getText(),
                    obj.getItemCode(),
                    obj.getQty(),0.0


            ));
        });


        order orders =new order(orderIdText,orderDateText,CusIdText,orderDeatails);
        new orderContraller().placeOrder(orders);

    }



    private void setdateAndTIME(){
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblOrderDate.setText(f.format(date));


   //-----------------------------------------------------------------

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime now = LocalTime.now();
            lblOrderTime.setText(now.getHour() + ":" + now.getMinute() + ":" + now.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    private void loadCusIDs() {
        List<Integer> customerids = coustomerContraller.getInstance().getAllCustomerId(); // assuming this returns a List or ArrayList
        ObservableList<Integer> allcoustomers = FXCollections.observableArrayList(customerids);
        cmbCustomerID.setItems(allcoustomers);
    }

    private void loadItemID(){
        List<String> allItemsids = itemContraller.getInstance().getAllitemIds();
        ObservableList<String> stringObservableList = FXCollections.observableArrayList(allItemsids);
        CmbitemCode.setItems( stringObservableList);


    }
    Double total=0.0;

    private void calculateTotal(){
        carts.forEach(obj->{
          total+= obj.getTotal();
        });
lblNetTotal.setText(String.valueOf(total));
    }

}
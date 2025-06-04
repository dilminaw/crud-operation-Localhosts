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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import model.coustomer;
import model.item;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class orderFormContraller implements Initializable {

    public TextField TxtProducts;
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
    private TableView<?> tableOrder;

    @FXML
    private TextField txtCustomerAddress;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtStock;

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

    }

    private void searchcoustomer(Integer id) {
        coustomer coustomer = coustomerContraller.getInstance().searchCoustomer(id);
        txtCustomerAddress.setText(coustomer.getAddress());
        txtDescription.setText(coustomer.getDiscription());
        txtCustomerName.setText(coustomer.getName());

    }

    @FXML
    void btnAddCardOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

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

}
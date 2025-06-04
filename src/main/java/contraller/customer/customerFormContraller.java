package contraller.customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.coustomer;

public class customerFormContraller {

    public TextField txtSallary;
    public TextField txtDiscription;
    @FXML
    private TextField TXTid;

    @FXML
    private TextField txtAddresas;



    @FXML
    private TextField txtName;
    coustomerService coustomerService=coustomerContraller.getInstance();

    @FXML
    void btnAddOnAction(ActionEvent event) {
        coustomerService.addCustomer(new coustomer(Integer.parseInt(TXTid.getText()),
                txtName.getText(),
                txtAddresas.getText(),
                Double.parseDouble(txtSallary.getText()),
                txtDiscription.getText()

                ));

    }

    @FXML
    void btnDeleteOnAcrtion(ActionEvent event) {
        coustomerService.deleteCustomer(TXTid.getText());

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        coustomer coustomer = coustomerService.searchCoustomer(Integer.valueOf(TXTid.getText()));
        txtName.setText(coustomer.getName());
        txtAddresas.setText(coustomer.getAddress());
        txtSallary.setText( String.valueOf(coustomer.getSallary()));

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        coustomerService.updateCustomer(new coustomer(Integer.parseInt(TXTid.getText()),
                txtName.getText(),
                txtAddresas.getText(),
                Double.parseDouble(txtSallary.getText()),
                txtDiscription.getText()
        ));

    }

}

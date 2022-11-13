package lk.ijse.jasper.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.ijse.jasper.db.DBConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;

public class CustomerFormController {

    public TextField txtName;
    public Label lblEmail;

    public void btnGenerateOnAction(ActionEvent actionEvent) {
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("cashierName",txtName.getText());
        hm.put("email",lblEmail.getText());
        InputStream inputStream = this.getClass().getResourceAsStream
                ("/lk/ijse/jasper/report/Blank_A4_4.jrxml");
        try {
            JasperReport compileReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, hm, DBConnection.getInstance().getConnection());
//            JasperPrintManager.printReport(jasperPrint,true);
            JasperViewer.viewReport(jasperPrint);
        } catch (JRException | SQLException | ClassNotFoundException e) {

            System.out.println(e);
            e.printStackTrace();
        }
    }
}

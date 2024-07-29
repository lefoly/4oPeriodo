/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Contato;
import model.dao.ContatoDaoJdbc;
import model.dao.DaoFactory;
import start.projetopadrao.App;

/**
 * FXML Controller class
 *
 * @author lefoly
 */
public class FormularioController implements Initializable {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelefone;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnGravar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnCancelarOnAction(ActionEvent event) throws IOException {
        App.setRoot("Principal");
    }

    @FXML
    private void btnGravarOnAction(ActionEvent event) throws Exception {
        Contato contato = new Contato();
        contato.setNome(txtNome.getText());
        contato.setEmail(txtEmail.getText());
        contato.setTelefone(txtTelefone.getText());
        ContatoDaoJdbc dao = DaoFactory.novoContatoDao();
        dao.incluir(contato);
        App.setRoot("Principal");
    }
}





















/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Contato;
import model.dao.ContatoDaoJdbc;
import model.dao.DaoFactory;
import start.projetopadrao.App;

/**
 * FXML Controller class
 *
 * @author lefoly
 */
public class PrincipalController implements Initializable {

    @FXML
    private Button btnIncluir;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnExcluir;
    @FXML
    private TextField txtFiltro;
    @FXML
    private Button btnFiltrar;
    @FXML
    private Button btnLimpar;
    @FXML
    private TableView<Contato> tblContato;
    @FXML
    private TableColumn<Contato, String> tblColNome;
    @FXML
    private TableColumn<Contato, String> tblColEmail;
    @FXML
    private TableColumn<Contato, String> tblColTelefone;

    private List<Contato> listaContatos;
    private ObservableList<Contato> observableListContatos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            carregarContatos();
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }

    @FXML
    private void btnIncluirOnAction(ActionEvent event) throws IOException {
        App.setRoot("Formulario");
    }

    @FXML
    private void btnEditarOnAction(ActionEvent event) {
    }

    @FXML
    private void btnExcluirOnAction(ActionEvent event) throws Exception {
        Contato contatoSelecionado = 
                tblContato.selectionModelProperty().getValue().getSelectedItem();
        if (contatoSelecionado != null) {
            ContatoDaoJdbc dao = DaoFactory.novoContatoDao();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Aviso");
            alert.setContentText("Confirma exclusão de " + contatoSelecionado.getNome() + "?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                try {
                    dao.excluir(contatoSelecionado);
                } catch (Exception e) {
                    String mensagem = "Ocorreu um erro: " + e.getMessage();
                    Alert alertErro = new Alert(Alert.AlertType.INFORMATION);
                    alertErro.setTitle("Aviso");
                    alertErro.setContentText(mensagem);
                    alertErro.showAndWait();
                }
            }
            carregarContatos();
        }
    }

    @FXML
    private void btnFiltrarOnAction(ActionEvent event) {
    }

    @FXML
    private void btnLimparOnAction(ActionEvent event) {
    }

    public void carregarContatos() {

        try {
          
        tblColNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        tblColEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        tblColTelefone.setCellValueFactory(new PropertyValueFactory<>("Telefone"));

        ContatoDaoJdbc dao = DaoFactory.novoContatoDao();
        listaContatos = dao.listar();

        observableListContatos = FXCollections.observableArrayList(listaContatos);
        tblContato.setItems(observableListContatos);

        } catch (Exception e) {
            
        }
    }

}

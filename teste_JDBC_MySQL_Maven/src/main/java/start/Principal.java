/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package start;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author lefoly
 */
public class Principal {

    public static void main(String[] args) {

        try {

            String opcao = "";
            while (!opcao.equals("0")) {

                opcao
                        = JOptionPane.showInputDialog("    AGENDA DE CONTATOS\n"
                                + "    ------------------\n"
                                + "    1. Inclusão\n"
                                + "    2. Edição\n"
                                + "    3. Exclusão\n"
                                + "    4. Listagem\n"
                                + "    0. Sair");

                switch (opcao) {
                    case "1":
                        //INCLUSÃO
                        //Contato c = new Contato("NOVO CONTATO", "novo@gmail.com", "0000-0000");
                        //incluir(c);               
                        break;

                    case "2":
                        //EDIÇÃO
                        //Contato c = new Contato(
                        //        "Contato EDITADO HOJE", "editado@gmail.com", "1212-1212");
                        //c.setId(8);
                        //editar(c);                    
                        break;

                    case "3":
                        //EXCLUSÃO
                        int id = Integer.parseInt(JOptionPane.showInputDialog(
                                "Digite o id do Contato a excluir:"));
                        Contato c = new Contato();
                        c.setId(id);
                        excluir(c);
                        break;

                    case "4":
                        //LISTAGEM
                        String listagem = "";
                        List<Contato> lista = listar();
                        for (Contato contato : lista) {
                            listagem += contato + "\n";
                        }
                        JOptionPane.showMessageDialog(null, listagem);
                        break;
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro: \n" + ex);
        }

    }

    public static void incluir(Contato c) throws SQLException {
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/agenda", "root", "");

        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO Contato (nome, email, telefone) VALUES(?, ?, ?) ");
        ps.setString(1, c.getNome());
        ps.setString(2, c.getEmail());
        ps.setString(3, c.getTelefone());
        ps.execute();

        conn.close();

    }

    public static void editar(Contato c) throws SQLException {

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/agenda", "root", "");
        PreparedStatement ps = conn.prepareStatement(
                "UPDATE Contato SET nome = ?, email = ?, telefone=? WHERE id = ?");
        ps.setString(1, c.getNome());
        ps.setString(2, c.getEmail());
        ps.setString(3, c.getTelefone());
        ps.setInt(4, c.getId());
        ps.execute();

        conn.close();

    }

    public static void excluir(Contato c) throws SQLException {
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/agenda", "root", "");

        PreparedStatement ps = conn.prepareStatement("DELETE FROM Contato WHERE id = ?");
        ps.setInt(1, c.getId());
        ps.execute();

        conn.close();

    }

    public static List<Contato> listar() throws SQLException {
        List<Contato> lista = new ArrayList();

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/agenda", "root", "");

        PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM Contato");
        ResultSet rs = ps1.executeQuery();
        while (rs.next()) {
            Contato c1 = new Contato();
            c1.setId(rs.getInt("id"));
            c1.setNome(rs.getString("nome"));
            c1.setEmail(rs.getString("email"));
            c1.setTelefone(rs.getString("telefone"));
            lista.add(c1);
        }

        conn.close();

        return lista;

    }

}

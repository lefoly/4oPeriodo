/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package start;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Aluno;
import model.Turma;

/**
 *
 * @author lefoly
 */
public class Principal {

    public static void main(String[] args) {

        Aluno a1 = new Aluno("Fulano");
        Aluno a2 = new Aluno("Beltrano");
        Aluno a3 = new Aluno("Ciclano");
        Aluno a4 = new Aluno("Maria");
        Aluno a5 = new Aluno("José");

        Turma t1 = new Turma("POO");
        List<Aluno> alunosT1 = new ArrayList();
        alunosT1.add(a1);
        alunosT1.add(a3);
        alunosT1.add(a4);
        t1.setAlunos(alunosT1);

        Turma t2 = new Turma("Banco de Dados");
        List<Aluno> alunosT2 = new ArrayList();
        alunosT2.add(a2);
        alunosT2.add(a5);
        t2.setAlunos(alunosT2);

        //System.out.println(t1);
        //System.out.println();
        //System.out.println(t2);

        //INCLUSAO
        incluir(t1);
        incluir(t2);

        //LISTAGEM
        List<Turma> lista = listar();
        for (Turma t : lista) {
            System.out.println(t);
        }
        
        //EXCLUSÃO
        //excluirTurmaComAlunos(1);
        
    }

    public static void incluir(Turma t) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/escola", "root", "");

            PreparedStatement ps = conn.prepareStatement("INSERT INTO Turma (nome) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, t.getNome());
            ps.execute();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idGerado = generatedKeys.getInt(1);
                t.setId(idGerado);
            }

            for (Aluno a : t.getAlunos()) {
                PreparedStatement ps2 = conn.prepareStatement("INSERT INTO Aluno (nome, turma_id) VALUES(?, ?)");
                ps2.setString(1, a.getNome());
                ps2.setInt(2, t.getId());
                ps2.execute();
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static List<Turma> listar() {
        List<Turma> listaTurmas = new ArrayList();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/escola", "root", "");

            PreparedStatement psTurma = conn.prepareStatement("SELECT * FROM Turma");
            ResultSet rsTurma = psTurma.executeQuery();
            while (rsTurma.next()) {
                Turma t = new Turma();
                t.setId(rsTurma.getInt("id"));
                t.setNome(rsTurma.getString("nome"));
                listaTurmas.add(t);
                PreparedStatement psAluno = conn.prepareStatement("SELECT * FROM Aluno WHERE turma_id = ?");
                psAluno.setInt(1, t.getId());
                ResultSet rsAluno = psAluno.executeQuery();
                List<Aluno> listaAlunos = new ArrayList();
                while (rsAluno.next()) {
                    Aluno a = new Aluno();
                    a.setId(rsAluno.getInt("id"));
                    a.setNome(rsAluno.getString("nome"));
                    listaAlunos.add(a);
                }
                t.setAlunos(listaAlunos);
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listaTurmas;
    }

    public static void excluirTurmaComAlunos(int turmaId) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/escola", "root", "");

            PreparedStatement psAlunos = conn.prepareStatement("DELETE FROM Aluno WHERE turma_id = ?");
            psAlunos.setInt(1, turmaId);
            psAlunos.execute();

            PreparedStatement psTurma = conn.prepareStatement("DELETE FROM Turma WHERE id = ?");
            psTurma.setInt(1, turmaId);
            psTurma.executeUpdate();

            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

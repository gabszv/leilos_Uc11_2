/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    
    public int cadastrarProduto (ProdutosDTO produto){
        
        conn = new conectaDAO().connectDB();
        int status;
        try {
            //preparando a string sql com o código de inserção para o banco de dados
            prep = conn.prepareStatement("INSERT INTO produtos(nome, valor, status)"
                    + "VALUES(?,?,?)");
            //setando os parâmetros
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            //executando a query
            status = prep.executeUpdate();
            //retornando o valor da query
            return status;
        } catch (SQLException ex) {
            //mensagem de erro caso o programa não consiga se conectar com o banco de dados
            JOptionPane.showMessageDialog(null,"Erro ao cadastrar dados do filme: "
                    + ex.getMessage());
            return ex.getErrorCode();
        }
        
        
    }
    
    public List<ProdutosDTO> listarProdutos(){
        
        conn = new conectaDAO().connectDB();
        String sql = "SELECT * FROM produtos";
        try {

            prep = this.conn.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            
            List<ProdutosDTO> listaProdutos = new ArrayList<>();

            while(rs.next()){
                ProdutosDTO produtos = new ProdutosDTO();
                produtos.setId(rs.getInt("id"));
                produtos.setNome(rs.getString("nome"));
                produtos.setValor(rs.getInt("valor"));
                produtos.setStatus(rs.getString("Status"));
                listaProdutos.add(produtos);
            }
            

            return listaProdutos;

            //tratando o erro, caso ele ocorra
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
            return null;
        }
    }
    
    
    
        
}


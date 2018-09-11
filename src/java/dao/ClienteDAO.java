package dao;
import model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ClienteDAO {
    private Connection conn; 
    private PreparedStatement stmt;
    private Statement st;
    private ResultSet rs;
    private ArrayList<Cliente> lista = new ArrayList<>();
    public ClienteDAO(){
        conn = new ConnectionFactory().getConexao();
    }
    
    public void inserir(Cliente cliente){
        String sql = "INSERT INTO CLIENTES (NOME, LOGRADOURO, NUMERO, BAIRRO, EMAIL, DDDTELEFONE, "
                + "NUMTELEFONE, ATIVO, CODCIDADE) VALUES (?,?,?,?,?,?,?,?,?)";
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getLogradouro());
            stmt.setString(3, cliente.getNumero());
            stmt.setString(4, cliente.getBairro());
            stmt.setString(5, cliente.getEmail());
            stmt.setInt(6, cliente.getDdd());
            stmt.setString(7, cliente.getNumTelefone());
            stmt.setBoolean (8, cliente.isStatusAtivo());
            stmt.setInt(9, cliente.getCodCidade());
            stmt.execute();
            stmt.close();
        }catch(Exception erro){
               throw new RuntimeException("Erro 2: "+erro);
        }
    }
     public void alterar(Cliente cliente){
        String sql = "UPDATE CLIENTES SET NOME = ?, LOGRADOURO = ?, NUMERO = ?, BAIRRO = ?, EMAIL = ?"
                + "DDDTELEFONE = ?, NUMTELEFONE = ?, ATIVO= ?, CODCIDADE = ? WHERE  CODCLIENTE = ?";
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getLogradouro());
            stmt.setString(3, cliente.getNumero());
            stmt.setString(4, cliente.getBairro());
            stmt.setString(5, cliente.getEmail());
            stmt.setInt(6, cliente.getDdd());
            stmt.setString(7, cliente.getNumTelefone());
            stmt.setBoolean (8, cliente.isStatusAtivo());
            stmt.setInt(9, cliente.getCodCidade());
            stmt.setInt(10, cliente.getCodCliente());
            stmt.execute();
            stmt.close();
        }catch(Exception erro){
               throw new RuntimeException("Erro 3: "+erro);
        }    
    }
     public void excluir(int valor){
        String sql = "DELETE FROM CLIENTES WHERE CODCLIENTE = "+valor;
        try{
            st = conn.createStatement();
            st.execute(sql);
            st.close();
        }catch(Exception erro){
               throw new RuntimeException("Erro 3: "+erro);
        }    
    }
    public ArrayList<Cliente> ListarTodos(){
        String sql = "SELECT * FROM CLIENTES";
        try{
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Cliente cliente =  new Cliente();
                cliente.setCodCliente(rs.getInt("CODCLIENTE"));
                cliente.setNome(rs.getString("NOME"));
                cliente.setLogradouro(rs.getString("LOGRADOURO"));
                cliente.setNumero(rs.getString("NUMERO"));
                cliente.setBairro(rs.getString("BAIRRO"));
                cliente.setEmail(rs.getString("EMAIL"));
                cliente.setDdd(rs.getInt("DDDTELEFONE"));
                cliente.setNumTelefone(rs.getString("NUMTELEFONE"));
                cliente.setStatusAtivo(rs.getBoolean("ATIVO"));
                cliente.setCodCidade(rs.getInt("CODCIDADE"));
                lista.add(cliente);
            }
        }catch(Exception erro){    
            throw new RuntimeException("Erro 5: "+erro);
        }
        return lista;
    }
}
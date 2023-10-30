package br.edu.iftm.tspi.contatos.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import br.edu.iftm.tspi.contatos.domain.Cliente;

@Component
public class ClienteDao {
    
    @Autowired
    JdbcTemplate db;

    public List<Cliente> getContatos(){
        String sql = "select * from contatos";
        return db.query(sql, (res, rowNum) -> new Cliente(
            res.getString("nome"),
            res.getString("email"),
            res.getString("telefone")
        ));
    }

    public Cliente getContato(String email){
        String sql = "select * from contatos where email = ?";
        List<Cliente> contatos = db.query(sql, (res, rowNum) -> new Cliente(
            res.getString("nome"),
            res.getString("email"),
            res.getString("telefone")),email);
            if(contatos != null && contatos.size() > 0){
                return contatos.get(0);
            }else{
                return null;
            }
    }

    public void salvar(Cliente cliente){
        String sql = "insert into contatos(nome,email,telefone) values(?,?,?)";
        db.update(sql, cliente.getNome(), cliente.getEmail(), cliente.getTelefone());
    }

    public void atualizar(Cliente cliente){
        String sql = "update contatos set nome = ?, telefone = ? where email = ?";
        db.update(sql, cliente.getNome(), cliente.getTelefone(), cliente.getEmail());
    }

    public void deletar(String email){
        String sql = "delete from contatos where email = ?";
        db.update(sql, email);
    }
}

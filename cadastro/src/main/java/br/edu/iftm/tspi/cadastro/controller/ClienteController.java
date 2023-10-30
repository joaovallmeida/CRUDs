package br.edu.iftm.tspi.cadastro.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.iftm.tspi.cadastro.DTO.Cliente;

@Controller
public class ClienteController {

    List<Cliente> cadastros = new ArrayList<>();

    public void addCliente(String nome, String email, String telefone) {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cadastros.add(cliente);
    }

    @GetMapping(value = "/home")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("cliente", new Cliente());
        mv.addObject("cadastros", cadastros);
        return mv;
    }

    @PostMapping(value = "/cadastrar")
    public ModelAndView cadastrar(@ModelAttribute("cliente") Cliente cliente) {
        boolean busca = false;
        for (int i = 0; i < cadastros.size(); i++) {
            if (cadastros.get(i).getEmail().equals(cliente.getEmail())) {
                cadastros.get(i).setNome(cliente.getNome());
                cadastros.get(i).setTelefone(cliente.getTelefone());
                busca = true;
                break;
            }
        }
        if (!busca) {
            cadastros.add(cliente);
        }
        return home();
    }

    @GetMapping(value = "/editar/{email}")
    public ModelAndView editar(@PathVariable String email) {
        ModelAndView mv = new ModelAndView("home");
        Cliente cliente = new Cliente();
        for (Cliente c : cadastros) {
            if (c.getEmail().equals(email)) {
                cliente = c;
            }
        }
        mv.addObject("cliente", cliente);
        mv.addObject("cadastros", cadastros);
        return mv;
    }

    @GetMapping(value = "/excluir/{email}")
    public ModelAndView excluir(@PathVariable String email){
        for(int i = 0; i < cadastros.size(); i++){
            if(cadastros.get(i).getEmail().equals(email)){
                cadastros.remove(cadastros.get(i));
            }
        }
        return home();
    }
}

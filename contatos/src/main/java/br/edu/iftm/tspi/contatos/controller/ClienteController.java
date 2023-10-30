package br.edu.iftm.tspi.contatos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.iftm.tspi.contatos.dao.ClienteDao;
import br.edu.iftm.tspi.contatos.domain.Cliente;

@Controller
public class ClienteController {

    @Autowired
    ClienteDao dao;

    @GetMapping(value = "/home")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("cliente", new Cliente());
        mv.addObject("cadastros", dao.getContatos());
        return mv;
    }

    @PostMapping(value = "/cadastrar")
    public ModelAndView cadastrar(@ModelAttribute("cliente") Cliente cliente) {
        if(dao.getContatos().isEmpty()){
            dao.salvar(cliente);
        }else if(dao.getContatos().contains(dao.getContato(cliente.getEmail()))){
            dao.atualizar(cliente);
        }else{
            dao.salvar(cliente);
        }
        return home();
    }

    @GetMapping(value = "/editar/{email}")
    public ModelAndView editar(@PathVariable String email) {
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("cliente", dao.getContato(email));
        mv.addObject("cadastros", dao.getContatos());
        return mv;
    }

    @GetMapping(value = "/excluir/{email}")
    public ModelAndView excluir(@PathVariable String email){
        dao.deletar(email);
        return home();
    }
}

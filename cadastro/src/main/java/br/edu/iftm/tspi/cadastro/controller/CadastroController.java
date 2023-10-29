package br.edu.iftm.tspi.cadastro.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.iftm.tspi.cadastro.DTO.CadastroDTO;

@Controller
public class CadastroController {
    List<CadastroDTO> cadastro = new ArrayList<>();

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("cadastro", cadastro);
        model.addAttribute("cliente", new CadastroDTO());
        return "home";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(Model model, CadastroDTO cliente) {
        if (cadastro.isEmpty()) {
            cadastro.add(cliente);
        } else {
            for (int i = 0; i < cadastro.size(); i++) {
                if (cadastro.get(i).getEmail().equals(cliente.getEmail())) {
                    cadastro.get(i).setNome(cliente.getNome());
                    cadastro.get(i).setTelefone(cliente.getTelefone());
                    cadastro.get(i).setEmail(cliente.getEmail());
                } else {
                    cadastro.add(cliente);
                }
            }
        }
        return home(model);
    }

    @GetMapping("/editar/{email}")
    public String editar(Model model, CadastroDTO cliente, @PathVariable String email){
        for(int i = 0; i < cadastro.size(); i++){
            if(cadastro.get(i).getEmail().equals(email)){
                cliente = cadastro.get(i);
                System.out.println(cliente);
                model.addAttribute("cliente", cliente);
            }
        }
        return home(model);
    }
    @GetMapping("/delete/{email}")
    public String deletar(Model model, CadastroDTO cliente, @PathVariable String email) {
        while (!cadastro.isEmpty()) {
            for (int i = 0; i < cadastro.size(); i++) {
                if (cadastro.get(i).getEmail().equals(email)) {
                    cadastro.remove(cadastro.get(i));
                }
            }
        }
        return home(model);
    }
}

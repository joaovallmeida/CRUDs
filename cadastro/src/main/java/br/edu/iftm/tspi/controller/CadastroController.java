package br.edu.iftm.tspi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.iftm.tspi.DTO.CadastroDTO;

@Controller
public class CadastroController {
    List<CadastroDTO> cadastro = new ArrayList<>();
    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("cadastro",cadastro);
        model.addAttribute("cliente", new CadastroDTO());
        return "home";
    }
}

package br.edu.iftm.tspi.clientes.resources;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClienteController {
    
    @RequestMapping("/")
    public String index(Model model){
        return "index";
    }
}

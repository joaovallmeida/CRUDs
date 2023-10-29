package br.edu.iftm.tspi.cadastro.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CadastroDTO {
    private String nome, telefone, email;
}

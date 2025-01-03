package me.dio.mockito.exemplos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyString;

/**
 * Teste da classe {@link CadastrarPessoa} apresentando cenários básicos de uso do Mockito, usando o recurso
 * de mocks e a manipulação de retornos, da forma mais simples e com manipulação de erros
 */
@ExtendWith(MockitoExtension.class) // usando a extensão Mockito para o JUnit 5
public class CadastrarPessoaTeste {

    @Mock // criando um mock para a classe ApiDosCorreios para simular a integração com a API dos Correios
    private ApiDosCorreios apiDosCorreios;

    @InjectMocks // Cadastro pessoa é uma dependencia de apiDosCorreos pelo qual o mock deve ser injetado
    private CadastrarPessoa cadastrarPessoa;

    @Test
    void cadastrarPessoa(){

        DadosLocalizacao dadosLocalizacao = new DadosLocalizacao("SP", "São Paulo", "Rua 1", "casa", "Lago Azul");

        // susbstituindo o retorno real da integração com a API dos Correios por retorno mock
        Mockito.when(apiDosCorreios.buscaDadosComBaseNoCep(anyString())).thenReturn(dadosLocalizacao);

        Pessoa Alejandro = cadastrarPessoa.cadastrarPessoa("Alejandro", "87654321", LocalDate.of(2024, 1, 3), "87654321");
        DadosLocalizacao enderecoAlejandro = Alejandro.getEndereco();

        // verificando se o retorno foi o esperado
        Assertions.assertEquals("Alejandro", Alejandro.getNome());
        Assertions.assertEquals("87654321", Alejandro.getDocumento());
        Assertions.assertEquals("SP", enderecoAlejandro.getUf());
        assertEquals(dadosLocalizacao.getBairro(), enderecoAlejandro.getBairro());
        assertEquals(dadosLocalizacao.getCidade(), enderecoAlejandro.getCidade());
        assertEquals(dadosLocalizacao.getUf(), enderecoAlejandro.getUf());
    }

    @Test
    void lancarExceptionQuandoChamarApiDosCorreios() { // testando o cenaário de falha na integração com a API dos Correios
        //outra forma
        //Mockito.doThrow(IllegalArgumentException.class).when(apiDosCorreios).buscaDadosComBaseNoCep(anyString());

        Mockito.when(apiDosCorreios.buscaDadosComBaseNoCep(anyString())).thenThrow(new RuntimeException("Erro ao buscar dados do CEP")); // simulando um erro ao chamar a API dos Correios

        Assertions.assertThrows(RuntimeException.class, () -> cadastrarPessoa.cadastrarPessoa("Alejandro", "87654321", LocalDate.of(2024, 1, 3), "87654321"));

    }
}

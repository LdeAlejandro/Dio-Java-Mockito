package me.dio.mockito.exemplos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Teste da classe {@link ServicoEnvioEmail} exemplificando testes usando Argument Captor
 */
@ExtendWith(MockitoExtension.class)
public class ServicoEnvioEmailTeste {

    @Mock // criando um mock para a classe PlataformaDeEnvio para simular a integração com a Plataforma de Envio
    private PlataformaDeEnvio plataforma;

    @InjectMocks // ServicoEnvioEmail é uma dependencia de PlataformaDeEnvio pelo qual o mock deve ser injetado
    private ServicoEnvioEmail servico;

    @Captor // criando um captor para capturar o email que foi enviado atraves dos argumentos
    private ArgumentCaptor<Email> emailCaptor;

    @Test
    public void validaSeEmailEstaComDadosCorretos() {

        String email = "jose.alve@provedor.com";
        String mensagem = "Mensagem de Teste 123";

        servico.enviaEmail(email, mensagem, true); // chamando o metodo de envio de email indicando com true que o formato é html
        Mockito.verify(plataforma).enviaEmail(emailCaptor.capture()); // verificando se o metodo de envio de email foi chamado e capturar o email

        Email emailCapturado = emailCaptor.getValue(); // pegando o email capturado
        Assertions.assertEquals(Formato.HTML, emailCapturado.getFormato()); // verificando se o formato do email capturado eh html
    }

}

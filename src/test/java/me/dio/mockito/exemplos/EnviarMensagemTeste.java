package me.dio.mockito.exemplos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

/**
 * Teste da classe {@link EnviarMensagem} exemplificando Spy
 */
@ExtendWith(MockitoExtension.class)
public class EnviarMensagemTeste {

    @Spy // criando um spy para a classe EnviarMensagem para observar comportamento do objeto
            //referencia o objeto real da classe
    EnviarMensagem enviarMensagem = new EnviarMensagem();

    @Test
    void Verificar_adicionarMensagem() {

        Mensagem mensagem = new Mensagem("Hello World");

        enviarMensagem.adicionarMensagem(mensagem);

        verify(enviarMensagem).adicionarMensagem(mensagem);
        Assertions.assertFalse(enviarMensagem.getMensagens().isEmpty());
        Assertions.assertEquals(1, enviarMensagem.getMensagens().size());
    }

}

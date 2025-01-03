package me.dio.mockito.exemplos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Teste da classe {@link Conta} usando Spy para realizar o recurso de verificações
 */
@ExtendWith(MockitoExtension.class)
public class ContaTeste {

    @Spy // criando um spy para a classe Conta para observar comportamento do objeto real
    private Conta conta = new Conta(3000);

    @Test
    void verificaSeChamouMetodoDebita() { // verifica se o metodo debita foi chamado de fato
        conta.pagaBoleto(300);
        Mockito.verify(conta).debita(300);
    }

    @Test
    void verificaSeNadaFoiChamado() {
        Mockito.verifyNoInteractions(conta);
    }

    @Test
    void verificaAOrdemDasChamadas() { // verifica a ordem das chamadas dos metodos
        InOrder inOrder = Mockito.inOrder(conta);
        conta.pagaBoleto(300);
        conta.debita(300);
        conta.enviaCreditoParaEmissor(300);
        inOrder.verify(conta).pagaBoleto(300);
        inOrder.verify(conta).debita(300);
        inOrder.verify(conta).enviaCreditoParaEmissor(300);
    }

    @Test
    void validaQuantidadeDeVezesQueMétodoFoiChamado() { // verifica a quantidade de vezes que um metodo foi chamado

        conta.validaSaldo(100);
        conta.validaSaldo(100);
        conta.validaSaldo(100);

        Mockito.verify(conta, Mockito.times(3)).validaSaldo(100);
    }
}

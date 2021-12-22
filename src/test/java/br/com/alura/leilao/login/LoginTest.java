package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginTest {

    private LoginPage loginPage;

    @BeforeEach
    void beforeEach(){
        this.loginPage = new LoginPage();
    }

    @AfterEach
    void afterEach(){
        this.loginPage.fechar();
    }

    @Test
    void deveriaEfetuarLoginComDadosValidos(){
        loginPage.efetuarLogin("fulano", "pass");

        String nomeUsuarioLogado = loginPage.getNomeUsuarioLogado();

        Assert.assertEquals("fulano", nomeUsuarioLogado);
        Assert.assertFalse(loginPage.isPaginaAtual());
    }

    @Test
    void naoDeveriaEfetuarLoginComDadosInvalidos(){
        loginPage.efetuarLogin("fulanoa", "pass");

        Assert.assertTrue(loginPage.isPaginaDeLoginComDadosInvalidos());
        Assert.assertNull(loginPage.getNomeUsuarioLogado());
        Assert.assertTrue(loginPage.contemTexto("Usuário e senha inválidos."));
    }

    @Test
    void naoDeveriaAcessarPaginaRestritaSemEstarLogado(){
        loginPage.navegaParaPaginaDeLances();


        Assert.assertTrue(loginPage.isPaginaDeLogin());
        Assert.assertFalse(loginPage.contemTexto("Dados do Leilão"));
    }
}

package leiloes;

import login.LoginPage;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LeiloesTest {

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
        loginPage.preencheFormularioDeLogin("fulano", "pass");
        loginPage.efetuarLogin();

        Assert.assertFalse(loginPage.isPaginaDeLogin());
        Assert.assertEquals("fulano", loginPage.getNomeUsuarioLogado());
    }

    @Test
    void naoDeveriaEfetuarLoginComDadosInvalidos(){
        loginPage.preencheFormularioDeLogin("invalido", "123");
        loginPage.efetuarLogin();

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

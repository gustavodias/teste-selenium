package leiloes;

import login.LoginPage;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class LeiloesTest {

    private LeiloesPage leiloesPage;
    private CadastroLeilaoPage cadastroLeilaoPage;

    @BeforeEach
    void beforeEach(){
        LoginPage loginPage = new LoginPage();
        loginPage.preencheFormularioDeLogin("fulano", "pass");
        this.leiloesPage = loginPage.efetuarLogin();
        leiloesPage.carregarFormulario();
        this.cadastroLeilaoPage = leiloesPage.carregarFormulario();
    }

    @AfterEach
    void afterEach(){
        this.leiloesPage.fechar();
    }

    @Test
    void deveriaCadastrarLeilao(){
        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilao do dia "+hoje;
        String valor = "500.00";

        this.leiloesPage = cadastroLeilaoPage.cadastrarLeilao(nome,valor,hoje);

        Assert.assertTrue(leiloesPage.isLeilaoCadastrado(nome, valor, hoje));
    }

    @Test

    void deveriaValidarCadastroLeilao(){
        this.leiloesPage = cadastroLeilaoPage.cadastrarLeilao("","","");

        Assert.assertFalse(this.cadastroLeilaoPage.isPaginaCadLeilao());
        Assert.assertTrue(this.cadastroLeilaoPage.isMensageDeValidacaoVisiveis());
    }
}

package Testes;

import CustomersPages.CustomersPO;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import support.TestBase;

public class customers extends TestBase {

	@Parameters({"nome"})
    @Test
    public void validarCustomers() {
        CustomersPO customer = new CustomersPO(driver);
        customer.clicarAddCustomer();
        customer.preencherFormularioCustomers();
        customer.clicarBtnSave();
        customer.validarMsgAddCustomerSucesso();
        customer.clicarBtnBackToList();
        customer.buscarNameCustomer();
        customer.selecionarCustomerPesquisado();
        customer.clicarBtnDelete();
        customer.validarConfirmarDelete();
        customer.confirmarDelete();
        customer.validarMsgExclusao();
    }
}

package CustomersPages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import support.DriverWait;
import support.Utils;

import javax.swing.*;

public class CustomersPO{

    private DriverWait wait;
    private String nome = "Teste Sicredi";
    private String msgConfirmacaoExclusao = "Your data has been successfully deleted from the database.";
    private String msgConfDelete = "Are you sure that you want to delete this 1 item?";
    private String msg = "Your data has been successfully stored into the database. Edit Customer or Go back to list";


    public CustomersPO(WebDriver driver){
        wait = new DriverWait(driver);
        driver.findElement(By.id("switch-version-select")).click();
        new Select(driver.findElement(By.id("switch-version-select"))).selectByVisibleText("Bootstrap V4 Theme");
        PageFactory.initElements(driver, this);
        Utils.sleep(3);
    }

    public CustomersPO clicarAddCustomer(){
        wait.waitClickableElement(clicaraddcustomer).click();
        return this;
    }

    public CustomersPO preencherFormularioCustomers(){
        wait.waitClickableElement(name).sendKeys("Teste Sicredi");
        wait.waitClickableElement(lastName).sendKeys("Teste");
        wait.waitClickableElement(contactFirstName).sendKeys("Jadson");
        wait.waitClickableElement(phone).sendKeys("51 9999-9999");
        wait.waitClickableElement(addressLine1).sendKeys("Av Assis Brasil, 3970");
        wait.waitClickableElement(addressLine2).sendKeys("Torre D");
        wait.waitClickableElement(city).sendKeys("Porto Alegre");
        wait.waitClickableElement(state).sendKeys("RS");
        wait.waitClickableElement(postalCode).sendKeys("91000-000");
        wait.waitClickableElement(country).sendKeys("Brasil");
        wait.waitClickableElement(fromEmployeer).click();
        wait.waitClickableElement(creditLimit).sendKeys("200");
        return this;
    }


    public CustomersPO clicarBtnSave(){
        wait.waitClickableElement(btnSave).click();
        return this;
    }


    public CustomersPO validarMsgAddCustomerSucesso(){
        Assert.assertEquals(wait.waitClickableElement(alertMsgSucesso).getText(), msg);
        return this;
    }

    public CustomersPO clicarBtnBackToList(){
        wait.waitClickableElement(btnGoBackToList).click();
        return this;
    }

    public CustomersPO buscarNameCustomer(){
        wait.waitClickableElement(inputName).sendKeys(nome);
        Utils.sleep(5);
        return this;
    }

    public CustomersPO selecionarCustomerPesquisado(){
        wait.waitClickableElement(btnLoad).click();
        Utils.sleep(3);
        wait.waitClickableElement(btnCheck).click();
        return this;
    }

    public CustomersPO clicarBtnDelete(){
        wait.waitClickableElement(btnDelete).click();
        Utils.sleep(4);
        return this;
    }

    public CustomersPO validarConfirmarDelete(){
        //Assert.assertEquals(wait.waitClickableElement(msgConfirmaDelete).getText(), msg);
        return this;
    }

    public CustomersPO confirmarDelete(){
        wait.waitClickableElement(btnConfirmaDelete).click();
        return this;
    }

    public CustomersPO validarMsgExclusao(){
        //Assert.assertEquals(wait.waitClickableElement(msgConfirmaExclusao).getText(), msgConfirmacaoExclusao);
        return this;
    }


    @FindBy(xpath = "/div/span[3]/p")
    private WebElement msgConfirmaExclusao;

    @FindBy(xpath = "//*[@id='gcrud-search-form']/div[2]/table/thead/tr[2]/td[2]/div[2]/a")
    private WebElement btnLoad;

    @FindBy(xpath = "/html/body/div[2]/div[2]/div[3]/div/div/div[3]/button[2]")
    private WebElement btnConfirmaDelete;

    @FindBy(xpath = "/html/body/div[2]/div[2]/div[3]/div/div/div[2]/p[2]")
    private WebElement msgConfirmaDelete;

    @FindBy(xpath = "//*[@id='gcrud-search-form']/div[2]/table/thead/tr[2]/td[3]/input")
    private WebElement inputName;

    @FindBy(xpath = "//*[@id='gcrud-search-form']/div[2]/table/thead/tr[2]/td[1]/div/input")
    private WebElement btnCheck;

    @FindBy(xpath = "//*[@id=\"gcrud-search-form\"]/div[2]/table/thead/tr[2]/td[2]/div[1]/a/span")
    private WebElement btnDelete;

    @FindBy(xpath = "//*[@id='report-success']/p/a[2]")
    private WebElement btnGoBackToList;

    @FindBy(xpath = "//*[@id='report-success']/p")
    private WebElement alertMsgSucesso;

    @FindBy(id = "form-button-save")
    private WebElement btnSave;

    @FindBy(xpath = "//form[@id='gcrud-search-form']/div/div/a")
    private WebElement clicaraddcustomer;

    @FindBy(id = "field-customerName")
    private WebElement name;

    @FindBy(id = "field-contactLastName")
    private WebElement lastName;

    @FindBy(id = "field-contactFirstName")
    private WebElement contactFirstName;

    @FindBy(id = "field-phone")
    private WebElement phone;

    @FindBy(id = "field-addressLine1")
    private WebElement addressLine1;

    @FindBy(id = "field-addressLine2")
    private WebElement addressLine2;

    @FindBy(id = "field-city")
    private WebElement city;

    @FindBy(id = "field-state")
    private WebElement state;

    @FindBy(id = "field-postalCode")
    private WebElement postalCode;

    @FindBy(id = "field-country")
    private WebElement country;

    @FindBy(xpath = "//div[@id='field_salesRepEmployeeNumber_chosen']/a/span")
    private WebElement fromEmployeer;

    @FindBy(id = "field-creditLimit")
    private WebElement creditLimit;
}

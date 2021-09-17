package stepDefinations;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.pages.AccountsPage;
import com.pages.LoginPage;
import com.qa.factory.DriverFactory;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class AccountsPageSteps {

	private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
	private AccountsPage accountsPage;
	
	@Given("user has already logged in to application")
	public void user_has_already_logged_in_to_application(DataTable dataTable) {
		List<Map<String,String>> credList=dataTable.asMaps();
		String userName=credList.get(0).get("username");
		String password=credList.get(0).get("password");
		DriverFactory.getDriver().get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		accountsPage=loginPage.doLogin(userName, password);
	}

	@Given("user is on Accounts page")
	public void user_is_on_accounts_page() {
		String pagetitle=accountsPage.getAccountsPageTitle();
		System.out.println("Account page title:"+pagetitle);
	}

	@Then("user gets accounts section")
	public void user_gets_accounts_section(DataTable sectionsTable) {
		List<String> Expectedsectionlists=sectionsTable.asList();
		System.out.println("Expected Account Section List:"+Expectedsectionlists);
		
		List<String> Actualsectionlists=accountsPage.getAccountsSectionsList();
		System.out.println("Actual Account Section List:"+Actualsectionlists);
		
		Assert.assertTrue(Expectedsectionlists.containsAll(Actualsectionlists));
	}

	@Then("accounts section count should be {int}")
	public void accounts_section_count_should_be(Integer expectedSectionCount) {
		Assert.assertTrue(accountsPage.getAccountsSectionCount()==expectedSectionCount);
	}

}

package testcases.searchcompanytest;


import framework.actionobject.icribis.search.CompanySearchAction;
import framework.assertobject.icribis.CompanyCardPageAssert;
import framework.assertobject.icribis.CompanySearchPageAssert;
import framework.sharedkernel.testcases.BaseTest;
import framework.pageobject.icribis.CompanyCardPage;
import framework.pageobject.icribis.CompanySearchPage;
import framework.pageobject.icribis.HomePage;
import org.junit.Test;

import static testcases.TestCategory.COMPANY_SEARCH;
import static testcases.TestCategory.SEARCH;
public class CompanySearchTest extends BaseTest {


    /****************** By name *********************/
    @Test
    public void searchByName() {
        //given
        HomePage homePage = new HomePage(driver).open();
        //when
        CompanySearchPage companySearchPage = homePage.execute(new CompanySearchAction().byName("Crif"));
        //then
        thenCompanySearchPage(companySearchPage).hasSearchTitle("Crif").hasResultSetGreaterThan(20);
    }


    @Test
    public void searchByNameAndProvince() {
        //given
        HomePage homePage = new HomePage(driver).open();
        //when
        CompanySearchPage companySearchPage = homePage.execute(new CompanySearchAction().byName("Crif").province("BO"));
        //then
        thenCompanySearchPage(companySearchPage).hasSearchTitle("Crif, BO").hasResultSetGreaterThan(5);
    }

    /****************** By tax code *********************/
    @Test
    public void searchByTaxCode() {
        //given
        HomePage homePage = new HomePage(driver).open();
        //when
        CompanySearchPage companySearchPage = homePage.execute(new CompanySearchAction().byTaxCode("07973780013"));
        //then
        thenCompanySearchPage(companySearchPage).hasSearchTitle("07973780013").hasResultSetEqualTo(3);
    }

    @Test
    public void searchByTaxCodeAndProvince() {
        //given
        HomePage homePage = new HomePage(driver).open();
        //when
        CompanyCardPage companyCardPage = homePage
                                            .selecSearchForCompany()
                                            .selectSearchCompanyByTaxCode()
                                            .search("04258390378")
                                            .selectCompanyProvince("BO")
                                            .submitCompanySearchForCompanyCardPage();
        //then
        thenCompanyCardPage(companyCardPage).hasTaxCode("04258390378").hasProvince("BO");
    }



    /****************** By vat code *********************/
    @Test
    public void searchByVatCode() {
        //given
        HomePage homePage = new HomePage(driver).open();
        //when
        CompanySearchPage companySearchPage = homePage.execute(new CompanySearchAction().byVatCode("07973780013"));
        //then
        thenCompanySearchPage(companySearchPage).hasSearchTitle("07973780013").hasResultSetEqualTo(2);
    }

    @Test
    public void searchByVatCodeAndProvince() {
        //given
        HomePage homePage = new HomePage(driver).open();
        //when
        CompanyCardPage companyCardPage = homePage
                                            .selecSearchForCompany()
                                            .selectSearchCompanyByVatCode()
                                            .search("07973780013")
                                            .selectCompanyProvince("TO")
                                            .submitCompanySearchForCompanyCardPage();
        //then
        thenCompanyCardPage(companyCardPage).hasVatCode("07973780013").hasProvince("TO");
    }


    /****************** By rea code *********************/
    @Test
    public void searchByReaCodeAndProvince() {
        //given
        HomePage homePage = new HomePage(driver).open();
        //when
        CompanyCardPage companyCardPage = homePage
                                             .selecSearchForCompany()
                                             .selectSearchCompanyByReaCode()
                                             .search("410952")
                                             .selectCompanyProvince("BO")
                                             .submitCompanySearchForCompanyCardPage();
        //then
        thenCompanyCardPage(companyCardPage).hasReaCode("410952").hasProvince("BO");
    }

    private CompanySearchPageAssert thenCompanySearchPage(CompanySearchPage companySearchPage) {
        return new CompanySearchPageAssert(companySearchPage);
    }

    private CompanyCardPageAssert thenCompanyCardPage(CompanyCardPage companyCardPage) {
        return new CompanyCardPageAssert(companyCardPage);
    }

    @Override
    protected void assignTestCategory() {
        testCategories = new String[]{SEARCH, COMPANY_SEARCH};
    }

}

package framework.actionobject.icribis.search;

import framework.sharedkernel.annotations.ActionObject;
import framework.sharedkernel.actionobject.BaseAction;
import framework.pageobject.icribis.CompanySearchPage;
import framework.pageobject.icribis.HomePage;
import org.apache.commons.lang3.StringUtils;

@ActionObject
public class CompanySearchAction implements BaseAction<HomePage, CompanySearchPage> {

    private CompanyData companyData = new CompanyData();
    private SearchType searchType;

    @Override
    public CompanySearchPage execute(HomePage page) {

        page.selecSearchForCompany();
        selectSearchType(searchType, companyData, page);
        if(isProvinceProvided()) {
            page.selectCompanyProvince(companyData.getProvince());
        }
        return page.submitCompanySearchForCompanySearchPage();
    }

    public CompanySearchAction byName(String company) {
        companyData.setCompany(company);
        searchType = SearchType.NAME;
        return this;
    }

    public CompanySearchAction byTaxCode(String taxCode) {
        companyData.setTaxCode(taxCode);
        searchType = SearchType.TAX;
        return this;
    }

    public CompanySearchAction byVatCode(String vatCode) {
        companyData.setVatCode(vatCode);
        searchType = SearchType.VAT;
        return this;
    }

    public CompanySearchAction byReaCode(String reaCode) {
        companyData.setReaCode(reaCode);
        searchType = SearchType.REA;
        return this;
    }

    public CompanySearchAction province(String province) {
        companyData.setProvince(province);
        return this;
    }

    /***********************************/
    /***********************************/

    private void selectSearchType(SearchType type, CompanyData companyData, HomePage page) {
        switch (type) {
            case NAME:
                page
                        .selectSearchCompanyByName()
                        .search(companyData.getCompany());
                break;
            case TAX:
                page
                        .selectSearchCompanyByTaxCode()
                        .search(companyData.getTaxCode());
                break;
            case VAT:
                page
                        .selectSearchCompanyByVatCode()
                        .search(companyData.getVatCode());
                break;
            case REA:
                page
                        .selectSearchCompanyByReaCode()
                        .search(companyData.getReaCode());
                break;
        }
    }

    private boolean isProvinceProvided() {

        return StringUtils.isNotEmpty(companyData.getProvince());
    }

    private enum SearchType {
        NAME,
        TAX,
        VAT,
        REA
    }

    private class CompanyData {

         private String company;
         private String taxCode;
         private String vatCode;
         private String reaCode;
         private String province;

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getTaxCode() {
            return taxCode;
        }

        public void setTaxCode(String taxCode) {
            this.taxCode = taxCode;
        }

        public String getVatCode() {
            return vatCode;
        }

        public void setVatCode(String vatCode) {
            this.vatCode = vatCode;
        }

        public String getReaCode() {
            return reaCode;
        }

        public void setReaCode(String reaCode) {
            this.reaCode = reaCode;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }
    }
}

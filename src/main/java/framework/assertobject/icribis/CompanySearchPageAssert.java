package framework.assertobject.icribis;

import framework.sharedkernel.annotations.AssertObject;
import framework.pageobject.icribis.CompanySearchPage;

import static org.assertj.core.api.Assertions.assertThat;


@AssertObject
public class CompanySearchPageAssert {

    private CompanySearchPage companySearchPage;

    public CompanySearchPageAssert(CompanySearchPage companySearchPage) {
        this.companySearchPage = companySearchPage;
    }

    public CompanySearchPageAssert hasSearchTitle(String searchTitle) {
        assertThat(companySearchPage.searchTitle()).isEqualToIgnoringCase(searchTitle);
        return this;
    }

    public CompanySearchPageAssert hasResultSetGreaterThan(int size) {
        assertThat(companySearchPage.resultSetSize()).isGreaterThan(size);
        return this;
    }

    public CompanySearchPageAssert hasResultSetEqualTo(int size) {
        assertThat(companySearchPage.resultSetSize()).isEqualTo(size);
        return this;
    }
}

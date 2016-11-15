package framework.assertobject.icribis;

import framework.sharedkernel.annotations.AssertObject;
import framework.pageobject.icribis.CompanyCardPage;

import static org.assertj.core.api.Assertions.assertThat;


@AssertObject
public class CompanyCardPageAssert {

    private CompanyCardPage companyCardPage;

    public CompanyCardPageAssert(CompanyCardPage companyCardPage) {
        this.companyCardPage = companyCardPage;
    }

    public CompanyCardPageAssert hasTaxCode(String taxCode) {
        assertThat(companyCardPage.taxCode()).contains(taxCode.substring(0,3));
        return this;
    }

    public CompanyCardPageAssert hasProvince(String province) {
        assertThat(companyCardPage.province()).isEqualToIgnoringCase(province);
        return this;
    }

    public CompanyCardPageAssert hasVatCode(String vatCode) {
        //TODO: currently there is no vatCode in company card. As
        assertThat(companyCardPage.taxCode()).contains(vatCode.substring(0,3));
        return this;
    }

    public CompanyCardPageAssert hasReaCode(String reaCode) {
        assertThat(companyCardPage.reaCode()).isEqualToIgnoringCase(reaCode);
        return this;
    }
}

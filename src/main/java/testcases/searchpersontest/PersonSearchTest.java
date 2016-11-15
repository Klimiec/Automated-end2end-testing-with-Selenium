package testcases.searchpersontest;

import framework.sharedkernel.testcases.BaseTest;
import static testcases.TestCategory.*;

public class PersonSearchTest extends BaseTest {

	@Override
	protected void assignTestCategory() {
		testCategories = new String[] {SEARCH, PERSON_SEARCH};
	}
}

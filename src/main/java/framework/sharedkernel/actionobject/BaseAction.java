package framework.sharedkernel.actionobject;

import framework.sharedkernel.pageobject.BasePageObject;

/**
 * @param <EntryPage> Entry page
 * @param <OutputPage> Exit page
 */
public interface BaseAction<EntryPage extends BasePageObject, OutputPage extends BasePageObject> {
    OutputPage execute(EntryPage page);
}

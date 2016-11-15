package framework.actionobject.backoffice;


import common.users.User;
import framework.sharedkernel.actionobject.BaseAction;
import framework.sharedkernel.annotations.ActionObject;
import framework.pageobject.backoffice.OpenCmsAdminPage;
import framework.pageobject.backoffice.UsersSearchPage;

@ActionObject
public class UserSearchAction implements BaseAction<OpenCmsAdminPage, UsersSearchPage> {

    private User user;

    public UserSearchAction(User user) {
        this.user = user;
    }

    @Override
    public UsersSearchPage execute(OpenCmsAdminPage openCmsAdminPage) {

         return openCmsAdminPage.navigateToBackOffice()
                                .clickUsersSearchLink()
                                .findUser(user);
    }
}


package common.configuration;

import framework.sharedkernel.annotations.ValueObject;
import org.openqa.selenium.Dimension;

@ValueObject
public class BrowserSize {

   final private  Dimension dimension;
    final private boolean maximised;

    public BrowserSize(String browserSize) {

        if(browserSize.equals("maximised")) {
            maximised = true;
            dimension = null;
        } else {
            maximised = false;
            dimension = new Dimension(Integer.valueOf(browserSize.split("x")[0]),
                    Integer.valueOf(browserSize.split("x")[1]));
        }
    }

    public boolean isMaximized() {
        return maximised;
    }

    public Dimension dimension() {
        return dimension;
    }
}

package main.jms.common;

import java.net.URL;

/**
 * Created by sbt-eshtokin-ml on 27.03.2017.
 */
public interface ValidateTools {
    public void validateByURL(URL xsd, byte[] xml);
}

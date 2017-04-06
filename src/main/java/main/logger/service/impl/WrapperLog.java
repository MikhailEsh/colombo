package main.logger.service.impl;

/**
 * Created by sbt-eshtokin-ml on 06.04.2017.
 */
public class WrapperLog {

    private Object content;
    private Class savedClass;

    public WrapperLog(Object content, Class savedClass) {
        this.content = content;
        this.savedClass = savedClass;
    }

    public Object getContent() {
        return content;
    }

    public Class getSavedClass() {
        return savedClass;
    }
}

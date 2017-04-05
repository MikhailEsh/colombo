package main.jms.receiver;

import main.jms.common.MessageWrapper;

/**
 * Created by sbt-eshtokin-ml on 31.03.2017.
 */
public interface MessageProcessor{

    public void execMessage(MessageWrapper messageWrapper);
}

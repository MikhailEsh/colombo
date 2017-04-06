package main.jms.common;

import main.logger.service.SystemLog;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ConcurrentMap;

import static com.google.common.collect.Maps.newConcurrentMap;
import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;

/**
 * Created by sbt-eshtokin-ml on 27.03.2017.
 */

@Component
@Scope("singleton")
public class ValidateToolsImpl implements ValidateTools {

    private ConcurrentMap<URL, Schema> schemas;

    static public SchemaFactory schemaFactory = null;



    public ValidateToolsImpl()
    {
        this.schemaFactory = createSchemaFactory();
        schemas  = newConcurrentMap();
    }

    public void validateByURL(URL xsd, byte[] xml)
    {
        Schema schema = createSchema(xsd);
        Validator validator = createValidator(schema);
        validate(validator, xml);
    }


    private Schema createSchema(URL schemaResource){
        synchronized (this){
            if (schemas.get(schemaResource) == null){
                Schema schema = null;
                try{
                    schema = schemaFactory.newSchema(schemaResource);
                } catch (SAXException e) {
                    SystemLog.SaveErrorLog(this.getClass(), e);
                }
                schemas.put(schemaResource, schema);
            }
        }
        return schemas.get(schemaResource);
    }



    private SchemaFactory createSchemaFactory(){
        SchemaFactory schemaFactory = null;
        try {
            schemaFactory = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI, "org.apache.xerces.jaxp.validation.XMLSchemaFactory", null);
        } catch (Exception e) {
            SystemLog.SaveErrorLog(this.getClass(), e);
        }
        return schemaFactory;
    }

    private Validator createValidator(Schema schema){
        javax.xml.validation.Validator validator = schema.newValidator();
        return validator;
    }

    private void validate(Validator jaxpValidator, byte[] xml){
        StreamSource streamSource = new StreamSource(new ByteArrayInputStream(xml));
        try {
            jaxpValidator.validate(streamSource);
        } catch (SAXException e) {
            SystemLog.SaveErrorLog(this.getClass(), e);
        } catch (IOException e) {
            SystemLog.SaveErrorLog(this.getClass(), e);
        }
    }




}

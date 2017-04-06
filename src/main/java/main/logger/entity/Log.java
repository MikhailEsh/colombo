package main.logger.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import javax.xml.bind.DatatypeConverter;

/**
 * Created by sbt-eshtokin-ml on 28.03.2017.
 */

@Entity
@Table(name = "logs")
public class Log implements Serializable {

    public Log() {};

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="EVENT_ID", length = 38)
    private long id;

    @Column(name = "PUT_TIME", nullable = false)
    private Timestamp putTime;

    @Column(name = "RQ_TIME", nullable = false)
    private Timestamp rqTime;

    @Column(name = "RQ_UID", nullable = false, length = 32)
    private String rqUid;

    @Column(name = "STATUS", nullable = false, length = 20)
    private String status;

    @Column(name = "OPERATION_NAME", nullable = false, length = 32)
    private String operationName;

    @Column(name = "ADAPTER", nullable = false, length = 150)
    private String adapter;

    @Column(name = "NAMETHREAD", nullable = false, length = 20)
    private String nameThread = "nameThreadDefault";


    @Column(name = "body", columnDefinition = "CLOB NOT NULL")
    @Lob
    private String body;

    public Timestamp getPutTime() {
        return putTime;
    }

    public Timestamp getRqTime() {
        return rqTime;
    }

    public void setRqTime(String rqTime) {
        Calendar calendar = DatatypeConverter.parseDateTime(rqTime);
        this.rqTime = new Timestamp(calendar.getTimeInMillis());
    }

    public String getRqUid() {
        return rqUid;
    }

    public void setRqUid(String rqUid) {
        this.rqUid = rqUid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setPutTime() {
        Date date = new Date();
        long time = date.getTime();
        this.putTime = new Timestamp(time);
    }

    public String getAdapter() {
        return adapter;
    }

    public void setAdapter(String adapter) {
        this.adapter = adapter;
    }


    public void setNameThread(String nameThread) {
        this.nameThread = nameThread;
    }
}

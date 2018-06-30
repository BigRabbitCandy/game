package cn.bluewhale.core.entity;

import java.io.Serializable;

public class Result implements Serializable {
    private static final long serialVersionUID = 5905715228490291386L;
    private Status status;
    private Object message;
    private Object extend;

    public Result(Status error) {
        this.status = error;
    }

    public Result(Status status, Object message) {
        this.status = status;
        this.message = message;
    }

    public Result(Result.Status status, Object message, Object extend) {
        this.status = status;
        this.message = message;
        this.extend = extend;
    }

    public void addError(Object message) {
        this.message = message;
        this.status = Result.Status.ERROR;
    }

    public void addOK(Object message) {
        this.message = message;
        this.status = Result.Status.OK;
    }

    public Object getMessage() {
        return this.message;
    }

    public Result.Status getStatus() {
        return this.status;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public void setStatus(Result.Status status) {
        this.status = status;
    }

    public Object getExtend() {
        return this.extend;
    }

    public void setExtend(Object extend) {
        this.extend = extend;
    }

    public static enum Status {
        OK,
        ERROR;

        private Status() {
        }
    }
}

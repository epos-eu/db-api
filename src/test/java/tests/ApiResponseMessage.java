package tests;

import org.epos.eposdatamodel.LinkedEntity;

public class ApiResponseMessage {
    public static final int ERROR = 1;
    public static final int WARNING = 2;
    public static final int INFO = 3;
    public static final int OK = 4;
    public static final int TOO_BUSY = 5;

    int code;
    String type;
    String message;
    LinkedEntity entity;

    public ApiResponseMessage() {
    }

    public ApiResponseMessage(int code, String message) {
        this.code = code;
        switch (code) {
            case ERROR:
                setType("error");
                break;
            case WARNING:
                setType("warning");
                break;
            case INFO:
                setType("info");
                break;
            case OK:
                setType("ok");
                break;
            case TOO_BUSY:
                setType("too busy");
                break;
            default:
                setType("unknown");
                break;
        }
        this.message = message;
    }

    public ApiResponseMessage(int code, LinkedEntity entity) {
    	this.code = code;
        switch (code) {
            case ERROR:
                setType("error");
                break;
            case WARNING:
                setType("warning");
                break;
            case INFO:
                setType("info");
                break;
            case OK:
                setType("ok");
                break;
            case TOO_BUSY:
                setType("too busy");
                break;
            default:
                setType("unknown");
                break;
        }
        this.entity = entity;
	}

	public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public LinkedEntity getEntity() {
        return entity;
    }

    public void setEntity(LinkedEntity entity) {
        this.entity = entity;
    }

	@Override
	public String toString() {
		return "ApiResponseMessage [code=" + code + ", type=" + type + ", message=" + message + ", entity=" + entity
				+ "]";
	}
    
}

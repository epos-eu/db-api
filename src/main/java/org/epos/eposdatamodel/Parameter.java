package org.epos.eposdatamodel;

import java.util.Objects;

/**
 * Parameter
 */
public class Parameter {

    /**
     * Format
     **/
    private String encodingFormat;

    /**
     * schema of the request
     **/
    private String conformsTo;

    /**
     * The type of action, can be object or result
     */
    private ActionEnum action;

    public Parameter encodingFormat(String encodingFormat) {
        this.encodingFormat = encodingFormat;
        return this;
    }

    /**
     * Format
     *
     * @return encodingFormat
     **/

    public String getEncodingFormat() {
        return encodingFormat;
    }

    public void setEncodingFormat(String encodingFormat) {
        this.encodingFormat = encodingFormat;
    }

    public Parameter conformsTo(String conformsTo) {
        this.conformsTo = conformsTo;
        return this;
    }

    /**
     * schema of the request
     *
     * @return conformsTo
     **/

    public String getConformsTo() {
        return conformsTo;
    }

    public void setConformsTo(String conformsTo) {
        this.conformsTo = conformsTo;
    }

    public Parameter action(ActionEnum action) {
        this.action = action;
        return this;
    }

    /**
     * The type of action, can be object or result
     *
     * @return action
     **/

    public ActionEnum getAction() {
        return action;
    }

    public void setAction(ActionEnum action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "encodingFormat='" + encodingFormat + '\'' +
                ", conformsTo='" + conformsTo + '\'' +
                ", action=" + action +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameter parameter = (Parameter) o;
        return Objects.equals(getEncodingFormat(), parameter.getEncodingFormat()) && Objects.equals(getConformsTo(), parameter.getConformsTo()) && getAction() == parameter.getAction();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEncodingFormat(), getConformsTo(), getAction());
    }

    /**
     * The type of action, can be object or result
     */
    public enum ActionEnum {
        OBJECT("object"),

        RESULT("result");

        private final String value;

        ActionEnum(String value) {
            this.value = value;
        }

        public static ActionEnum fromValue(String text) {
            for (ActionEnum b : ActionEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
}

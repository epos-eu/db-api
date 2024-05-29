package org.epos.eposdatamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A description of a web service operation.
 */
public class Operation extends EPOSDataModelEntity {

    /**
     * The HTTP method.
     **/
    private String method;

    /**
     * This property is used to specify the output format of the Operation. The possible values are listed here: https://www.iana.org/assignments/media-types/media-types.xhtml
     **/
    private List<String> returns;

    /**
     * The syntax of the template literal is specified by the [RFC6570] URI Template syntax.
     **/
    private String template;

    /**
     * A variable-to-property mapping of the IRI template.
     **/
    private List<Mapping> mapping;

    /**
     * Reverse reference to the related webservice.
     */
    private List<LinkedEntity> webservice;


    public void addMapping(Mapping mapping) {
        if (this.mapping == null) {
            ArrayList<Mapping> tmpList = new ArrayList<>();
            tmpList.add(mapping);
            this.setMapping(tmpList);
        } else {
            this.getMapping().add(mapping);
        }
    }

    public void addReturns(String returns) {
        if (this.getReturns() == null) {
            ArrayList<String> returnsList = new ArrayList<>();
            returnsList.add(returns);
            this.setReturns(returnsList);
        } else {
            this.getReturns().add(returns);
        }
    }


    public Operation method(String method) {
        this.method = method;
        return this;
    }

    /**
     * The HTTP method.
     *
     * @return method
     **/

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Operation returns(List<String> returns) {
        this.returns = returns;
        return this;
    }

    public Operation addReturnsItem(String returnsItem) {
        if (this.returns == null) {
            this.returns = new ArrayList<>();
        }
        this.returns.add(returnsItem);
        return this;
    }

    /**
     * This property is used to specify the output format of the Operation. The possible values are listed here: https://www.iana.org/assignments/media-types/media-types.xhtml
     *
     * @return returns
     **/

    public List<String> getReturns() {
        return returns;
    }

    public void setReturns(List<String> returns) {
        this.returns = returns;
    }

    public Operation template(String template) {
        this.template = template;
        return this;
    }

    /**
     * The syntax of the template literal is specified by the [RFC6570] URI Template syntax.
     *
     * @return template
     **/

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Operation mapping(List<Mapping> mapping) {
        this.mapping = mapping;
        return this;
    }

    public Operation addMappingItem(Mapping mapping) {
        if (this.mapping == null) {
            this.mapping = new ArrayList<>();
        }
        this.mapping.add(mapping);
        return this;
    }

    /**
     * A variable-to-property mapping of the IRI template.
     *
     * @return spatialExtent
     **/
    public List<Mapping> getMapping() {
        return mapping;
    }

    public void setMapping(List<Mapping> mapping) {
        this.mapping = mapping;
    }


    public List<LinkedEntity> getWebservice() {
        return webservice;
    }

    public Operation setWebservice(List<LinkedEntity> webservice) {
        this.webservice = webservice;
        return this;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "method='" + method + '\'' +
                ", returns=" + returns +
                ", template='" + template + '\'' +
                ", mapping=" + mapping +
                ", webservice=" + webservice +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Operation operation = (Operation) o;
        return Objects.equals(getMethod(), operation.getMethod()) && Objects.equals(getReturns(), operation.getReturns()) && Objects.equals(getTemplate(), operation.getTemplate()) && Objects.equals(getMapping(), operation.getMapping()) && Objects.equals(getWebservice(), operation.getWebservice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMethod(), getReturns(), getTemplate(), getMapping(), getWebservice());
    }
}

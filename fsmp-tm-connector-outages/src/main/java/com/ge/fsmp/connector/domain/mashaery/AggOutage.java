package com.ge.fsmp.connector.domain.mashaery;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Gagandeep Singh on 9/19/16.
 */
public class AggOutage {

    private Long id;

    private List<String> esn;

    private String customerName;

    private Instant projectStartDate;

    private Instant projectCompletionDate;

    private String projectType;

    private String country;

    private String city;

    private List<String> outageTypeList;

    private String status;

    private String description;

    private Map<String, Object> additonalFields;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getEsn() {
        return esn;
    }

    public void setEsn(List<String> esn) {
        this.esn = esn;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Instant getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(Instant projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public Instant getProjectCompletionDate() {
        return projectCompletionDate;
    }

    public void setProjectCompletionDate(Instant projectCompletionDate) {
        this.projectCompletionDate = projectCompletionDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getOutageTypeList() {
        return outageTypeList;
    }

    public void setOutageTypeList(List<String> outageTypeList) {
        this.outageTypeList = outageTypeList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public Map<String, Object> getAdditonalFields() {
        return additonalFields;
    }

    public void setAdditonalFields(Map<String, Object> additonalFields) {
        this.additonalFields = additonalFields;
    }

    public AggOutage(Long id, List<String> esn, String customerName, Instant projectStartDate,
            Instant projectCompletionDate, String projectType, String country, String city, List<String> outageTypeList,
            String status, String description, Map<String, Object> additonalFields) {

           this.id = id;
           this.esn = esn;
           this.customerName = customerName;
           this.projectStartDate = projectStartDate;
           this.projectCompletionDate = projectCompletionDate;
           this.projectType = projectType;
           this.country = country;
           this.city = city;
           this.outageTypeList = outageTypeList;
           this.status = status;
           this.description = description;
           this.additonalFields = additonalFields;
    }

    public AggOutage() {

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AggOutage aggOutage = (AggOutage) o;

        return Objects.equals(id, aggOutage.id) && Objects.equals(esn, aggOutage.esn)
                && Objects.equals(customerName, aggOutage.customerName)
                && Objects.equals(projectStartDate, aggOutage.projectStartDate)
                && Objects.equals(projectCompletionDate, aggOutage.projectCompletionDate)
                && Objects.equals(projectType, aggOutage.projectType) && Objects.equals(country, aggOutage.country)
                && Objects.equals(city, aggOutage.city) && Objects.equals(outageTypeList, aggOutage.outageTypeList)
                && Objects.equals(status, aggOutage.status) && Objects.equals(description, aggOutage.description)
                && Objects.equals(additonalFields, aggOutage.additonalFields);

    }

    @Override
    public int hashCode() {

        return Objects.hash(id, esn, customerName, projectStartDate, projectCompletionDate, projectType, country, city,
                            outageTypeList, status, description, additonalFields);
    }

}

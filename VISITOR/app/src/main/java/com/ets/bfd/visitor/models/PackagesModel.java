package com.ets.bfd.visitor.models;

import java.io.Serializable;

public class PackagesModel implements Serializable {

    private String id;
    private String operator_id;
    private String title_en;
    private String title_bn;
    private String staying_type_id;
    private String duration_day;
    private String duration_night;
    private String price_start_from;
    private String price_up_to;
    private String description_en;
    private String description_bn;
    private String package_image;
    private String status;
    private String remarks;
    private String created_at;
    private String updated_at;
    private String organization_name_en;
    private String organization_name_bn;
    private String staying_type_name_en;
    private String staying_type_name_bn;

    private String code;
    private String message;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(String operator_id) {
        this.operator_id = operator_id;
    }

    public String getTitle_en() {
        return title_en;
    }

    public void setTitle_en(String title_en) {
        this.title_en = title_en;
    }

    public String getTitle_bn() {
        return title_bn;
    }

    public void setTitle_bn(String title_bn) {
        this.title_bn = title_bn;
    }

    public String getStaying_type_id() {
        return staying_type_id;
    }

    public void setStaying_type_id(String staying_type_id) {
        this.staying_type_id = staying_type_id;
    }

    public String getDuration_day() {
        return duration_day;
    }

    public void setDuration_day(String duration_day) {
        this.duration_day = duration_day;
    }

    public String getDuration_night() {
        return duration_night;
    }

    public void setDuration_night(String duration_night) {
        this.duration_night = duration_night;
    }

    public String getPrice_start_from() {
        return price_start_from;
    }

    public void setPrice_start_from(String price_start_from) {
        this.price_start_from = price_start_from;
    }

    public String getPrice_up_to() {
        return price_up_to;
    }

    public void setPrice_up_to(String price_up_to) {
        this.price_up_to = price_up_to;
    }

    public String getDescription_en() {
        return description_en;
    }

    public void setDescription_en(String description_en) {
        this.description_en = description_en;
    }

    public String getDescription_bn() {
        return description_bn;
    }

    public void setDescription_bn(String description_bn) {
        this.description_bn = description_bn;
    }

    public String getPackage_image() {
        return package_image;
    }

    public void setPackage_image(String package_image) {
        this.package_image = package_image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getOrganization_name_en() {
        return organization_name_en;
    }

    public void setOrganization_name_en(String organization_name_en) {
        this.organization_name_en = organization_name_en;
    }

    public String getOrganization_name_bn() {
        return organization_name_bn;
    }

    public void setOrganization_name_bn(String organization_name_bn) {
        this.organization_name_bn = organization_name_bn;
    }

    public String getStaying_type_name_en() {
        return staying_type_name_en;
    }

    public void setStaying_type_name_en(String staying_type_name_en) {
        this.staying_type_name_en = staying_type_name_en;
    }

    public String getStaying_type_name_bn() {
        return staying_type_name_bn;
    }

    public void setStaying_type_name_bn(String staying_type_name_bn) {
        this.staying_type_name_bn = staying_type_name_bn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

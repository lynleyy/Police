package com.rzice.lynley.police.bean;

/**
 * 作者： XMZ on 2017/3/14 13:31.
 * 邮箱：Lynley1207@163.com
 */

public class LoginInfo {
    /**
     * resultBean : {"statusCode":"200","message":"登录成功1!","resultMap":null}
     */

    private ResultBeanBean resultBean;

    public ResultBeanBean getResultBean() {
        return resultBean;
    }

    public void setResultBean(ResultBeanBean resultBean) {
        this.resultBean = resultBean;
    }

    public static class ResultBeanBean {
        /**
         * statusCode : 200
         * message : 登录成功1!
         * resultMap : null
         */

        private String statusCode;
        private String message;
        private Object resultMap;

        public String getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(String statusCode) {
            this.statusCode = statusCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Object getResultMap() {
            return resultMap;
        }

        public void setResultMap(Object resultMap) {
            this.resultMap = resultMap;
        }
    }
}

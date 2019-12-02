package com.security.complete.dto;

public class ErrorResponse {
    private String responseCode;
    private String reason;

    /**
     *
     */
    public ErrorResponse() {
        super();
    }

    /**
     * @param responseCode
     * @param reason
     */
    public ErrorResponse(String responseCode, String reason) {
        super();
        this.responseCode = responseCode;
        this.reason = reason;
    }

    /**
     * @return the responseCode
     */
    public String getResponseCode() {
        return responseCode;
    }

    /**
     * @param responseCode
     *            the responseCode to set
     */
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason
     *            the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }
}

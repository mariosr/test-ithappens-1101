package br.ithappens.auxiliary;

public enum ResponseStatus {
    SUCCESS("true"),
    FAILED("false");
    private final String status;

    private ResponseStatus(String status) {
       this.status = status;
    }

    public String getStatus() {
       return status;
    }
}
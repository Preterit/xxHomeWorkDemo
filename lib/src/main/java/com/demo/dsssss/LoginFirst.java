package com.demo.dsssss;//package com.dahuatch.Login;

public class LoginFirst {

    private String userName;
    private String clientType;
    private String ipAddress;


    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getClientType() {
        return clientType;
    }
    public void setClientType(String clientType) {
        this.clientType = clientType;
    }
    public String getIpAddress() {
        return ipAddress;
    }
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    @Override
    public String toString() {

        return "[userName=" + userName + ",clientType=" + clientType + ",ipAddress=" + ipAddress + "]";
    }
}
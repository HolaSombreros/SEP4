package com.dai.shared;

public class DownLink {
    private String cmd;
    private String EUI;
    private int port;
    private String data;

    public DownLink(String cmd, String EUI, int port, String data) {
        this.cmd = cmd;
        this.EUI = EUI;
        this.port = port;
        this.data = data;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getEUI() {
        return EUI;
    }

    public void setEUI(String EUI) {
        this.EUI = EUI;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

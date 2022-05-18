package com.dai.shared;

public class SocketData {
    private String cmd;
    private String EUI;
    private String ts;
    private String data;

    public SocketData(String cmd, String EUI, String ts, String data) {
        this.cmd = cmd;
        this.EUI = EUI;
        this.ts = ts;
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

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Cmd: " + cmd + ", EUI: " + EUI + ", ts: " + ts + ", data: " + data;
    }
}

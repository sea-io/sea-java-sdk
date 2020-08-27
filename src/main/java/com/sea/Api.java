package com.sea;

public class Api {

    public static void main(String[] args) {

        try {
            SeaSdk SeaSdk = getSeaSdk();
            if (true) {
                System.out.println(SeaSdk.getConnect().getBalance("SEA1wzvdw9Yipk7E9MuLY4GGX4Ym9tHeDe"));
                System.out.println(SeaSdk.getConnect().getNodeSyncStatus());
                System.exit(0);
            }
        }catch (Exception ex){
        }
    }
    public static SeaSdk getSeaSdk() throws Exception {

        String ip = "http://polaris1.Sea.io";
        String restUrl = ip + ":" + "20334";
        String rpcUrl = ip + ":" + "20336";
        String wsUrl = ip + ":" + "20335";

        SeaSdk wm = SeaSdk.getInstance();
        wm.setRpc(rpcUrl);
        wm.setRestful(restUrl);
        wm.setDefaultConnect(wm.getRpc());
        wm.openWalletFile("wallet2.dat");

        return wm;
    }
}
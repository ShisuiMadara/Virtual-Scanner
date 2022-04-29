package scanner;

import com.google.gson.*;
public class ScannerMessage {
    private long BoxId_i64;
    private int ScannerNum_i32;
    private String BarCode_str;

    public ScannerMessage(long a, int b, String c){
        BoxId_i64 = a;
        ScannerNum_i32 = b;
        BarCode_str = c;
    }

    public ScannerMessage(String str){
        Gson conv = new Gson();
        JsonArray j = conv.fromJson(str, JsonArray.class);
        BoxId_i64 = j.get(4).getAsJsonObject().get("3").getAsJsonObject().get("rec").getAsJsonObject().get("2").getAsJsonObject().get("i64").getAsLong();
        ScannerNum_i32 = j.get(4).getAsJsonObject().get("3").getAsJsonObject().get("rec").getAsJsonObject().get("1").getAsJsonObject().get("i32").getAsInt();
        BarCode_str = j.get(4).getAsJsonObject().get("3").getAsJsonObject().get("rec").getAsJsonObject().get("3").getAsJsonObject().get("str").getAsString();
    }

    public String CustomMessage(){
        Gson conv = new Gson();
        JsonArray jarr = new JsonArray();
        jarr.add(1);
        jarr.add("EmbeddedRoutingWrapperMQ");
        jarr.add(4);
        jarr.add(0);
        JsonObject oppp = new JsonObject();
        JsonObject opp = new JsonObject();
        JsonObject op = new JsonObject();
        JsonObject o1 = new JsonObject();
        JsonObject o2 = new JsonObject();
        JsonObject o3 = new JsonObject();
        o1.addProperty("i32", ScannerNum_i32);
        o2.addProperty("i64", BoxId_i64);
        o3.addProperty("str", BarCode_str);
        op.add("1", o1);
        op.add("2", o2);
        op.add("3", o3);
        opp.add("rec", op);
        oppp.add("3", opp);
        jarr.add(oppp);
        return jarr.toString();
    }

    public long BoxID(){
        return BoxId_i64;
    }

    public int ScannerNum(){
        return ScannerNum_i32;
    }

    public String BarCode(){
        return BarCode_str;
    }
}

package scanner;

public class ScannerMessage {
    private long BoxId_i64;
    private int ScannerNum_i32;
    private String BarCode_str;

    public ScannerMessage(long a, int b, String c){
        BoxId_i64 = a;
        ScannerNum_i32 = b;
        BarCode_str = c;
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

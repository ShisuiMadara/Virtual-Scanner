package scanner;

public class DestinationMessage {
    private long BoxId_i64;
    private int ScannerNum_i32;
    private String BarCode_str;
    private long CcsID_i64;


    public DestinationMessage(long a, int b, String c, long d){
        BoxId_i64 = a;
        ScannerNum_i32 = b;
        BarCode_str = c;
        CcsID_i64 = d;
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

    public long CcsID(){
        
        return CcsID_i64;
    }
}

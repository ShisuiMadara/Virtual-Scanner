package scanner;

public class SoftMessage {
    private long BoxId_i64;
    private int ScannerNum_i32;
    private long CcsID_i64;

    public SoftMessage(long a, int b, long c){
        BoxId_i64 = a;
        ScannerNum_i32 = b;
        CcsID_i64 = c;
    }

    public long BoxID(){
        return BoxId_i64;
    }

    public int ScannerNum(){
        return ScannerNum_i32;
    }

    public long CcsID(){
        return CcsID_i64;
    }
}

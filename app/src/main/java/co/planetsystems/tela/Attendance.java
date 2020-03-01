package co.planetsystems.tela;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "attendance")
public class Attendance {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "in")
    private String clockIn;

    @ColumnInfo(name = "in_bytes")
    private String inBytes;

    @ColumnInfo(name = "in_bitmap")
    private String inBitmap;

    @ColumnInfo(name = "out")
    private String clockOut;

    @ColumnInfo(name = "out_bytes")
    private String outBytes;

    @ColumnInfo(name = "out_bitmap")
    private String outBitmap;


}

package co.planetsystems.tela;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "attendance")
public class Attendance {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    @ColumnInfo(name = "teacher_nin")
    private String teacherNIN;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "in")
    private String clockIn;

    @ColumnInfo(name = "in_prints")
    private byte[] inPrints;

    @ColumnInfo(name = "in_bitmap")
    private byte[] inBitmap;

    @ColumnInfo(name = "out")
    private String clockOut;

    @ColumnInfo(name = "out_prints")
    private byte[] outPrints;

    @ColumnInfo(name = "out_bitmap")
    private byte[] outBitmap;

    public Attendance(@NonNull String teacherNIN, String date, String clockIn, byte[] inPrints, byte[] inBitmap, String clockOut, byte[] outPrints, byte[] outBitmap) {
        this.teacherNIN = teacherNIN;
        this.date = date;
        this.clockIn = clockIn;
        this.inPrints = inPrints;
        this.inBitmap = inBitmap;
        this.clockOut = clockOut;
        this.outPrints = outPrints;
        this.outBitmap = outBitmap;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClockIn() {
        return clockIn;
    }

    public void setClockIn(String clockIn) {
        this.clockIn = clockIn;
    }

    public byte[] getInPrints() {
        return inPrints;
    }

    public void setInPrints(byte[] inPrints) {
        this.inPrints = inPrints;
    }

    public byte[] getInBitmap() {
        return inBitmap;
    }

    public void setInBitmap(byte[] inBitmap) {
        this.inBitmap = inBitmap;
    }

    public String getClockOut() {
        return clockOut;
    }

    public void setClockOut(String clockOut) {
        this.clockOut = clockOut;
    }

    public byte[] getOutPrints() {
        return outPrints;
    }

    public void setOutPrints(byte[] outPrints) {
        this.outPrints = outPrints;
    }

    public byte[] getOutBitmap() {
        return outBitmap;
    }

    public void setOutBitmap(byte[] outBitmap) {
        this.outBitmap = outBitmap;
    }

    public String getTeacherNIN() {
        return teacherNIN;
    }

    public void setTeacherNIN(String teacherNIN) {
        this.teacherNIN = teacherNIN;
    }
}

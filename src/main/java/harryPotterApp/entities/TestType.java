package harryPotterApp.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class TestType {

    @Id
    private Long id;

    private Byte aByte;
    private Character aChar;
    private Short aShort;
    private Integer aInt;
    private Long aLong;
    private Double aDouble;
    private Float aFloat;
    private Boolean aBoolean;
    private String aString;
    private BigDecimal bigDecimal;


    public TestType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getaByte() {
        return aByte;
    }

    public void setaByte(Byte aByte) {
        this.aByte = aByte;
    }

    public Character getaChar() {
        return aChar;
    }

    public void setaChar(Character aChar) {
        this.aChar = aChar;
    }

    public Short getaShort() {
        return aShort;
    }

    public void setaShort(Short aShort) {
        this.aShort = aShort;
    }

    public Integer getaInt() {
        return aInt;
    }

    public void setaInt(Integer aInt) {
        this.aInt = aInt;
    }

    public Long getaLong() {
        return aLong;
    }

    public void setaLong(Long aLong) {
        this.aLong = aLong;
    }

    public Double getaDouble() {
        return aDouble;
    }

    public void setaDouble(Double aDouble) {
        this.aDouble = aDouble;
    }

    public Float getaFloat() {
        return aFloat;
    }

    public void setaFloat(Float aFloat) {
        this.aFloat = aFloat;
    }

    public Boolean getaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public String getaString() {
        return aString;
    }

    public void setaString(String aString) {
        this.aString = aString;
    }

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestType testType = (TestType) o;
        return Objects.equals(id, testType.id) &&
                Objects.equals(aByte, testType.aByte) &&
                Objects.equals(aChar, testType.aChar) &&
                Objects.equals(aShort, testType.aShort) &&
                Objects.equals(aInt, testType.aInt) &&
                Objects.equals(aLong, testType.aLong) &&
                Objects.equals(aDouble, testType.aDouble) &&
                Objects.equals(aFloat, testType.aFloat) &&
                Objects.equals(aBoolean, testType.aBoolean) &&
                Objects.equals(aString, testType.aString) &&
                Objects.equals(bigDecimal, testType.bigDecimal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, aByte, aChar, aShort, aInt, aLong, aDouble, aFloat, aBoolean, aString, bigDecimal);
    }
}

package ATM.Notes;

public class Notes implements Cloneable {
    private String note;
    private long count;


    public void setNote(String note)
    {
        this.note = note;
    }

    public String getNote()
    {
        return this.note;
    }

    public void setCount(long count)
    {
        this.count = count;
    }

    public long getCount()
    {
        return this.count;
    }

    @Override
    public Notes clone() throws CloneNotSupportedException
    {
        return (Notes) super.clone();
    }
}
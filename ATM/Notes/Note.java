package ATM.Notes;

public abstract class Note implements Cloneable {
    private String note;
    private long count;

    protected Note(String note, long count)
    {
        this.note = note;
        this.count = count;
    }
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
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
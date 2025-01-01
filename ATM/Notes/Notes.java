package ATM.Notes;


public class Notes implements Cloneable {// implementing cloneable interface for using clone without any error
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

//    overriding clone method from protected - public && Object return type - Notes return type as we are cloning the notes onject
    @Override
    public Notes clone() throws CloneNotSupportedException // clone method must be used with try catch block instead we are using like this(similar to try-catch)
    {
        return (Notes) super.clone();
    }
}
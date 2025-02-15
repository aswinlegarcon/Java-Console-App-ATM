package ATM.Notes;

public class Notes<T extends Note>
{
    T[] noteObjects;
    int length = 0;

    public Notes()
    {
        noteObjects = (T[])new Note[4];
    }
    public void add(T obj)
    {
        if(length<noteObjects.length)
        {
            noteObjects[length] = obj;
            length++;
        }
    }

    public T get(int index)
    {
        try{
            return noteObjects[index];
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Error index below 4");
        }
       return null;
    }

    public T[] getNoteObjects() {
        return noteObjects;
    }

    public void setNoteObjects(T[] noteObjects) {
        this.noteObjects = noteObjects;
    }
}

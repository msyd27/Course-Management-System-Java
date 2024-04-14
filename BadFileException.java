import java.io.IOException;

public class BadFileException extends IOException
{
    public BadFileException() {}

    public BadFileException(String message)
    {
        super(message);
    }
}

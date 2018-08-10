a class as a container for the program
logic that defines the behavior of an application.classes are the
building blocks with which all Java applications and applets are built. Everything in a Java
program must be inside a class.

```
Object obj = Calendar.getInstance();
long time = 0;
if(obj instanceof Calendar)
{
time = ((Calendar)obj).getTime();
}
if(obj instanceof Date)
{
time = ((Date)obj).getTime(); // This line will never be reached, obj is not a Date type.
}
```

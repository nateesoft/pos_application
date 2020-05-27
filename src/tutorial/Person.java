package tutorial;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Person implements java.io.Serializable {

    private String firstName;
    private String lastName;
    private int salary;

    public Person(String f, String l, int s) {
        this.firstName = f;
        this.lastName = l;
        this.salary = s;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + salary;
    }
}

class WriteObject{
//    public static void main(String[] args) {
//        try {
//            FileOutputStream fos = new FileOutputStream("C:/Person2.bin");
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            Person p = new Person("Tony", "Ja+", 30000);
//            oos.writeObject(p);
//            oos.close();
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(WriteObject.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(WriteObject.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
}

class WriteObject2{
//    public static void main(String[] args) {
//        try {
//            FileOutputStream fos = new FileOutputStream("C:/Person2.bin");
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject("Natee Sungthongngam");
//            oos.close();
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(WriteObject.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(WriteObject.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
}

class ReadObject{
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("C:/EDIT.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
//            Person p = (Person) ois.readObject();
//            System.out.println(p);
            Object o = ois.readObject();
            System.out.println(o);
            ois.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReadObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

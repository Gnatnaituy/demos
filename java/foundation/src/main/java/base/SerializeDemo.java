package base;


import java.io.*;

public class SerializeDemo implements java.io.Serializable {
    private String name;
    private String address;
    private transient int SSN;
    private int number;

    private void mailCheck() {
        System.out.println("Mail check to " + name + " in " + address);
    }

    private static void serializeObjectDemo() {
        SerializeDemo serializeDemo = new SerializeDemo();
        serializeDemo.name = "Hasaker";
        serializeDemo.address = "Tianjin";
        serializeDemo.number = 1008611;
        serializeDemo.SSN = 1001011;

        try {
            FileOutputStream fileOutputStream =
                    new FileOutputStream("C:\\Users\\Hasaker\\Desktop\\serializeDemo.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(serializeDemo);

            objectOutputStream.close();
            fileOutputStream.close();

            System.out.println("Serialize Object" + serializeDemo.toString() + " Finished!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deserializeObjectDemo() {
        SerializeDemo serializeDemo;

        try {
            FileInputStream fileInputStream =
                    new FileInputStream("C:\\Users\\Hasaker\\Desktop\\serializeDemo.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            serializeDemo = (SerializeDemo) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (ClassNotFoundException e) {
            System.out.println("SerializeDemo class not found!");
            e.printStackTrace();
            return;
        }

        System.out.println("Name: " + serializeDemo.name);
        System.out.println("Address: " + serializeDemo.address);
        System.out.println("Number: " + serializeDemo.number);
        System.out.println("SSN: " + serializeDemo.SSN);
        serializeDemo.mailCheck();
    }

    public static void main(String[] args) {
        serializeObjectDemo();
        deserializeObjectDemo();
    }
}

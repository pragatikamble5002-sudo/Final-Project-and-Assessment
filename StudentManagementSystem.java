import java.util.*;
import java.io.*;

class StudentManagementSystem
{
    static ArrayList<String> records = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    static void load()
    {
        try
        {
            BufferedReader br = new BufferedReader(
                new FileReader("students.txt"));
            String s;
            while((s = br.readLine()) != null)
            {
                records.add(s);
            }
            br.close();
        }
        catch(Exception e)
        {
            // File will be created when records are saved.
        }
    }

    static void save()
    {
        try
        {
            FileWriter fw = new FileWriter("students.txt");
            for(String s : records)
            {
                fw.write(s + "\n");
            }
            fw.close();
        }
        catch(Exception e)
        {
            System.out.println("Error saving records");
        }
    }

    static void addStudent()
    {
        System.out.print("Enter ID: ");
        String id = sc.nextLine();

        if(findStudent(id) != -1)
        {
            System.out.println("ID already exists!");
            return;
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Course: ");
        String course = sc.nextLine();

        System.out.print("Enter Marks: ");
        String marks = sc.nextLine();

        records.add(id + "|" + name + "|" + course + "|" + marks);
        save();

        System.out.println("Student added successfully!");
    }

    static int findStudent(String id)
    {
        for(int i = 0; i < records.size(); i++)
        {
            String a[] = records.get(i).split("\\|", -1);
            if(a[0].equals(id))
                return i;
        }
        return -1;
    }

    static void viewStudents()
    {
        if(records.size() == 0)
        {
            System.out.println("No student records found.");
            return;
        }

        for(String s : records)
        {
            System.out.println(s.replace("|", "  "));
        }
    }

    static void searchStudent()
    {
        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();

        int i = findStudent(id);

        if(i == -1)
            System.out.println("Student not found!");
        else
            System.out.println(records.get(i).replace("|", "  "));
    }

    static void updateStudent()
    {
        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();

        int i = findStudent(id);

        if(i == -1)
        {
            System.out.println("Student not found!");
            return;
        }

        System.out.print("Enter New Name: ");
        String name = sc.nextLine();

        System.out.print("Enter New Course: ");
        String course = sc.nextLine();

        System.out.print("Enter New Marks: ");
        String marks = sc.nextLine();

        records.set(i, id + "|" + name + "|" + course + "|" + marks);
        save();

        System.out.println("Student updated successfully!");
    }

    static void deleteStudent()
    {
        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();

        int i = findStudent(id);

        if(i == -1)
            System.out.println("Student not found!");
        else
        {
            records.remove(i);
            save();
            System.out.println("Student deleted successfully!");
        }
    }

    public static void main(String args[])
    {
        load();

        while(true)
        {
            System.out.println("\n--- STUDENT MANAGEMENT SYSTEM ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");

            System.out.print("Enter Choice: ");
            String choice = sc.nextLine();

            switch(choice)
            {
                case "1": addStudent(); break;
                case "2": viewStudents(); break;
                case "3": searchStudent(); break;
                case "4": updateStudent(); break;
                case "5": deleteStudent(); break;
                case "6":
                    save();
                    System.out.println("Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
import java.io.IOException;
import java.util.Scanner;

class WrongStudentName extends Exception { }
class WrongStudentAge extends Exception { }
class WrongMenu extends Exception{ }

class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        while(true) {
            try {
                int ex = Readmenu();
                switch(ex) {
                    case 1: exercise1(); break;
                    case 2: exercise2(); break;
                    case 3: exercise3(); break;
                    default: return;
                }
            } catch(IOException e) {

            } catch(WrongStudentName e) {
                System.out.println("Błędne imie studenta!");
            } catch(WrongStudentAge e) {
                System.out.println("Błędny wiek studenta!");
            }catch(WrongMenu e) {
                System.out.println("Błąd");
            }
        
        }
    }

    public static int Readmenu() throws WrongMenu {
      int menu1;
        System.out.println("Wciśnij:");
        System.out.println("1 - aby dodać studenta");
        System.out.println("2 - aby wypisać wszystkich studentów");
        System.out.println("3 - aby wyszukać studenta po imieniu");
        System.out.println("0 - aby wyjść z programu");
         try {
    menu1 = scan.nextInt();
    } catch (Exception e) {
      scan.nextLine();
      throw new WrongMenu();
    }
    if (menu1 < 0 || menu1 > 3) {
      throw new WrongMenu();
    }
    return menu1;
    }
      
  

    public static String ReadName() throws WrongStudentName {
        scan.nextLine();
        System.out.println("Podaj imie: ");
        String name = scan.nextLine();
        if(name.contains(" "))
            throw new WrongStudentName();

        return name;
    }
  public static int ReadAge() throws WrongStudentAge {
      System.out.println("Podaj wiek: ");
      int age = scan.nextInt();
       if(age < 1 || age >100)
            throw new WrongStudentAge();

        return age;
    }

  
    public static void exercise1() throws IOException, WrongStudentName, WrongStudentAge {
        var name = ReadName();
        var age = ReadAge();
        System.out.println("Podaj datę urodzenia DD-MM-YYY");
        var date = scan.nextLine();
        (new Service()).addStudent(new Student(name, age, date));
    }

    public static void exercise2() throws IOException {
        var students = (new Service()).getStudents();
        for(Student current : students) {
            System.out.println(current.ToString());
        }
    }

    public static void exercise3() throws IOException {
        scan.nextLine();
        System.out.println("Podaj imie: ");
        var name = scan.nextLine();
        var wanted = (new Service()).findStudentByName(name);
        if(wanted == null)
            System.out.println("Nie znaleziono...");
        else {
            System.out.println("Znaleziono: ");
            System.out.println(wanted.ToString());
        }
    }
}

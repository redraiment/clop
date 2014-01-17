package me.zzp.clop;

public class SwitchTest {
    public static void main(String[] args) {
      for (String e : new String[] {
        "a", "b", "c", "d"
      }) {
        switch (e) {
          case "a":
            System.out.println("a");
            break;
          default:
            System.out.println("default");
          case "b":
            System.out.println("b");
            break;
          case "c":
            System.out.println("c");
            break;
        }
      }
    }
}

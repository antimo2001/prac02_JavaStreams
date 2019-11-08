import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

//// copypaste to https://repl.it/languages

class Role {
  String roleName;
  long roleId;

  public Role() { }

  public Role(String roleName, long roleId) {
    this.roleName = roleName;
    this.roleId = roleId;
  }

  public static List<Role> getRoles(long userId) {
    List<Role> roles = new ArrayList<Role>();
    roles.add(new Role("admin", 1));
    roles.add(new Role("org-admin", 2));
    roles.add(new Role("normal", 3));
    return roles;
  }

  public static boolean isAdmin(List<Role> roles) {
    return Role.hasRole(roles, "admin");
  }

  public static boolean isOrgAdmin(List<Role> roles) {
    return Role.hasRole(roles, "org-admin");
  }

  public static boolean isNormal(List<Role> roles) {
    return Role.hasRole(roles, "normal");
  }

  private static boolean hasRole(List<Role> roles, String name) {
    boolean retVal = false;
    for(Role r : roles) {
      if (name.equals(r.roleName)) {
        retVal = true;
        break;
      }
    }
    return retVal;
  }
}

class Main {
  public static void main(String[] args) {
    Main.prac02();
  }
  // Example of pre-stream thinking with for loops
  static void prac00() {
    List<Role> myroles = Role.getRoles(0);

    Role firstrole = myroles.get(0);
    System.out.println(String.format("Hello world! %d, %s", firstrole.roleId, firstrole.roleName));
    
    boolean isAdmin = Role.isAdmin(myroles);
    System.out.println(String.format("%s", isAdmin));
  }
  // Example of thinking in Streams and functional composition
  static void prac01() {
    List<Role> myroles = Role.getRoles(0);

    List<Role> filtered = myroles.stream()
      .filter(r -> "admin".equals(r.roleName))
      .collect(Collectors.toList());

    boolean isAdmin = filtered.size() > 0;

    System.out.println(String.format("isAdmin? %s", isAdmin));
  }
  // Example of Stream.anyMatch
  static void prac02() {
    List<Role> myroles = Role.getRoles(0);

    boolean isAdmin = myroles.stream()
      .anyMatch(r -> "admin".equals(r.roleName));

    System.out.println(String.format("isAdmin? %s", isAdmin));
  }
}
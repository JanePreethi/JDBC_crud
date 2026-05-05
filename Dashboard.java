package crud;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Dashboard extends Login{
	Scanner sc = new Scanner(System.in);
	public Dashboard() throws SQLException,InputMismatchException  {
		System.out.print("1.Admin Login \n2.Student Login \nEnter Choice: ");
		int c = sc.nextInt();
		System.out.println();
		if(c==1) {
			System.out.print("Enter Admin ID : ");
			int a = sc.nextInt();
			System.out.print("Enter Password : ");
			String p = sc.next();
			if(adminlog(a,p)) {
				System.out.println();
				AdminMenu();
			}
			else {
				System.out.print("Invalid Login!");
			}
		}
		else if(c==2) {
			StuMenu();
		}
		else {
			System.out.print("Invalid Choice");
		}
	}
	public void AdminMenu() throws SQLException,InputMismatchException  {
		while(true) {
			System.out.println("--Admin Dashboard--");
			System.out.print("1.Register New Student \n2.View Student Details"
					+ " \n3.Update \n4.Delete \n0-Exit\nEnter Choice : ");
			int ac= sc.nextInt();
			System.out.println();
			if(ac==1) {
				System.out.println("--Student Registration--");
				System.out.print("Enter Register Number : ");
				int regno = sc.nextInt();
				System.out.print("Enter Name : ");
				String name = sc.next();
				System.out.print("Enter DOB (YYYY-MM-DD): ");
				String dob = sc.next();
				System.out.print("Enter Gender (M/F): ");
				String gen = sc.next();
				System.out.print("Enter Course : ");
				String course = sc.next();
				System.out.print("Enter Fees Amount : ");
				int fees = sc.nextInt();
				int r=add(regno, name, dob, gen, course, fees);
				System.out.println(r>0 ? "Record Added":"Registration Failed!");
				System.out.println();
			}
			else if(ac==2) {
					System.out.println("--Student Details--");
					System.out.print("Enter Register Number : ");
					int n = sc.nextInt();
					view(n);
					System.out.println();
			}
			else if(ac==3) {
				System.out.println("--Update Fees--");
				System.out.print("Enter Register Number : ");
				int regno = sc.nextInt();
				System.out.print("Update Fees Amount : ");
				int fees = sc.nextInt();
				int r = edit(regno, fees);
				System.out.println(r>0 ? "Saved":"Failed to update!");
				System.out.println();
			}
			else if(ac==4) {
				System.out.println("--Delete Student Record--");
				System.out.print("Enter Register Number : ");
				int regno = sc.nextInt();
				int r = remove(regno);
				System.out.println(r>0 ? "Deleted":"Failed to Delete!");
				System.out.println();
			}
			else if(ac==0) {
				System.out.println("App Closed!");
				break;
			}
			else {
				System.out.print("Invalid Choice");
				System.out.println();
			}
		}
	}
	public void StuMenu() throws SQLException,InputMismatchException  {
		while(true) {
			System.out.println("--Student Login--");
			System.out.print("1.Login\n2.Create New Profile \nEnter Your Choice :");
			int stuc=sc.nextInt();	
			System.out.println();
			if(stuc==1) {
				System.out.print("Enter Register Number : ");
				int regno = sc.nextInt();
				System.out.print("Enter Username : ");
				String uname = sc.next();
				System.out.print("Enter Password : ");
				String upass = sc.next();
				System.out.println();
				if(stuLog(regno, uname, upass)) {
					while(true) {
						System.out.println("Welcome "+uname);
						System.out.println("--Student Dashboard--");
						System.out.print("1.View my Details \n2.Change Username"
								+ " \n3.Delete my account \n0.Exit \nEnter your choice : ");
						int stc = sc.nextInt();
						System.out.println();
						if(stc==1) {
							view(regno);
							System.out.println();
						}
						else if(stc==2) {
							System.out.print("Enter Register Number : ");
							int reg = sc.nextInt();
							if(reg==regno) {
								System.out.print("Create New Username : ");
								String euname = sc.next();
								System.out.print("Create New password : ");
								String eupass = sc.next();
								int r = (stuEdit(reg,euname, eupass));
								System.out.println(r>0 ? "Updated":"Failed to update!");
								System.out.println();
								StuMenu();
							}
							else {
								System.out.println("Invalid Register Number!");
							}
						}
						else if(stc==3) {
							System.out.print("Enter Register Number : ");
							int reg = sc.nextInt();
							if(reg==regno) {
								System.out.print("Press Y to confirm delete!");
								char del = sc.next().charAt(0);
								if(del=='y' || del == 'Y') {
									System.out.println(stuDel(reg)>0 ? "Account Deleted":"Failed to delete account!");
									break;
								}
							}
							else {
								System.out.println("Invalid Register Number!");
								System.out.println();
							}
						}
						else if(stc==0) {
							System.out.println("App Closed!");
							System.out.println();
							break;
						}
					}
				}
				else {
					System.out.println("Invalid Login!");
				}
			}
			else if(stuc==2) {
				System.out.print("Enter Register Number : ");
				int regno = sc.nextInt();
				System.out.print("Create Username : ");
				String uname = sc.next();
				System.out.print("Create Password : ");
				String upass = sc.next();
				int r = stuadd(regno, uname, upass);
				System.out.println(r>0? "Profile Created":"Failed to create profile!");
				System.out.println();
			}
			else {
				System.out.print("Invalid Choice \n");
			}
		}
	}
}

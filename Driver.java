import java.io.*;

public class Driver {
	public static void main(String [] args) throws FileNotFoundException, IOException {
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));

		double [] c1 = {6,-2,5};
		int [] e1 = {0,1,3};
		Polynomial p1 = new Polynomial(c1, e1);
		p1.print(p1);

		double [] c2 = {-9, -2};
		int [] e2 = {4, 1};
		Polynomial p2 = new Polynomial(c2,e2);
		p2.print(p2);

		Polynomial s = p1.add(p2);
		s.print(s);
		System.out.println("s(0.1) = " + s.evaluate(0.1));

		double [] c3 = {1,1};
		int [] e3 = {0,1};
		Polynomial p3 = new Polynomial(c3, e3);

		double [] c4 = {1,-1};
		int [] e4 = {0, 1};
		Polynomial p4 = new Polynomial(c4,e4);

		Polynomial a = p3.multiply(p4);
		a.print(a);
		if(a.hasRoot(1))
			System.out.println("1 is a root of a");

		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");

		File f = new File("new_f.txt");
		Polynomial y = new Polynomial(f);
		y.print(y);

		String new_f = "new_f.txt";
		y.saveToFile(new_f);

	}
}

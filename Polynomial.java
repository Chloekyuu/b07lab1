import java.lang.Math;
import java.util.Arrays;
import java.io.*;

public class Polynomial {

	double [] coefficients;
	int [] exponents;

	public Polynomial() {
		this.coefficients = new double[1];
		this.exponents = new int[1];
	}

	public Polynomial(double [] coeff, int [] expo) {
		this.coefficients = coeff;
		this.exponents = expo;
	}

	public Polynomial(File f) throws FileNotFoundException, IOException {
		BufferedReader opened = new BufferedReader(new FileReader(f));
		String line = opened.readLine();

		String [] positive = line.split("\\+", 0);
		String [] terms = new String[100];
		int k = 0;

		for (int i = 0; i < positive.length; i++) {
			String [] negative = positive[i].split("\\-", 0);

			terms[k] = negative[0];
			k ++;

			for (int j = 1; j < negative.length; j ++) {
				terms[k] = "-" + negative[j];
				k ++;
			}
		}

		double [] coeff = new double[k];
		int [] expo = new int[k];
		int m = 0;

		for (int i = 0; i < k; i ++) {
			if (terms[i].contains("x")) {
				String [] co_ex = terms[i].split("x", 0);
				coeff[m] = Double.parseDouble(co_ex[0]);
				expo[m] = Integer.parseInt(co_ex[1]);
			}
			else {
				coeff[m] = Double.parseDouble(terms[i]);
				expo[m] = 0;
			}
			m ++;
		}

		this.exponents = expo;
		this.coefficients = coeff;
	}

	public void saveToFile (String f_name) throws FileNotFoundException {

		String x = "";

		PrintStream new_f = new PrintStream(f_name);

		for (int i = 0; i < this.exponents.length; i ++)
		{
			if (this.coefficients[i] > 0 && i > 0) x += "+";
			x += String.valueOf(this.coefficients[i]) + "x" + String.valueOf(this.exponents[i]);
		}
		new_f.println(x);
	}

	public int getLargest(int[] arr){  
		int temp;
		for (int i = 0; i < arr.length; i++)
        {
            for (int j = i + 1; j < arr.length; j++)
            {
                if (arr[i] > arr[j])
                {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
       return arr[arr.length - 1];
	}

	public int contains(int [] arr, int x) {
		for (int i = 0; i < arr.length; i ++) {
			if (arr[i] == x) return i;
		}
		return -1;
	}

	public Polynomial add(Polynomial x) {
		int length = this.exponents.length;
		int range = Math.max(getLargest(this.exponents), getLargest(x.exponents));

		for (int i = 0; i < x.exponents.length; i ++){
			if (contains(this.exponents, x.exponents[i]) != -1) {
				length ++;
			}
		}

		double [] coeff = new double[length];
		int [] expo = new int[length];
		int index_this;
		int index_x;
		int n = 0;

		for (int i = 0; i < range + 1; i ++)
		{
			index_this = contains(this.exponents, i);
			index_x = contains(x.exponents, i);
			if (index_this != -1 && index_x != -1) {
				expo[n] = i;
				coeff[n] += this.coefficients[index_this];
				coeff[n] += x.coefficients[index_x];
				n ++;
			}
			else if (index_this != -1) {
				expo[n] = i;
				coeff[n] += this.coefficients[index_this];
				n ++;
			}
			else if (index_x != -1) {
				expo[n] = i;
				coeff[n] += x.coefficients[index_x];
				n ++;
			}
		}
		return new Polynomial(coeff, expo);
	}

	public double evaluate(double x) {
		double sum = 0;
		for (int i = 0; i < coefficients.length; i ++)
			sum += coefficients[i] * Math.pow(x, exponents[i]);
		return sum;
	}

	public boolean hasRoot(double x) {
		return evaluate(x) == 0;
	}

	public Polynomial multiply(Polynomial x) {

		double [] tem_coeff = new double[100];
		int [] tem_ex = new int[100];
		int k = 0;

		for (int i = 0; i < x.exponents.length; i ++) {
			for (int j = 0; j < this.exponents.length; j ++){

				double coeff = x.coefficients[i] * this.coefficients[j];
				int expo = x.exponents[i] + this.exponents[j] + 1;
				int index = contains(tem_ex, expo);

				if (index == -1) {
					tem_ex[k] = expo;
					tem_coeff[k] = coeff;
					k ++;
				}
				else {
					tem_coeff[index] += coeff;
				}
			}
		}

		for (int i = 0; tem_ex[i] != 0; i++) {
			if (tem_coeff[i] == 0) k --;
		}

		double [] coeff = new double[k];
		int [] expo = new int[k];
		int m = 0;

		for (int i = 0; tem_ex[i] != 0; i++) {
			if (tem_coeff[i] != 0) {
				coeff[m] = tem_coeff[i];
				expo[m] = tem_ex[i] - 1;
				m ++;
			}
		}

		return new Polynomial(coeff, expo);
	}

	public void print(Polynomial x) {
		for (int i = 0; i < x.exponents.length; i ++) {
			System.out.println(x.coefficients[i] + " * x ^ " + x.exponents[i]);
		}
	}

}
import java.lang.Math;

public class Polynomial {

	double [] coefficients;

	public Polynomial() {
		this.coefficients = new double[1];
	}

	public Polynomial(double [] coeff) {
		this.coefficients = coeff;
	}

	public Polynomial add(Polynomial x) {
		int length = Math.max(this.coefficients.length, x.coefficients.length);
		double [] sum = new double[length];

		for (int i = 0; i < length; i ++)
		{
			if (i < this.coefficients.length) {
				sum[i] += this.coefficients[i];
			}
			if (i < x.coefficients.length) {
				sum[i] += x.coefficients[i];
			}
		}
		return new Polynomial(sum);
	}

	public double evaluate(double x) {
		double sum = 0;
		for (int i = 0; i < coefficients.length; i ++)
			sum += coefficients[i] * Math.pow(x, i);
		return sum;
	}

	public boolean hasRoot(double x) {
		return evaluate(x) == 0;
	}

}
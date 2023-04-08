//姚証傑 D1167005
import java.util.Scanner; // Package for input stream.
import java.util.Random; // Package for random number generator.
public class Polynomial_D1167005{

	private int degree; // Degree.
	private double[] coeff; // Coefficients.
	
	// Default constructor of zero polynomial.
	public Polynomial_D1167005() {
		this.degree =0;
		this.coeff = new double[] {};
	}

	// Constructor of degree n polynomial.
	public Polynomial_D1167005(int n) {
		this.degree = n;
		this.coeff  = new double[n+1];
		Random r = new Random(0);
		for(int i=0; i<n+1; i++) {
			if(r.nextInt(3)==0 || i==n) {
				this.coeff[i]= r.nextDouble()*20-10;
			}else {
				this.coeff[i]=0;
			}
		}
	}

	// Constructor of a polynomial by copy polynomial P.
	public Polynomial_D1167005(Polynomial_D1167005 P) {
		this.degree = P.degree;
		this.coeff = new double[P.degree+1];
	}

	// Get the number of non-zero coefficient terms.
	public int getTermCount() {
		int TC = 0;
		int i;
		for(i=0; i<this.degree+1;i++) {
			if(this.coeff[i]!=0) {
				TC++;
			}
		}return TC;
	}

	// Evaluate polynomial of value a, P(a).
	public double evalPolynomial(double a) {
		double Sum =0;
		for(int i=0;i<this.coeff.length;i++) {
			Sum+= coeff[i]* Math.pow(a, i);
		}
		return Sum;
	}
	
	// Polynomial addition.	
	public Polynomial_D1167005 add(Polynomial_D1167005 P) {
		Polynomial_D1167005 result;
		if(this.degree > P.degree)
			result = new Polynomial_D1167005(this);
		else
			result = new Polynomial_D1167005(P);

		for(int i=0;i <= result.degree;i++) {
			if(i<= Math.min(this.degree, P.degree)) {
				result.coeff[i]= this.coeff[i]+ P.coeff[i];
			}else if(this.degree > P.degree){
				result.coeff[i]=this.coeff[i];
			}else {
				result.coeff[i]= P.coeff[i];
			}
		}
		return result;
	}
	
	// Polynomial subtraction.
	public Polynomial_D1167005 minus(Polynomial_D1167005 P) {
		Polynomial_D1167005 result;
		if(this.degree > P.degree)
			result = new Polynomial_D1167005(this);
		else
			result = new Polynomial_D1167005(P);

		for(int i=0;i <= result.degree;i++) {
			if(i<= Math.min(this.degree, P.degree)) {
				result.coeff[i]= this.coeff[i]- P.coeff[i];
			}else if(this.degree > P.degree){
				result.coeff[i]=this.coeff[i];
			}else {
				result.coeff[i]= -P.coeff[i];
			}
		}
		return result;
	}
	
	
	// Polynomial multiplication.
	public Polynomial_D1167005 time(Polynomial_D1167005 P) {
		Polynomial_D1167005 result= new Polynomial_D1167005(this.degree+P.degree);
		for(int i=0; i<=result.degree;i++) {
			result.coeff[i]=0;
		}
		for(int i=0; i<=this.degree;i++){
			for(int j=0; j<=P.degree;j++) {
				result.coeff[i+j]+= this.coeff[i]*P.coeff[j];
			}
		}
		return result;
	}

	// Print polynomial.
	public void printPolynomial() {
		int counter =0;
		for(int i=this.degree;i>=0;i--) {
			if(this.coeff[i]!=0) {
				if(counter%6==0) {
					if(counter==0) {
						System.out.print("\t");
					}else {
						System.out.print("\n\t");
					}
				}
				if(i==this.degree) {
					System.out.printf("%.2f X^%d",coeff[i],i);
				}else {
					if(this.coeff[i]>0) {
						System.out.print(" + ");
					}else {
						System.out.print(" - ");
					}
					if(i==0) {
						System.out.printf("%.2f ", Math.abs(coeff[i]));
					}else if(i==1) {
						System.out.printf("%.2f X ", Math.abs(coeff[i]));
					}else {
						System.out.printf("%.2f X^%d", Math.abs(coeff[i]),i);
					}
				}
				counter++;
			}
		}
		System.out.println();
	}

	// Main program.
	public static void main(String[] args) {
		Scanner in= new Scanner(System.in);
		int degreeP1, degreeP2;
		double real;
		do {  //input the number
			System.out.print("Enter the degree of polynomial P1(X) (between 0 and 100):");
			degreeP1 = in.nextInt();
			System.out.print("Enter the degree of polynomial P2(X) (between 0 and 100):");
			degreeP2 = in.nextInt();
			System.out.print("Enter a real number a(between -1.0 and 1.0): ");
			real = in.nextDouble();
			
		} while (degreeP1<0 || degreeP1>100 && degreeP2<0 || degreeP2>100 && real<-1.0 || real>1.0);
		
		Polynomial_D1167005 P1 = new Polynomial_D1167005(degreeP1);
		Polynomial_D1167005 P2 = new Polynomial_D1167005(degreeP2);
		
		System.out.printf(">>>> Polynomial P1(X) has %d non-zero coefficient terms.\n",P1.getTermCount());
		P1.printPolynomial();
		System.out.printf(">>>> Polynomial P2(X) has %d non-zero coefficient terms.\n",P2.getTermCount());
		P2.printPolynomial();
		System.out.printf(">>>> Evaluation of polynomial P1(a) = %.6f\n\n",P1.evalPolynomial(real));
		System.out.printf(">>>> Evaluation of polynomial P2(a) = %.6f\n\n",P2.evalPolynomial(real));
		
		Polynomial_D1167005 P3 = P1.add(P2);
		System.out.printf(">>>> Polynomial P3(X) has %d non-zero coefficient terms.\n",P3.getTermCount());
		P3.printPolynomial();
		System.out.printf("\n>>>> Evaluation of polynomial P3(a) = %.6f\n",P3.evalPolynomial(real));
		if (Math.abs(P3.evalPolynomial(real) - P1.evalPolynomial(real) - P2.evalPolynomial(real)) < 0.0001) {
			System.out.println(">>>> Prove. P1(a)+P2(a) == (P1+P2)(a)\n");
		} else {
			System.out.println(">>>> Prove failed. P1(a)+P2(a) != (P1+P2)(a)\n");
		}
		
		Polynomial_D1167005 P4 = P1.minus(P2);
		System.out.printf(">>>> Polynomial P4(X) has %d non-zero coefficient terms.\n",P4.getTermCount());
		P4.printPolynomial();
		System.out.printf("\n>>>> Evaluation of polynomial P4(a) = %.6f\n",P4.evalPolynomial(real));
		if (Math.abs(P4.evalPolynomial(real) - P1.evalPolynomial(real) + P2.evalPolynomial(real)) < 0.0001) {
			System.out.println(">>>> Prove. P1(a)-P2(a) == (P1-P2)(a)\n");
		} else {
			System.out.println(">>>> Prove failed. P1(a)-P2(a) != (P1-P2)(a)\n");
		}
		
		
		Polynomial_D1167005 P5 = P1.time(P2);
		System.out.printf(">>>> Polynomial P5(X) has %d non-zero coefficient terms.\n",P5.getTermCount());
		P5.printPolynomial();
		System.out.printf("\n>>>> Evaluation of polynomial P5(a) = %.6f\n",P5.evalPolynomial(real));
		if (Math.abs(P5.evalPolynomial(real) - P1.evalPolynomial(real)*P2.evalPolynomial(real)) < 0.0001) {
			System.out.println(">>>> Prove. P1(a)*P2(a) == (P1*P2)(a)\n");
		} else {
			System.out.println(">>>> Prove failed. P1(a)*P2(a) != (P1*P2)(a)\n");
		}
	}

}

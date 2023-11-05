package client;
	import java.io.Serializable;

	public class Operation implements Serializable {  
	    int numero1;
	    int numero2;
	    String operateur;
	    double res =0;
	    
	    public double getRes() {
	        return res;
	    }

	    public void setRes(double r) {
	     res = r;
	    }

	    public Operation(int nb1, int nb2, String op) {
	       numero1 = nb1;
	     numero2 = nb2;
	     operateur = op;
	    }

	    // Getters et setters pour accéder et modifier les attributs
	    public int getNb1() {
	        return numero1;
	    }

	    public void setNb1(int nb1) {
	       numero1 = nb1;
	    }

	    public int getNb2() {
	        return numero2;
	    }

	    public void setNb2(int nb2) {
	     numero2 = nb2;
	    }

	    public String getOp() {
	        return operateur;
	    }

	    public void setOp(String op) {
	     operateur = op;
	    }
	}
package com.scotiabankcolpatria.hiring;

public class CreditRiskAssessment {

    public double standardDeviation(int[] paymentDelays) {
    	//Se inicializan las variables que se usaran para el calculo de la dfesviación estandar
    	int lengthPayments = paymentDelays.length;
    	double sum = 0.0;
    	double squareDiff = 0.0;
    	double diff = 0.0;
    	double mean = 0.0;
    	double variance = 0.0;
    	double deviation = 0.0;
    	    	
    	//Se calcula la media de los datos del arreglo
    	for (int i : paymentDelays) {
			sum += i;
		}
    	mean = sum / lengthPayments;
    	
    	//Se calculan los cuadrados de las diferencias entre cada número y la media
    	for (int i : paymentDelays) {
			diff = i - mean;
			squareDiff += Math.pow(diff, 2);
		}
    	//Se calcula la varianza dividiendo la diferencia de cuadrados entre la el número de elementos
    	variance = squareDiff / lengthPayments;
    	
    	//Se calcula la desviación sacando raiz al la variancia
    	deviation = Math.sqrt(variance);
//        return deviation;
    	
        return deviation;
    }

    public int paymentDelayMaxPeakIndex(int[] paymentDelays) {
    	//Se inicializan variables
    	int limit = paymentDelays.length;
        int index = -1;
        int diff = 0;

        int left = 0;
        int right = 0;
        //Validacion para ver si hay un pico inicial
        if (paymentDelays[0] > paymentDelays[1]) {
			index = 0;
		}
        //Validacion de picos en el arreglo sin considerar los extremos
        for (int i = 1; i <= limit - 2; i++) {
            if (paymentDelays[i] > paymentDelays[i - 1] && paymentDelays[i] > paymentDelays[i + 1]) {
                left = paymentDelays[i] - paymentDelays[i - 1];
                right = paymentDelays[i] - paymentDelays[i + 1];
                if ((left + right) > diff) {
                	diff = left + right;
                	index = i;
				}
            }
        }
        //Validacion para ver si hay un pico al final
        if (paymentDelays[paymentDelays.length - 1] > paymentDelays[paymentDelays.length - 2]) {
			index = paymentDelays.length - 1;
		}

        return index;
    }

    public double[] latePaymentProbabilityByPeriod(int[][] paymentDelays) {
        //TODO implement.
        return null;
    }
    
    public static void main(String[] args) {
		CreditRiskAssessment c = new CreditRiskAssessment();
		System.out.println(c.standardDeviation(new int[]{0, 1, 3, 1, 0, 5, 0, 3, 2, 3}));
		System.out.println(c.standardDeviation(new int[]{-5, 1, 8, 7, 2}));
		System.out.println("paymentMax");
		System.out.println("1: " + c.paymentDelayMaxPeakIndex(new int[]{5, 4, 2, 2, 0, 10, 10, 3}) + "\n");
		System.out.println("2: " + c.paymentDelayMaxPeakIndex(new int[]{5, 4, 2, 2, 0, 10, 8, 3})+ "\n");
		System.out.println("3: " + c.paymentDelayMaxPeakIndex(new int[]{0, 1, 1, 1, 0, 0, 0, 0})+ "\n");
		System.out.println("4: " + c.paymentDelayMaxPeakIndex(new int[]{3, 3, 0, 3, 3, 0, 0, 3})+ "\n");
		System.out.println("5: " + c.paymentDelayMaxPeakIndex(new int[]{17, 18, 17, 1, 0, 16, 0, 0})+ "\n");
	}
}

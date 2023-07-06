package com.scotiabankcolpatria.hiring;

/**
 * Clase que contiene los metodos para realizar el analisis de riesgo de pagos tardios,
 * pagos inusuales y desviación estandar.
 */
public class CreditRiskAssessment {

	/**
	 * Calcula la desviacion estandar de los pagos realizados en cierto periodo de tiempo
	 * @param paymentDelays Vector con cada uno de los pagos realizados en el mes correspondiente
	 * @return Retorna el vlaor de la desviación estandar claculada
	 */
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

    /**
     * Extrae el indice del calculo del pico más alto de un pago inusual
     * @param paymentDelays Vector con los pagos realizados en el mes correspondiente
     * @return Retorna el el indice donde se encuentra el pago inusual maximo
     */
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

    /**
     * Calcula la probabilidad de que un pago se realice de manera inoportuna
     * @param paymentDelays Matriz con los pagos de distintos productos de un cliente
     * @return Retorna un vector con las probabilidades de un pago tardio
     */
    public double[] latePaymentProbabilityByPeriod(int[][] paymentDelays) {
        int rows = paymentDelays.length;
        int columns = paymentDelays[0].length;
        double[] probability = new double[columns];

        for (int j = 0; j < columns; j++) {
        	int count = 0;
            for (int i = 0; i < rows; i++) {
            	if (paymentDelays[i][j] > 0) {
            		count ++;
				}
            }
            probability[j] = (double)count / (double)rows;
        }

        return probability;
    }
}

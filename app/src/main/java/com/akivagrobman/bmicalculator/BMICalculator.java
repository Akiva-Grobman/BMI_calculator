package com.akivagrobman.bmicalculator;

public class BMICalculator {

    public static double calcBMI(double height, double weight) {
        double bmi;
        height =  height / 100; // converted to meters
        bmi = weight / (height * height);
        int leftDot = (int) bmi;
        int rightDot = ((int) ((bmi * 100) % 100)); // two numbers after dot
        double rightRounded = Math.round((double) rightDot / 10); // for example 69=>70 (6.9=>7.0)
        return (double) leftDot + rightRounded / 10.0;
    }

    public static String getWeightStatus(double bmi) {
        String status = "";
        if (bmi < 15) {
            status = "Anorexic";
        } else if (isBetween(bmi, 15, 18.5)) {
            status = "Underweight";
        } else if (isBetween(bmi, 18.5, 24.9)) {
            status = "Normal";
        } else if (isBetween(bmi, 25, 29.9)) {
            status = "Overweight";
        } else if (isBetween(bmi, 30, 35)) {
            status = "Obese";
        } else if (bmi > 35) {
            status = "Extreme Obese";
        }

        return status;
    }

    public static double getIdealWeight(double height, int age, String bodyType) {
        double slimness = calculateSlimness(bodyType);
        double idealWeight = (height - 100 + ((double)(age / 10))) * 0.9 * slimness;
        int leftDot = (int) idealWeight;
        int rightDot = ((int) ((idealWeight * 100) % 100)); // two numbers after dot
        double rightRounded = Math.round((double) rightDot / 10); // for example 69=>70 (6.9=>7.0)
        return (double) leftDot + rightRounded / 10.0;
    }

    private static boolean isBetween(double x, double lower, double upper) {
        return lower <= x && x <= upper;
    }

    private static double calculateSlimness(String bodyType) {
        double slimness;
        switch (bodyType) {
            case "Small":
                slimness = 0.9;
                break;
            case "Medium":
                slimness = 1;
                break;
            case "Large":
                slimness = 1.1;
                break;
            default:
                slimness = 0;
                break;
        }
        return slimness;
    }

}

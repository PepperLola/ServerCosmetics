package palight.serverCosmetics.util;

import org.bukkit.util.Vector;

import javax.vecmath.Matrix3d;

public class MathUtil {
    public static Matrix3d getRotationMatrixX(double angle) {
        return new Matrix3d(
                1, 0, 0,
                0, Math.cos(angle), Math.sin(angle),
                0, -1 * Math.sin(angle), Math.cos(angle)
        );
    }

    public static Matrix3d getRotationMatrixY(double angle) {
        return new Matrix3d(
                Math.cos(angle), 0, Math.sin(angle),
                0, 1, 0,
                -1 * Math.sin(angle), 0, Math.cos(angle)
        );
    }

    public static Matrix3d getRotationMatrixZ(double angle) {
        return new Matrix3d(
                Math.cos(angle), Math.sin(angle), 0,
                -1 * Math.sin(angle), Math.cos(angle), 0,
                0, 0, 1
        );
    }

    public static double degToRad(double degrees) {
        return degrees * (Math.PI / 180);
    }

    public static double radToDeg(double radians) {
        return radians * (180 / Math.PI);
    }

    public static Vector vectorMatrixMultiply(Vector vector, Vector unitVector, double theta) {
        double x = vector.getX();
        double y = vector.getY();
        double z = vector.getZ();

        double u = unitVector.getX();
        double v = unitVector.getY();
        double w = unitVector.getZ();

        double v1 = u * x + v * y + w * z;

        double xPrime = u* v1 *(1d - Math.cos(theta))
                + x*Math.cos(theta)
                + (-w*y + v*z)*Math.sin(theta);
        double yPrime = v* v1 *(1d - Math.cos(theta))
                + y*Math.cos(theta)
                + (w*x - u*z)*Math.sin(theta);
        double zPrime = w* v1 *(1d - Math.cos(theta))
                + z*Math.cos(theta)
                + (-v*x + u*y)*Math.sin(theta);

        return new Vector(xPrime, yPrime, zPrime);
    }

    public static double csc(double radians) {
        return 1 / Math.sin(radians);
    }

    public static double sec(double radians) {
        return 1 / Math.cos(radians);
    }

    public static double cot(double radians) {
        return Math.cos(radians) / Math.sin(radians);
    }

    public static double sinDeg(double degrees) {
        return Math.sin(degToRad(degrees));
    }

    public static double cosDeg(double degrees) {
        return Math.cos(degToRad(degrees));
    }

    public static double tanDeg(double degrees) {
        return Math.cos(degToRad(degrees));
    }

    public static double cscDeg(double degrees) {
        return csc(degToRad(degrees));
    }

    public static double secDeg(double degrees) {
        return sec(degToRad(degrees));
    }

    public static double cotDeg(double degrees) {
        return cot(degToRad(degrees));
    }
}

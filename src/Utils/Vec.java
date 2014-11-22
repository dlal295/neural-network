package Utils;



public class Vec {

    private static final long serialVersionUID = 1L;
    private static final double EPSILON = 0.001;
    public double x, y;

    public Vec(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vec(Vec v){
        this.x = v.x;
        this.y = v.y;
    }

    /** Set the vector component-wise. */
    public Vec set(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public void setZero() {
        x = 0.0;
        y = 0.0;
    }

    /** Return the sum of this vector and another; does not alter either one. */
    public Vec add(Vec v) {
        return new Vec(x + v.x, y + v.y);
    }



    /** Return the difference of this vector and another; does not alter either one. */
    public Vec sub(Vec v) {
        return new Vec(x - v.x, y - v.y);
    }

    /** Return this vector multiplied by a scalar; does not alter this vector. */
    public Vec mul(double a) {
        return new Vec(x * a, y * a);
    }

    /** Return the negation of this vector; does not alter this vector. */
    public Vec negate() {
        return new Vec(-x, -y);
    }


    /** Adds values to this vector and returns result - alters this vector. */
    public Vec addLocal(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    /** Subtract another vector from this one and return result - alters this vector. */
    public Vec subLocal(Vec v) {
        x -= v.x;
        y -= v.y;
        return this;
    }

    /** Multiply this vector by a number and return result - alters this vector. */
    public Vec mulLocal(double a) {
        x *= a;
        y *= a;
        return this;
    }

    /** Get the skew vector such that dot(skew_vec, other) == cross(vec, other) */
    public Vec skew() {
        return new Vec(-y, x);
    }

    /** Get the skew vector such that dot(skew_vec, other) == cross(vec, other) */
    public void skew(Vec out) {
        out.x = -y;
        out.y = x;
    }


    /** Return the length of this vector. */
    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    /** Return the squared length of this vector. */
    public double lengthSquared() {
        return (x * x + y * y);
    }

    /** Normalize this vector and return the length before normalization. Alters this vector. */
    public double normalize() {
        double length = length();
        if (length < EPSILON) {
            return 0;
        }

        double invLength = 1.0 / length;
        x *= invLength;
        y *= invLength;
        return length;
    }


    /** Return a new vector that has positive components. */
    public final Vec abs() {
        return new Vec(Math.abs(x), Math.abs(y));
    }

    public final void absLocal() {
        x = Math.abs(x);
        y = Math.abs(y);
    }

    // @Override // annotation omitted for GWT-compatibility
    /** Return a copy of this vector. */
    public final Vec clone() {
        return new Vec(x, y);
    }

    @Override
    public final String toString() {
        return "(" + x + "," + y + ")";
    }

  /*
   * Static
   */

    public final static Vec abs(Vec a) {
        return new Vec(Math.abs(a.x), Math.abs(a.y));
    }

    public final static void absToOut(Vec a, Vec out) {
        out.x = Math.abs(a.x);
        out.y = Math.abs(a.y);
    }

    public final static double dot(final Vec a, final Vec b) {
        return a.x * b.x + a.y * b.y;
    }

    public final static double cross(final Vec a, final Vec b) {
        return a.x * b.y - a.y * b.x;
    }

    public final static Vec cross(Vec a, double s) {
        return new Vec(s * a.y, -s * a.x);
    }

    public final static void crossToOut(Vec a, double s, Vec out) {
        final double tempy = -s * a.x;
        out.x = s * a.y;
        out.y = tempy;
    }

    public final static void crossToOutUnsafe(Vec a, double s, Vec out) {
        assert (out != a);
        out.x = s * a.y;
        out.y = -s * a.x;
    }

    public final static Vec cross(double s, Vec a) {
        return new Vec(-s * a.y, s * a.x);
    }

    public final static void crossToOut(double s, Vec a, Vec out) {
        final double tempY = s * a.x;
        out.x = -s * a.y;
        out.y = tempY;
    }

    public final static void crossToOutUnsafe(double s, Vec a, Vec out) {
        assert (out != a);
        out.x = -s * a.y;
        out.y = s * a.x;
    }

    public final static void negateToOut(Vec a, Vec out) {
        out.x = -a.x;
        out.y = -a.y;
    }

    public final static Vec min(Vec a, Vec b) {
        return new Vec(a.x < b.x ? a.x : b.x, a.y < b.y ? a.y : b.y);
    }

    public final static Vec max(Vec a, Vec b) {
        return new Vec(a.x > b.x ? a.x : b.x, a.y > b.y ? a.y : b.y);
    }

    public final static void minToOut(Vec a, Vec b, Vec out) {
        out.x = a.x < b.x ? a.x : b.x;
        out.y = a.y < b.y ? a.y : b.y;
    }

    public final static void maxToOut(Vec a, Vec b, Vec out) {
        out.x = a.x > b.x ? a.x : b.x;
        out.y = a.y > b.y ? a.y : b.y;
    }


}

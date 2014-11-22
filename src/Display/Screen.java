package Display;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import Utils.Vec;

import javax.swing.*;

import static org.lwjgl.opengl.GL11.*;


public class Screen {




    public static int width, height;

    public static long lastFrameMS;
    public static int fps;
    public static long lastFPS;

    private static Color currentColor;

    public Screen(int width, int height) {
        Screen.width = width;
        Screen.height = height;

        setUpDisplay();
        setUpMatrices();

        currentColor = Color.white;

        lastFPS = getTime();
    }

    private static void setUpDisplay() {
        try {
            Display.setDisplayMode(new DisplayMode((int) width, (int) height));
            Display.setTitle("Game");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            cleanUp(true);
        }
    }

    private static void setUpMatrices() {

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        //GL11.glShadeModel(GL11.GL_SMOOTH);
        //GL11.glDisable(GL11.GL_DEPTH_TEST);
        //GL11.glDisable(GL11.GL_LIGHTING);

        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        //GL11.glClearDepth(1);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        //GL11.glViewport(0, 0, (int) width, (int) height);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        glOrtho(0, (int) width,  (int) height,0, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);


    }

    public static void render() {
        glClear(GL_COLOR_BUFFER_BIT);
        updateFPS();
        //font.drawText(getCenter().x, getCenter().y, "--------------------------", 45);
    }

    public static void setColor(Color c) {
        setColor(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
    }

    public static void setColor(Color c, int a) {
        setColor(c.getRed(), c.getGreen(), c.getBlue(), a);
    }

    public static void setColor(int r, int g, int b, int a) {
        glColor4f(r / 255f, g / 255f, b / 255f, a / 255f);
        currentColor = new Color(r / 255f, g / 255f, b / 255f, a / 255f);
    }

    public static void setColor(int r, int g, int b) {
        setColor(r, g, b, 255);
    }

    public static void setColor(int hex) {
        setColor((hex >> 16) & 0xFF, (hex >> 8) & 0xFF, hex & 0xFF);
    }

    public static void setBGColor(Color c) {
        glClearColor(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
    }

    
    public static void fillPoly(double[] x, double[] y) {
        glPushMatrix();
        glBegin(GL_TRIANGLE_FAN);
        for (int i = 0; i < x.length; i++) {
            glVertex2d(x[i], y[i]);
        }
        glEnd();
        glPopMatrix();
    }

    public static void fillPoly(Vec[] points) {
        glPushMatrix();
        glBegin(GL_TRIANGLE_FAN);
        for (int i = 0; i < points.length; i++) {
            glVertex2d(points[i].x, points[i].y);
        }
        glEnd();
        glPopMatrix();
    }

    public static void drawRect(double x, double y, double width, double height, double angle){
        glPushMatrix();
        glTranslated(x + width / 2, y + height / 2, 0);
        glRotated(Math.toDegrees(angle), 0, 0, 1);

        glBegin(GL_LINE_LOOP);
        glVertex2d(-width/2,-height/2);
        glVertex2d(width/2,-height/2);
        glVertex2d(width/2,height/2);
        glVertex2d(-width/2,height/2);
        glEnd();

        glPopMatrix();
    }

    public static void drawRect(double x, double y, double width, double height){
        fillRect(x,y,width,height,0);
    }

    public static void fillRect(double x, double y, double width, double height, double angle) {
        glPushMatrix();
        glTranslated(x + width / 2, y + height / 2, 0);
        glRotated(Math.toDegrees(angle), 0, 0, 1);
        glRectd(-width / 2, -height / 2, width / 2, height / 2);
        glPopMatrix();
    }

    public static void fillRect(double x, double y, double width, double height) {
        fillRect(x, y, width, height, 0);
    }

    public static void drawCircle(double cx, double cy, double r, int num_segments) {
        glDisable(GL_TEXTURE);
        double theta = 2 * 3.1415926 / num_segments;
        double c = Math.cos(theta);//precalculate the sine and cosine
        double s = Math.sin(theta);
        double t;

        double x = r;//we start at angle = 0
        double y = 0;

        glBegin(GL_LINE_LOOP);
        for (int i = 0; i < num_segments; i++) {
            glVertex2d(x + cx, y + cy);//output vertex

            //apply the rotation matrix
            t = x;
            x = c * x - s * y;
            y = s * t + c * y;
        }
        glEnd();
    }

    public static void drawCircle(double cx, double cy, double r) {
        drawCircle(cx, cy, r, (int) (Math.max(r / 2, 15)));
    }

    public static void fillCircle(double cx, double cy, double r, int num_segments) {
        double theta = 2 * 3.1415926 / num_segments;
        double c = Math.cos(theta);//precalculate the sine and cosine
        double s = Math.sin(theta);
        double t;

        double x = r;//we start at angle = 0
        double y = 0;

        glBegin(GL_TRIANGLE_FAN);
        for (int ii = 0; ii < num_segments; ii++) {
            glVertex2d(x + cx, y + cy);//output vertex

            //apply the rotation matrix
            t = x;
            x = c * x - s * y;
            y = s * t + c * y;
        }
        glEnd();
    }

    public static void fillCircle(double cx, double cy, double r) {
        fillCircle(cx, cy, r, (int) (Math.max(r / 2, 15)));
    }

    public static void drawLine(double x1, double y1, double x2, double y2) {
        glBegin(GL_LINES);
        glVertex2d(x1, y1);
        glVertex2d(x2, y2);
        glEnd();
    }

    public static void drawLine(Vec p1, Vec p2) {
        drawLine(p1.x, p1.y, p2.x, p2.y);
    }

    private static void updateFPS() {
        if (getTime() - lastFPS > 1000) {
            Display.setTitle("FPS: " + fps);
            fps = 0;
            lastFPS += 1000;
        }
        fps++;
    }

    public static int getDelta() {
        long time = getTime();
        int delta = (int) (time - lastFrameMS);
        lastFrameMS = time;
        return delta;
    }

    public static long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    private static void cleanUp(boolean asCrash) {
        Display.destroy();
        System.exit(asCrash ? 1 : 0);
    }

    public static Vec getCenter() {
        return new Vec(width / 2, height / 2);
    }


}

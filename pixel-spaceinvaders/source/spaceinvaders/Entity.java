package spaceinvaders;

import java.awt.Graphics;

public abstract class Entity
{
    protected double x1;
    protected double y1;
    protected double x2;
    protected double y2;
    protected boolean visible;
    protected boolean active;

    public Entity (double x1, double y1, double x2, double y2)
    {
        this (x1, y1, x2, y2, true, true);
    }

    public Entity (double x1, double y1, double x2, double y2, boolean active, boolean visible)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.visible = visible;
        this.active = active;
    }

    public abstract void update (long elapsed);

    public abstract void draw (Graphics g);

    public boolean hits (Entity that)
    {
        return this.x1 < that.x2
            && this.y1 < that.y2
            && this.x2 > that.x1
            && this.y2 > that.y1;
    }

    public double getX1 () { return x1; }
    public void setX1 (double value) { x1 = value; }

    public double getY1 () { return y1; }
    public void setY1 (double value) { y1 = value; }

    public double getX2 () { return x2; }
    public void setX2 (double value) { x2 = value; }

    public double getY2 () { return y2; }
    public void setY2 (double value) { y2 = value; }

    public double getWidth () { return x2 - x1; }
    public double getHeight () { return y2 - y1; }

    public boolean isVisible () { return visible; }
    public void setVisible (boolean value) { visible = value; }

    public void show () { visible = true; }
    public void hide () { visible = false; }

    public boolean isActive () { return active; }
    public void setActive (boolean value) { active = value; }

    public void enable () { active = true; }
    public void disable () { active = true; }
}

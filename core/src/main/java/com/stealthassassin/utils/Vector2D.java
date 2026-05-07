package com.stealthassassin.utils;

public class Vector2D {

    private float x;
    private float y;


    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D other) {
        this.x = other.x;
        this.y = other.y;
    }


    public Vector2D add(Vector2D other) {
        this.x += other.x;
        this.y += other.y;
        return this;
    }

    public Vector2D add(float x, float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vector2D subtract(Vector2D other) {
        this.x -= other.x;
        this.y -= other.y;
        return this;
    }

    public Vector2D scale(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public float lengthSquared() {
        return x * x + y * y;
    }

    
    public float distance(Vector2D other) {
        float dx = this.x - other.x;
        float dy = this.y - other.y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    public float distance(float otherX, float otherY) {
        float dx = this.x - otherX;
        float dy = this.y - otherY;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    public Vector2D normalize() {
        float len = length();
        if (len != 0) {
            this.x /= len;
            this.y /= len;
        }
        return this;
    }

    public float angle() {
        return (float) Math.atan2(y, x);
    }

    public float angleDegrees() {
        return (float) Math.toDegrees(Math.atan2(y, x));
    }

    public float dot(Vector2D other) {
        return this.x * other.x + this.y * other.y;
    }

    public Vector2D copy() {
        return new Vector2D(this.x, this.y);
    }


    public float getX() { return x; }
    public float getY() { return y; }

    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vector2D other) {
        this.x = other.x;
        this.y = other.y;
    }

    public Vector2D lerp(Vector2D target, float alpha) {
        this.x += alpha * (target.x - this.x);
        this.y += alpha * (target.y - this.y);
        return this;
    }
    
    public boolean isZero() {
        return x == 0 && y == 0;
    }

    @Override
    public String toString() {
        return "Vector2D(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vector2D)) return false;
        Vector2D other = (Vector2D) obj;
        return Float.compare(x, other.x) == 0 && Float.compare(y, other.y) == 0;
    }

    @Override
    public int hashCode() {
        return Float.hashCode(x) * 31 + Float.hashCode(y);
    }
}

package org.air.Classes;

public class CelestialBodies{
    protected float weight, radius, speed;
    protected int ID;
    protected String category, name;
    public CelestialBodies (int ID, String name, float weight, float radius, float speed, String category) {
        this.ID = ID;
        this.name = name;
        this.weight = weight;
        this.radius = radius;
        this.speed = speed;
        this.category = category;}
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public void setID(int ID) { this.ID = ID; }
    public int getID() { return ID; }
    public void setWeight(float weight) { this.weight = weight; }
    public float getWeight() { return weight; }
    public void setRadius(float radius) { this.radius = radius; }
    public float getRadius() { return radius; }
    public void setSpeed(float speed) { this.speed = speed; }
    public float getSpeed() { return speed; }
    public void setCategory(String category) { this.category = category; }
    public String getCategory() { return category; }
}

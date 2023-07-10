package com.epamTasks.task4.models;

public abstract class Entity {
    protected long id;

    protected Entity() {
        this.id = 0;
    }

    protected Entity(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String toString() {
        return Long.toString(this.id);
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Entity entity = (Entity) obj;
        return entity.getId() == this.getId();
    }

    public int hashCode() {
        String defaultStr = "abcd";
        String str = this.toString();
        int length = str.length();
        int sumOfChars = 0;
        for (int i = 0; i < 4; i++) {
            sumOfChars += i < length ? str.charAt(i) : defaultStr.charAt(i);
        }
        return length + sumOfChars;
    }
}
